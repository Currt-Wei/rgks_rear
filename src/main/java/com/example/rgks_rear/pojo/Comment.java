package com.example.rgks_rear.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 软工课设车队！
 * @since 2021-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;

    /**
     * 留言内容
     */
    private String content;

    /**
     * 对应回复官 本质user_id
     */
    private Long replyId;

    /**
     * 对应被回复者 本质user_id
     */
    private Long receiveId;

    private Long exerciseId;

    private Long commentType;

    private String createTime;

    public Comment(String content,Long replyId,Long receiveId,Long exerciseId,Long commentType,String createTime){
        this.content=content;
        this.replyId=replyId;
        this.receiveId=receiveId;
        this.exerciseId=exerciseId;
        this.commentType=commentType;
        this.createTime=createTime;
    }
    // 这里注意并不能删去，因为会影响mybatis查找后返回结果时的回填。
    // 具体报错 mybatis参数格式化异常:NumberFormatException: For input string:"xx"，将commentId填入到了content里面导致异常
    public Comment(Long commentId,String content,Long replyId,Long receiveId,Long exerciseId,Long commentType,String createTime){
        this.commentId=commentId;
        this.content=content;
        this.replyId=replyId;
        this.receiveId=receiveId;
        this.exerciseId=exerciseId;
        this.commentType=commentType;
        this.createTime=createTime;
    }
    public Comment(){

    }
}
