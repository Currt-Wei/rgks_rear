package com.example.rgks_rear.dto;

import com.example.rgks_rear.pojo.Exercise;
import lombok.Data;

@Data
public class QueryExerciseDTO {
    //返回路径
    private String respCode;
    //返回信息
    private String msg;
    // 面试题详细信息
    Exercise exercise;
}
