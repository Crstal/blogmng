package com.crystal.blog.controller.back;

import com.crystal.blog.common.bean.Result;
import com.crystal.blog.common.bean.param.ArticleParam;
import com.crystal.blog.common.bean.response.ArticleVO;
import com.crystal.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
* @Author: caoyue
* @Description: 文章
* @Date: 17:24 2018/8/27
**/
@Controller
@RequestMapping("/back/article")
public class BackArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 跳转到文章修改页面
     * @param id
     * @return
     */
    @GetMapping(value = {"{id}", ""})
    public ModelAndView toEdit(@PathVariable(required = false, value = "id") Integer id) {
        ArticleVO articleVO = null;
        if (id == null) {
            articleVO = new ArticleVO();
        } else {
            articleVO = articleService.queryDetail(id);
        }
        ModelAndView modelAndView = new ModelAndView("front/article_edit");
        modelAndView.addObject("article", articleVO);
        return modelAndView;
    }

    /**
     * 保存文章内容
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> save(@Validated ArticleParam articleParam) {
        Integer id = articleService.save(articleParam);
       return Result.wrapSuccessfulResult(id);
    }

    /**
     * 删除文章
     * @param articleParam
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public String delete(ArticleParam articleParam) {

        return null;
    }
}
