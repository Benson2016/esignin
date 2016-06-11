/*
Navicat MySQL Data Transfer

Source Server         : local_db
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : esignin

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2016-05-26 20:31:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_qr_code
-- ----------------------------
DROP TABLE IF EXISTS `t_qr_code`;
CREATE TABLE `t_qr_code` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '二维码ID',
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '业务主题',
  `sign_in_type` int(11) DEFAULT NULL COMMENT '签到类型',
  `create_user` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '二维码创建者',
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '二维码图片内容',
  `effective_time_start` timestamp NULL DEFAULT NULL COMMENT '二维码生效时间',
  `effective_time_end` timestamp NULL DEFAULT NULL COMMENT '二维码失效时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `is_valid` tinyint(1) DEFAULT NULL COMMENT '是否有效',
  `description` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '业务描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_qr_code
-- ----------------------------
