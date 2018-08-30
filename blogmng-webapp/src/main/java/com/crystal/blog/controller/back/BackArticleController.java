package com.crystal.blog.controller.back;

import com.crystal.blog.common.bean.param.ArticleParam;
import com.crystal.blog.common.enums.ArticleStatusEnum;
import com.crystal.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping("")
    public String toEdit() {
        return "front/article_edit";
    }

    /**
     * 保存文章内容
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String save(@Validated ArticleParam articleParam) {
        articleService.save(articleParam);
        if (ArticleStatusEnum.PUBLISH.equals(articleParam.getStatus())) {
            return "front/article";
        }
        return "article_edit";
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
