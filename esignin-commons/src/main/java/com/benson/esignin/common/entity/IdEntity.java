package com.benson.esignin.common.entity;

import com.benson.esignin.common.utils.UUIDUtil;

import java.io.Serializable;

/**
 * ID 实体类,通用类
 *
 * @author: Benson Xu
 * @date: 2016年05月21日 23:38
 */
public class IdEntity implements Serializable {

    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void generateUUId() {
        this.id = UUIDUtil.getUUID();
    }
}
