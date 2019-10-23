package com.crystal.blog.service;

import com.crystal.blog.common.bean.response.ThemeEffectVO;

import java.util.List;

public interface ThemeEffectService {

    /**
     * 查询所有的主题事件
     * @return
     */
    List<ThemeEffectVO> queryAllThemeEffects();

    /**
     * 修改主题事件使用状态
     * @param id
     */
    void changeThemeEffectStatus(Integer id);
}
