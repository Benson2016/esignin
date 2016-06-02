package com.benson.esignin.web.controller;

import com.benson.esignin.common.cons.CommonCons;
import com.benson.esignin.common.cons.SysCons;
import com.benson.esignin.common.enums.StateResponse;
import com.benson.esignin.common.utils.*;
import com.benson.esignin.web.domain.entity.PermissionInfo;
import com.benson.esignin.web.domain.entity.QrCode;
import com.benson.esignin.web.domain.entity.RoleInfo;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.domain.vo.QrCodeQuery;
import com.benson.esignin.web.domain.vo.QrCodeResponse;
import com.benson.esignin.web.domain.vo.UserInfoResponse;
import com.benson.esignin.web.service.IQrCodeService;
import com.benson.esignin.web.service.IUserInfoService;
import com.benson.esignin.web.utils.QRCodeUtil;
import com.google.gson.JsonArray;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 二维码控制类
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 深圳市华阳信通科技发展有限公司 Copyright (c) 2016
 * @since 2016年05月26日 19:27
 */
@Controller
@RequestMapping("/code")
public class QrCodeController {

    private final static Logger logger = Logger.getLogger(QrCodeController.class);

    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IQrCodeService qrCodeService;


    /**
     * 显示二维码页面
     * @param model
     * @param businessId
     * @return
     */
    @RequestMapping(value = "/showCode")
    public String showCode(Model model, @RequestParam String businessId) {

        if (CommonUtil.isNull(businessId)) {
            logger.error("业务Id为空,无法正常显示二维码!");
        }

        model.addAttribute(SysCons.BUSINESS_ID, businessId);

        return "scan";
    }

    /**
     * 获取二维码
     * @param businessId 业务ID
     * @param response
     */
    @RequestMapping(value = "/getQrCode")
    public void getQrCode(@RequestParam String businessId, HttpServletRequest request, HttpServletResponse response) {
        logger.info("getQrCode Start......");
        try {
            if (CommonUtil.isNull(businessId)) {
                logger.error("业务Id为空,无法正常获取二维码!");
                return;
            }

            // 查询业务二维码
            QrCode qrCode = qrCodeService.findOne(businessId);

            if (CommonUtil.isNotNull(qrCode)) {
                QRCodeUtil.generate(qrCode.getImage(), 300, 300, response);
            } else {
                logger.info(String.format("没有查找到业务ID为[%s]的二维码!", businessId));
                outputNotFileImg(request, response);
            }

        } catch (Exception e) {
            logger.error("获取二维码时发生异常: ", e);
        } finally {
            logger.info("The End Of getQrCode.");
        }
    }

    /**
     * 打印输出404图片
     * @param response
     * @throws IOException
     */
    private void outputNotFileImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取工程访问对应的绝对路径
        String realPath = request.getServletContext().getRealPath("/");

        // 输出404图片
        String notFoundImg = realPath + "commons/img/404.jpg";
        logger.info("获取404图片路径为:" + notFoundImg);

        response.setContentType("text/html; charset=UTF-8");
        response.setContentType("image/jpeg");

        FileInputStream fis = new FileInputStream(notFoundImg);
        OutputStream os = response.getOutputStream();

