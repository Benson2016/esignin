package com.benson.esignin.web.controller;

import com.benson.esignin.common.cons.CommonCons;
import com.benson.esignin.common.enums.StateResponse;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.common.utils.DateUtil;
import com.benson.esignin.common.utils.ExportExcelUtil;
import com.benson.esignin.common.utils.JsonUtil;
import com.benson.esignin.web.domain.entity.SignInType;
import com.benson.esignin.web.domain.vo.SignInTypeQuery;
import com.benson.esignin.web.domain.vo.SignInTypeResponse;
import com.benson.esignin.web.service.ISignInTypeService;
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
import java.util.List;

/**
 * 签到类型
 *
 * @author: Benson Xu
 * @date: 2016年06月04日 17:28
 */
@Controller
@RequestMapping("/type")
public class SignInTypeController {

    private final static Logger logger = Logger.getLogger(SignInTypeController.class);

    @Autowired
    private ISignInTypeService signInTypeService;



    /**
     * 添加签到类别
     * @param type
     * @return
     */
    @RequestMapping(value = "/addType", method = RequestMethod.POST)
    @ResponseBody
    public Object addType(SignInType type) {
        SignInTypeResponse response = null;

        if (CommonUtil.isNull(type)) {
            response = new SignInTypeResponse(StateResponse.ERROR_PARAM);
            return JsonUtil.toJson(response);
        }

        try {
            // 添加默认项
            // id为自增长列,无需设置
            // 保存类别记录
            int result = signInTypeService.add(type);

            logger.info(String.format("此次添加类别记录%d条.", result));

            response = new SignInTypeResponse(StateResponse.SUCCESS);
            response.setRspMsg("添加成功！");
        } catch (Exception e) {
            logger.error("添加签到类别记录失败！异常：{}", e);
            response = new SignInTypeResponse(StateResponse.ERROR_SYS);
        }

        return JsonUtil.toJson(response);
    }

    /**
     * 更新保存签到类别
     * @param type
     * @return
     */
    @RequestMapping(value = "/saveType", method = RequestMethod.POST)
    @ResponseBody
    public Object saveType(SignInType type) {
        SignInTypeResponse response = null;

        if (CommonUtil.isNull(type)) {
            response = new SignInTypeResponse(StateResponse.ERROR_PARAM);
            return JsonUtil.toJson(response);
        }

        try {
            // 保存类别记录
            int result = signInTypeService.update(type);

            logger.info(String.format("此次更新类别记录%d条.", result));

            response = new SignInTypeResponse(StateResponse.SUCCESS);
            response.setRspMsg("保存成功！");
        } catch (Exception e) {
            logger.error("更新签到类别记录失败！异常：{}", e);
            response = new SignInTypeResponse(StateResponse.ERROR_SYS);
        }

        return JsonUtil.toJson(response);
    }

    /**
     * 删除签到类别
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delType", method = RequestMethod.POST)
    @ResponseBody
    public Object delType(@RequestParam String ids) {
        SignInTypeResponse response = null;

        if (CommonUtil.isNull(ids)) {
            response = new SignInTypeResponse(StateResponse.ERROR_PARAM);
            return JsonUtil.toJson(response);
        }

        try {
            // 删除给定id的记录
            int result = signInTypeService.deleteByIds(ids);
            logger.info(String.format("此次删除记录%d条.", result));

            response = new SignInTypeResponse(StateResponse.SUCCESS);
            response.setRspMsg("删除成功！");
        } catch (Exception e) {
            logger.error("手动删除签到类别记录失败！异常：{}", e);
            response = new SignInTypeResponse(StateResponse.ERROR_SYS);
        }

        return JsonUtil.toJson(response);
    }


    /**
     * 前往类别添加页
     * @param model
     * @return
     */
    @RequestMapping(value = "/toTypeAdd")
    public String toTypeAdd(Model model) {
        // 操作类型:1添加,2编辑
        model.addAttribute("operType", 1);

        return "admin/signin_type_edit";
    }

    /**
     * 前往类别编辑页
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/toTypeEdit")
    public String toTypeEdit(@RequestParam Integer id, Model model) {

        try {
            SignInType type = signInTypeService.findOne(id);
            model.addAttribute("siType", JsonUtil.bean2Json(type));
            model.addAttribute("operType", 2);
        } catch (Exception e) {
            logger.error("前往编辑页,查询类别记录失败！异常：{}", e);
        }

        return "admin/signin_type_edit";
    }


    // 导出数据
    @RequestMapping(value = "/exportTypeData",method = RequestMethod.POST)
    public void exportTypeData(SignInTypeQuery query, HttpServletRequest request, HttpServletResponse response) {

        List<? extends Serializable> records = null;
        String[] headers = null;
        String fileName = "records.xls";
        try {
            records = signInTypeService.findAllByQuery(query);
            // 设置Excel文件名称
            fileName = "type_records_" + DateUtil.getCurrDate(CommonCons.D_FMT_DATE_SEQ) + ".xls";
            logger.info("即将导出签到类别信息数据。");

            headers = new String[]{"类别ID", "类别名称"};

            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);// 设定输出文件头
            response.setContentType("application/msexcel");// 定义输出类型
            ExportExcelUtil.exportExcel("签到类别信息", headers, records, response.getOutputStream(), CommonCons.D_FMT_NORMAL);

        } catch (IOException e) {
            logger.error("导出数据IO异常：{}", e);
        } catch (Exception e) {
            logger.error("导出数据异常：{}", e);
        } finally {
            logger.info("数据导出完毕！文件名：" + fileName);
        }
    }


}
