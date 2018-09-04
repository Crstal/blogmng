package com.crystal.blog.common.bean.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleVO {
    private Integer id;

    /**
     * 字段：user_id
     * 含义：用户id
     */
    private Integer userId;

    /**
     * 含义：文章类型
     */
    private String categoryCode;

    /**
     * 含义：文章类型
     */
    private String categoryName;

    // 标签
    private List<TagVO> tags;

    /**
     * 含义：文章标题
     */
    private String title;

    /**
     * 含义：上一篇文章id
     */
    private Integer preArticleId;

    /**
     * 含义：上一篇文章标题
     */
    private String preArticleTitle;

    /**
     * 含义：下一篇文章id
     */
    private Integer nextArticleId;

    /**
     * 含义：下一篇文章标题
     */
    private String nextArticleTitle;

    /**
     * 含义：文章内容id
     */
    private Integer contentId;

    /**
     * 含义：文章状态：1发布 2私有 3草稿 4回收
     */
    private Integer status;

    /**
     * 含义：关注数量
     */
    private Integer followCount;

    /**
     * 含义：评论数量
     */
    private Integer commentCount;

    /**
     * 含义：浏览数量
     */
    private Integer visitCount;

    private String content;

    private String createBy;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String updateBy;
    private Date updateTime;
}
