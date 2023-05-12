package com.startersuite.web.config.swagger;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DocketInfo {
    /**
     * 标题
     **/
    private String title = "";

    /**
     * 描述
     **/
    private String description = "";

    /**
     * 版本
     **/
    private String version = "";

    /**
     * 许可证
     **/
    private String license = "";
    /**
     * 许可证URL
     **/
    private String licenseUrl = "";

    /**
     * 服务条款URL
     **/
    private String termsOfServiceUrl = "";

    private SwaggerProperties.Contact contact = new SwaggerProperties.Contact();

    /**
     * swagger会解析的包路径
     **/
    private String basePackage = "";

    /**
     * swagger会解析的url规则
     **/
    private List<String> basePath = new ArrayList<>();

    /**
     * 在basePath基础上需要排除的url规则
     **/
    private List<String> excludePath = new ArrayList<>();

    /**
     * 全局参数配置
     **/
    private List<SwaggerProperties.GlobalOperationParameter> globalOperationParameters;

    /**
     * 忽略的参数类型
     **/
    private List<Class<?>> ignoredParameterTypes = new ArrayList<>();
}
