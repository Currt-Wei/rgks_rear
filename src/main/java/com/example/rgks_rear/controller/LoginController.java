package com.example.rgks_rear.controller;

import com.example.rgks_rear.dto.LoginDTO;
import com.example.rgks_rear.dto.LogoutDTO;
import com.example.rgks_rear.dto.RegisterDTO;
import com.example.rgks_rear.pojo.User;
import com.example.rgks_rear.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@CrossOrigin
@RestController
@RequestMapping("index")
public class LoginController {

    @Autowired
    private IUserService userService;

    @PostMapping("login")
    public LoginDTO login(@RequestBody User user){
        LoginDTO loginDTO=userService.login(user.getEmail(),user.getPassword());
//        String res=loginDTO.getRespCode();
//        if(res=="200"){
//            session.setAttribute("user_id",loginDTO.getUser().getUserId());
//        }
        return loginDTO;
    }

     @PostMapping("logout")
    public LogoutDTO logout(HttpSession session){
         session.invalidate();
         LogoutDTO logoutDTO=new LogoutDTO();
         logoutDTO.setRespCode("200");
         logoutDTO.setMsg("注销成功");
         return logoutDTO;
     }

    @PostMapping("register")
    public RegisterDTO register(@RequestBody User user){
        RegisterDTO registerDTO=new RegisterDTO();
        User user1=userService.lambdaQuery().eq(User::getEmail,user.getEmail()).one();
        if(user1!=null){
            registerDTO.setRespCode("400");
            registerDTO.setMsg("邮箱已存在，请重新输入！");
            return registerDTO;
        }
        boolean success=userService.save(user);

        if(success){
            registerDTO.setRespCode("200");
            registerDTO.setMsg("注册功");
            return registerDTO;
        }
        registerDTO.setRespCode("400");
        registerDTO.setMsg("注册失败");
        return registerDTO;
    }

    @PostMapping("getUser")
    public User getUser(@RequestBody String token){
        return userService.getUser(token);

    }
}

