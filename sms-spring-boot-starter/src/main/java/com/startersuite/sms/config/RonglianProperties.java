package com.startersuite.sms.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "ronglian")
public class RonglianProperties {

    private String serverIp;
    private String serverPort;
    private String accountSid;
    private String accountToken;
    private String appId;
}
