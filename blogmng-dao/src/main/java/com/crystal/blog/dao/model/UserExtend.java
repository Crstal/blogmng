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
public class UserExtend extends BaseModel implements Serializable {
    /**
     * 字段：user_id
     * 含义：用户id
     */
    private Integer userId;

    /**
     * 字段：follow_count
     * 含义：关注数量
     */
    private Integer followCount;

    /**
     * 字段：fans_count
     * 含义：粉丝数量
     */
    private Integer fansCount;

    /**
     * 字段：comment_count
     * 含义：评论数量
     */
    private Integer commentCount;

    /**
     * 字段：visit_count
     * 含义：访问数量
     */
    private Integer visitCount;

    private static final long serialVersionUID = 1L;
}