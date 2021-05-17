package com.example.rgks_rear.controller;


import com.example.rgks_rear.common.Constant;
import com.example.rgks_rear.dto.ExerciseQueryCommentDTO;
import com.example.rgks_rear.dto.SaveCommentDTO;
import com.example.rgks_rear.pojo.Comment;
import com.example.rgks_rear.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 软工课设车队！
 * @since 2021-05-15
 */

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    ICommentService commentService;

    @PostMapping("/save")
    public SaveCommentDTO SaveComment(Long commentType,Long replyId,Long receiveId,String content,Long exerciseId ){
        Comment comment=new Comment(content,replyId,receiveId,exerciseId,commentType);
        SaveCommentDTO saveCommentDTO=new SaveCommentDTO();
        boolean b = commentService.save(comment);
        if(b){
            saveCommentDTO.setCommentId(comment.getCommentId());
            saveCommentDTO.setRespCode(Constant.SaveSuccess);
            saveCommentDTO.setMsg(Constant.MsgSaveSuccess);
        }else{
            saveCommentDTO.setCommentId(0L);
            saveCommentDTO.setRespCode(Constant.CommentSaveFail);
            saveCommentDTO.setMsg(Constant.MsgCommentSaveFail);
        }
        return saveCommentDTO;
    }

    @GetMapping("/exercise_query")
    public ExerciseQueryCommentDTO ExerciseQueryComment(Long exerciseId){
        ExerciseQueryCommentDTO exerciseQueryCommentDTO=new ExerciseQueryCommentDTO();
        List<Comment> comments = commentService.lambdaQuery().eq(Comment::getExerciseId,exerciseId).orderByAsc(Comment::getCommentId).list();
        exerciseQueryCommentDTO.setComments(comments);
        exerciseQueryCommentDTO.setRespCode(Constant.QuerySuccess);
        exerciseQueryCommentDTO.setMsg(Constant.MsgQuerySuccess);
        return exerciseQueryCommentDTO;
    }
}