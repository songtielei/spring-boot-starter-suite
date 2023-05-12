package com.startersuite.web.config;

import com.startersuite.core.constant.ErrorCode;
import com.startersuite.core.entity.ErrorEntity;
import com.startersuite.core.exception.BusinessException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobeExceptionHandler {

    @Value("${include-stack-trace:true}")
    private Boolean includeStackTrace;

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorEntity> runtimeExceptionHandler(RuntimeException ex) {

        log.error("RuntimeException : ", ex);
        ErrorEntity errorEntity = ErrorEntity.error(ErrorCode.ERROR_INTERNAL);
        errorEntity = includeStackTrace ? errorEntity.trace(getStackTrace(ex)) : errorEntity;
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorEntity);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorEntity> exceptionHandler(Exception ex) {

        log.error("Exception : ", ex);
        if (ex instanceof MethodArgumentNotValidException) {
            Map<String, String> fieldError = new HashMap<>();
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) ex;
            BindingResult result = methodArgumentNotValidException.getBindingResult();
            if (result.hasErrors()) {
                List<ObjectError> errors = result.getAllErrors();
                errors.forEach(p -> {
                    FieldError f = (FieldError) p;
                    fieldError.put(f.getField(), f.getDefaultMessage());
                });
            }
            ErrorEntity errorEntity = ErrorEntity.error(ErrorCode.FAIL_BADREQUEST)
                .message("字段验证错误").fieldError(fieldError);
            errorEntity = includeStackTrace ? errorEntity.trace(getStackTrace(ex)) : errorEntity;
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorEntity);
        } else {
            ErrorEntity errorEntity = ErrorEntity.error(ErrorCode.ERROR_INTERNAL);
            errorEntity = includeStackTrace ? errorEntity.trace(getStackTrace(ex)) : errorEntity;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorEntity);
        }

    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity<ErrorEntity> businessExceptionHandler(BusinessException ex) {

        log.warn("BusinessException : ", ex);
        ErrorEntity errorEntity = ErrorEntity.error(ex.getErrorCode()).message(ex.getMessage());
        errorEntity = includeStackTrace ? errorEntity.trace(getStackTrace(ex)) : errorEntity;
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorEntity);
    }

    private String getStackTrace(Throwable error) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        error.printStackTrace(printWriter);
        printWriter.flush();
        stringWriter.flush();
        return stringWriter.toString();
    }
}
