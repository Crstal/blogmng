package com.crystal.blog.sso.util;

import com.crystal.blog.sso.bean.Principal;
import com.crystal.blog.sso.core.PrincipalHolder;

public class AuthorizeUtil {

    public static Principal getCurrentUser() {
        return PrincipalHolder.get();
    }
}
