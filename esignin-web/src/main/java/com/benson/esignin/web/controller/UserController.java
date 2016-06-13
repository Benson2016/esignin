package com.benson.esignin.web.controller;

import com.benson.esignin.common.cons.SysCons;
import com.benson.esignin.common.enums.StateResponse;
import com.benson.esignin.common.utils.*;
import com.benson.esignin.web.annotation.SysControllerLog;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.domain.vo.UserInfoResponse;
import com.benson.esignin.web.service.IUserInfoService;
import com.benson.esignin.web.utils.UserUtil;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    private final Logger logger = Logger.getLogger(UserController.class);


    @Autowired
    private IUserInfoService userInfoService;


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
    @SysControllerLog(content = "用户注册")
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
     * @param username
     * @param password
     * @param result
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @SysControllerLog(content = "用户登录")
    public String login(@Valid String username, @Valid String password, BindingResult result, Model model, HttpServletRequest request) {
        logger.info("-------->>>进入登录环节<<<--------");
        try {
            // 验证用户信息
            if (CommonUtil.isNull(username, password)) {
                model.addAttribute("operType", "1");
                model.addAttribute("rspMsg", "用户名或密码不能为空！");
                model.addAttribute("rspCode", "101");
                return "login";
            }

            Subject subject = SecurityUtils.getSubject();
            // 已登陆则 跳到首页
            if (subject.isAuthenticated()) {
                logger.info(String.format("用户【%s】已登录，即将转到后台首页。", username));
                return "admin/index";
            }

            final UserInfo authUserInfo = userInfoService.authentication(new UserInfo(username, password));
            if (CommonUtil.isNull(authUserInfo)) {
                model.addAttribute("operType", "1");
                model.addAttribute("rspMsg", "用户名或密码错误！");
                model.addAttribute("rspCode", "101");
                return "login";
            }
            // 身份验证
            subject.login(new UsernamePasswordToken(authUserInfo.getUserName(), authUserInfo.getPassword()));
            // 验证成功在Session中保存用户信息
            UserUtil.storedUserToSession(request, authUserInfo, SysCons.LOGIN_USER);

        } catch (AuthenticationException e) {
            // 身份验证失败
            model.addAttribute("rspMsg", "用户名或密码错误 ！");
            return "login";
        } catch (Exception e) {
            model.addAttribute("rspMsg", "系统错误 ！");
            return "login";
        }
        logger.info("-------->>>退出登录环节<<<--------");

        // 登录成功跳转至首页
        return "redirect:/admin/toAdmin.bs";
    }

    /**
     * 用户登出
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @SysControllerLog(content = "用户退出登录")
    public String logout(HttpServletRequest request) {
        UserInfo authUserInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (null != authUserInfo)
            logger.info(String.format("*** user [%s] logout ***", authUserInfo.getUserName()));

        // 移除用户信息
        removeUserFromSession(request, SysCons.LOGIN_USER);
        // 登出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }


    /**
     * 手机用户注册
     * @param mobile
     * @param fullName
     * @return
     */
    @RequestMapping(value = "/regByMobile", method = RequestMethod.POST)
    @ResponseBody
    @SysControllerLog(content = "手机用户注册")
    public Object regByMobile(@RequestParam String mobile, @RequestParam String fullName, HttpServletRequest request) {
        logger.info("regByMobile Begin......");
        UserInfoResponse response = null;
        // 参数校验
        if (CommonUtil.isNull(mobile, fullName)) {
            response = new UserInfoResponse(StateResponse.ERROR_PARAM);
            return JsonUtil.toJson(response);
        }

        // 手机用户注册
        try {
            UserInfo newUser = new UserInfo();
            newUser.setFullName(fullName);
            newUser.setMobile(mobile);
            //默认手机号为用户名
            newUser.setUserName(mobile);

            // 设置默认属性
            newUser.setAge(0);
            newUser.setSex(3);
            newUser.setFlag(1);
            newUser.setIsValid(1);
            newUser.setCreateTime(DateUtil.getCurrentDateTime());
            newUser.setUpdateTime(newUser.getCreateTime());
            newUser.generateUUId();
            // 密码默认666666
            newUser.setPassword(EncryptionUtil.sha256Encode(SysCons.DEFAULT_PASSWORD));

            int result = userInfoService.add(newUser);

            // 此处,添加日志 DB
            // console log
            logger.info(String.format("手机用户注册%s,手机[%s]姓名[%s]", result==1?"成功":"失败", mobile, fullName));

            response = new UserInfoResponse(StateResponse.SUCCESS);
            // 保存用户信息
            UserUtil.storedUserToSession(request, newUser, SysCons.LOGIN_USER);
            // 设置返回信息
            response.setUn(newUser.getUserName());
            response.setUp(newUser.getPassword());
        } catch (Exception e) {
            logger.error("手机用户注册发生异常:{}", e);
        } finally {
            logger.info("The End Of regByMobile.");
        }

        return JsonUtil.toJson(response);
    }


    /**
     * 手机用户登录
     * @param mobile
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginByMobile", method = RequestMethod.POST)
    @ResponseBody
    @SysControllerLog(content = "手机用户登录")
    public Object loginByMobile(@RequestParam String mobile, HttpServletRequest request) {
        logger.info("loginByMobile Begin......");
        UserInfoResponse response = null;

        // 参数校验
        if (CommonUtil.isNull(mobile)) {
            response = new UserInfoResponse(StateResponse.ERROR_PARAM);
            return JsonUtil.toJson(response);
        }

        try {

            final UserInfo loginUser = userInfoService.findByMobile(mobile);
            if (CommonUtil.isNull(loginUser)) {
                response = new UserInfoResponse(StateResponse.INVALID);
                return JsonUtil.toJson(response);
            }

            // 登录成功,存储信息
            UserUtil.storedUserToSession(request, loginUser, SysCons.LOGIN_USER);
            // 返回成功提示
            response = new UserInfoResponse(StateResponse.SUCCESS);
            response.setUn(loginUser.getUserName());
            response.setUp(loginUser.getPassword());

        } catch (Exception e) {
            logger.error("手机用户登录时发生异常:{}", e);
        } finally {
            logger.info("The End Of loginByMobile.");
        }

        return JsonUtil.toJson(response);
    }


    /**
     * 从Session中移除用户信息
     * @param request
     * @param name
     */
    private void removeUserFromSession(HttpServletRequest request, String name) {
        request.getSession().removeAttribute(name);
    }

    @RequestMapping(value = "/delUser",method = RequestMethod.POST)
    @ResponseBody
    @SysControllerLog(content = "删除用户操作")
    public Object delUser(@RequestParam String ids) {
        UserInfoResponse response = null;
        if (CommonUtil.isNull(ids)) {
            response = new UserInfoResponse(StateResponse.ERROR_PARAM);
            return JsonUtil.toJson(response);
        }

        try {
            // 删除给定id的记录
            String[] idArray = ids.split(",");
            int result = 0;
            for (String id : idArray) {
                result += userInfoService.delete(id);
            }
            //int result = userInfoService.deleteByIds(ids);
            logger.info(String.format("===》》》用户删除操作，预期删除 %d条记录，实际删除 %d条记录。", idArray.length, result));

            response = new UserInfoResponse(StateResponse.SUCCESS);
            response.setRspMsg("删除成功！");
        } catch (Exception e) {
            logger.error("手动删除用户记录失败！异常：{}", e);
            response = new UserInfoResponse(StateResponse.ERROR_SYS);
        }

        return JsonUtil.toJson(response);
    }

}
