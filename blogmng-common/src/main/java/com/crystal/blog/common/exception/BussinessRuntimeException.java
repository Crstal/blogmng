package com.crystal.blog.common.exception;

/**
* @Author: caoyue
* @Description: 博客通用错误异常
* @Date: 14:47 2018/8/10
**/
public class BussinessRuntimeException extends RuntimeException {

    private Integer code;
    private String message;

    public BussinessRuntimeException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
