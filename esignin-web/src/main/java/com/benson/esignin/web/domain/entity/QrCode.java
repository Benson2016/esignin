package com.benson.esignin.web.domain.entity;

import com.benson.esignin.common.entity.IdEntity;

import java.util.Date;

/**
 * 二维码信息
 *
 * @author: Benson Xu
 * @date: 2016年05月28日 23:12
 */
public class QrCode extends IdEntity {


    private Integer signInType; // 签到类型

    private String createUser;  // 二维码创建者

    private String image;   // 二维码图片地址

    private Date effectiveTimeStart;    // 二维码生效时间

    private Date effectiveTimeEnd;  // 二维码失效时间

    private Integer isValid;    // 是否有效



    public Integer getSignInType() {
        return signInType;
    }

    public void setSignInType(Integer signInType) {
        this.signInType = signInType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getEffectiveTimeStart() {
        return effectiveTimeStart;
    }

    public void setEffectiveTimeStart(Date effectiveTimeStart) {
        this.effectiveTimeStart = effectiveTimeStart;
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
