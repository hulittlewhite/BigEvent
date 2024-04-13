package com.itheima.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

// lombok 在编译阶段，为实体类自动生成 setter、getter、toString 方法
// pom 文件中引入依赖，在实体类上添加注解
@Data
public class User {

    // 主键ID
    private Integer id;

    // 用户名
    private String username;

    // 密码
    // 让 Spring MVC 把当前对象转换成 json 字符串的时候，忽略 password，最终的 json 字符串中就没有 password 这个属性了
    @JsonIgnore
    private String password;

    // 昵称
    private String nickname;

    // 邮箱
    private String email;

    // 用户头像地址
    private String userPic;

    // 创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;

}
