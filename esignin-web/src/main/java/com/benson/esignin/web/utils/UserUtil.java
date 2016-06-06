package com.benson.esignin.web.utils;

import com.benson.esignin.common.cons.SysCons;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.domain.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息操作工具类
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月03日 11:32
 */
public class UserUtil {

    /**
     * 获取当前登录用户信息
     * @param request
     * @return
     */
    public static UserInfo getLoginUser(HttpServletRequest request) {
        UserInfo logerUser = (UserInfo) request.getSession().getAttribute(SysCons.LOGIN_USER);
        return logerUser;
    }

    /**
     * 判断当前用户是否已登录
     * @param request
     * @return
     */
    public static boolean isLogin(HttpServletRequest request) {
        UserInfo logerUser = getLoginUser(request);
        return CommonUtil.isNotNull(logerUser);
    }

}
