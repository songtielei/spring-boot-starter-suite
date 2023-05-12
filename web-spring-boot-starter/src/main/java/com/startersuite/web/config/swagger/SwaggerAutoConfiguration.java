package com.startersuite.web.config.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 默认访问地址 swagger-ui/index.html
 */
@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(value = "springfox.documentation.enabled", havingValue = "true", matchIfMissing = true)
@Import({SwaggerUiConfiguration.class, SwaggerAuthorizationConfiguration.class, DocketConfiguration.class})
public class SwaggerAutoConfiguration {

    @Bean
    public DocketBeanFactoryPostProcessor docketBeanFactoryPostProcessor() {
        return new DocketBeanFactoryPostProcessor();
    }

}
