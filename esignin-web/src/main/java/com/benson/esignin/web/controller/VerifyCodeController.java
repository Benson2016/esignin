package com.benson.esignin.web.controller;

import com.benson.esignin.common.enums.StateResponse;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.common.utils.DateUtil;
import com.benson.esignin.common.utils.JsonUtil;
import com.benson.esignin.common.utils.RandomCodeUtil;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.domain.entity.VerifyCode;
import com.benson.esignin.web.domain.vo.VerifyCodeResponse;
import com.benson.esignin.web.service.IUserInfoService;
import com.benson.esignin.web.service.IVerifyCodeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 验证码控制类
 *
 * @author: Benson Xu
 * @date: 2016年05月28日 16:43
 */
@Controller
public class VerifyCodeController {

    private final Logger logger = Logger.getLogger(VerifyCodeController.class);

    @Autowired
    private IVerifyCodeService verifyCodeService;
    @Autowired
    private IUserInfoService userInfoService;



    @RequestMapping(value = "/getVerifyCode", method = RequestMethod.POST)
    @ResponseBody
    public Object getVerifyCode(@RequestParam String mobile) {
        logger.info("Enter getVerifyCode Method.");
        VerifyCodeResponse response = null;

        if (CommonUtil.isNull(mobile)) {
            response = new VerifyCodeResponse(StateResponse.ERROR_PARAM);
            return response;
        }


        response = new VerifyCodeResponse(StateResponse.SUCCESS);

        try {
            // 如果存在有效验证码,则直接返回
            VerifyCode verifyCode = verifyCodeService.findByMobile(mobile);
            if (CommonUtil.isNotNull(verifyCode) && verifyCode.getIsValid()==1) {
                response.setVcid(verifyCode.getId());
                Integer countdown = DateUtil.getDifferenceSeconds(new Date(), verifyCode.getEffectiveTimeEnd());
                response.setCountDown(countdown);

                return JsonUtil.toJson(response);
            }


            // 发送新验证码
            verifyCode = new VerifyCode();
            verifyCode.generateUUId();
            verifyCode.setVerifyCode(RandomCodeUtil.getRandomCode());
            verifyCode.setMobile(mobile);
            verifyCode.setIsValid(1);
            verifyCode.setCreateTime(DateUtil.getCurrentDateTime());
            verifyCode.setEffectiveTimeEnd(DateUtil.addMinuteToDate(verifyCode.getCreateTime(), 5)); //5分钟有效期
            // 保存验证码
            verifyCodeService.add(verifyCode);

            // 调用发送短信接口
            logger.info("即将发送:" + JsonUtil.bean2Json(verifyCode));
            // 接口测试......

            // 计算倒计时
            Integer countdown = DateUtil.getDifferenceSeconds(new Date(), verifyCode.getEffectiveTimeEnd());
            response.setCountDown(countdown);
            response.setVcid(verifyCode.getId());


        } catch (Exception e) {
            logger.error("获取手机验证码异常:{}", e);
        } finally {
            logger.info("Leave getVerifyCode Method.");
        }

        return JsonUtil.toJson(response);
    }


    @RequestMapping(value = "/checkCode", method = RequestMethod.POST)
    @ResponseBody
    public Object checkCode(@RequestParam String mobile, @RequestParam String code, @RequestParam String cid) {
        logger.info("Check Code Begin......");
        VerifyCodeResponse response = null;

        if (CommonUtil.isNull(mobile, code, cid)) {
            response = new VerifyCodeResponse(StateResponse.ERROR_PARAM);
            return JsonUtil.toJson(response);
        }


        try {
            VerifyCode verifyCode = verifyCodeService.findOne(cid);
            if (CommonUtil.isNull(verifyCode) || verifyCode.getIsValid()==0 || DateUtil.compareDate(verifyCode.getEffectiveTimeEnd(), new Date())==-1) {
                response = new VerifyCodeResponse(StateResponse.INVALID);
                response.setRspMsg("验证码已失效!");
                return JsonUtil.toJson(response);
            } else if (!code.equals(verifyCode.getVerifyCode())) {
                response = new VerifyCodeResponse(StateResponse.ERROR_PARAM);
                response.setRspMsg("验证码不正确!");
                return JsonUtil.toJson(response);
            }

            // 校验通过
            response = new VerifyCodeResponse(StateResponse.SUCCESS);
            // 查询当前手机用户是否已注册
            UserInfo searchUser = userInfoService.findByMobile(mobile);
            response.setIsUser(CommonUtil.isNull(searchUser)?0:1);

        } catch (Exception e) {
            logger.error("手机验证码验证发生异常:{}", e);
        } finally {
            logger.info("The End Of Check Code.");
        }

        return JsonUtil.toJson(response);
    }


}
