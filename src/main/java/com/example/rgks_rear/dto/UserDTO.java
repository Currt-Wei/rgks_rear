package com.example.rgks_rear.dto;

import com.example.rgks_rear.pojo.Exercise;
import com.example.rgks_rear.pojo.User;
import lombok.Data;

@Data
public class UserDTO {
    //返回路径
    private String respCode;
    //返回信息
    private String msg;
    // 面试题ID
    private User user;
}