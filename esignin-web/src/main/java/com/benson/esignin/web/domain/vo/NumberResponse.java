package com.benson.esignin.web.domain.vo;

import com.benson.esignin.common.enums.StateResponse;

/**
 * 数量响应类
 *
 * @author: Benson Xu
 * @date: 2016年06月20日 23:05
 */
public class NumberResponse extends BaseResponse {

    private Integer newUserCount;

    private Integer newRecordCount;


    public NumberResponse(StateResponse stateResponse) {
        super(stateResponse);
    }

    public Integer getNewUserCount() {
        return newUserCount;
    }

    public void setNewUserCount(Integer newUserCount) {
        this.newUserCount = newUserCount;
    }

    public Integer getNewRecordCount() {
        return newRecordCount;
    }

    public void setNewRecordCount(Integer newRecordCount) {
        this.newRecordCount = newRecordCount;
    }
}
