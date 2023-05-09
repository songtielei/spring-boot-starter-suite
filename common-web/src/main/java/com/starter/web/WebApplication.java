package com.starter.web;

import com.starter.web.config.WebConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// 扫描 WebConfig 类 所在的包 下面的 bean
@ComponentScan(basePackageClasses = {WebConfig.class})
public class WebApplication {

}
