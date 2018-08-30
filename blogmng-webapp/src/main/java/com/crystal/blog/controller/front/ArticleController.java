package com.crystal.blog.controller.front;

import com.crystal.blog.common.bean.param.ArticleQueryParam;
import com.crystal.blog.common.bean.response.ArticleVO;
import com.crystal.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
* @Author: caoyue
* @Description: 文章
* @Date: 17:24 2018/8/27
**/
@Controller
@RequestMapping("/front/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 查询文章
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ModelAndView show(@PathVariable(value = "id") Integer id) {
        ArticleVO articleVO = articleService.queryByPrimaryKey(id);
        ModelAndView modelAndView = new ModelAndView("front/article");
        modelAndView.addObject("article", articleVO);
        return modelAndView;
    }

    /**
     * 查询文章列表
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String queryList(ArticleQueryParam param) {
        return null;
    }

}
