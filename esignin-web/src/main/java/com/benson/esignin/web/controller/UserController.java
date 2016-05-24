package com.benson.esignin.web.controller;

import com.benson.esignin.common.utils.*;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.service.IUserInfoService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
            model.addAttribute("error", "参数错误！");
            model.addAttribute("errorCode", "101");
            return "app/login";
        }
        if (result.hasErrors()) {
            model.addAttribute("error", "参数错误！");
            model.addAttribute("errorCode", "101");
            return "app/login";
        }

        try {

            // 首先查询用户名是否已被占用
            UserInfo target = userInfoService.findByUserName(userInfo.getUserName());
            logger.info("target:" + target);
            if (CommonUtil.isNotNull(target)) {
                model.addAttribute("error", "用户名已被占用！请更换一个");
                model.addAttribute("errorCode", "102");
                return "app/login";
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
            logger.info(rs>1?"执行成功！" :"执行失败！");
            model.addAttribute("error", rs>0?"注册成功！":"网络异常，稍后重试！");
            model.addAttribute("errorCode", "100");
        } catch (Exception e) {
            logger.error("用户注册发生异常:{}!", e);
        } finally {
            logger.info("*** 退出 register method ***");
        }

        // 注册成功跳转至登录页面
        return "app/login";
    }

    /**
     * 用户登录
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid UserInfo user, BindingResult result, Model model, HttpServletRequest request) {
        try {
            Subject subject = SecurityUtils.getSubject();
            // 已登陆则 跳到首页
            if (subject.isAuthenticated()) {
                logger.info(String.format("用户【%s】已登录，即将转到后台首页。", user.getUserName()));
                return "redirect:/";
            }
            if (result.hasErrors()) {
                logger.info("登录参数错误！");
                model.addAttribute("error", "参数错误！");
                return "app/login";
            }
            // 身份验证
            subject.login(new UsernamePasswordToken(user.getUserName(), user.getPassword()));
            // 验证成功在Session中保存用户信息
            final UserInfo authUserInfo = userInfoService.findByUserName(user.getUserName());
            request.getSession().setAttribute("userInfo", authUserInfo);
        } catch (AuthenticationException e) {
            // 身份验证失败
            model.addAttribute("error", "用户名或密码错误 ！");
            return "app/login";
        }
        // 登录成功跳转至首页
        return "redirect:/";
    }


}
