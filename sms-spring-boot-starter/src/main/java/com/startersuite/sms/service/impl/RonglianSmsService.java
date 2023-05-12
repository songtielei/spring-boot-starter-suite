package com.startersuite.sms.service.impl;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.startersuite.sms.service.SmsService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RonglianSmsService implements SmsService {

    private CCPRestSmsSDK ccpRestSmsSDK;

    public RonglianSmsService(CCPRestSmsSDK ccpRestSmsSDK) {
        this.ccpRestSmsSDK = ccpRestSmsSDK;
    }

    @Override
    public void send(String mobile, Long templateId, String[] data) {
        ccpRestSmsSDK.sendTemplateSMS(mobile, String.valueOf(templateId), data);
    }
}
