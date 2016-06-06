package com.benson.esignin.web.domain.entity;

import com.benson.esignin.common.cons.CommonCons;
import com.benson.esignin.common.entity.IdEntity;
import com.benson.esignin.common.utils.DateUtil;

import java.util.Date;

/**
 * 系统日志信息
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月06日 11:17
 */
public class SysLog extends IdEntity {

    private String ip;		    // IP地址
    private String userName;    // 用户名称
    private String moduleName;	// 模块名称
    private String operContent;	// 操作内容
    private Date operTime;	    // 操作的时间
    private String consumeTime;	// 消耗时间（毫秒单位）

    public SysLog() {}

    /**
     * 创建系统日志记录
     * @param ip IP地址
     * @param userName 用户名称
     * @param moduleName 模块名称
     * @param operContent 操作内容
     * @param operTime 操作的时间
     * @param consumeTime 消耗时间
     */
    public SysLog(String ip, String userName, String moduleName, String operContent, Date operTime, String consumeTime) {
        this.ip = ip;
        this.moduleName = moduleName;
        this.operContent = operContent;
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
