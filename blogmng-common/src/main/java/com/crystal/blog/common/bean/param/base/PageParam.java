package com.crystal.blog.common.bean.param.base;

import lombok.Data;

import java.io.Serializable;

/**
 *  分页参数
 */
@Data
public class PageParam implements Serializable {
    private static final long serialVersionUID = -4447387204978744727L;

    /** 页码 */
    private Integer pageNo = 1;

    /** 每页数量 */
    private Integer pageSize = 10;

    /** 偏移量 */
    private Integer offset = 0;

    public Integer getOffset() {
        return (pageNo - 1) * pageSize;
    }
}
