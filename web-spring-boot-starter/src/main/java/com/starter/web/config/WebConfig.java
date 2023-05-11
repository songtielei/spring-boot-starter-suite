package com.starter.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.web.filter.ExceptionHandlerFilter;
import com.starter.web.util.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {

    @Value("${include-stack-trace:true}")
    private Boolean includeStackTrace;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public SpringContextUtils springContextUtils() {
        return new SpringContextUtils();
    }

    // springSecurityFilterChain中配置了 CorsFilter [http.cors()] 需要手工指定Bean
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // allowcredential 为true 保持跨域 Ajax 时的 Cookie
        // 此时Access-Control-Allow-Origin 的值不能为 '*'
        config.setAllowCredentials(false);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Bean
    public FilterRegistrationBean customCorsFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(corsFilter());

        //IMPORTANT #2: I didn't stress enough the importance of this line in my original answer,
        //but it's here where we tell Spring to load this filter at the right point in the chain
        //(with an order of precedence higher than oauth2's filters)
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    // CustomErrorController 可以处理 filter 与 404 异常
    // 在这里还配置 filter 异常是因为它可以打印更明晰的错误信息
    @Bean
    public FilterRegistrationBean exceptionHandlerFilter() {
        ExceptionHandlerFilter exceptionHandlerFilter = new ExceptionHandlerFilter();
        exceptionHandlerFilter.setIncludeStackTrace(includeStackTrace);
        exceptionHandlerFilter.setObjectMapper(objectMapper);
        FilterRegistrationBean bean = new FilterRegistrationBean(exceptionHandlerFilter);

        //IMPORTANT #2: I didn't stress enough the importance of this line in my original answer,
        //but it's here where we tell Spring to load this filter at the right point in the chain
        //(with an order of precedence higher than oauth2's filters)
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        return bean;
    }

}
