package com.crystal.blog.service.impl;

import com.crystal.blog.common.bean.response.ThemeEffectVO;
import com.crystal.blog.common.enums.ErrorCode;
import com.crystal.blog.common.enums.ThemeStatusEnum;
import com.crystal.blog.common.exception.BussinessRuntimeException;
import com.crystal.blog.common.util.BeanUtil;
import com.crystal.blog.dao.mapper.ThemeEffectMapper;
import com.crystal.blog.dao.model.ThemeEffect;
import com.crystal.blog.dao.model.ThemeEffectExample;
import com.crystal.blog.service.ThemeEffectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class ThemeEffectServiceImpl implements ThemeEffectService {

    @Autowired
    private ThemeEffectMapper themeEffectMapper;

    @Override
    public List<ThemeEffectVO> queryAllThemeEffects() {
        ThemeEffectExample example = new ThemeEffectExample();
        List<ThemeEffect> themeEffects = themeEffectMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(themeEffects)) {
            return Collections.EMPTY_LIST;
        }
        List<ThemeEffectVO> result = BeanUtil.transferList(themeEffects, ThemeEffectVO.class);
        return result;
    }

    @Override
    public void changeThemeEffectStatus(Integer id) {
        ThemeEffect themeEffect = themeEffectMapper.selectByPrimaryKey(id);
        if (themeEffect == null) {
            log.info("根据id查询主题事件为空，参数：{}", id);
            throw new BussinessRuntimeException(ErrorCode.DB_SELECT_ERROR);
        }
        if (ThemeStatusEnum.NO_USE.getCode().equals(themeEffect.getStatus())) {
            themeEffect.setStatus(ThemeStatusEnum.USED.getCode());
        } else {
            themeEffect.setStatus(ThemeStatusEnum.NO_USE.getCode());
        }
        themeEffectMapper.updateByPrimaryKey(themeEffect);
    }

}
