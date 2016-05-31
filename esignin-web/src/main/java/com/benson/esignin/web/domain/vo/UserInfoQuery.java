package com.benson.esignin.web.domain.vo;

/**
 * 用户查询类
 *
 * @author: Benson Xu
 * @date: 2016年05月31日 23:39
 */
public class UserInfoQuery {

    private int page = 1;

    private int size = 10;

    private String mobile;

    private String fullName;

    private String userName;


    public int getPage() {
        return page;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
