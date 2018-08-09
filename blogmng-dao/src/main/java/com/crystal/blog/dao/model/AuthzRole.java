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
public class AuthzRole extends BaseModel implements Serializable {
    /**
     * 字段：role_code
     * 含义：角色编号
     */
    private String roleCode;

    /**
     * 字段：authz_code
     * 含义：权限编号
     */
    private String authzCode;

    /**
     * 字段：authz_type
     * 含义：权限类型：1功能 2requestUrl
     */
    private Integer authzType;

    private static final long serialVersionUID = 1L;
}