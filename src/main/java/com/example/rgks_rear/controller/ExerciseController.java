package com.example.rgks_rear.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.example.rgks_rear.common.Constant;
import com.example.rgks_rear.dto.*;
import com.example.rgks_rear.mapper.ExerciseMapper;
import com.example.rgks_rear.pojo.Exercise;
import com.example.rgks_rear.pojo.User;
import com.example.rgks_rear.service.IExerciseService;
import com.example.rgks_rear.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 面试题表 前端控制器
 * </p>
 *
 * @author 软工课设车队！
 * @since 2021-05-15
 */
@CrossOrigin
@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    private IUserService userService;

    @Autowired
    IExerciseService exerciseService;

    @PostMapping("save")
    public SaveExerciseDTO Save(@RequestBody Exercise exercise){
        SaveExerciseDTO saveExerciseDTO=new SaveExerciseDTO();
        boolean b = exerciseService.save(exercise);
        saveExerciseDTO.setExercise(exercise);
        if(b){
            saveExerciseDTO.setMsg(Constant.MsgSaveSuccess);
            saveExerciseDTO.setRespCode(Constant.SaveSuccess);
        }else{
            saveExerciseDTO.setMsg(Constant.MsgNotChange);
            saveExerciseDTO.setRespCode(Constant.NotChange);
        }
        return saveExerciseDTO;
    }

    @GetMapping("query")
    public QueryExerciseDTO Query(@RequestParam(name = "exerciseId") Long exerciseId){
        QueryExerciseDTO queryExerciseDTO=new QueryExerciseDTO();
        Exercise exercise = exerciseService.getById(exerciseId);
        queryExerciseDTO.setExercise(exercise);
        if(exercise!=null){
            queryExerciseDTO.setMsg(Constant.MsgQuerySuccess);
            queryExerciseDTO.setRespCode(Constant.QuerySuccess);
        }else{
            queryExerciseDTO.setMsg(Constant.MsgQueryFail);
            queryExerciseDTO.setRespCode(Constant.QueryFail);
        }
        return queryExerciseDTO;
    }

    @GetMapping("delete")
    public DeleteExerciseDTO Delete(@RequestParam(name = "exerciseId")Long exerciseId){
        DeleteExerciseDTO deleteExerciseDTO=new DeleteExerciseDTO();
        boolean b = exerciseService.removeById(exerciseId);
        if(b){
            deleteExerciseDTO.setMsg(Constant.MsgDeleteSuccess);
            deleteExerciseDTO.setRespCode(Constant.DeleteSuccess);
        }else{
            deleteExerciseDTO.setMsg(Constant.MsgDeleteFail);
            deleteExerciseDTO.setRespCode(Constant.DeleteFail);
        }
        return deleteExerciseDTO;
    }

    @GetMapping("teacher")
    public QueryTeacherExerciseDTO QueryByTeacherId(@RequestParam(name = "teacherId") Long teacherId){
        QueryTeacherExerciseDTO q=new QueryTeacherExerciseDTO();
        LambdaQueryChainWrapper<Exercise> eq = exerciseService.lambdaQuery().eq(Exercise::getTeacherId, teacherId);
        List<Exercise> exercises = eq.list();
        q.setItems(exercises);
        q.setMsg(Constant.MsgQuerySuccess);
        q.setRespCode(Constant.QuerySuccess);
        return q;
    }

    @GetMapping("student")
    public QueryStudentExerciseDTO QueryByStudentId(@RequestParam(name = "studentId")Long studentId){
        QueryStudentExerciseDTO q=new QueryStudentExerciseDTO();
        LambdaQueryChainWrapper<Exercise> eq = exerciseService.lambdaQuery().eq(Exercise::getStudentId, studentId);
        List<Exercise> exercises = eq.list();
        q.setItems(exercises);
        q.setMsg(Constant.MsgQuerySuccess);
        q.setRespCode(Constant.QuerySuccess);
        return q;
    }

    @PostMapping("invite")
    public InviteDTO Invite(String email,Long exerciseId){
        User student=userService.lambdaQuery().eq(User::getEmail,email).one();
        Exercise exercise=exerciseService.getById(exerciseId);
        exercise.setStudentId(student.getUserId());
        boolean success=exerciseService.saveOrUpdate(exercise);
        InviteDTO inviteDTO=new InviteDTO();
        if(success){
            inviteDTO.setRespCode("200");
            inviteDTO.setMsg("邀请成功!");
            return inviteDTO;
        }
        inviteDTO.setRespCode("400");
        inviteDTO.setMsg("邀请失败,请确认邀请人的邮箱是否存在!");
        return inviteDTO;
    }
}
