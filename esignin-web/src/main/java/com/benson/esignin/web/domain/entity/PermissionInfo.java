package com.benson.esignin.web.domain.entity;

import com.benson.esignin.common.entity.IdEntity;

import java.util.Date;

/**
 * 权限信息
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright BensonXu Copyright (c) 2016
 * @since 2016年05月31日 14:48
 */
public class PermissionInfo extends IdEntity {

    private String name;    // 权限名字

    private String flag;    // 权限标识

    private String description;    // 权限描述

    private Date createTime;    // 创建时间

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
