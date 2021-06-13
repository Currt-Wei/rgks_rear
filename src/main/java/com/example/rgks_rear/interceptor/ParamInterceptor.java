package com.example.rgks_rear.interceptor;

import cn.hutool.json.JSONObject;
import com.example.rgks_rear.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ParamInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = httpServletRequest.getHeader("token");
        //token验证
        if(token!=null){
            String id = stringRedisTemplate.opsForValue().get(token);
            if(id!=null){
                System.out.println("token验证成功");
                return true;
            }else{
                System.out.println("token验证失败");
                returnJson(httpServletResponse);
                return false;
            }
        }else{
            System.out.println("token验证失败");
            returnJson(httpServletResponse);
            return false;
        }

    }

    private void returnJson(HttpServletResponse response){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            LoginDTO msg = new LoginDTO();
            msg.setRespCode("404");
            msg.setMsg("没有token或token无效");
            JSONObject jsonObject = new JSONObject(msg);
            writer.print(jsonObject);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
