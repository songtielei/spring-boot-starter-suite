package com.startersuite.sms.service.impl;

import com.startersuite.sms.service.SmsService;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.Template;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class YunpianSmsService implements SmsService {

    private YunpianClient yunpianClient;

    public YunpianSmsService(YunpianClient yunpianClient) {
        this.yunpianClient = yunpianClient;
    }

    @Override
    public void send(String mobile, Long templateId, String[] data) {
        Map<String, String> param = new HashMap<>(2);
        param.put("mobile", mobile);
        String tplContent = getTplContent(templateId);
        String content = getContent(tplContent, data);
        param.put("text", content);
        yunpianClient.sms().single_send(param);
    }

    private String getTplContent(Long templateId) {
        Map<String, String> param = new HashMap<>(1);
        param.put("tpl_id", String.valueOf(templateId));
        Result<List<Template>> result = yunpianClient.tpl().get(param);
        if (!result.isSucc()) {
            throw new RuntimeException("查询模版失败 templateId: " + templateId);
        }
        Template template = result.getData().get(0);
        if (template == null) {
            throw new RuntimeException("没有此模版 templateId: " + templateId);
        }
        String tplContent = template.getTpl_content();
        return tplContent;
    }

    private String getContent(String tplContent, String[] data) {
        String content = tplContent;
        Matcher matcher = Pattern.compile("#[^#]+#").matcher(content);
        int i = 0;
        while (matcher.find()) {
            content = content.replaceAll(matcher.group(), data[i++]);
        }
        return content;
    }
}
