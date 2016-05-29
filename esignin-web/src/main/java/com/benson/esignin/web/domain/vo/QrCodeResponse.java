package com.benson.esignin.web.domain.vo;

import com.benson.esignin.common.enums.StateResponse;

/**
 * 二维码控制层响应类
 *
 * @author: Benson Xu
 * @date: 2016年05月29日 11:14
 */
public class QrCodeResponse extends BaseResponse {


    private String un;  //用户名

    private String up;  //用户密码

    private String um;  //用户手机号码

    public QrCodeResponse() {
        super();
    }

    public QrCodeResponse(StateResponse stateResponse) {
        super(stateResponse);
    }

    public QrCodeResponse(Integer rspCode, String rspMsg) {
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

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }
}
