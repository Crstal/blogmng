package com.crystal.blog.common.exception;

import com.crystal.blog.common.enums.ErrorCode;

/**
* @Author: caoyue
* @Description: 通用错误异常
* @Date: 14:47 2018/8/10
**/
public class BussinessRuntimeException extends RuntimeException {

    private String code;
    private String message;

    public BussinessRuntimeException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BussinessRuntimeException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
