package com.benson.esignin.web.controller;

import com.benson.esignin.common.cons.SysCons;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.domain.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 页面控制类
 *
 * @author Benson Xu
 * @date 2016年05月10日 18:28
 */
@Controller
@RequestMapping("/page")
public class PageController {

    private final static Logger logger = LoggerFactory.getLogger(PageController.class);


    /**
     * 登录页
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 后台首页
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpSession session) {
        UserInfo authUserInfo = (UserInfo) session.getAttribute("userInfo");
        if (CommonUtil.isNull(authUserInfo))
            return "login";
        else
            return "admin/index";
    }

    /**
     * dashboard页
     */
    @RequestMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    /**
     * 扫码注册页
     */
    @RequestMapping("/register")
    public String register() {
        return "app/register";
    }

    /**
     * 手机验证页面
     */
    @RequestMapping("/mobileVerify")
    public String mobileVerify() {
        return "app/mobile_verify";
    }

    /**
     * success页
     */
    @RequestMapping("/success")
    public String success() {
        return "success";
    }

    /**
     * 去二维码编辑页面
     */
    @RequestMapping("/toQrEdit")
    public String toQrEdit() {
        return "admin/qrcode_edit";
    }


    @RequestMapping("/handler")
    public String handler(@RequestParam String businessId, HttpServletRequest request) {

        if(CommonUtil.isNull(businessId)) {
            logger.error("扫码一进来,发现业务ID为空!!!");
        } else {
            // 扫码一进来先存储业务ID
            request.getSession().setAttribute(SysCons.BUSINESS_ID, businessId);
            logger.info(String.format("扫码一进来先存储业务ID[%s]", businessId));
        }

        return "scan_handler";
    }


}
