package com.benson.esignin.web.domain.vo;

import com.benson.esignin.common.enums.StateResponse;

import java.io.Serializable;

/**
 * 基础响应类
 *
 * @author: Benson Xu
 * @date: 2016年05月28日 13:45
 */
public class BaseResponse implements Serializable {

    private Integer rspCode; // 响应码

    private String rspMsg;  //响应消息


    public BaseResponse() {}

    public BaseResponse(StateResponse stateResponse) {
        this.rspCode = stateResponse.getCode();
        this.rspMsg = stateResponse.getMsg();
    }

    public BaseResponse(Integer rspCode, String rspMsg) {
        this.rspCode = rspCode;
        this.rspMsg = rspMsg;
    }

    public Integer getRspCode() {
        return rspCode;
    }

    public void setRspCode(Integer rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }
}
