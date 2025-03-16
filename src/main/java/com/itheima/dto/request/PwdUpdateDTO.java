package com.itheima.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Data
public class PwdUpdateDTO {
    @NotEmpty(message = "原密码不能为空")
    @JsonProperty("old_pwd")
    private String oldPwd;
    
    @Pattern(regexp = "^\\S{5,16}$", message = "新密码格式错误")
    @JsonProperty("new_pwd")
    private String newPwd;
    
    @Pattern(regexp = "^\\S{5,16}$", message = "确认密码格式错误")
    @JsonProperty("re_pwd")
    private String rePwd;
}
