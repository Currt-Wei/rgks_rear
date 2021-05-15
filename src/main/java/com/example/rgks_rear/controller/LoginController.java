package com.example.rgks_rear.controller;

import com.example.rgks_rear.dto.LoginDTO;
import com.example.rgks_rear.dto.LogoutDTO;
import com.example.rgks_rear.pojo.User;
import com.example.rgks_rear.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("index")
public class LoginController {
    @Autowired
    private IUserService userService;

    @PostMapping("login")
    public LoginDTO login(String email, String password, HttpSession session, Model model){
        LoginDTO loginDTO=userService.login(email,password);
        String res=loginDTO.getRespCode();
        if(res=="200"){
            session.setAttribute("user_id",loginDTO.getUser().getUserId());
            model.addAttribute("user",loginDTO.getUser());
        }
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


}

