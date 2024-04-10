package com.itheima.pojo;

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
