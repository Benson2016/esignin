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

    @RequestMapping(value = "mgrUser")
    public String mgrUser(Model model, HttpServletRequest request, HttpServletResponse response) {
        logger.info("enter to mgrUser Method.");

        logger.info("leave to mgrUser Method.");
        return "admin/mgr_user";
    }

    @RequestMapping(value = "mgrRole")
    public String mgrRole(Model model, HttpServletRequest request, HttpServletResponse response) {
        logger.info("enter to mgrRole Method.");

        logger.info("leave to mgrRole Method.");
        return "admin/mgr_role";
    }

    @RequestMapping(value = "mgrPermission")
    public String mgrPermission(Model model, HttpServletRequest request, HttpServletResponse response) {
        logger.info("enter to mgrPermission Method.");

        logger.info("leave to mgrPermission Method.");
        return "admin/mgr_permission";
    }

    @RequestMapping(value = "mgrMenu")
    public String mgrMenu(Model model, HttpServletRequest request, HttpServletResponse response) {
        logger.info("enter to mgrMenu Method.");

        logger.info("leave to mgrMenu Method.");
        return "admin/mgr_permission";
    }




}
