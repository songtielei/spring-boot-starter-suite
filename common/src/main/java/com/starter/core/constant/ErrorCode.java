package com.starter.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    OK(200, "OK"),
    FAIL_BADREQUEST(400, "请求参数错误"),
    FAIL_UNAUTHORIZED(401, "用户未认证"),
    FAIL_FORBIDDEN(403, "禁止访问"),
    ERROR_INTERNAL(500, "服务器错误"),
    ERROR_BUSINESS(550, "业务错误");

    private Integer code;
    private String description;
}
