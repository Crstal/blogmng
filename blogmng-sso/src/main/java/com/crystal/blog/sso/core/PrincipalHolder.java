package com.crystal.blog.sso.core;

import com.crystal.blog.sso.bean.Principal;

/**
* @Author: caoyue
* @Description: 当前线程持有用户
* @Date: 20:15 2018/9/12
**/
public class PrincipalHolder {

    private static ThreadLocal<Principal> threadLocal = new ThreadLocal<Principal>();

    public static Principal get() {
        return threadLocal.get();
    }

    public static void set(Principal principal) {
        threadLocal.set(principal);
    }

    public static void clear() {
        threadLocal.remove();
    }
}
