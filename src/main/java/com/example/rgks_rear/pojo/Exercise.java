package com.example.rgks_rear.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 面试题表
 * </p>
 *
 * @author 软工课设车队！
 * @since 2021-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Exercise implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "exercise_id", type = IdType.AUTO)
    private Long exerciseId;

    /**
     * 面试题名字
     */
    private String title;

    /**
     * 对应面试官 本质user_id
     */
    private Long teacherId;

    /**
     * 面试题url链接
     */
    private String url;

    /**
     * 面试题内容
     */
    private String content;

    /**
     * 对应学生 本质user_id
     */
    private Long studentId;

    /**
     * 是否已经被完成(之后不可修改)
     */
    private Long isFinished;

    /**
     * 是否被删除
     */
    private Long isDeleted;

    /**
     * 代码语言类型 枚举(待与前端沟通确定)
     */
    private Long typeOfCode;

    /**
     * 代码内容
     */
    private String code;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    public Exercise(Long exerciseId,String title,Long teacherId,
             String url,String content,Long studentId,
             Long isFinished,Long isDeleted,Long typeOfCode,String code,Date createTime,Date modifiedTime){
        this.exerciseId=exerciseId;
        this.title=title;
        this.teacherId=teacherId;
        this.url=url;
        this.content=content;
        this.studentId=studentId;
        this.isFinished=isFinished;
        this.isDeleted=isDeleted;
        this.typeOfCode=typeOfCode;
        this.code=code;
        this.createTime=createTime;
        this.modifiedTime=modifiedTime;
    }
}
