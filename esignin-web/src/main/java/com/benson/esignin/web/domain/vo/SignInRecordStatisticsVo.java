package com.benson.esignin.web.domain.vo;

/**
 * 签到统计VO
 *
 * @author: Benson Xu
 * @date: 2016年06月17日 00:45
 */
public class SignInRecordStatisticsVo {

    private Integer signInType;

    private Integer counts;


    public Integer getSignInType() {
        return signInType;
    }

    public void setSignInType(Integer signInType) {
        this.signInType = signInType;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}
