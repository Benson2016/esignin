package com.benson.esignin.web.domain.vo;

/**
 * 角色查询类
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月01日 18:11
 */
public class RoleInfoQuery extends BasePageQuery{

    private String name;    // 角色名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
