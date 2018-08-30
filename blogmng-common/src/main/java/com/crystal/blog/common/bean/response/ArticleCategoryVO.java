package com.crystal.blog.common.bean.response;

import lombok.Data;

@Data
public class ArticleCategoryVO {

    private Integer id;

    /**
     * 含义：父类型
     */
    private Integer parentId;

    /**
     * 含义：文章类型
     */
    private String code;

    /**
     * 含义：类型名称
     */
    private String name;

}
