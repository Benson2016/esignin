package com.benson.esignin.web.domain.entity;

import com.benson.esignin.common.cons.CommonCons;
import com.benson.esignin.common.entity.IdEntity;
import com.benson.esignin.common.utils.DateUtil;

import java.util.Date;

/**
 * 签到记录表
 *
 * 数据表:t_sign_in_record
 * @author: Benson Xu
 * @date: 2016年05月29日 09:21
 */
public class SignInRecord extends IdEntity {

    private String qrid;    // 二维码ID

    private String userId;  // 签到用户ID

    private String mobile;  // 手机号码

    private Date createTime;// 签到时间

    private Integer isValid;// 是否有效：1有效，0无效


    public String getQrid() {
        return qrid;
    }

    public void setQrid(String qrid) {
        this.qrid = qrid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    /**
     * 获取创建时间的字符串格式
     * @return
     */
    public String getCreateTimeStr() {
        return DateUtil.converToString(createTime, CommonCons.D_FMT_NORMAL);
    }

}
