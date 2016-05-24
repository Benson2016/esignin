package com.benson.esignin.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {enter your description}
 *
 * @author: Benson Xu
 * @date: 2016年05月24日 23:11
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final static Logger logger = Logger.getLogger(AdminController.class);

    @RequestMapping(value = "toAdmin")
    public String toAdmin(Model model, HttpServletRequest request, HttpServletResponse response) {
        logger.info("enter to toAdmin Method.");

        logger.info("leave to toAdmin Method.");
        return "admin/index";
    }


}
