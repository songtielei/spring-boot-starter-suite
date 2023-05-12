package com.startersuite.sms.config;

import com.startersuite.sms.service.SmsService;
import com.startersuite.sms.service.impl.YunpianSmsService;
import com.yunpian.sdk.YunpianClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "yunpian", name = "apiKey")
public class YunpianConfig {

    @Value("${yunpian.apikey}")
    private String apikey;

    private YunpianClient yunpianClient() {
        YunpianClient yunpianClient = new YunpianClient(apikey).init();
        return yunpianClient;
    }

    @Bean
    public SmsService smsService() {
        YunpianSmsService smsService = new YunpianSmsService(yunpianClient());
        return smsService;
    }
}
