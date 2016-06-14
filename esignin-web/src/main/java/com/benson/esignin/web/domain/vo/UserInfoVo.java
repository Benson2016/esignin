package com.benson.esignin.web.domain.vo;


import java.io.Serializable;

/**
 * 用户信息VO类
 *
 * @author: Benson Xu
 * @date: 2016年06月15日 01:21
 */
public class UserInfoVo implements Serializable {

    private String id;    //ID

    private String userName;    //用户名

    private String fullName;    //姓名

    private String password;    //密码

    private Integer sex;        //性别:1.男;2.女;3.未知

    private String mobile;      //手机号码

    private String email;       //邮箱

    private Integer age;        //年龄

    private String createTime;    //创建时间

    private String updateTime;    //更新时间

    private Integer flag;       //用户标识：1.普通用户，2.普通管理员，3.超级管理员

    private Integer isValid;    //是否有效：0无效，1有效（默认）


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
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
}
