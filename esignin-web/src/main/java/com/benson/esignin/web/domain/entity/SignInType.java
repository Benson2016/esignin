package com.benson.esignin.web.domain.entity;

import java.io.Serializable;

/**
 * 签到类型
 *
 * @author: Benson Xu
 * @date: 2016年05月29日 00:11
 */
public class SignInType implements Serializable {

    private Integer id; //类型ID,自增长列

    private String typeName; // 类型名称


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
