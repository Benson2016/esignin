package com.benson.esignin.web.controller;

import com.benson.esignin.common.enums.StateResponse;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.common.utils.JsonUtil;
import com.benson.esignin.common.utils.TimestampUtil;
import com.benson.esignin.web.annotation.SysControllerLog;
import com.benson.esignin.web.domain.entity.PermissionInfo;
import com.benson.esignin.web.domain.entity.RoleInfo;
import com.benson.esignin.web.domain.vo.UserInfoResponse;
import com.benson.esignin.web.service.IPermissionInfoService;
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
 * 权限控制层
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月01日 19:13
 */
@Controller
@RequestMapping("/perm")
public class PermissionController {

    private final Logger logger = Logger.getLogger(PermissionController.class);

    @Autowired
    private IPermissionInfoService permissionInfoService;

    @RequestMapping(value = "/delPermission",method = RequestMethod.POST)
    @SysControllerLog(content = "删除权限记录.")
    @ResponseBody
    public Object delPermission(@RequestParam String ids) {
        UserInfoResponse response = null;
        if (CommonUtil.isNull(ids)) {
            response = new UserInfoResponse(StateResponse.ERROR_PARAM);
            return JsonUtil.toJson(response);
        }

        try {
            // 删除给定id的记录
            String[] idArray = ids.split(",");
            int result = permissionInfoService.deleteByIds(ids);
            logger.info(String.format("===》》》权限删除操作，预期删除 %d条记录，实际删除 %d条记录。", idArray.length, result));

            response = new UserInfoResponse(StateResponse.SUCCESS);
            response.setRspMsg("删除成功！");
        } catch (Exception e) {
            logger.error("手动删除权限记录失败！异常：{}", e);
            response = new UserInfoResponse(StateResponse.ERROR_SYS);
        }

        return JsonUtil.toJson(response);
    }


    /**
     * 添加权限信息
     * @param info
     * @param request
     * @return
     */
    @RequestMapping(value = "/addPerm", method = RequestMethod.POST)
    @SysControllerLog(content = "添加权限信息.")
    @ResponseBody
    public Object addPerm(PermissionInfo info, HttpServletRequest request) {
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
            int rs = permissionInfoService.add(info);
            logger.info(rs>0?"后台添加权限成功！" :"后台添加权限失败！");

            response = new UserInfoResponse(StateResponse.SUCCESS);
            response.setRspMsg("添加成功！");
        } catch (Exception e) {
            response = new UserInfoResponse(StateResponse.ERROR_SYS);
            logger.error("添加权限异常:", e);
        }

        return JsonUtil.toJson(response);
    }

    /**
     * 去角权限息编辑页面
     * @param pid 权限ID
     * @param model
     * @return
     */
    @RequestMapping("/toPermEdit")
    public String toPermEdit(@RequestParam String pid, Model model) {
        if (CommonUtil.isNull(pid)) {
            logger.error("uid为空,非法进入,无权进入用户编辑页面.");
            return "404";
        }
        try {
            PermissionInfo info = permissionInfoService.findOne(pid);
            String json = JsonUtil.bean2Json(info);
            model.addAttribute("perm", json);
        } catch (Exception e) {
            logger.error("去权限信息编辑页面之前发生异常：", e);
        }
        return "admin/permission_edit";
    }

    /**
     * 修改权限信息
     * @param vo
     * @return
     */
    @RequestMapping(value = "/savePerm", method = RequestMethod.POST)
    @SysControllerLog(content = "修改权限信息.")
    @ResponseBody
    public Object savePerm(PermissionInfo vo) {
        UserInfoResponse response = null;
        if (CommonUtil.isNull(vo)) {
            response = new UserInfoResponse(StateResponse.ERROR_PARAM);
            return JsonUtil.toJson(response);
        }
        try {
            PermissionInfo info = permissionInfoService.findOne(vo.getId());
            if (CommonUtil.isNull(info)) {
                response = new UserInfoResponse(StateResponse.ERROR_PARAM);
                response.setRspMsg("找不到目标权限信息.");
                return JsonUtil.toJson(response);
            }

            info.setName(vo.getName());
            info.setFlag(vo.getFlag());
            info.setDescription(vo.getDescription());
            int rs = permissionInfoService.update(info);
            logger.info(rs>0?"后台修改权限成功！" :"后台修改权限失败！");

            response = new UserInfoResponse(StateResponse.SUCCESS);
            response.setRspMsg("修改成功！");
        } catch (Exception e) {
            response = new UserInfoResponse(StateResponse.ERROR_SYS);
            logger.error("修改权限异常:", e);
        }

        return JsonUtil.toJson(response);
    }

}
