package com.benson.esignin.web.controller;

import com.benson.esignin.common.enums.StateResponse;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.common.utils.JsonUtil;
import com.benson.esignin.common.utils.TimestampUtil;
import com.benson.esignin.web.annotation.SysControllerLog;
import com.benson.esignin.web.domain.entity.RoleInfo;
import com.benson.esignin.web.domain.vo.UserInfoResponse;
import com.benson.esignin.web.service.IRoleInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 角色控制层
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月01日 19:08
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private final Logger logger = Logger.getLogger(RoleController.class);

    @Autowired
    private IRoleInfoService roleInfoService;

    @RequestMapping(value = "/delRole",method = RequestMethod.POST)
    @SysControllerLog(content = "删除角色信息.")
    @ResponseBody
    public Object delRole(@RequestParam String ids) {
        UserInfoResponse response = null;
        if (CommonUtil.isNull(ids)) {
            response = new UserInfoResponse(StateResponse.ERROR_PARAM);
            return JsonUtil.toJson(response);
        }

        try {
            // 删除给定id的记录
            String[] idArray = ids.split(",");
            int result = roleInfoService.deleteByIds(ids);
            logger.info(String.format("===》》》角色删除操作，预期删除 %d条记录，实际删除 %d条记录。", idArray.length, result));

            response = new UserInfoResponse(StateResponse.SUCCESS);
            response.setRspMsg("删除成功！");
        } catch (Exception e) {
            logger.error("手动删除角色记录失败！异常：{}", e);
            response = new UserInfoResponse(StateResponse.ERROR_SYS);
        }

        return JsonUtil.toJson(response);
    }


    /**
     * 添加角色信息
     * @param info
     * @param request
     * @return
     */
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    @SysControllerLog(content = "添加角色信息.")
    @ResponseBody
    public Object addRole(RoleInfo info, HttpServletRequest request) {
        UserInfoResponse response = null;
        if (CommonUtil.isNull(info)) {
            response = new UserInfoResponse(StateResponse.ERROR_PARAM);
            return JsonUtil.toJson(response);
        }
        try {
            // 初始化值
            info.generateUUId();
            // 设置创建时间
            info.setCreateTime(TimestampUtil.getCurrentTimestampWithFormat());
            // 保存信息
            int rs = roleInfoService.add(info);
            logger.info(rs>0?"后台添加角色成功！" :"后台添加角色失败！");

            response = new UserInfoResponse(StateResponse.SUCCESS);
            response.setRspMsg("添加成功！");
        } catch (Exception e) {
            response = new UserInfoResponse(StateResponse.ERROR_SYS);
            logger.error("添加角色异常:", e);
        }

        return JsonUtil.toJson(response);
    }

    /**
     * 去角色信息编辑页面
     * @param rid 角色ID
     * @param model
     * @return
     */
    @RequestMapping("/toRoleEdit")
    public String toRoleEdit(@RequestParam String rid, Model model) {
        if (CommonUtil.isNull(rid)) {
            logger.error("uid为空,非法进入,无权进入用户编辑页面.");
            return "404";
        }
        try {
            RoleInfo info = roleInfoService.findOne(rid);
            String json = JsonUtil.bean2Json(info);
            model.addAttribute("role", json);
        } catch (Exception e) {
            logger.error("去角色信息编辑页面之前发生异常：", e);
        }
        return "admin/role_edit";
    }

    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    @SysControllerLog(content = "修改角色信息.")
    @ResponseBody
    public Object saveRole(RoleInfo vo) {
        UserInfoResponse response = null;
        if (CommonUtil.isNull(vo)) {
            response = new UserInfoResponse(StateResponse.ERROR_PARAM);
            return JsonUtil.toJson(response);
        }

        try {
            RoleInfo info = roleInfoService.findOne(vo.getId());
            if (CommonUtil.isNull(info)) {
                response = new UserInfoResponse(StateResponse.ERROR_PARAM);
                response.setRspMsg("找不到目标角色信息.");
                return JsonUtil.toJson(response);
            }

            info.setName(vo.getName());
            info.setFlag(vo.getFlag());
            info.setDescription(vo.getDescription());
            int rs = roleInfoService.update(info);
            logger.info(rs>0?"后台修改角色成功！" :"后台修改角色失败！");

            response = new UserInfoResponse(StateResponse.SUCCESS);
            response.setRspMsg("修改成功！");
        } catch (Exception e) {
            response = new UserInfoResponse(StateResponse.ERROR_SYS);
            logger.error("修改角色异常:", e);
        }

        return JsonUtil.toJson(response);
    }


    // 去角色授权页面
    @RequestMapping("/toRoleGrant")
    public String toRoleGrant(@RequestParam String ids, Model model) {
        if (CommonUtil.isNull(ids)) {
            logger.error("uid为空,非法进入,无权进入用户编辑页面.");
            return "404";
        }
        model.addAttribute("ids", ids);

        return "admin/role_user_list";
    }

}
