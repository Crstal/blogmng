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
public class Comment extends BaseModel implements Serializable {
    /**
     * 字段：user_id
     * 含义：用户id
     */
    private Integer userId;

    /**
     * 字段：nick_name
     * 含义：用户昵称
     */
    private String nickName;

    /**
     * 字段：icon_atta_id
     * 含义：头像id
     */
    private Integer iconAttaId;

    /**
     * 字段：article_id
     * 含义：文章id
     */
    private Integer articleId;

    /**
     * 字段：parent_id
     * 含义：父级id
     */
    private Integer parentId;

    /**
     * 字段：send_user_id
     * 含义：给谁评论的
     */
    private Integer sendUserId;

    /**
     * 字段：status
     * 含义：评论状态 1已发送 2已读 3已删除
     */
    private Integer status;

    /**
     * 字段：comment
     * 含义：评论内容
     */
    private String comment;

    private static final long serialVersionUID = 1L;
}