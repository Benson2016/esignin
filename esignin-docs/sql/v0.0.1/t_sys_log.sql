
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log` (
  `id` varchar(32) NOT NULL COMMENT '业务ID',
  `ip` varchar(32) DEFAULT NULL COMMENT 'IP地址',
  `user_name` varchar(128) DEFAULT NULL COMMENT '用户名称',
  `module_name` varchar(255) DEFAULT NULL COMMENT '模块名称',
  `oper_content` varchar(512) DEFAULT NULL COMMENT '操作内容',
  `oper_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `consume_time` varchar(64) DEFAULT NULL COMMENT '消耗时间（单位毫秒）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
-- ----------------------------
-- Records of t_sys_log
-- ----------------------------


-- ----------------------------
-- Table structure for t_sys_exception_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_exception_log`;
CREATE TABLE `t_sys_exception_log` (
  `id` varchar(32) NOT NULL COMMENT '业务ID',
  `ip` varchar(32) DEFAULT NULL COMMENT 'IP地址',
  `user_name` varchar(128) DEFAULT NULL COMMENT '用户名称',
  `module_name` varchar(255) DEFAULT NULL COMMENT '模块名称',
  `oper_content` varchar(512) DEFAULT NULL COMMENT '操作内容',
  `exception` text(65535) DEFAULT NULL COMMENT '异常信息',
  `oper_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `consume_time` varchar(64) DEFAULT NULL COMMENT '消耗时间（单位毫秒）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sys_exception_log
-- ----------------------------
