package com.benson.esignin.web.controller;

import com.benson.esignin.common.utils.*;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.service.IUserInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

/**
 * 用户信息控制层业务类
 *
 * @author: Benson Xu
 * @date: 2016年05月21日 23:54
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping(value = "/testService")
    public String testService() {

        logger.info("Welcome to my testService method.");

        try {
            String id = "123";
            UserInfo userInfo = userInfoService.findOne(id);
            if (CommonUtil.isNull(userInfo)) {
                logger.info("当前搜索的用户不存在!");
            } else {
                System.out.println(JsonUtil.bean2Json(userInfo));
                logger.info(String.format("查到数据,用户名为%s", userInfo.getUserName()));
            }

        } catch (Exception e) {
            logger.error("查询数据出错!{}", e);
        }

        logger.info("exit testService method.");
        return "redirect:/";
    }


    @RequestMapping(value = "/findAll")
    public String findAll(Model model, HttpServletRequest request, HttpServletResponse response) {
        logger.info("enter findUserAll method.");

        try {
            List<UserInfo> users = userInfoService.findAll();

            model.addAttribute("users", users);
        } catch (Exception e) {
            logger.error("查询所有用户信息出现异常:{}", e);
        }

        logger.info("exit findUserAll method.");
        return "welcome";
    }


    /**
     * 注册用户
     * @param userInfo
     * @param result
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid UserInfo userInfo, BindingResult result, Model model, HttpServletRequest request) {
        logger.info("*** 进入 register method ***");
        if (null == userInfo) {
            model.addAttribute("msg", "参数错误！");
            model.addAttribute("code", "101");
            return "login";
        }
        if (result.hasErrors()) {
            model.addAttribute("msg", "参数错误！");
            model.addAttribute("code", "101");
            return "login";
        }

        try {

            // 首先查询用户名是否已被占用
            UserInfo target = userInfoService.selectByUserName(userInfo.getUserName());
            logger.info("target:" + target);
            if (CommonUtil.isNotNull(target)) {
                model.addAttribute("msg", "用户名已被占用！请更换一个");
                model.addAttribute("code", "102");
                return null;
            }

            // 注册用户
            userInfo.setId(UUIDUtil.getUUID());
            userInfo.setAge(0);     //默认0
            userInfo.setSex(3);     //默认未知
            userInfo.setFlag(1);    //默认普通会员
            userInfo.setIsValid(1); //默认有效
            Timestamp currTime = TimestampUtil.getCurrentTimestampWithFormat();
            userInfo.setCreateTime(currTime); //设置创建时间
            userInfo.setUpdateTime(currTime);
            if (CommonUtil.isNotNull(userInfo.getPassword()))
                userInfo.setPassword(MD5Util.md5(userInfo.getPassword()));

            int rs = userInfoService.add(userInfo);
            logger.info(rs==1?"执行成功！" :"执行失败！");
        } catch (Exception e) {
            logger.error("用户注册发生异常:{}!", e);
        } finally {
            logger.info("*** 退出 register method ***");
        }

        // 注册成功跳转至登录页面
        return "app/login";
    }

}
