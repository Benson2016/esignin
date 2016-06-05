package com.benson.esignin.web.domain.vo;


/**
 * 二维码VO类
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月03日 14:54
 */
public class QrCodeVo {

    private String id;  // 业务ID

    private String title;   // 业务主题

    private Integer signInType; // 签到类型

    private String createUser;  // 二维码创建者

    private String image;   // 二维码图片内容

    private String effectiveTimeStart;  // 二维码生效时间

    private String effectiveTimeEnd;  // 二维码失效时间

    private String createTime;  // 创建时间

    private String description; // 业务描述

    private Integer isValid;    // 是否有效


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSignInType() {
        return signInType;
    }

    public void setSignInType(Integer signInType) {
        this.signInType = signInType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEffectiveTimeStart() {
        return effectiveTimeStart;
    }

    public void setEffectiveTimeStart(String effectiveTimeStart) {
        this.effectiveTimeStart = effectiveTimeStart;
    }

    public String getEffectiveTimeEnd() {
        return effectiveTimeEnd;
    }

    public void setEffectiveTimeEnd(String effectiveTimeEnd) {
        this.effectiveTimeEnd = effectiveTimeEnd;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

}
