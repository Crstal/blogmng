package com.crystal.blog.controller.back;

import com.crystal.blog.common.bean.param.ArticleParam;
import com.crystal.blog.common.bean.param.ArticleQueryParam;
import com.crystal.blog.common.bean.response.ArticleCategoryVO;
import com.crystal.blog.common.bean.response.ArticleVO;
import com.crystal.blog.common.bean.response.TagVO;
import com.crystal.blog.common.bean.response.base.PageInfo;
import com.crystal.blog.common.bean.response.base.Result;
import com.crystal.blog.common.enums.ErrorCode;
import com.crystal.blog.common.exception.BussinessRuntimeException;
import com.crystal.blog.service.ArticleCategoryService;
import com.crystal.blog.service.ArticleService;
import com.crystal.blog.sso.bean.Principal;
import com.crystal.blog.sso.util.AuthorizeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
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
@RequestMapping("/back")
public class BackArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @GetMapping(value = "/articles")
    public Result<PageInfo<ArticleVO>> getArticles(ArticleQueryParam articleQueryParam) {
        Principal principal = AuthorizeUtil.getCurrentUser();
        if (principal == null) {
            throw new BussinessRuntimeException(ErrorCode.NOT_LOGIN);
        }
        articleQueryParam.setUserId(principal.getId());
        PageInfo<ArticleVO> articleVOPageInfo = articleService.queryArticleListWithPage(articleQueryParam);
        return Result.wrapSuccessfulResult(articleVOPageInfo);
    }

    /**
     * 跳转到文章修改页面
     * @param id
     * @return
     */
    @GetMapping(value = {"/article/{id}", "/article"})
    public ModelAndView toEdit(@PathVariable(required = false, value = "id") Integer id) {
        ArticleVO articleVO = null;
        if (id == null) {
            articleVO = new ArticleVO();
        } else {
            articleVO = articleService.queryDetail(id);
        }
        List<ArticleCategoryVO> categoryList = articleCategoryService.selectCategoryList(null);
        List<TagVO> tagList = articleCategoryService.selectTagList();
        if (!CollectionUtils.isEmpty(articleVO.getTags()) && !CollectionUtils.isEmpty(tagList)) {
            for (TagVO cate : tagList) {
                for (TagVO tag : articleVO.getTags()) {
                    if (cate.getId().equals(tag.getId())) {
                        cate.setChecked(true);
                        break;
                    }
                }
            }
        }

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
    @PostMapping(value = "/article")
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
    @DeleteMapping(value = "article")
    public String delete(ArticleParam articleParam) {
        return null;
    }
}
