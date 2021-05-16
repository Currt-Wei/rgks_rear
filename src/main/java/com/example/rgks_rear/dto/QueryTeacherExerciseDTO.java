package com.example.rgks_rear.dto;

import com.example.rgks_rear.pojo.Exercise;
import lombok.Data;

import java.util.List;

@Data
public class QueryTeacherExerciseDTO {
    // 返回路径
    private String respCode;
    // 返回信息
    private String msg;
    // 老师的面试题
    private List<Exercise> items;
}
