package com.example.rgks_rear.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;
}
