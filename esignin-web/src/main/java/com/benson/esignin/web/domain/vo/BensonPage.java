package com.benson.esignin.web.domain.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 *
 * @author: Benson Xu
 * @date: 2016年06月01日 00:33
 */
public class BensonPage<T> implements Serializable {

    private int page;   // 当前页

    private int pageSize; // 每页大小

    private int total;  // 总数

    private List<T> content; // 数据内容

    public BensonPage(int page, int pageSize, List<T> content) {
        this.page = page;
        this.pageSize = pageSize;
        this.content = content;
        this.total = null== content?0:content.size();
    }

    public BensonPage(int page, int pageSize, List<T> content, int total) {
        this.page = page;
        this.pageSize = pageSize;
        this.content = content;
        this.total = total;
    }

    /**
     * 获取总页数
     * @return
     */
    public int getTotalPages() {
        return this.pageSize == 0?1:(int)Math.ceil((double)this.total / (double)this.pageSize);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

}
