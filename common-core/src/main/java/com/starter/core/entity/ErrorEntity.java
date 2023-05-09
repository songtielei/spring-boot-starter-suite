package com.starter.core.entity;

import com.starter.core.constant.ErrorCode;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorEntity implements Serializable {

    @ApiModelProperty("timestamp")
    private LocalDateTime timestamp;
    @ApiModelProperty("code")
    private Integer code;
    @ApiModelProperty("message")
    private String message;
    @ApiModelProperty("fieldError")
    private Map<String, String> fieldError;
    @ApiModelProperty("trace")
    private String trace;

    private ErrorEntity(LocalDateTime timestamp, Integer code, String message) {
        this.timestamp = timestamp;
        this.code = code;
        this.message = message;
    }

    public static ErrorEntity error(ErrorCode errorCode) {
        ErrorEntity errorEntity = new ErrorEntity(LocalDateTime.now(), errorCode.getCode(),
                errorCode.getDescription());
        return errorEntity;
    }

    public ErrorEntity message(String message) {
        this.message = message;
        return this;
    }

    public ErrorEntity fieldError(Map<String, String> fieldError) {
        this.fieldError = fieldError;
        return this;
    }

    public ErrorEntity trace(String trace) {
        this.trace = trace;
        return this;
    }

}
