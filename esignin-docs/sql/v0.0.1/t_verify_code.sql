use esignin;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_verify_code
-- ----------------------------

CREATE TABLE `t_verify_code` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '验证码ID',
  `mobile` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号码',
  `verify_code` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '验证码',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `effective_time_end` timestamp NULL DEFAULT NULL COMMENT '验证码失效时间',
  `is_valid` tinyint(1) DEFAULT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;