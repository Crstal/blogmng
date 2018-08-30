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
public class Role extends BaseModel implements Serializable {
    /**
     * 字段：code
     * 含义：角色编号
     */
    private String code;

    /**
     * 字段：description
     * 含义：角色说明
     */
    private String description;

    /**
     * 字段：status
     * 含义：状态：1开启 2关闭
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}