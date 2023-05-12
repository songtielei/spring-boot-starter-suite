package com.startersuite.web.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("springfox.documentation.authorization")
public class SwaggerAuthorizationProperties {

    /**
     * 鉴权策略ID，对应 SecurityReferences ID
     */
    private String name = "Authorization";

    /**
     * 鉴权策略，可选 ApiKey | BasicAuth | None，默认ApiKey
     */
    private String type = "ApiKey";

    /**
     * 鉴权传递的Header参数
     */
    private String keyName = "TOKEN";

    /**
     * 需要开启鉴权URL的正则
     */
    private String authRegex = "^.*$";

}


