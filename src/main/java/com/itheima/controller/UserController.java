package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$")String password) {
        // 查询用户
        User user = userService.findByUserName(username);
        if (user == null) {
            // 未被占用，则可以注册
            userService.register(username, password);
            return Result.success();
        } else {
            // 已被占用
            return Result.error("用户名已被占用！");
        }

    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username,
                        @Pattern(regexp = "^\\S{5,16}$")String password) {
        // 根据用户名查询用户
        User loginUser = userService.findByUserName(username);
        // 判断该用户是否存在
        if (loginUser == null) {
            return Result.error("用户名不存在！");
        }
        // 判断密码是否正确
        // loginUser 对象中的 password 是密文
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            // 登录成功
            return Result.success("JWT Token 令牌...");
        }
        return Result.error("密码错误！");
    }

}
