package com.crystal.blog.dao.model;

import com.crystal.blog.dao.model.base.BaseModel;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2018/09/11
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenRecord extends BaseModel implements Serializable {
    /**
     * 字段：type
     * 含义：类型：1微信 2qq
     */
    private Integer type;

    /**
     * 字段：token
     * 含义：配置token
     */
    private String token;

    /**
     * 字段：access_token
     * 含义：访问token
     */
    private String accessToken;

    /**
     * 字段：expire_in
     * 含义：过期时间
     */
    private Date expireIn;

    private static final long serialVersionUID = 1L;
}