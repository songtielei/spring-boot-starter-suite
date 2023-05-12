package com.startersuite.sms.config;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.startersuite.sms.service.SmsService;
import com.startersuite.sms.service.impl.RonglianSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "ronglian", name = "serverIp")
@EnableConfigurationProperties(RonglianProperties.class)
public class RonglianConfig {
    @Autowired
    private RonglianProperties properties;

    private CCPRestSmsSDK ccpRestSmsSDK() {
        //初始化SDK
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

        //******************************注释*********************************************
        //*初始化服务器地址和端口                                                       *
        //*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
        //*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
        //*******************************************************************************
        restAPI.init(properties.getServerIp(), properties.getServerPort());

        //******************************注释*********************************************
        //*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
        //*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
        //*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
        //*******************************************************************************
        restAPI.setAccount(properties.getAccountSid(), properties.getAccountToken());


        //******************************注释*********************************************
        //*初始化应用ID                                                                 *
        //*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
        //*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
        //*******************************************************************************
        restAPI.setAppId(properties.getAppId());

        return restAPI;
    }

    @Bean
    public SmsService smsService() {
        RonglianSmsService smsService = new RonglianSmsService(ccpRestSmsSDK());
        return smsService;
    }
}
