package com.example.rgks_rear;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.rgks_rear.mapper")
public class RgksRearApplication {

    public static void main(String[] args) {
        SpringApplication.run(RgksRearApplication.class, args);
    }

}
