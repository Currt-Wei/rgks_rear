package com.example.rgks_rear.dto;

import com.example.rgks_rear.pojo.Comment;
import lombok.Data;

import java.util.List;

@Data
public class ExerciseQueryCommentDTO {
    // 返回状态码
    private String respCode;
    // 返回信息
    private String msg;

    private List<Comment> comments;
}
