package com.example.rgks_rear.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgks_rear.dto.LoginDTO;
import com.example.rgks_rear.pojo.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 软工课设车队！
 * @since 2021-05-15
 */
public interface IUserService extends IService<User> {
    public LoginDTO login(String email, String password);

}
