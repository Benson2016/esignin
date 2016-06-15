package com.benson.esignin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 统计业务控制层
 *
 * @author: Benson Xu
 * @date: 2016年06月16日 00:51
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {


    /**
     * 统计用户
     * @param model
     * @return
     */
    @RequestMapping(value = "/user")
    public String user(Model model) {

        return "admin/statistics_user";
    }


    /**
     * 统计签到
     * @param model
     * @return
     */
    @RequestMapping(value = "/signIn")
    public String signIn(Model model) {

        return "admin/statistics_signin";
    }

}
