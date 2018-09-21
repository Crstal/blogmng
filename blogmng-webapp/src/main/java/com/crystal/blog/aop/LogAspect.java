package com.crystal.blog.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(0) // 小的先执行
@Component
@Slf4j
public class LogAspect {

    @Before("execution(* com.crystal.blog.controller.*.*(..))") //6:直接拦截方法名
    public void logAroundController(JoinPoint joinPoint){
       log.info("调用{}类的{}方法，参数{}", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), joinPoint.getArgs());
    }
}
