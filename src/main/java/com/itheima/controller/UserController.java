package com.itheima.controller;

import com.itheima.dto.request.PwdUpdateDTO;
import com.itheima.dto.request.RegisterOrLoginDTO;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

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
        return userService.update(user);
    }

    @PatchMapping("updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        return userService.updateAvatar(avatarUrl);
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody @Validated PwdUpdateDTO dto) {
        return userService.updatePwd(dto);
    }

}
