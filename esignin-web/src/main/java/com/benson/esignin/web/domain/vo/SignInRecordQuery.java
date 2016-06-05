package com.benson.esignin.web.domain.vo;


/**
 * 签到查询类
 *
 * @author: Benson Xu
 * @date: 2016年06月05日 01:01
 */
public class SignInRecordQuery extends BasePageQuery {


    private String mobile;      // 手机号码

    private String startTime;   // 开始时间

    private String endTime;     // 结束时间


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
