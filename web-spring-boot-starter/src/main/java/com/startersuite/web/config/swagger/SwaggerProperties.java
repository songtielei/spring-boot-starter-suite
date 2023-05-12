package com.startersuite.web.config.swagger;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties("springfox.documentation")
public class SwaggerProperties extends DocketInfo {

    /**
     * host信息
     **/
    private String host = "";

    /**
     * 分组文档
     **/
    private Map<String, DocketInfo> docket = new LinkedHashMap<>();

    /**
     * 是否使用默认预定义的响应消息 ，默认 true
     **/
    private Boolean applyDefaultResponseMessages = true;

    /**
     * 全局响应消息
     **/
    private GlobalResponseMessage globalResponseMessage;

    @Data
    @NoArgsConstructor
    public static class GlobalOperationParameter {
        /**
         * 参数名
         **/
        private String name;

        /**
         * 描述信息
         **/
        private String description;

        /**
         * 指定参数类型
         **/
        @Value("modelRef")
        private String type;

        /**
         * 指定参数类型
         **/
        private String format = "";

        /**
         * 参数放在哪个地方:header,query,path,formData,cookie,form
         **/
        private String parameterType;

        /**
         * 参数是否必须传
         **/
        private Boolean required = false;

    }

    @Data
    @NoArgsConstructor
    public static class Contact {

        /**
         * 联系人
         **/
        private String name = "";
        /**
         * 联系人url
         **/
        private String url = "";
        /**
         * 联系人email
         **/
        private String email = "";

    }

    @Data
    @NoArgsConstructor
    public static class GlobalResponseMessage {

        /**
         * POST 响应消息体
         **/
        List<GlobalResponseMessageBody> post = new ArrayList<>();

        /**
         * GET 响应消息体
         **/
        List<GlobalResponseMessageBody> get = new ArrayList<>();

        /**
         * PUT 响应消息体
         **/
        List<GlobalResponseMessageBody> put = new ArrayList<>();

        /**
         * PATCH 响应消息体
         **/
        List<GlobalResponseMessageBody> patch = new ArrayList<>();

        /**
         * DELETE 响应消息体
         **/
        List<GlobalResponseMessageBody> delete = new ArrayList<>();

        /**
         * HEAD 响应消息体
         **/
        List<GlobalResponseMessageBody> head = new ArrayList<>();

        /**
         * OPTIONS 响应消息体
         **/
        List<GlobalResponseMessageBody> options = new ArrayList<>();

        /**
         * TRACE 响应消息体
         **/
        List<GlobalResponseMessageBody> trace = new ArrayList<>();

    }

    @Data
    @NoArgsConstructor
    public static class GlobalResponseMessageBody {

        /**
         * 响应码
         **/
        private String code;

        /**
         * 响应消息
         **/
        private String description;

        private String representation;

        /**
         * 响应体
         **/
        private String modelRef;

    }

}
