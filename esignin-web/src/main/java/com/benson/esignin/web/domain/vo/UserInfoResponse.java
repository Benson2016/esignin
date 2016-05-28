package com.benson.esignin.web.domain.vo;

import com.benson.esignin.common.enums.StateResponse;

/**
 * 用户信息响应类
 *
 * @author: Benson Xu
 * @date: 2016年05月28日 21:26
 */
public class UserInfoResponse extends BaseResponse {


    public UserInfoResponse() {
        super();
    }

    public UserInfoResponse(StateResponse stateResponse) {
        super(stateResponse);
    }

    public UserInfoResponse(Integer rspCode, String rspMsg) {
        super(rspCode, rspMsg);
    }

}
