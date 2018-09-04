package com.crystal.blog.common.bean.response.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    /** 序列化id */
    private static final long serialVersionUID = 8823675572536558666L;

    /** 错误代码 */
    private String code;

    /** 错误详细信息 */
    private String message;

    /** 调用是否成功 */
    private boolean success = false;

    /** 返回结果，调用失败返回null */
    private T data;

    private long total = 0;

    private static String SUCCESS_CODE = "0";

    /**
     * the construction method of Result
     */
    public Result() {
    }

    public static <T> Result<T> wrapErrorResult(String errCode, String errMsg) {
        Result<T> result = new Result<>();
        result.success = false;
        result.code = errCode;
        result.message = errMsg;
        return result;
    }

    public static <T> Result<T> wrapSuccessfulResult(T data) {
        Result<T> result = new Result<>();
        result.data = data;
        result.success = true;
        result.code = SUCCESS_CODE;
        if (data instanceof PageInfo) {
            result.total = ((PageInfo) data).getTotal();
        }
        return result;
    }

    public static <T> Result<T> wrapSuccessfulResult(T data, long total) {
        Result<T> result = new Result<>();
        result.data = data;
        result.success = true;
        result.code = SUCCESS_CODE;
        result.total = total;
        return result;
    }

    public static <T> Result<T> wrapSuccessfulResult(T data, String message) {
        Result<T> result = new Result<>();
        result.data = data;
        result.success = true;
        result.message = message;
        result.code = SUCCESS_CODE;
        if (data instanceof PageInfo) {
            result.total = ((PageInfo) data).getTotal();
        }
        return result;
    }
}