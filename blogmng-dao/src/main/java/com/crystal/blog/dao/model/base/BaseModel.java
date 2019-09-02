package com.crystal.blog.dao.model.base;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Date;

@Data
public class BaseModel {
    private Integer id;

    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;

    public void setDefaultValue(String operator) {
        if (StringUtils.isEmpty(operator)) {
            operator = "system";
        }
        if (StringUtils.isEmpty(createBy)) {
            createBy = operator;
            createTime = new Date();
        }

        updateBy = operator;
        updateTime = new Date();
    }

    public void setModifiedValue(String operator) {
        if (StringUtils.isEmpty(operator)) {
            operator = "system";
        }

        updateBy = operator;
        updateTime = new Date();
    }
}
