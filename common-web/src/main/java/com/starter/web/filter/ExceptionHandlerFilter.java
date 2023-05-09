package com.starter.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.core.constant.ErrorCode;
import com.starter.core.entity.ErrorEntity;
import com.starter.core.exception.BusinessException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Setter
    private boolean includeStackTrace;

    @Setter
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws IOException {
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            log.error("error filter： ", e);
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

            ErrorCode errorCode;
            if (e instanceof BusinessException) {
                BusinessException b = (BusinessException) e;
                errorCode = b.getErrorCode();
            } else {
                errorCode = ErrorCode.ERROR_INTERNAL;
            }
            ErrorEntity errorEntity = ErrorEntity.error(errorCode);
            errorEntity = this.includeStackTrace ? errorEntity.trace(this.getStackTrace(e)) : errorEntity;
            PrintWriter printWriter = httpServletResponse.getWriter();
            printWriter.append(objectMapper.writeValueAsString(errorEntity));
        }

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
