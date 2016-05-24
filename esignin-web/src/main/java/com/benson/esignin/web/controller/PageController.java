package com.benson.esignin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String login(Model model) {
        model.addAttribute("operType", "1");
        model.addAttribute("rspMsg", "");
        return "app/login";
    }

    /**
     * 后台首页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {

        return "app/index";
    }

    /**
     * dashboard页
     */
    @RequestMapping("/dashboard")
    public String dashboard() {
        return "app/dashboard";
    }


}
