package com.crystal.blog.common.enums;

import lombok.Data;

import java.io.Serializable;

public enum ErrorCode implements Serializable {

    /** ----------------------公用异常信息(1000-1999)---------------------- */
    /** 成功 */
    SUCCESS("1000", "成功"),
    /** 失败 */
    FAILURE("1001", "失败"),

    SYS_ERROR("1002", " 系统异常"),

    TRANSFORM_ERROR("1003","对象转换异常"),

    /**调用异常 */
    SERVICE_REQUEST_REPEAT("1101","重复调用"),

    DB_ADD_ERROR("1201","数据库添加异常"),

    DB_UPDATE_ERROR("1202","数据库修改异常"),

    DB_DELETE_ERROR("1203","数据库删除异常"),

    DB_SELECT_ERROR("1204","数据库查询异常"),

    PARAMETER_TRANSFORM_ERROR("1301","参数转换异常"),

    PARAMETER_INVALID("1302", "参数无效");

    private String code;
    private String message;

    private ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
