package com.crystal.blog.dao.model;

import com.crystal.blog.dao.model.base.BaseModel;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2018/08/29
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFollow extends BaseModel implements Serializable {
    /**
     * 字段：user_id
     * 含义：用户id
     */
    private Integer userId;

    /**
     * 字段：follow_user_id
     * 含义：关注用户id
     */
    private Integer followUserId;

    /**
     * 字段：follow_user_nick_name
     * 含义：关注用户昵称
     */
    private String followUserNickName;

    private static final long serialVersionUID = 1L;
}