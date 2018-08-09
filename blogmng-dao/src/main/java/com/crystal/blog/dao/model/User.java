package com.crystal.blog.dao.model;

import com.crystal.blog.dao.model.base.BaseModel;
import java.io.Serializable;
import java.util.Date;
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
public class User extends BaseModel implements Serializable {
    /**
     * 字段：login_Name
     * 含义：登陆名
     */
    private String loginName;

    /**
     * 字段：nick_Name
     * 含义：昵称
     */
    private String nickName;

    /**
     * 字段：password
     * 含义：密码
     */
    private String password;

    /**
     * 字段：salt
     * 含义：加密密钥
     */
    private String salt;

    /**
     * 字段：icon_atta_id
     * 含义：头像
     */
    private Integer iconAttaId;

    /**
     * 字段：sex
     * 含义：性别 1女 2男 3中性
     */
    private Integer sex;

    /**
     * 字段：email
     * 含义：邮箱
     */
    private String email;

    /**
     * 字段：mobile
     * 含义：手机号
     */
    private String mobile;

    /**
     * 字段：birth
     * 含义：生日
     */
    private Date birth;

    /**
     * 字段：status
     * 含义：状态：1正常 2删除 3冻结
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}