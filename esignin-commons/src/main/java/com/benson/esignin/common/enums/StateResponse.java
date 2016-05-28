package com.benson.esignin.common.enums;

/**
 * 状态响应 枚举类
 *
 * @author: Benson Xu
 * @date: 2016年05月28日 13:55
 */
public enum StateResponse {

    SUCCESS(100, "请求成功!"),
    INVALID(101, "信息失效!"),
    ERROR_PARAM(102, "参数错误!"),

    // more...

    ERROR_SYS(999, "系统错误!");

    private int code;
    private String msg;

    StateResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
