package com.crystal.blog.dao.model;

import com.crystal.blog.dao.model.base.BaseModel;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2018/08/09
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment extends BaseModel implements Serializable {
    /**
     * 字段：atta_type
     * 含义：附件类型
     */
    private Integer attaType;

    /**
     * 字段：atta_name
     * 含义：附件名称
     */
    private String attaName;

    /**
     * 字段：url
     * 含义：附件地址
     */
    private String url;

    /**
     * 字段：status
     * 含义：附件状态 1正常 2删除
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}