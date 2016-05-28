package com.benson.esignin.web.domain.entity;

import com.benson.esignin.common.entity.IdEntity;

import java.util.Date;

/**
 * 验证码实体类
 * 数据表:t_verify_code
 *
 * @author: Benson Xu
 * @date: 2016年05月28日 10:30
 */
public class VerifyCode extends IdEntity {

    private String mobile;  //手机号码

    private String verifyCode;  //验证码

    private Date createTime;//创建时间

    private Date effectiveTimeEnd;  //时效时间

    private Integer isValid;//是否有效

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEffectiveTimeEnd() {
        return effectiveTimeEnd;
    }

    public void setEffectiveTimeEnd(Date effectiveTimeEnd) {
        this.effectiveTimeEnd = effectiveTimeEnd;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}
