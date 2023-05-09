package com.starter.core.exception;

import com.starter.core.constant.ErrorCode;
import lombok.Getter;

public class BusinessException extends RuntimeException {

    @Getter
    private ErrorCode errorCode;

    private BusinessException() {}

    private BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    private BusinessException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public static BusinessException build(ErrorCode errorCode) {
        return new BusinessException(errorCode, errorCode.getDescription());
    }

    public static BusinessException build(ErrorCode errorCode, String message) {
        return new BusinessException(errorCode, message);
    }

    public static BusinessException build(ErrorCode errorCode, Throwable cause) {
        return new BusinessException(errorCode, errorCode.getDescription(), cause);
    }

    public static BusinessException build(ErrorCode errorCode, String message, Throwable cause) {
        return new BusinessException(errorCode, message, cause);
    }
}
