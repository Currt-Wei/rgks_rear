package com.example.rgks_rear.controller;


import com.example.rgks_rear.common.Constant;
import com.example.rgks_rear.dto.QueryExerciseDTO;
import com.example.rgks_rear.dto.UserDTO;
import com.example.rgks_rear.pojo.Exercise;
import com.example.rgks_rear.pojo.User;
import com.example.rgks_rear.service.IExerciseService;
import com.example.rgks_rear.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 软工课设车队！
 * @since 2021-05-15
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("query")
    public UserDTO Query(Long userId){
        UserDTO userDTO=new UserDTO();
        User user = userService.getUserById(userId);
        userDTO.setUser(user);
        if(user!=null){
            userDTO.setMsg(Constant.MsgQuerySuccess);
            userDTO.setRespCode(Constant.QuerySuccess);
        }else{
            userDTO.setMsg(Constant.MsgQueryFail);
            userDTO.setRespCode(Constant.QueryFail);
        }
        return userDTO;
    }
}
