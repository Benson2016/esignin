package com.benson.esignin.web.domain.entity;

import com.benson.esignin.common.cons.CommonCons;
import com.benson.esignin.common.entity.IdEntity;
import com.benson.esignin.common.utils.DateUtil;

import java.util.Date;

/**
 * 二维码信息
 *
 * @author: Benson Xu
 * @date: 2016年05月28日 23:12
 */
public class QrCode extends IdEntity {

    private String title;   // 业务主题

    private Integer signInType; // 签到类型

    private String createUser;  // 二维码创建者

    private String image;   // 二维码图片内容

    private Date effectiveTimeStart;    // 二维码生效时间

    private Date effectiveTimeEnd;  // 二维码失效时间

    private Integer isValid;    // 是否有效

    private String description; // 业务描述


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取创建时间
     * @return 字符串格式
     */
    public String getCreateTimeStr() {
        return DateUtil.converToString(this.effectiveTimeStart, CommonCons.D_FMT_NORMAL);
    }
    public String getEffectiveTimeEndStr() {
        return DateUtil.converToString(this.effectiveTimeEnd, CommonCons.D_FMT_NORMAL);
    }
}
