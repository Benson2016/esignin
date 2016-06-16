alter table t_sys_user add column origin tinyint(1) default 0 comment '来源：1.后台添加;2.后台注册;3.手机注册';

alter table t_verify_code add column send_status tinyint(1) default 0 comment '短信发送状态：0.未发送，1.已发送，2.发送失败';