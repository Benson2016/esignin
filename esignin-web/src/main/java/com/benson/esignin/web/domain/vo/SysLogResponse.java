package com.benson.esignin.web.domain.vo;

import com.benson.esignin.common.enums.StateResponse;

/**
 * 系统日志响应类
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月06日 16:13
 */
public class SysLogResponse extends BaseResponse {

    public SysLogResponse() {
        super();
    }

    public SysLogResponse(StateResponse stateResponse) {
        super(stateResponse);
    }

    public SysLogResponse(Integer rspCode, String rspMsg) {
        super(rspCode, rspMsg);
    }
}
