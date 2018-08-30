package com.crystal.blog.common.util;

import com.crystal.blog.common.bean.response.UserVO;

public class AuthUtil {

    public static UserVO getCurrentUser() {
        UserVO userVO = new UserVO();
        userVO.setId(1);
        userVO.setLoginName("crystal");
        userVO.setNickName("蛾子");
        return userVO;
    }
}
