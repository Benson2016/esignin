package com.benson.esignin.web.domain.vo;

/**
 * 用户统计VO类
 *
 * @author: Benson Xu
 * @date: 2016年06月16日 22:57
 */
public class UserStatisticsVo {

    private String month;   //年月:yyyy-MM

    private Integer counts; //数量


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

}
