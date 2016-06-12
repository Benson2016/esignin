package com.benson.esignin.web.domain.entity;

import com.benson.esignin.common.cons.CommonCons;
import com.benson.esignin.common.entity.IdEntity;
import com.benson.esignin.common.utils.DateUtil;

import java.util.Date;

/**
 * 系统异常日志
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月06日 14:32
 */
public class SysExceptionLog extends IdEntity {

    private String ip;		    // IP地址
    private String userName;    // 用户名称
    private String moduleName;	// 模块名称
    private String operContent;	// 操作内容
    private String exception;	// 异常信息
    private Date operTime;	    // 操作的时间
    private String consumeTime;	// 消耗时间（毫秒单位）

    public SysExceptionLog() {}

    /**
     * 创建系统异常日志
     * @param ip IP地址
     * @param userName 用户名称
     * @param moduleName 模块名称
     * @param operContent 操作内容
     * @param exception 异常信息
     * @param operTime 操作的时间
     * @param consumeTime 消耗时间
     */
    public SysExceptionLog(String ip, String userName, String moduleName, String operContent, String exception, Date operTime, String consumeTime) {
        this.ip = ip;
        this.userName = userName;
        this.moduleName = moduleName;
        this.operContent = operContent;
        this.exception = exception;
        this.operTime = operTime;
        this.consumeTime = consumeTime;
    }

    /**
     * 获取操作时间
     * @return 字符串格式
     */
    public String getOperTimeStr() {
        return DateUtil.converToString(this.operTime, CommonCons.D_FMT_NORMAL);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getOperContent() {
        return operContent;
    }

    public void setOperContent(String operContent) {
        this.operContent = operContent;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public String getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(String consumeTime) {
        this.consumeTime = consumeTime;
    }

}
