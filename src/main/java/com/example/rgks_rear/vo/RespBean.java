package com.example.rgks_rear.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object obj;

    //成功返回结果
    public static RespBean success(){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(),RespBean.success().getMessage(),null);
    }

    public static RespBean success(Object obj){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(),RespBean.success().getMessage(),obj);
    }

    //返回错误结果
    public static RespBean error(RespBeanEnum respBeanEnum) {
        return new RespBean(RespBeanEnum.ERROR.getCode(), respBeanEnum.getMessage(),null);
    }

    public static RespBean error(RespBeanEnum respBeanEnum, Object obj) {
        return new RespBean(RespBeanEnum.ERROR.getCode(), respBeanEnum.getMessage(),obj);
    }
}
