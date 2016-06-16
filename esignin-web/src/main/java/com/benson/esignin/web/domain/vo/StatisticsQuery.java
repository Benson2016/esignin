package com.benson.esignin.web.domain.vo;

/**
 * 统计查询
 *
 * @author: Benson Xu
 * @date: 2016年06月17日 00:10
 */
public class StatisticsQuery {

    private String year;    // 年份

    private Integer origin; // 来源


    public StatisticsQuery() {}

    public StatisticsQuery(String year, Integer origin) {
        this.year = year;
        this.origin = origin;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }
}
