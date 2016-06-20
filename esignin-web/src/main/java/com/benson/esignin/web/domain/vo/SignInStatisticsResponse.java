package com.benson.esignin.web.domain.vo;

import com.benson.esignin.common.enums.StateResponse;

/**
 * 签到统计响应类
 *
 * @author: Benson Xu
 * @date: 2016年06月20日 20:18
 */
public class SignInStatisticsResponse extends BaseResponse {

    private int[] data;

    private String[] names;

    private int size; // 数据大小:0代表无数据;大于0代表有


    public SignInStatisticsResponse() {
        super();
    }

    public SignInStatisticsResponse(StateResponse stateResponse) {
        super(stateResponse);
    }


    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
