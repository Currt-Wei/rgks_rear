package com.example.rgks_rear.dto;

import com.example.rgks_rear.pojo.User;
import lombok.Data;

@Data
public class LoginDTO {
    //返回路径
    private String respCode;
    //返回信息
    private String msg;
    //user
    private User user;
    //token
    private String token;
}
