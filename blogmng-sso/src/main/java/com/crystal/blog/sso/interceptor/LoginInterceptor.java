package com.crystal.blog.sso.interceptor;

import com.crystal.blog.sso.bean.Principal;
import com.crystal.blog.sso.core.PrincipalHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    public static final String sessionIdName = "authId";

    private String loginUrl = "http://crystalcao.top/login";

    // 在目标方法执行前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断是否登陆，将登陆用户保存到线程中
        String authId = request.getParameter(sessionIdName);
        if (StringUtils.isEmpty(authId)) {
            response.sendRedirect(loginUrl);
            return false;
        }
        Object user = request.getSession().getAttribute(authId);
        if (user == null) {
            response.sendRedirect(loginUrl);
            return false;
        }
        Principal principal = (Principal) user;
        PrincipalHolder.set(principal);
        log.info("1111111111111");
        return true;
    }

    // 在目标方法执行后执行，但在请求返回前，我们仍然可以对 ModelAndView进行修改
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("22222222222");
    }

    // 在请求已经返回之后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("33333333333333");
        PrincipalHolder.clear();
    }
}
