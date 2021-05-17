package com.example.rgks_rear.controller;

import com.example.rgks_rear.dto.LoginDTO;
import com.example.rgks_rear.dto.LogoutDTO;
import com.example.rgks_rear.dto.RegisterDTO;
import com.example.rgks_rear.pojo.User;
import com.example.rgks_rear.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
@CrossOrigin
@RestController
@RequestMapping("index")
public class LoginController {
    @Autowired
    private IUserService userService;

    @PostMapping("login")
    public LoginDTO login(String email, String password){
        LoginDTO loginDTO=userService.login(email,password);
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
        boolean success=userService.save(user);
        RegisterDTO registerDTO=new RegisterDTO();
        if(success){
            registerDTO.setRespCode("200");
            registerDTO.setMsg("插入数据成功");
            return registerDTO;
        }
        registerDTO.setRespCode("400");
        registerDTO.setMsg("插入数据失败");
        return registerDTO;
    }
}

