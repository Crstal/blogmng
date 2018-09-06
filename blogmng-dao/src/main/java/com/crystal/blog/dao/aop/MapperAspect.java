package com.crystal.blog.dao.aop;

import com.crystal.blog.common.bean.param.base.PageParam;
import com.crystal.blog.common.bean.response.UserVO;
import com.crystal.blog.common.bean.response.base.PageInfo;
import com.crystal.blog.common.util.AuthUtil;
import com.crystal.blog.common.util.BeanUtil;
import com.crystal.blog.dao.model.base.BaseModel;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Order(1) // 小的先执行
@Component
@Slf4j
public class MapperAspect {

    @Before("execution(* com.crystal.blog.dao.mapper.*.insert*(..))") //6:直接拦截方法名
    public void insertBefore(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            if (args[0] instanceof BaseModel) {
                BaseModel baseModel = (BaseModel) args[0];
                UserVO userVO = AuthUtil.getCurrentUser();
                baseModel.setCreateBy(userVO.getLoginName());
                baseModel.setCreateTime(new Date());
                baseModel.setUpdateBy(userVO.getLoginName());
                baseModel.setUpdateTime(new Date());
                log.info("调用方法{}插入对象{}", joinPoint.getSignature().getName(), baseModel);
            }
        }
    }

    @Before("execution(* com.crystal.blog.dao.mapper.*.update*(..))") //6:直接拦截方法名
    public void updateBefore(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length == 1) {
            if (args[0] instanceof BaseModel) {
                BaseModel baseModel = (BaseModel) args[0];
                UserVO userVO = AuthUtil.getCurrentUser();
                baseModel.setUpdateBy(userVO.getLoginName());
                baseModel.setUpdateTime(new Date());
                log.info("调用方法{}修改对象{}", joinPoint.getSignature().getName(), baseModel);
            }
        }
    }

   /* @Before("execution(* com.crystal.blog.dao.mapper.*.*WithPage(..))") //6:直接拦截方法名
    public void queryPageBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            if (args[0] instanceof PageParam) {
                PageParam pageParam = (PageParam) args[0];
                PageHelper.startPage(pageParam.getOffset(), pageParam.getPageSize());
                log.info("分页查询{}参数{}", joinPoint.getSignature().getName(), pageParam);
            }
        }
    }*/

  /*  @Around("execution(* com.crystal.blog.dao.mapper.*.*WithPage(..))")
    public Object processTx(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            if (args[0] instanceof PageParam) {
                PageParam pageParam = (PageParam) args[0];
                PageHelper.startPage(pageParam.getOffset(), pageParam.getPageSize());
                log.info("分页查询{}参数{}", joinPoint.getSignature().getName(), pageParam);
            }
        }
        Object rvt = joinPoint.proceed();
        return rvt;
    }*/

}