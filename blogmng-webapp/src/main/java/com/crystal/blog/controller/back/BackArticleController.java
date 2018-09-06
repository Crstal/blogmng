package com.crystal.blog.controller.back;

import com.crystal.blog.common.bean.param.ArticleParam;
import com.crystal.blog.common.bean.response.ArticleCategoryVO;
import com.crystal.blog.common.bean.response.ArticleVO;
import com.crystal.blog.common.bean.response.TagVO;
import com.crystal.blog.common.bean.response.base.Result;
import com.crystal.blog.service.ArticleCategoryService;
import com.crystal.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @Autowired
    private ArticleCategoryService articleCategoryService;

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
        List<ArticleCategoryVO> categoryList = articleCategoryService.selectCategoryList(null);
        List<TagVO> tagList = articleCategoryService.selectTagList();

        ModelAndView modelAndView = new ModelAndView("front/article_edit");
        modelAndView.addObject("article", articleVO);
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("tagList", tagList);
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
