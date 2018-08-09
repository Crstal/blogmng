package com.crystal.blog.dao.model;

import com.crystal.blog.dao.model.base.BaseModel;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2018/08/09
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleUser extends BaseModel implements Serializable {
    /**
     * 字段：role_code
     * 含义：角色编号
     */
    private String roleCode;

    /**
     * 字段：user_id
     * 含义：用户id
     */
    private Integer userId;

    private static final long serialVersionUID = 1L;
}