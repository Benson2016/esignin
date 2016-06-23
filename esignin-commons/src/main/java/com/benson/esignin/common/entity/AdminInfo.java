package com.benson.esignin.common.entity;

/**
 * 系统管理员信息
 *
 * @author Benson Xu
 * @version 1.0
 * @since 2016年06月20日 11:10
 */
public class AdminInfo {

    private String name;    //姓名
    private String email;   //邮件
    private String mobile;  //手机号码

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