        try {
            // 读取图片并输出
            byte[] buffer = new byte[fis.available()];
            int len = 0;
            while ((len=fis.read(buffer))!=-1) {
                os.write(buffer, 0, len);
                os.flush();
            }
        } catch (IOException ioe) {
            logger.error("读取404图片异常:", ioe);
        } finally {
            if (null != os) os.close();
            if (null != fis) fis.close();
        }
    }


    /**
     * 构建二维码记录
     * @param response
     */
    @RequestMapping(value = "/createCode")
    public void createCode(HttpServletResponse response) {
        logger.info("createCode Start......");
        try {
            QrCode qrCode = new QrCode();
            qrCode.generateUUId();
            qrCode.setCreateUser("88888888");
            qrCode.setTitle("一起嗨皮");
            qrCode.setDescription("一起嗨皮,一起玩耍!");
            qrCode.setSignInType(4);
            qrCode.setIsValid(1);
            // 设置5分钟有效时间
            qrCode.setEffectiveTimeStart(DateUtil.getCurrentDateTime());
            qrCode.setEffectiveTimeEnd(DateUtil.addMinuteToDate(qrCode.getEffectiveTimeStart(), 5));
            // 设置图片内容
            String content = "http://192.168.31.135:8080/esignin/page/handler.bs?" + SysCons.BUSINESS_ID + "=" + qrCode.getId();
            qrCode.setImage(content);
            // 保存QR记录
            qrCodeService.add(qrCode);

            // 打印提示信息
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print("业务二维码构建成功! 业务ID为" + qrCode.getId());

        } catch (Exception e) {
            logger.error("获取二维码时发生异常: ", e);
        } finally {
            logger.info("The End Of createCode.");
        }
    }

    /**
     * 扫二维码登录
     * @param un
     * @param up
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginByQR")
    @ResponseBody
    public Object loginByQR(@RequestParam String un, @RequestParam String up, HttpServletRequest request) {
        QrCodeResponse response = null;

        try {
            logger.info("Enter loginByQR method.");
            logger.info(String.format("QR登录--校验记录:un[%s],up[%s]", un, up));

            // 参数校验
            if (CommonUtil.isNull(un, up)) {
                response = new QrCodeResponse(StateResponse.ERROR_PARAM);
                return JsonUtil.toJson(response);
            }

            //验证用户信息
            UserInfo loginUser = userInfoService.authentication(new UserInfo(un, up));

            // 用户不存在
            if (CommonUtil.isNull(loginUser)) {
                response = new QrCodeResponse(StateResponse.INVALID);
                response.setRspMsg("用户尚未注册！");
                return JsonUtil.toJson(response);
            }

            // 验证成功在Session中保存用户信息
            request.getSession().setAttribute(SysCons.LOGIN_USER, loginUser);
            // 返回消息提示
            response = new QrCodeResponse(StateResponse.SUCCESS);
            response.setUn(loginUser.getUserName());
            response.setUp(loginUser.getPassword());

        } catch (Exception e) {
            response = new QrCodeResponse(StateResponse.ERROR_SYS);
            logger.error("QR登录异常：{}", e);
        } finally {
            logger.info("Leave loginByQR method.");
        }

        return JsonUtil.toJson(response);
    }

    /**
     * 删除QrCode
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delQrCode",method = RequestMethod.POST)
    @ResponseBody
    public Object delQrCode(@RequestParam String ids) {
        UserInfoResponse response = null;
        if (CommonUtil.isNull(ids)) {
            response = new UserInfoResponse(StateResponse.ERROR_PARAM);
            return JsonUtil.toJson(response);
        }

        try {
            // 删除给定id的记录
            String[] idArray = ids.split(",");
            int result = qrCodeService.deleteByIds(ids);
            logger.info(String.format("===》》》二维码删除操作，预期删除 %d条记录，实际删除 %d条记录。", idArray.length, result));

            response = new UserInfoResponse(StateResponse.SUCCESS);
            response.setRspMsg("删除成功！");
        } catch (Exception e) {
            logger.error("手动删除角色记录失败！异常：{}", e);
            response = new UserInfoResponse(StateResponse.ERROR_SYS);
        }

        return JsonUtil.toJson(response);
    }

    // 导出QrCode数据
    @RequestMapping(value = "/exportQrCodeData",method = RequestMethod.POST)
    public void exportQrCodeData(QrCodeQuery query, HttpServletRequest request, HttpServletResponse response) {

        List<? extends Serializable> records = null;
        Field[] fields = null;
        String[] headers = null;
        String fileName = "records.xls";
        try {
            records = qrCodeService.findAllByQuery(query);
            fields = new QrCode().getClass().getDeclaredFields();
            // 设置Excel文件名称
            fileName = "qrcode_records_" + DateUtil.getCurrDate(CommonCons.D_FMT_DATE_SEQ) + ".xls";
            logger.info("即将导出QrCede信息数据。");
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
            ExportExcelUtil.exportExcel("补偿记录", headers, records, response.getOutputStream(), CommonCons.D_FMT_NORMAL);

        } catch (IOException e) {
            logger.error("导出数据IO异常：{}", e);
        } catch (Exception e) {
            logger.error("导出数据异常：{}", e);
        } finally {
            logger.info("数据导出完毕！文件名：" + fileName);
        }
    }


}
