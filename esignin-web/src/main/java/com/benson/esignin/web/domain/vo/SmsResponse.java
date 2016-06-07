package com.benson.esignin.web.domain.vo;

/**
 * 短信响应类
 *
 * @author: Benson Xu
 * @date: 2016年06月07日 22:09
 */
public class SmsResponse {

    /*
    * 示例: {"returnstatus":"Success","message":"ok","remainpoint":"-2488035","taskID":"5130819","successCounts":"1"}
    * */

    private String returnstatus;    // 返回状态值：成功返回Success 失败返回：Faild

    private String message;         // 返回信息

    private String remainpoint;     // 运营商结算无意义，可不用解析

    private String taskID;          // 返回本次任务的序列ID

    private String successCounts;   // 返回成功短信数


    public String getReturnstatus() {
        return returnstatus;
    }

    public void setReturnstatus(String returnstatus) {
        this.returnstatus = returnstatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRemainpoint() {
        return remainpoint;
    }

    public void setRemainpoint(String remainpoint) {
        this.remainpoint = remainpoint;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getSuccessCounts() {
        return successCounts;
    }

    public void setSuccessCounts(String successCounts) {
        this.successCounts = successCounts;
    }

}
