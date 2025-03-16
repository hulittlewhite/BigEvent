package com.itheima.service.impl;

import com.itheima.dto.request.PwdUpdateDTO;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    /**
     * 根据用户名查询用户
     *
     * @param username 需要查询的用户名
     * @return 查询到的用户对象
     */
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }


    /**
     * 注册
     *
     * @param username 需要注册的用户名
     * @param password 需要注册的密码
     * @return 注册结果
     */
    @Override
    public Result register(String username, String password) {
        User user = findByUsername(username);
        if (user != null) {
            return Result.error("用户名已被占用");
        }
        userMapper.add(username, Md5Util.getMD5String(password));
        return Result.success();
    }


    /**
     * 更新用户昵称和邮箱
     *
     * @param user 用户对象
     */
    @Override
    public Result update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
        return Result.success();
    }


    /**
     * 更新用户头像
     *
     * @param avatarUrl 用户头像地址
     */
    @Override
    public Result updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl, id);
        return Result.success();
    }


    /**
     * 更新用户密码
     *
     * @param dto 密码传输对象
     * @return 更新密码结果
     */
    @Override
    public Result updatePwd(PwdUpdateDTO dto) {
        // 1.校验参数
        if (!StringUtils.hasLength(dto.getOldPwd())
                || !StringUtils.hasLength(dto.getNewPwd())
                || !StringUtils.hasLength(dto.getRePwd())) {
            return Result.error("缺少必要的参数");
        }

        // 2.校验旧密码
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = findByUsername(username);
        if (loginUser == null) {
            return Result.error("用户不存在");
        }
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(dto.getOldPwd()))) {
            return Result.error("旧密码填写错误");
        }
        if (!dto.getNewPwd().equals(dto.getRePwd())) {
            return Result.error("两次密码不一致");
        }
        if (loginUser.getPassword().equals(Md5Util.getMD5String(dto.getNewPwd()))) {
            return Result.error("新密码不能与旧密码相同");
        }

        // 3.更新密码
        userMapper.updatePwd(Md5Util.getMD5String(dto.getNewPwd()), loginUser.getId());
        return Result.success();
    }


    /**
     * 登录认证
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    @Override
    public Result authenticateUser(String username, String password) {
        User user = findByUsername(username);
        if (user == null) {
            return Result.error("用户不存在");
        }
        if (Md5Util.getMD5String(password).equals(user.getPassword())) {
            // 登录成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误！");
    }


    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @Override
    public Result<User> getUserInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = findByUsername(username);
        return Result.success(user);
    }

}
