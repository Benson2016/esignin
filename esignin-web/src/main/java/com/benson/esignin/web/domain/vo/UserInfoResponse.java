package com.benson.esignin.web.domain.vo;

import com.benson.esignin.common.enums.StateResponse;

/**
 * 用户信息响应类
 *
 * @author: Benson Xu
 * @date: 2016年05月28日 21:26
 */
public class UserInfoResponse extends BaseResponse {

    private String un;  //用户名

    private String up;  //用户密码


    public UserInfoResponse() {
        super();
    }

    public UserInfoResponse(StateResponse stateResponse) {
        super(stateResponse);
    }

    public UserInfoResponse(Integer rspCode, String rspMsg) {
        super(rspCode, rspMsg);
    }


    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }
}
