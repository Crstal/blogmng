package com.crystal.blog.controller.front;

import com.crystal.blog.common.bean.response.ThemeEffectVO;
import com.crystal.blog.common.bean.response.base.Result;
import com.crystal.blog.service.ThemeEffectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ThemeController {

    @Autowired
    private ThemeEffectService themeEffectService;

    /**
     * 获取所有的主题事件
     */
    @GetMapping("themeEffects")
    @ResponseBody
    public Result<List<ThemeEffectVO>> selectAllThemeEffects() {
        List<ThemeEffectVO> list = themeEffectService.queryAllThemeEffects();
        return Result.wrapSuccessfulResult(list);
    }

    /**
     * 修改使用状态
     * @param id
     * @return
     */
    @PostMapping("themeEffect/{id}")
    @ResponseBody
    public Result<Void> changeThemeEffectStatus(@PathVariable(value = "id") Integer id) {
        themeEffectService.changeThemeEffectStatus(id);
        return Result.wrapSuccessfulResult(null);
    }
}
