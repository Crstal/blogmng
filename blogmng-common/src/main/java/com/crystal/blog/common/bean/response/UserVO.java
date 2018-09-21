package com.crystal.blog.common.bean.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserVO {

    private Integer id;

    private String loginName;

    /**
     * 含义：昵称
     */
    private String nickName;

    /**
     * 含义：密码
     */
    private String password;

    /**
     * 含义：加密密钥
     */
    private String salt;

    /**
     * 含义：头像
     */
    private Integer iconAttaId;

    /**
     * 含义：性别 1女 2男 3中性
     */
    private Integer sex;

    /**
     * 含义：邮箱
     */
    private String email;

    /**
     * 含义：手机号
     */
    private String mobile;

    /**
     * 含义：生日
     */
    private Date birth;

    // 微信openId
    private String openId;

    /**
     * 含义：状态：1正常 2删除 3冻结
     */
    private Integer status;
}
