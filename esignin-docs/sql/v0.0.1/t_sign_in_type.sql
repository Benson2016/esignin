/*
Navicat MySQL Data Transfer

Source Server         : local_db
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : esignin

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2016-05-26 20:31:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sign_in_type
-- ----------------------------
DROP TABLE IF EXISTS `t_sign_in_type`;
CREATE TABLE `t_sign_in_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '类型ID',
  `type_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sign_in_type
-- ----------------------------
INSERT INTO `esignin`.`t_sign_in_type` (`id`, `type_name`) VALUES ('1', '会议签到');
INSERT INTO `esignin`.`t_sign_in_type` (`id`, `type_name`) VALUES ('2', '活动签到');
INSERT INTO `esignin`.`t_sign_in_type` (`id`, `type_name`) VALUES ('3', '考试签到');
INSERT INTO `esignin`.`t_sign_in_type` (`id`, `type_name`) VALUES ('4', '一起嗨皮');
