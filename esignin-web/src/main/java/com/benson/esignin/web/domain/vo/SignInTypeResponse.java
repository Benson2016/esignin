package com.benson.esignin.web.domain.vo;

import com.benson.esignin.common.enums.StateResponse;

/**
 * 签到类别响应类
 *
 * @author: Benson Xu
 * @date: 2016年06月04日 18:49
 */
public class SignInTypeResponse extends BaseResponse {

    public SignInTypeResponse() {
        super();
    }

    public SignInTypeResponse(StateResponse stateResponse) {
        super(stateResponse);
    }

    public SignInTypeResponse(Integer rspCode, String rspMsg) {
        super(rspCode, rspMsg);
    }
}
