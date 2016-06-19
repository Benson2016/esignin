package com.benson.esignin.web.domain.vo;

import com.benson.esignin.common.enums.StateResponse;

/**
 * 用户统计响应类
 *
 * @author: Benson Xu
 * @date: 2016年06月20日 00:07
 */
public class UserStatisticsResponse extends BaseResponse {


    private int[] data1;
    private int[] data2;
    private int[] data3;


    public UserStatisticsResponse() {
        super();
        data1 = new int[12];
        data2 = new int[12];
        data3 = new int[12];
    }

    public UserStatisticsResponse(StateResponse stateResponse) {
        super(stateResponse);
        data1 = new int[12];
        data2 = new int[12];
        data3 = new int[12];
    }


    public int[] getData1() {
        return data1;
    }

    public void setData1(int[] data1) {
        this.data1 = data1;
    }

    public int[] getData2() {
        return data2;
    }

    public void setData2(int[] data2) {
        this.data2 = data2;
    }

    public int[] getData3() {
        return data3;
    }

    public void setData3(int[] data3) {
        this.data3 = data3;
    }
}
