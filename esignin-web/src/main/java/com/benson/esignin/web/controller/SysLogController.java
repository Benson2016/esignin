package com.benson.esignin.web.controller;

import com.benson.esignin.common.cons.CommonCons;
import com.benson.esignin.common.enums.StateResponse;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.common.utils.DateUtil;
import com.benson.esignin.common.utils.ExportExcelUtil;
import com.benson.esignin.common.utils.JsonUtil;
import com.benson.esignin.web.annotation.SysControllerLog;
import com.benson.esignin.web.domain.entity.SysExceptionLog;
import com.benson.esignin.web.domain.entity.SysLog;
import com.benson.esignin.web.domain.vo.*;
import com.benson.esignin.web.service.ISysExceptionLogService;
import com.benson.esignin.web.service.ISysLogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 系统日志控制层
 * 包括系统日志、异常日志
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月06日 15:33
 */
@Controller
@RequestMapping("/log")
public class SysLogController {

    private final static Logger logger = Logger.getLogger(SysLogController.class);
    
    @Autowired
    private ISysLogService sysLogService;
    @Autowired
    private ISysExceptionLogService sysExceptionLogService;


    // 系统日志管理页
    @RequestMapping(value = "/mgrSysLog")
    public String mgrSysLog(Model model, HttpServletRequest request, HttpServletResponse response) {
        return "admin/mgr_sys_log";
    }

    // 系统异常日志管理页
    @RequestMapping(value = "/mgrSysExceptionLog")
    public String mgrSysExceptionLog(Model model, HttpServletRequest request, HttpServletResponse response) {
        return "admin/mgr_sys_exception_log";
    }

    @RequestMapping(value = "/sysLogListData",method = RequestMethod.POST)
    @ResponseBody
    public Object sysLogListData(SysLogQuery query) {
        logger.info("enter to sysLogListData Method.");

        BensonPage<SysLog> page = null;
        String result = null;
        try {
            page = sysLogService.findByPage(query);
            result = JsonUtil.bean2Json(page);
        }catch (Exception e) {
            logger.error("查询系统日志记录列表异常：{}", e);
        } finally {
            logger.info("leave to sysLogListData Method.");
        }
        return result;
    }

    @RequestMapping(value = "/sysExceptionLogListData",method = RequestMethod.POST)
    @ResponseBody
    public Object sysExceptionLogListData(SysLogQuery query) {
        logger.info("enter to sysExceptionLogListData Method.");

        BensonPage<SysExceptionLog> page = null;
        String result = null;
        try {
            page = sysExceptionLogService.findByPage(query);
            result = JsonUtil.bean2Json(page);
        }catch (Exception e) {
            logger.error("查询系统异常日志记录列表异常：{}", e);
        } finally {
            logger.info("leave to sysExceptionLogListData Method.");
        }
        return result;
    }

    /**
     * 删除系统日志记录
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delSysLog",method = RequestMethod.POST)
    @SysControllerLog(content = "删除系统日志信息.")
    @ResponseBody
    public Object delSysLog(@RequestParam String ids) {
        SysLogResponse response = null;
        if (CommonUtil.isNull(ids)) {
            response = new SysLogResponse(StateResponse.ERROR_PARAM);
            return JsonUtil.toJson(response);
        }

        try {
            // 删除给定id的记录
            int result = sysLogService.deleteByIds(ids);
            logger.info(String.format("===》》》系统日志删除操作，本次删除 %d条记录。", result));

            response = new SysLogResponse(StateResponse.SUCCESS);
            response.setRspMsg("删除成功！");
        } catch (Exception e) {
            logger.error("手动删除系统日志记录失败！异常：{}", e);
            response = new SysLogResponse(StateResponse.ERROR_SYS);
        }

        return JsonUtil.toJson(response);
    }

    /**
     * 删除系统异常日志记录
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delSysExceptionLog",method = RequestMethod.POST)
    @SysControllerLog(content = "删除系统异常日志信息.")
    @ResponseBody
    public Object delSysExceptionLog(@RequestParam String ids) {
        SysLogResponse response = null;
        if (CommonUtil.isNull(ids)) {
            response = new SysLogResponse(StateResponse.ERROR_PARAM);
            return JsonUtil.toJson(response);
        }

        try {
            // 删除给定id的记录
            int result = sysExceptionLogService.deleteByIds(ids);
            logger.info(String.format("===》》》系统异常日志删除操作，本次删除 %d条记录。", result));

            response = new SysLogResponse(StateResponse.SUCCESS);
            response.setRspMsg("删除成功！");
        } catch (Exception e) {
            logger.error("手动删除系统异常日志记录失败！异常：{}", e);
            response = new SysLogResponse(StateResponse.ERROR_SYS);
        }

        return JsonUtil.toJson(response);
    }


    // 导出系统日志
    @RequestMapping(value = "/exportSysLogData",method = RequestMethod.POST)
    @SysControllerLog(content = "导出系统日志信息.")
    public void exportSysLogData(SysLogQuery query, HttpServletRequest request, HttpServletResponse response) {

        List<? extends Serializable> records = null;
        Field[] fields = null;
        String[] headers = null;
        String fileName = "records.xls";
        try {
            // 根据日志类型导出相应日志
            if (1 == query.getLogType()) {
                records = sysLogService.findAllByQuery(query);
                fields = new SysLog().getClass().getDeclaredFields();
                // 设置Excel文件名称
                fileName = "syslog_records_" + DateUtil.getCurrDate(CommonCons.D_FMT_DATE_SEQ) + ".xls";
                logger.info("即将导出系统操作日志数据。");
            } else if (2 == query.getLogType()) {
                records = sysExceptionLogService.findAllByQuery(query);
                fields = new SysExceptionLog().getClass().getDeclaredFields();
                // 设置Excel文件名称
                fileName = "exception_log_records_" + DateUtil.getCurrDate(CommonCons.D_FMT_DATE_SEQ) + ".xls";
                logger.info("即将导出系统异常日志数据。");
            }

            // 设置列头文字
            if (null != fields) {
                headers = new String[fields.length];
                for (int i=0; i<fields.length; i++) {
                    headers[i] = fields[i].getName();
                }
            }

            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);// 设定输出文件头
            response.setContentType("application/msexcel");// 定义输出类型
            ExportExcelUtil.exportExcel("二维码信息", headers, records, response.getOutputStream(), CommonCons.D_FMT_NORMAL);

        } catch (IOException e) {
            logger.error("导出数据IO异常：{}", e);
        } catch (Exception e) {
            logger.error("导出数据异常：{}", e);
        } finally {
            logger.info("数据导出完毕！文件名：" + fileName);
        }
    }

    // more methods...

}
