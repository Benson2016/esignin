use esignin;


SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE IF NOT EXISTS `t_sign_in_record` (
  `id` VARCHAR(32) NOT NULL COMMENT '记录ID',
  `qrid` VARCHAR(32) NULL COMMENT '二维码ID',
  `user_id` VARCHAR(32) NULL COMMENT '签到用户ID',
  `mobile` VARCHAR(11) NULL COMMENT '手机号码',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '签到时间',
  `is_valid` TINYINT(1) NULL DEFAULT 1 COMMENT '是否有效：1有效，0无效',
  PRIMARY KEY (`id`),
  INDEX `idx_qrid` (`qrid` ASC, `user_id` ASC)  COMMENT '用户签到索引'
)
ENGINE = InnoDB