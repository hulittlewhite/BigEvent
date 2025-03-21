package com.itheima.service;

import com.itheima.dto.request.PwdUpdateDTO;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;

public interface UserService {

    // 根据用户名查询用户
    User findByUsername(String username);

    // 注册
    Result register(String username, String password);

    // 更新用户昵称和邮箱
    Result update(User user);

    // 更新头像
    Result updateAvatar(String avatarUrl);

    // 更新密码
    Result updatePwd(PwdUpdateDTO dto);

    // 登录认证
    Result authenticateUser(String username, String password);

    // 获取用户信息
    Result<User> getUserInfo();
}
