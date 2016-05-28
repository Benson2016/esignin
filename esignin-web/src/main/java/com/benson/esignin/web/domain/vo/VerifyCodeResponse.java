package com.benson.esignin.web.domain.vo;

import com.benson.esignin.common.enums.StateResponse;

/**
 * 验证码响应类
 *
 * @author: Benson Xu
 * @date: 2016年05月28日 13:48
 */
public class VerifyCodeResponse extends BaseResponse {

    private Integer countDown;  //倒计时

    private String vcid;    //验证码ID

    private Integer isUser;   //是用户:1是,0否


    public VerifyCodeResponse() {
        super();
    }

    public VerifyCodeResponse(StateResponse stateResponse) {
        super(stateResponse);
    }

    public VerifyCodeResponse(Integer rspCode, String rspMsg) {
        super(rspCode, rspMsg);
    }

    public Integer getCountDown() {
        return countDown;
    }

    public void setCountDown(Integer countDown) {
        this.countDown = countDown;
    }

    public String getVcid() {
        return vcid;
    }

    public void setVcid(String vcid) {
        this.vcid = vcid;
    }

    public Integer getIsUser() {
        return isUser;
    }

    public void setIsUser(Integer isUser) {
        this.isUser = isUser;
    }
}
