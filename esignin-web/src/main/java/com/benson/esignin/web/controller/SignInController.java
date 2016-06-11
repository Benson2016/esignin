package com.benson.esignin.web.controller;

import com.benson.esignin.common.cons.SysCons;
import com.benson.esignin.common.enums.StateResponse;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.common.utils.DateUtil;
import com.benson.esignin.common.utils.JsonUtil;
import com.benson.esignin.web.annotation.SysControllerLog;
import com.benson.esignin.web.domain.entity.QrCode;
import com.benson.esignin.web.domain.entity.SignInRecord;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.service.IQrCodeService;
import com.benson.esignin.web.service.ISignInRecordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 签到控制类
 *
 * @author: Benson Xu
 * @date: 2016年05月28日 21:52
 */
@Controller
public class SignInController {

    private final Logger logger = Logger.getLogger(SignInController.class);

    @Autowired
    private ISignInRecordService signInRecordService;
    @Autowired
    private IQrCodeService qrCodeService;

    /**
     * 用户签到
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/signIn")
    @SysControllerLog(content = "用户签到.")
    public String signIn(Model model, HttpServletRequest request) {
        logger.info("signIn Method Begin......");
        try {

            String businessId = (String) request.getSession().getAttribute(SysCons.BUSINESS_ID);

            UserInfo loginUser = (UserInfo) request.getSession().getAttribute(SysCons.LOGIN_USER);

            if (CommonUtil.isNull(businessId)) {
                logger.error(String.format("找不到当前用户[%s]所执行的业务ID!", CommonUtil.isNull(loginUser)?"":loginUser.getMobile()));
                return "redirect:500";
            }
            if (CommonUtil.isNull(loginUser)) {
                logger.error(String.format("找不到当前用户[%s]信息!", CommonUtil.isNull(loginUser)?"":loginUser.getMobile()));
                return "redirect:500";
            }

            // 检查当前用户是否已经签过,是的话直接返回
            SignInRecord record = signInRecordService.findByQridAndUserId(businessId, loginUser.getId());
            if (CommonUtil.isNotNull(record)) {
                model.addAttribute("alreadySign", true);

            } else { // 否则,添加签到记录
                model.addAttribute("alreadySign", false);

                record = new SignInRecord();
                record.generateUUId();
                record.setQrid(businessId);
                record.setUserId(loginUser.getId());
                record.setMobile(loginUser.getMobile());
                record.setCreateTime(DateUtil.getCurrentDateTime());
                record.setIsValid(1);
                // 保存签到记录
                int result = signInRecordService.add(record);
                if (result==0) {
                    logger.error(String.format("用户[%s]签到记录保存未成功! 记录:%s", loginUser.getMobile(), JsonUtil.bean2Json(record)));
                } else {
                    logger.info(String.format("用户[%s]签到成功! 记录:%s", loginUser.getMobile(), JsonUtil.bean2Json(record)));
                }
            }

            model.addAttribute("userName", loginUser.getUserName());
            model.addAttribute("businessId", businessId);

        } catch (Exception e) {
            logger.error("用户签到时发生异常:{}", e);
        } finally {

            logger.info("End The Of signIn Method.");
        }

        return "app/signin_success";
    }

    /**
     * 获取签到列表
     * @param model
     * @param businessId 业务ID
     * @return
     */
    @RequestMapping(value = "/getSignInList")
    public String getSignInList(Model model, @RequestParam String businessId) {
        logger.info("getSignInList Method Begin......");

        if (CommonUtil.isNull(businessId)) {
            String msg = "签到情况获取失败,业务ID不能为空!";
            StateResponse sr = StateResponse.ERROR_PARAM;
            model.addAttribute("rspMsg", msg);
            model.addAttribute("rspCode", sr.getCode());
            logger.error(msg);

        } else {
            try {
                QrCode qrCode = qrCodeService.findOne(businessId);
                String businessName = CommonUtil.isNull(qrCode)?null:qrCode.getTitle();

                List<SignInRecord> records = signInRecordService.findAllByBusinessId(businessId);

                if (CommonUtil.isNull(records)) records = new ArrayList<SignInRecord>();

                // 手机号码部分隐藏处理
                for (SignInRecord record : records) {
                    record.setMobile(CommonUtil.replaceChar(record.getMobile()));
                }

                model.addAttribute("records", records);
                model.addAttribute("businessName", businessName);

                StateResponse sr = StateResponse.SUCCESS;
                model.addAttribute("rspMsg", sr.getMsg());
                model.addAttribute("rspCode", sr.getCode());
            } catch (Exception e) {
                logger.error("获取签到列表时，发生异常：", e);
            }
        }

        logger.info("End The Of getSignInList Method.");

        return "app/signin_list";
    }

}
