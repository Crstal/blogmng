package com.crystal.blog.common.exception;

import com.crystal.blog.common.enums.ErrorCode;

/**
* @Author: caoyue
* @Description: 通用错误异常
* @Date: 11:32 2018/8/16
**/
public class BussinessException {

    private String code;
    private String message;

    public BussinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BussinessException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
