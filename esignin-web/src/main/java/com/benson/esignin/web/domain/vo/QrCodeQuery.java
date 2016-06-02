package com.benson.esignin.web.domain.vo;


/**
 * 二维码查询类
 *
 * @author: Benson Xu
 * @date: 2016年06月02日 07:31
 */
public class QrCodeQuery extends BasePageQuery {

    private String title;       // 主题

    private Integer signInType; // 签到类型

    private String startTime;   // 开始时间

    private String endTime;     // 结束时间


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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
