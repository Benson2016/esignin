package com.benson.esignin.web.controller;

import com.benson.esignin.common.cons.CommonCons;
import com.benson.esignin.common.utils.DateUtil;
import com.benson.esignin.common.utils.ExportExcelUtil;
import com.benson.esignin.common.utils.JsonUtil;
import com.benson.esignin.web.domain.entity.PermissionInfo;
import com.benson.esignin.web.domain.entity.RoleInfo;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.PermissionInfoQuery;
import com.benson.esignin.web.domain.vo.RoleInfoQuery;
import com.benson.esignin.web.domain.vo.UserInfoQuery;
import com.benson.esignin.web.service.*;
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
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台管理控制层
 *
 * @author: Benson Xu
 * @copyright BensonXu Copyright (c) 2016
 * @date: 2016年05月24日 23:11
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final static Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IRoleInfoService roleInfoService;
    @Autowired
    private IPermissionInfoService permissionInfoService;



    @RequestMapping(value = "toAdmin")
    public String toAdmin(Model model, HttpServletRequest request, HttpServletResponse response) {
        logger.info("enter to toAdmin Method.");

        logger.info("leave to toAdmin Method.");
        return "admin/index";
    }

    /**
     * 用户管理列表
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "mgrUser")
    public String mgrUser(Model model, HttpServletRequest request, HttpServletResponse response) {
        logger.info("enter to mgrUser Method.");
        try {
            List<UserInfo> userList = userInfoService.findAll();

            model.addAttribute("userList", userList);
        }catch (Exception e) {
            logger.error("查询用户列表异常：{}", e);
        } finally {
            logger.info("leave to mgrUser Method.");
        }

        return "admin/mgr_user";
    }

    @RequestMapping(value = "mgrRole")
    public String mgrRole(Model model, HttpServletRequest request, HttpServletResponse response) {
        logger.info("enter to mgrRole Method.");
        try {
            List<RoleInfo> dataList = roleInfoService.findAll();

            if (null == dataList) dataList = new ArrayList<RoleInfo>();

            model.addAttribute("dataList", dataList);

        }catch (Exception e) {
            logger.error("查询用户列表异常：{}", e);
        } finally {
            logger.info("leave to mgrRole Method.");
        }
        return "admin/mgr_role";
    }

    @RequestMapping(value = "mgrPermission")
    public String mgrPermission(Model model, HttpServletRequest request, HttpServletResponse response) {
        logger.info("enter to mgrPermission Method.");
        try {
            List<PermissionInfo> dataList = permissionInfoService.findAll();

            if (null == dataList) dataList = new ArrayList<PermissionInfo>();

            model.addAttribute("dataList", dataList);

        }catch (Exception e) {
            logger.error("查询用户列表异常：{}", e);
        } finally {
            logger.info("leave to mgrPermission Method.");
        }
        return "admin/mgr_permission";
    }

    @RequestMapping(value = "mgrMenu")
    public String mgrMenu(Model model, HttpServletRequest request, HttpServletResponse response) {
        logger.info("enter to mgrMenu Method.");

        logger.info("leave to mgrMenu Method.");
        return "admin/mgr_permission";
    }

    @RequestMapping(value = "welcome")
    public String welcome(Model model) {
        return "admin/welcome";
    }


    // 查询用户列表
    @RequestMapping(value = "/userListData",method = RequestMethod.POST)
    @ResponseBody
    public Object userListData(UserInfoQuery query) {
        logger.info("enter to userListData Method.");

        BensonPage<UserInfo> page = null;

        String result = null;
        try {
            page = userInfoService.findByPage(query);

            result = JsonUtil.bean2Json(page);
            logger.info(String.format("分页查询结果：" + result));

        }catch (Exception e) {
            logger.error("查询用户列表异常：{}", e);
        } finally {
            logger.info("leave to userListData Method.");
        }

        return result;
    }

    // 查询角色列表
    @RequestMapping(value = "/roleListData",method = RequestMethod.POST)
    @ResponseBody
    public Object roleListData(RoleInfoQuery query) {
        logger.info("enter to roleListData Method.");

        BensonPage<RoleInfo> page = null;
        String result = null;
        try {
            page = roleInfoService.findByPage(query);
            result = JsonUtil.bean2Json(page);
        }catch (Exception e) {
            logger.error("查询用户列表异常：{}", e);
        } finally {
            logger.info("leave to roleListData Method.");
        }
        return result;
    }

    // 查询权限列表
    @RequestMapping(value = "/permListData",method = RequestMethod.POST)
    @ResponseBody
    public Object permListData(PermissionInfoQuery query) {
        logger.info("enter to permListData Method.");

        BensonPage<PermissionInfo> page = null;
        String result = null;
        try {
            page = permissionInfoService.findByPage(query);
            result = JsonUtil.bean2Json(page);
        }catch (Exception e) {
            logger.error("查询用户列表异常：{}", e);
        } finally {
            logger.info("leave to permListData Method.");
        }
        return result;
    }



    /**
     * 导出数据到Excel文档中
     * @param exportType 导出类型：1.用户；2.角色；3.权限
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportDataToExcel",method = RequestMethod.POST)
    public void exportDataToExcel(@RequestParam Integer exportType, HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("\n当前导出类型："+exportType+"\n");

        List<? extends Serializable> records = null;
        Field[] fields = null;
        String[] headers = null;
        String fileName = "records.xls";


        try {

            // 根据导出类型导出业务相应数据：1.用户；2.角色；3.权限
            switch (exportType) {
                case 1:
                    records = userInfoService.findAll();
                    fields = new UserInfo().getClass().getDeclaredFields();
                    fileName = "user_records_" + DateUtil.getCurrDate(CommonCons.D_FMT_DATE_SEQ) + ".xls";
                    logger.info("即将导出所有用户信息数据。");
                    break;
                case 2:
                    records = roleInfoService.findAll();
                    fields = new RoleInfo().getClass().getDeclaredFields();
                    fileName = "role_records_" + DateUtil.getCurrDate(CommonCons.D_FMT_DATE_SEQ) + ".xls";
                    logger.info("即将导出所有角色信息数据。");
                    break;
                case 3:
                    records = permissionInfoService.findAll();
                    fields = new PermissionInfo().getClass().getDeclaredFields();
                    fileName = "permission_records_" + DateUtil.getCurrDate(CommonCons.D_FMT_DATE_SEQ) + ".xls";
                    logger.info("即将导出所有权限信息数据。");
                    break;
                default:
                    logger.info("【exportType="+ exportType +"】导出类型不存在！");
                    break;
            }

            logger.info("fields长度：" + fields.length);
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
