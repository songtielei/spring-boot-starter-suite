package com.startersuite.example.web.controller;

import com.startersuite.core.constant.ErrorCode;
import com.startersuite.core.exception.BusinessException;
import com.startersuite.example.web.dto.WebDTO;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "用户管理控制器")
@RestController
@RequestMapping(value = "/web")
public class WebController {

    @PostMapping("/exception/{type}")
    public ResponseEntity<String> exception(@PathVariable("type") String type) throws Exception {
        if (type.equals("businessException")) {
            throw BusinessException.build(ErrorCode.ERROR_BUSINESS, "exception");
        } else if (type.equals("exception")) {
            throw new Exception("exception");
        } else if (type.equals("runtimeException")) {
            throw new RuntimeException("runtimeexception");
        }

        return ResponseEntity.ok(type);
    }

    @PostMapping("/time")
    public ResponseEntity<WebDTO> time(@RequestBody WebDTO webDTO) {
        WebDTO dto = WebDTO.builder().id(webDTO.getId() + 2).localDate(LocalDate.now())
                .zonedDateTime(ZonedDateTime.now())
                .localDateTime(LocalDateTime.now().minusDays(3)).instant(Instant.now()).build();
        return ResponseEntity.ok(dto);
    }

}
