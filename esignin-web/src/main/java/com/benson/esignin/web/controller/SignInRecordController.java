package com.benson.esignin.web.controller;

import com.benson.esignin.common.cons.CommonCons;
import com.benson.esignin.common.enums.StateResponse;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.common.utils.DateUtil;
import com.benson.esignin.common.utils.ExportExcelUtil;
import com.benson.esignin.common.utils.JsonUtil;
import com.benson.esignin.web.domain.entity.QrCode;
import com.benson.esignin.web.domain.entity.SignInRecord;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.QrCodeQuery;
import com.benson.esignin.web.domain.vo.SignInRecordQuery;
import com.benson.esignin.web.domain.vo.UserInfoResponse;
import com.benson.esignin.web.service.ISignInRecordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * 签到记录控制层业务类
 *
 * @author: Benson Xu
 * @date: 2016年06月05日 09:11
 */
@Controller
@RequestMapping("record")
public class SignInRecordController {

    private final static Logger logger = Logger.getLogger(SignInRecordController.class);

    @Autowired
    private ISignInRecordService signInRecordService;


    @RequestMapping(value = "/recordListData",method = RequestMethod.POST)
    @ResponseBody
    public Object recordListData(SignInRecordQuery query) {
        logger.info("enter to recordListData Method.");

        BensonPage<SignInRecord> page = null;
        String result = null;
        try {
            page = signInRecordService.findByPage(query);
            result = JsonUtil.bean2Json(page);
        }catch (Exception e) {
            logger.error("查询签到记录列表异常：{}", e);
        } finally {
            logger.info("leave to recordListData Method.");
        }
        return result;
    }


    /**
     * 删除记录
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delRecord",method = RequestMethod.POST)
    @ResponseBody
    public Object delRecord(@RequestParam String ids) {
        UserInfoResponse response = null;
        if (CommonUtil.isNull(ids)) {
            response = new UserInfoResponse(StateResponse.ERROR_PARAM);
            return JsonUtil.toJson(response);
        }

        try {
            // 删除给定id的记录
            int result = signInRecordService.deleteByIds(ids);
            logger.info(String.format("===》》》签到记录删除操作，此次删除%d条记录。", result));

            response = new UserInfoResponse(StateResponse.SUCCESS);
            response.setRspMsg("删除成功！");
        } catch (Exception e) {
            logger.error("手动签到记录失败！异常：{}", e);
            response = new UserInfoResponse(StateResponse.ERROR_SYS);
        }

        return JsonUtil.toJson(response);
    }


    @RequestMapping(value = "/exportRecordData",method = RequestMethod.POST)
    public void exportRecordData(SignInRecordQuery query, HttpServletRequest request, HttpServletResponse response) {

        List<? extends Serializable> records = null;
        String[] headers = new String[]{"二维码ID", "签到用户ID", "签到用户手机", "签到时间", "有效标识"};
        String fileName = "records.xls";
        try {
            records = signInRecordService.findAllByQuery(query);
            // 设置Excel文件名称
            fileName = "sign_in_records_" + DateUtil.getCurrDate(CommonCons.D_FMT_DATE_SEQ) + ".xls";
            logger.info("即将导出签到记录信息数据。");

            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);// 设定输出文件头
            response.setContentType("application/msexcel");// 定义输出类型
            ExportExcelUtil.exportExcel("签到记录列表", headers, records, response.getOutputStream(), CommonCons.D_FMT_NORMAL);

        } catch (IOException e) {
            logger.error("导出数据IO异常：{}", e);
        } catch (Exception e) {
            logger.error("导出数据异常：{}", e);
        } finally {
            logger.info("数据导出完毕！文件名：" + fileName);
        }
    }


}
