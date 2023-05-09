package com.starter.core.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class LoginEntity implements Serializable {

    private String username;
    private String password;
    private String captcha;
    private String token;

}
