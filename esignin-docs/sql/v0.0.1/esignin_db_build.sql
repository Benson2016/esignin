/*
Navicat MySQL Data Transfer

Source Server         : local_db
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : esignin

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2016-05-24 11:01:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_permission`;
CREATE TABLE `t_sys_permission` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键Id',
  `name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限名字',
  `flag` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限标识',
  `description` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限描述',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sys_permission
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色主键',
  `name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `flag` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色标识',
  `description` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色描述',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_permission`;
CREATE TABLE `t_sys_role_permission` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `role_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色ID',
  `permission_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`),
  KEY `role_id_idx` (`role_id`),
  KEY `PERMISSION_ID_FK_idx` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键ID',
  `user_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `full_name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户全名',
  `password` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户密码',
  `sex` tinyint(1) DEFAULT NULL COMMENT '用户性别',
  `mobile` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户手机号码',
  `email` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户邮箱',
  `age` int(4) DEFAULT NULL COMMENT '用户年龄',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '最近修改时间',
  `flag` tinyint(1) DEFAULT NULL COMMENT '用户标识：1.普通用户，2.普通管理员，3.超级管理员',
  `is_valid` tinyint(1) NOT NULL COMMENT '有效标识：1有效，0无效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idt_sys_user_UNIQUE` (`id`),
  UNIQUE KEY `name_idx` (`user_name`) USING BTREE,
  KEY `mobile_idx` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('34b689b73a5c4a52ac921c5c1587df11', 'benson', 'BensonXu', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '3', '15820217825', 'xubinsen@126.com', '0', '2016-05-24 10:15:57', '2016-05-24 10:15:57', '1', '1');

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键ID',
  `role_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`,`user_id`),
  KEY `USER_ID_FK_idx` (`user_id`),
  KEY `ROLE_ID_FK_idx` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------


insert into `esignin`.`t_sys_role` ( `flag`, `id`, `name`, `description`, `create_time`) values ( 'super', '100000000', '超级管理员', '超级管理员。', '2016-05-31 21:00:14');
insert into `esignin`.`t_sys_role` ( `flag`, `id`, `name`, `description`, `create_time`) values ( 'admin', '100000001', '系统管理员', '系统管理员。', '2016-05-31 21:02:33');
insert into `esignin`.`t_sys_role` ( `flag`, `id`, `name`, `description`, `create_time`) values ( 'user', '100000002', '普通会员', '普通会员。', '2016-05-31 21:10:33');

insert into `esignin`.`t_sys_permission` ( `flag`, `id`, `name`, `description`, `create_time`) values ( 'sys', '100000000', '系统管理', '系统管理模块。', '2016-05-31 21:03:54');
insert into `esignin`.`t_sys_permission` ( `flag`, `id`, `name`, `description`, `create_time`) values ( 'user', '100000001', '用户管理', '用户管理模块。', '2016-05-31 21:04:48');
insert into `esignin`.`t_sys_permission` ( `flag`, `id`, `name`, `description`, `create_time`) values ( 'role', '100000002', '角色管理', '角色管理模块。', '2016-05-31 21:05:24');
insert into `esignin`.`t_sys_permission` ( `flag`, `id`, `name`, `description`, `create_time`) values ( 'permission', '100000003', '权限管理', '权限管理模块。', '2016-05-31 21:05:58');
