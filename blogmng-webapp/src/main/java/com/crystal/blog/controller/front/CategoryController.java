package com.crystal.blog.controller.front;

import com.crystal.blog.common.bean.Result;
import com.crystal.blog.common.bean.response.ArticleCategoryVO;
import com.crystal.blog.common.bean.response.TagVO;
import com.crystal.blog.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/front")
@Controller
public class CategoryController {

    @Autowired
    private ArticleCategoryService articleCategoryService;

    /**
     * 获取文章分类
     */
    @GetMapping("categories")
    @ResponseBody
    public Result<List<ArticleCategoryVO>> selectCategoryList() {
        List<ArticleCategoryVO> result = articleCategoryService.selectCategoryList();
        return Result.wrapSuccessfulResult(result);
    }

    /**
     * 获取文章分类
     */
    @GetMapping("tags")
    @ResponseBody
    public Result<List<TagVO>> selectTagList() {
        List<TagVO> result = articleCategoryService.selectTagList();
        return Result.wrapSuccessfulResult(result);
    }

}
