package com.itheima.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterOrLoginDTO {
    @Pattern(regexp = "^\\S{5,16}$", message = "用户名需为5~16个非空字符")
    private String username;

    @Pattern(regexp = "^\\S{5,16}$", message = "密码需为5~16个非空字符")
    private String password;
}
