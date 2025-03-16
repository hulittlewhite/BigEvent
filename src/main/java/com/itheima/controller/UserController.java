package com.itheima.controller;

import com.itheima.dto.request.PwdUpdateDTO;
import com.itheima.dto.request.RegisterOrLoginDTO;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Validated RegisterOrLoginDTO dto) {
        return userService.register(dto.getUsername(), dto.getPassword());
    }

    @PostMapping("/login")
    public Result login(@Validated RegisterOrLoginDTO dto) {
        return userService.authenticateUser(dto.getUsername(), dto.getPassword());
    }

    @GetMapping("/userInfo")
    public Result<User> getUserInfo() {
        return userService.getUserInfo();
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody @Validated PwdUpdateDTO dto) {
        return userService.updatePwd(dto);
    }

}
