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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
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
        model.addAttribute("operType", "2"); //操作类型：1登录，2注册
        if (null == userInfo) {
            model.addAttribute("rspMsg", "参数错误！");
            model.addAttribute("rspCode", "101");
            return "login";
        }
        if (result.hasErrors()) {
            model.addAttribute("rspMsg", "参数错误！");
            model.addAttribute("rspCode", "101");
            return "login";
        }

        try {
            // 首先查询用户名是否已被占用
            UserInfo target = userInfoService.findByUserName(userInfo.getUserName());
            logger.info("target:" + target);
            if (CommonUtil.isNotNull(target)) {
                model.addAttribute("rspMsg", "该用户名已存在！请更换一个后重试。");
                model.addAttribute("rspCode", "102");
                return "login";
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

            int rs = userInfoService.add(userInfo);
            logger.info(rs>1?"执行成功！" :"执行失败！");
            model.addAttribute("rspMsg", "注册成功！");
            model.addAttribute("rspCode", "100");
            model.addAttribute("operType", "1");
        } catch (Exception e) {
            model.addAttribute("rspMsg", "网络异常，请稍后重试！");
            model.addAttribute("rspCode", "103");
            model.addAttribute("operType", "2");
            logger.error("用户注册发生异常:{}!", e);
        } finally {
            logger.info("*** 退出 register method ***");
        }

        // 注册成功跳转至登录页面
        return "login";
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
            /*Subject subject = SecurityUtils.getSubject();
            // 已登陆则 跳到首页
            if (subject.isAuthenticated()) {
                logger.info(String.format("用户【%s】已登录，即将转到后台首页。", user.getUserName()));
                return "app/index";
            }
            if (result.hasErrors()) {
                logger.info("登录参数错误！");
                model.addAttribute("rspMsg", "参数错误！");
                model.addAttribute("rspCode", "101");
                model.addAttribute("operType", "1");
                return "login";
            }
            // 身份验证
            subject.login(new UsernamePasswordToken(user.getUserName(), user.getPassword()));
            // 验证成功在Session中保存用户信息
            final UserInfo authUserInfo = userInfoService.findByUserName(user.getUserName());
            request.getSession().setAttribute("userInfo", authUserInfo);
            */
            model.addAttribute("operType", "1");
            // 验证用户信息
            if (CommonUtil.isNull(user.getUserName(), user.getPassword())) {
                model.addAttribute("rspMsg", "用户名或密码不能为空！");
                model.addAttribute("rspCode", "101");
                return "login";
            }
            logger.info(String.format("*** 验证用户：%s ***", JsonUtil.bean2Json(user)));
            final UserInfo authUserInfo = userInfoService.authentication(user);
            if (CommonUtil.isNull(authUserInfo)) {
                model.addAttribute("rspMsg", "用户名或密码错误！");
                model.addAttribute("rspCode", "101");
                return "login";
            }
            logger.info(String.format("***用户【%s】验证通过！***", authUserInfo.getUserName()));
            // 验证成功在Session中保存用户信息
            request.getSession().setAttribute("userInfo", authUserInfo);
        } catch (AuthenticationException e) {
            // 身份验证失败
            model.addAttribute("rspMsg", "用户名或密码错误 ！");
            return "login";
        } catch (IOException ex) {
            model.addAttribute("rspMsg", "系统错误 ！");
            return "login";
        }

        // 登录成功跳转至首页
        return "app/index";
    }

    /**
     * 用户登出
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        UserInfo authUserInfo = (UserInfo) session.getAttribute("userInfo");
        if (null != authUserInfo)
            logger.info(String.format("*** user [%s] logout ***", authUserInfo.getUserName()));

        session.removeAttribute("userInfo");
        // 登出操作
        /*Subject subject = SecurityUtils.getSubject();
        subject.logout();*/
        return "login";
    }

}
