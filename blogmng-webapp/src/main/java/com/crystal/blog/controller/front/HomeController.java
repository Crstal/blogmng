package com.crystal.blog.controller.front;

import com.crystal.blog.common.bean.param.ArticleQueryParam;
import com.crystal.blog.common.bean.response.ArticleVO;
import com.crystal.blog.common.util.DateUtil;
import com.crystal.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.DateUtils;

import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("")
    public ModelAndView home(ArticleQueryParam articleQueryParam) {
        List<ArticleVO> hotArticleList = articleService.queryHotArticleList();
        List<ArticleVO> newArticleList = articleService.queryNewArticleList();

        ModelAndView modelAndView = new ModelAndView("front/index");
        modelAndView.addObject("hotArticleList", hotArticleList);
        modelAndView.addObject("newArticleList", newArticleList);
        modelAndView.addObject("articleParam", articleQueryParam);
        return modelAndView;
    }

    /**
     * 归档
     * @param year
     * @return
     */
    @GetMapping(value = {"history/{year}", "history"})
    public ModelAndView history(@PathVariable(required = false) Integer year) {
        if (year == null) {
            year = DateUtils.createNow().get(Calendar.YEAR);
        }
        List<ArticleVO> articleVOS = articleService.queryArticleList(year);
        ModelAndView modelAndView = new ModelAndView("front/history");
        modelAndView.addObject("articleVOS", articleVOS);
        modelAndView.addObject("years", DateUtil.startToNowYearList());
        modelAndView.addObject("year", year);
        return modelAndView;
    }
}
