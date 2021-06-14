package com.example.rgks_rear.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgks_rear.dto.LoginDTO;
import com.example.rgks_rear.mapper.UserMapper;
import com.example.rgks_rear.pojo.User;
import com.example.rgks_rear.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 软工课设车队！
 * @since 2021-05-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public LoginDTO login(String email, String password){
        LoginDTO loginDTO=new LoginDTO();
        User user=lambdaQuery().eq(User::getEmail,email).one();
        if(user==null){
            loginDTO.setRespCode("400");
            loginDTO.setMsg("登陆失败,邮箱不存在");
            loginDTO.setUser(null);
            return loginDTO;
        }
        if(!user.getPassword().equals(password)){
            loginDTO.setRespCode("400");
            loginDTO.setMsg("登陆失败,密码不正确");
            loginDTO.setUser(null);
            return loginDTO;
        }
        String token=UUID.randomUUID().toString().replaceAll("-", "");
        stringRedisTemplate.opsForValue().set(token, String.valueOf(user.getUserId()), 3600, TimeUnit.SECONDS);//将用户的ID信息存入redis缓存，并设置一小时的过期时间
        String id = stringRedisTemplate.opsForValue().get(token);
        System.out.println(id);
        loginDTO.setToken(token);
        loginDTO.setRespCode("200");
        loginDTO.setMsg("登陆成功");
        loginDTO.setUser(user);
        return loginDTO;
    }

    public User getUser(String token){
        String id = stringRedisTemplate.opsForValue().get(token);
        System.out.println(id);
        User user=new User();
        if(id!=null){
            user=lambdaQuery().eq(User::getUserId,id).one();
            return user;
        }else{
            System.out.println("token过期");
            return user;
        }
    }
}
