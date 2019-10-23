/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-08-27 17:16:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `category_code` varchar(11) NOT NULL COMMENT '文章类型',
  `category_name` varchar(20) NOT NULL COMMENT '文章类型',
  `title` varchar(50) NOT NULL COMMENT '文章标题',
  `pre_article_id` int(11) DEFAULT NULL COMMENT '上一篇文章id',
  `pre_article_title` varchar(50) DEFAULT NULL COMMENT '上一篇文章标题',
  `next_article_id` int(11) DEFAULT NULL COMMENT '下一篇文章id',
  `next_article_title` varchar(50) DEFAULT NULL COMMENT '下一篇文章标题',
  `content_id` varchar(50) NOT NULL COMMENT '文章内容id',
  `status` tinyint(4) DEFAULT '1' COMMENT '文章状态：1发布 2私有 3草稿 4回收',
  `follow_count` int(11) DEFAULT '0' COMMENT '关注数量',
  `comment_count` int(11) DEFAULT '0' COMMENT '评论数量',
  `visit_count` int(11) DEFAULT '0' COMMENT '浏览数量',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `atta_type` int(4) DEFAULT NULL COMMENT '附件类型',
  `atta_name` varchar(20) DEFAULT NULL COMMENT '附件名称',
  `url` varchar(120) DEFAULT NULL COMMENT '附件地址',
  `status` tinyint(4) DEFAULT NULL COMMENT '附件状态 1正常 2删除',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件表';

-- ----------------------------
-- Table structure for authz
-- ----------------------------
DROP TABLE IF EXISTS `authz`;
CREATE TABLE `authz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL COMMENT '权限编号',
  `description` varchar(20) NOT NULL COMMENT '权限说明',
  `type` tinyint(4) DEFAULT NULL COMMENT '权限类型：1功能 2requestUrl',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Table structure for authz_role
-- ----------------------------
DROP TABLE IF EXISTS `authz_role`;
CREATE TABLE `authz_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(20) NOT NULL COMMENT '角色编号',
  `authz_code` varchar(20) NOT NULL COMMENT '权限编号',
  `authz_type` tinyint(4) NOT NULL COMMENT '权限类型：1功能 2requestUrl',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_code` (`role_code`,`authz_code`,`authz_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `icon_atta_id` int(11) DEFAULT NULL COMMENT '头像id',
  `article_id` int(11) NOT NULL COMMENT '文章id',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级id',
  `send_user_id` int(11) DEFAULT NULL COMMENT '给谁评论的',
  `status` tinyint(4) DEFAULT NULL COMMENT '评论状态 1已发送 2已读 3已删除',
  `comment` varchar(100) NOT NULL COMMENT '评论内容',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章评论表';

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `article_content`;
CREATE TABLE `article_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text COMMENT '内容',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 1正常 2删除',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容表';

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL COMMENT '角色编号',
  `description` varchar(20) NOT NULL COMMENT '角色说明',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态：1开启 2关闭',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(20) NOT NULL COMMENT '角色编号',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色用户表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(20) DEFAULT NULL COMMENT '登陆名',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '加密密钥',
  `icon_atta_id` int(11) DEFAULT NULL COMMENT '头像',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别 1女 2男 3中性',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `birth` datetime DEFAULT NULL COMMENT '生日',
  `open_id` varchar(50) DEFAULT NULL COMMENT '微信openId',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态：1正常 2删除 3冻结',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_Name` (`login_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for user_article_catetory
-- ----------------------------
DROP TABLE IF EXISTS `article_category`;
CREATE TABLE `article_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) COMMENT '父类型',
  `code` varchar(20) NOT NULL COMMENT '文章类型',
  `name` varchar(20) NOT NULL COMMENT '类型名称',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章类型表';

DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `tag` varchar(20) NOT NULL COMMENT '标签类型',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签表';


DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NOT NULL COMMENT '文章id',
  `tag` varchar(20) NOT NULL COMMENT '标签',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章标签表';



-- ----------------------------
-- Table structure for user_extend
-- ----------------------------
DROP TABLE IF EXISTS `user_extend`;
CREATE TABLE `user_extend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `follow_count` int(11) NOT NULL DEFAULT '0' COMMENT '关注数量',
  `fans_count` int(11) NOT NULL DEFAULT '0' COMMENT '粉丝数量',
  `comment_count` int(11) DEFAULT '0' COMMENT '评论数量',
  `visit_count` int(11) DEFAULT '0' COMMENT '访问数量',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户扩展表';

-- ----------------------------
-- Table structure for user_follow
-- ----------------------------
DROP TABLE IF EXISTS `user_follow`;
CREATE TABLE `user_follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `follow_user_id` int(11) NOT NULL COMMENT '关注用户id',
  `follow_user_nick_name` varchar(20) NOT NULL COMMENT '关注用户昵称',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`follow_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关注表';

-- ----------------------------
-- Table structure for user_follow_article
-- ----------------------------
DROP TABLE IF EXISTS `user_follow_article`;
CREATE TABLE `user_follow_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `follow_article_Id` int(11) NOT NULL COMMENT '关注文章id',
  `follow_article_title` varchar(50) NOT NULL COMMENT '关注文章标题',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关注文章表';

CREATE TABLE `access_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
	`type` TINYINT(4) NOT NULL COMMENT '类型：1微信 2qq',
  `token` varchar(50) NOT NULL COMMENT '配置token',
  `access_token` varchar(512) NOT NULL COMMENT '访问token',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='访问token';

CREATE TABLE `theme_effect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
	`name` TINYINT(4) NOT NULL COMMENT '时间名称',
	`css_urls` varchar(100) NULL COMMENT '样式地址',
  `script_urls` varchar(100) NULL COMMENT '脚本地址',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题效果事件';
