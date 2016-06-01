package com.benson.esignin.web.domain.entity;

import com.benson.esignin.common.cons.CommonCons;
import com.benson.esignin.common.entity.IdEntity;
import com.benson.esignin.common.utils.DateUtil;

import java.util.Date;

public class UserInfo extends IdEntity {

    private String userName;    //用户名

    private String fullName;    //姓名

    private String password;    //密码

    private Integer sex;        //性别

    private String mobile;      //手机号码

    private String email;       //邮箱

    private Integer age;        //年龄

    private Date createTime;    //创建时间

    private Date updateTime;    //更新时间

    private Integer flag;       //用户标识：1.普通用户，2.普通管理员，3.超级管理员

    private Integer isValid;    //是否有效：0无效，1有效（默认）


    public UserInfo() {}

    public UserInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getCreateTimeStr() {
        return DateUtil.converToString(this.createTime, CommonCons.D_FMT_NORMAL);
    }

}