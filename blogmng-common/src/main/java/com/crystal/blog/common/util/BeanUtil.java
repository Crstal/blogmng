package com.crystal.blog.common.util;

import com.crystal.blog.common.enums.ErrorCode;
import com.crystal.blog.common.exception.BussinessRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BeanUtil {

    public static <BO, VO> VO transfer(BO bo, Class<VO> vo) {
        VO result = null;
        try {
            result = vo.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("bean对象转换创建实例{}异常", vo.getName());
            throw new BussinessRuntimeException(ErrorCode.TRANSFORM_ERROR.getCode(), ErrorCode.TRANSFORM_ERROR.getMessage());
        }
        if (bo != null) {
            BeanUtils.copyProperties(bo, result);
        }
        return result;
    }

    public static <BO, VO> List<VO> transferList(List<BO> boList, Class<VO> clazz) {
        List<VO> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(boList)) {
            for (BO bo : boList) {
                result.add(transfer(bo, clazz));
            }
        }
        return result;
    }
}
