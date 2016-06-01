package com.benson.esignin.web.domain.vo;

/**
 * 分页查询基类
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月01日 18:08
 */
public class BasePageQuery {

    protected int page = 1;

    protected int size = 10;

    public int getPage() {
        return page==0?1:page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
