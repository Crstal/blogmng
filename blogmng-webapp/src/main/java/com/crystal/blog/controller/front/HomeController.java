package com.crystal.blog.controller.front;

import com.alibaba.druid.mock.MockArray;
import com.crystal.blog.common.bean.param.ArticleQueryParam;
import com.crystal.blog.common.bean.response.ArticleVO;
import com.crystal.blog.common.bean.response.base.PageInfo;
import com.crystal.blog.dao.model.Article;
import com.crystal.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private ArticleService articleService;


    @GetMapping("")
    public ModelAndView home(ArticleQueryParam articleQueryParam) {
        ModelAndView modelAndView = new ModelAndView("front/index");
        modelAndView.addObject("articleParam", articleQueryParam);
        PageInfo<ArticleVO> articlePage = articleService.queryArticleListWithPage(articleQueryParam);
        modelAndView.addObject("articlePage", articlePage);
        return modelAndView;
    }
}
