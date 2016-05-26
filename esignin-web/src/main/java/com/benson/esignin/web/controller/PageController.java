package com.benson.esignin.web.controller;

import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.domain.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
            return "app/index";
    }

    /**
     * dashboard页
     */
    @RequestMapping("/dashboard")
    public String dashboard() {
        return "app/dashboard";
    }

    /**
     * success页
     */
    @RequestMapping("/success")
    public String success() {
        return "success";
    }


}
