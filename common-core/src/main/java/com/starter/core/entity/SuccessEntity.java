package com.starter.core.entity;

import com.starter.core.constant.ErrorCode;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SuccessEntity<T> {

    public final Integer code;
    public final String message;
    public final T data;

    public static <T> SuccessEntity<T> ok() {
        return ok("成功", null);
    }

    public static <T> SuccessEntity<T> ok(T data) {
        return ok("成功", data);

    }

    public static <T> SuccessEntity<T> ok(String message, T data) {
        return SuccessEntity.<T>builder()
                .code(ErrorCode.OK.getCode()).message(message).data(data)
                .build();
    }
}
