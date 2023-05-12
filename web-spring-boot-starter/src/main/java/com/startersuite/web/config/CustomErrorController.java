package com.startersuite.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.startersuite.core.constant.ErrorCode;
import com.startersuite.core.entity.ErrorEntity;
import com.startersuite.core.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomErrorController extends BasicErrorController {

    @Autowired
    private ObjectMapper objectMapper;

    public CustomErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    /**
     * 覆盖默认的JSON响应
     */
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {

        ErrorEntity errorEntity = ErrorEntity.error(ErrorCode.ERROR_INTERNAL);
        Map<String, Object> result = new HashMap<>();
        Field[] fields = errorEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object object = ReflectionUtils.getFieldValue(errorEntity, field.getName());
            result.put(field.getName(), object);
        }
        return ResponseEntity.status(getStatus(request)).body(result);
    }

}