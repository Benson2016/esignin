package com.benson.esignin.web.controller;

import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.common.utils.DataUtil;
import com.benson.esignin.common.utils.JsonUtil;
import com.benson.esignin.web.utils.QRCodeUtil;
import com.google.gson.JsonObject;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
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


    /**
     * 获取二维码
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getQrCode")
    public String getQrCode(Model model, HttpServletRequest request, HttpServletResponse response) {



        String loginUrl = "http://xubstest.ematong.com/esignin/index.jsp";

        String realPath = request.getSession().getServletContext().getRealPath("");

        /*logger.info("当前realPath：" + realPath);

        String rootPath = request.getContextPath();//获取webapp目录

        String imgPath = rootPath + "/upload/images/loginByQR.png";*/

        String savePath = "D:/temp/images/loginByQR.png";

        QRCodeUtil.encode(loginUrl, 300, 300, savePath);

        model.addAttribute("qrImg", savePath);

        return "qrcode";
    }

    @RequestMapping(value = "/loginByQR")
    public void loginByQR(@RequestParam String appVersion, HttpServletRequest request, HttpServletResponse response) {

        logger.info(appVersion + "\n");

        /*logger.info("Enter loginByQR method.");
        try {

        } catch (Exception e) {
            logger.error("QR登录异常：{}", e);
        } finally {
            logger.info("Leave loginByQR method.");
        }*/
        //return "login";

        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("resCode", "100");
            map.put("resMsg", "登录成功！");
            DataUtil.writeToJson(response, map);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
