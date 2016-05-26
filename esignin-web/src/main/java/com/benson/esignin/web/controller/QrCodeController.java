package com.benson.esignin.web.controller;

import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.common.utils.DataUtil;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.service.IUserInfoService;
import com.benson.esignin.web.utils.QRCodeUtil;
import com.google.gson.JsonArray;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private IUserInfoService userInfoService;


    /**
     * 获取二维码
     *
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
    @ResponseBody
    public Object loginByQR(@RequestParam String av, @RequestParam String un, @RequestParam String up,
                            HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> data = new HashMap<String, String>();

        try {
            logger.info("Enter loginByQR method.");
            logger.info(String.format("本次登陆校验记录:av[%s],un[%s],up[%s]", av, un, up));

            //验证用户信息
            UserInfo loginUser = userInfoService.authentication(new UserInfo(un, up));
            if (CommonUtil.isNull(loginUser)) {
                // 根据用户序列去查询
                loginUser = userInfoService.findByUserSerial(av);
            }

            if (CommonUtil.isNull(loginUser)) {
                // 用户不存在
                data.put("rspCode", "101");
                data.put("rspMsg", "用户尚未注册！");
                return data;
            }

            // 验证成功在Session中保存用户信息
            request.getSession().setAttribute("userInfo", loginUser);
            data.put("rspCode", "100");
            data.put("rspMsg", "登录成功！");
            data.put("un", loginUser.getUserName());
            data.put("up", loginUser.getPassword());
        } catch (Exception e) {
            data.put("rspCode", "999");
            data.put("rspMsg", "QR登录异常！");
            logger.error("QR登录异常：{}", e);
        } finally {
            logger.info("Leave loginByQR method.");
        }

        return data;
    }

}
