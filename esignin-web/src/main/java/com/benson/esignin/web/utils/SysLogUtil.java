package com.benson.esignin.web.utils;

import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.common.utils.DateUtil;
import com.benson.esignin.common.utils.SpringUtil;
import com.benson.esignin.common.utils.StringUtil;
import com.benson.esignin.web.domain.entity.SysExceptionLog;
import com.benson.esignin.web.domain.entity.SysLog;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.service.ISysExceptionLogService;
import com.benson.esignin.web.service.ISysLogService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

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
     * 添加操作日志
     * @param log
     */
    public static void addSysLog(SysLog log) {
        try {
            if (CommonUtil.isNull(log.getId()))
                log.generateUUId(); // 如果ID字段值为空，则生成ID
            /*int result = */getSysLogService().add(log);
            //logger.info("===》》》本次添加操作日志到DB的记录数：" + result);
        } catch (Exception e) {
            logger.error("添加系统日志时发生异常：", e);
        }
    }

    /**
     * 添加异常日志
     * @param log
     */
    public static void addSysExceptionLog(SysExceptionLog log) {
        try {
            if (CommonUtil.isNull(log.getId()))
                log.generateUUId(); // 如果ID字段值为空，则生成ID
            /*int result = */getSysExceptionLogService().add(log);
            //logger.info("===》》》本次添加异常日志到DB的记录数：" + result);
        } catch (Exception e) {
            logger.error("添加系统异常日志时发生异常：", e);
        }
    }


    /**
     * 将异常信息转化成字符串
     * @param t
     * @return
     */
    public static String getExceptionMsg(Throwable t) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            t.printStackTrace(new PrintStream(baos));
        } finally{
            try {
                baos.close();
            } catch (IOException e) {
            }
        }
        return baos.toString();
    }

    /**
     * 获取部分异常信息
     * @param content 异常信息
     * @param rows 需要截取的行数
     * @return
     */
    public static String getPartForException(String content, int rows) {
        if (!StringUtil.isNullString(content)) {
            // 关于异常信息,只取前面十行数据
            String[] contents = content.split("\r\n");
            String[] newArray = Arrays.copyOf(contents, rows);
            content = Arrays.toString(newArray);
        }
        return content;
    }


}
