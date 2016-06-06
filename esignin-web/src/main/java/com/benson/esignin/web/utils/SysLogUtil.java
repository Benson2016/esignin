package com.benson.esignin.web.utils;

import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.common.utils.DateUtil;
import com.benson.esignin.common.utils.SpringUtil;
import com.benson.esignin.web.domain.entity.SysExceptionLog;
import com.benson.esignin.web.domain.entity.SysLog;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.service.ISysExceptionLogService;
import com.benson.esignin.web.service.ISysLogService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统日志工具类
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月06日 18:11
 */
public class SysLogUtil {

    private final static Logger logger = Logger.getLogger(SysLogUtil.class);

    // 系统日志接口
    private static ISysLogService sysLogService;
    private static ISysExceptionLogService sysExceptionLogService;

    /**
     * 获取系统日志接口
     * @return
     */
    public static ISysLogService getSysLogService() {
        if(null == sysLogService) {
            sysLogService = (ISysLogService) SpringUtil.getBean("sysLogService");
            logger.info("首次获取系统日志接口 sysLogService");
        }
        return sysLogService;
    }

    /**
     * 获取系统异常日志接口
     * @return
     */
    public static ISysExceptionLogService getSysExceptionLogService() {
        if(null == sysExceptionLogService) {
            sysExceptionLogService = (ISysExceptionLogService) SpringUtil.getBean("sysExceptionLogService");
            logger.info("首次获取系统异常日志接口 sysExceptionLogService");
        }
        return sysExceptionLogService;
    }

    /**
     * 添加系统日志
     * @param log
     */
    public static void addSysLog(SysLog log) {
        try {
            if (CommonUtil.isNull(log.getId()))
                log.generateUUId(); // 如果ID字段值为空，则生成ID
            getSysLogService().add(log);
        } catch (Exception e) {
            logger.error("添加系统日志时发生异常：", e);
        }
    }

    /**
     * 添加系统日志
     * @param log
     */
    public static void addSysExceptionLog(SysExceptionLog log) {
        try {
            if (CommonUtil.isNull(log.getId()))
                log.generateUUId(); // 如果ID字段值为空，则生成ID
            getSysExceptionLogService().add(log);
        } catch (Exception e) {
            logger.error("添加系统异常日志时发生异常：", e);
        }
    }

    /**
     * 添加登录日志
     * @param loginUser
     * @param request
     */
    public static void addLoginLog(UserInfo loginUser, HttpServletRequest request) {
        String ip = "192.168.0.127";
        SysLog log = new SysLog(ip, loginUser.getUserName(), "login", "用户登录系统。", DateUtil.getCurrentDateTime(), "100");
        addSysLog(log);
    }

}
