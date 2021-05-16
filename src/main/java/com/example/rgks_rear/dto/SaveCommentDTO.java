package com.example.rgks_rear.dto;

import com.example.rgks_rear.pojo.Exercise;
import lombok.Data;

import java.util.List;

@Data
public class SaveCommentDTO {
    // 返回路径
    private String respCode;
    // 返回信息
    private String msg;
    // 面试题主键
    private Long commentId;
}
