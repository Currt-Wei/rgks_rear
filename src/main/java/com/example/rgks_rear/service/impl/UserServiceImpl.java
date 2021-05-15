package com.example.rgks_rear.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgks_rear.dto.LoginDTO;
import com.example.rgks_rear.mapper.UserMapper;
import com.example.rgks_rear.pojo.User;
import com.example.rgks_rear.service.IUserService;
import org.springframework.stereotype.Service;

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
        loginDTO.setRespCode("200");
        loginDTO.setMsg("登陆成功");
        loginDTO.setUser(user);
        return loginDTO;
    }
}
