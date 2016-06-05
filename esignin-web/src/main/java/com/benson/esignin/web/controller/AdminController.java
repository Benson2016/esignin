package com.benson.esignin.web.controller;

import com.benson.esignin.common.cons.CommonCons;
import com.benson.esignin.common.utils.DateUtil;
import com.benson.esignin.common.utils.ExportExcelUtil;
import com.benson.esignin.common.utils.JsonUtil;
import com.benson.esignin.web.domain.entity.*;
import com.benson.esignin.web.domain.vo.*;
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
    @Autowired
    private IQrCodeService qrCodeService;
    @Autowired
    private ISignInTypeService signInTypeService;

    // 后台管理页
    @RequestMapping(value = "toAdmin")
    public String toAdmin(Model model, HttpServletRequest request, HttpServletResponse response) {
        /*logger.info("enter to toAdmin Method.");

        logger.info("leave to toAdmin Method.");*/
        return "admin/index";
    }
    // 用户管理页
    @RequestMapping(value = "mgrUser")
    public String mgrUser(Model model, HttpServletRequest request, HttpServletResponse response) {
        return "admin/mgr_user";
    }
    // 角色管理页
    @RequestMapping(value = "mgrRole")
    public String mgrRole(Model model, HttpServletRequest request, HttpServletResponse response) {
        return "admin/mgr_role";
    }
    // 权限管理页
    @RequestMapping(value = "mgrPermission")
    public String mgrPermission(Model model, HttpServletRequest request, HttpServletResponse response) {
        return "admin/mgr_permission";
    }
    // 菜单管理页
    @RequestMapping(value = "mgrMenu")
    public String mgrMenu(Model model, HttpServletRequest request, HttpServletResponse response) {
        logger.info("enter to mgrMenu Method.");

        logger.info("leave to mgrMenu Method.");
        return "admin/mgr_permission";
    }
    // QrCode管理页
    @RequestMapping(value = "mgrQrCode")
    public String mgrQrCode(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 获取所有业务类型
            List<SignInType> signInTypeList = signInTypeService.findAll();

            model.addAttribute("signInTypeList", signInTypeList);
            String json = JsonUtil.bean2Json(signInTypeList);
            model.addAttribute("signInTypeData", json);
        } catch (Exception e) {
            logger.error("获取签到类型列表异常：{}", e);
        }

        return "admin/mgr_qrcode";
    }

    // 签到类别管理
    @RequestMapping(value = "mgrSignInType")
    public String mgrSignInType(Model model) {

        return "admin/mgr_signin_type";
    }

    // 签到记录管理
    @RequestMapping(value = "mgrSignInRecord")
    public String mgrSignInRecord(Model model) {

        return "admin/mgr_signin_record";
    }

    // 后台欢迎页
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

    // 查询权限列表
    @RequestMapping(value = "/qrCodeListData",method = RequestMethod.POST)
    @ResponseBody
    public Object qrCodeListData(QrCodeQuery query) {
        logger.info("enter to qrCodeListData Method.");

        BensonPage<QrCode> page = null;
        String result = null;
        try {
            page = qrCodeService.findByPage(query);
            result = JsonUtil.bean2Json(page);
        }catch (Exception e) {
            logger.error("查询二维码表异常：{}", e);
        } finally {
            logger.info("leave to qrCodeListData Method.");
        }
        return result;
    }

    @RequestMapping(value = "/typeListData",method = RequestMethod.POST)
    @ResponseBody
    public Object typeListData(SignInTypeQuery query) {
        logger.info("enter to typeListData Method.");

        BensonPage<SignInType> page = null;
        String result = null;
        try {
            page = signInTypeService.findByPage(query);
            result = JsonUtil.bean2Json(page);
        }catch (Exception e) {
            logger.error("查询签到类型列表异常：{}", e);
        } finally {
            logger.info("leave to typeListData Method.");
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
        String sheelName = "信息列表";
        try {
            // 根据导出类型导出业务相应数据：1.用户；2.角色；3.权限
            switch (exportType) {
                case 1:
                    records = userInfoService.findAll();
                    fields = new UserInfo().getClass().getDeclaredFields();
                    fileName = "user_records_" + DateUtil.getCurrDate(CommonCons.D_FMT_DATE_SEQ) + ".xls";
                    sheelName = "用户列表";
                    logger.info("即将导出所有用户信息数据。");
                    break;
                case 2:
                    records = roleInfoService.findAll();
                    fields = new RoleInfo().getClass().getDeclaredFields();
                    fileName = "role_records_" + DateUtil.getCurrDate(CommonCons.D_FMT_DATE_SEQ) + ".xls";
                    sheelName = "角色列表";
                    logger.info("即将导出所有角色信息数据。");
                    break;
                case 3:
                    records = permissionInfoService.findAll();
                    fields = new PermissionInfo().getClass().getDeclaredFields();
                    fileName = "permission_records_" + DateUtil.getCurrDate(CommonCons.D_FMT_DATE_SEQ) + ".xls";
                    sheelName = "权限列表";
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
            ExportExcelUtil.exportExcel(sheelName, headers, records, response.getOutputStream(), CommonCons.D_FMT_NORMAL);

        } catch (IOException e) {
            logger.error("导出数据IO异常：{}", e);
        } catch (Exception e) {
            logger.error("导出数据异常：{}", e);
        } finally {
            logger.info("数据导出完毕！文件名：" + fileName);
        }
    }


    // otherwise methods ...

}
