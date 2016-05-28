package com.benson.esignin.web.controller;

import com.benson.esignin.common.cons.SysCons;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.domain.entity.UserInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 签到控制类
 *
 * @author: Benson Xu
 * @date: 2016年05月28日 21:52
 */
@Controller
public class SignInController {

    private final Logger logger = Logger.getLogger(SignInController.class);


    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signIn(Model model, HttpServletRequest request) {
        logger.info("signIn Method Begin......");
        try {

            String businesId = (String) request.getSession().getAttribute(SysCons.BUSINESS_ID);

            UserInfo loginUser = (UserInfo) request.getSession().getAttribute(SysCons.LOGIN_USER);

            if (CommonUtil.isNull(businesId)) {
                logger.info(String.format("找不到当前用户[%s]所执行的业务ID!", CommonUtil.isNull(loginUser)?"":loginUser.getMobile()));
            }

            // 添加签到内容
            //......

            logger.info(String.format("用户[%s]签到成功!", CommonUtil.isNull(loginUser)?"":loginUser.getMobile()));

        } catch (Exception e) {
            logger.error("用户签到时发生异常:{}", e);
        } finally {

            logger.info("End The Of signIn Method.");
        }

        return "app/signin_success";
    }


}
