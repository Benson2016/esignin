package com.benson.esignin.web.security;

import com.benson.esignin.common.cons.SysCons;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.domain.entity.PermissionInfo;
import com.benson.esignin.web.domain.entity.RoleInfo;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.service.IPermissionInfoService;
import com.benson.esignin.web.service.IRoleInfoService;
import com.benson.esignin.web.service.IUserInfoService;
import com.benson.esignin.web.utils.UserUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户身份安全验证，实现认证和授权管理
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月12日 17:13
 */
@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {

    private final static Logger logger = Logger.getLogger(SecurityRealm.class);

    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IRoleInfoService roleInfoService;
    @Autowired
    private IPermissionInfoService permissionInfoService;

    /**
     * 授权操作
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("-------->>> Enter doGetAuthorizationInfo Method <<<--------");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = String.valueOf(principals.getPrimaryPrincipal());

        try {
            UserInfo userInfo = userInfoService.findByUserName(username);
            if (CommonUtil.isNull(userInfo)) {
                throw new UnknownAccountException("没有找到该用户！");
            }

            // 添加角色
            final List<RoleInfo> roles = roleInfoService.findAllByUserId(userInfo.getId());
            for (RoleInfo role : roles) {
                authorizationInfo.addRole(role.getFlag());
                logger.info(String.format("RoleName：%s, RoleFlag：%s", role.getName(), role.getFlag()));

                // 添加权限
                final List<PermissionInfo> permissionInfos = permissionInfoService.findAllByRoleId(role.getId());
                for (PermissionInfo perm : permissionInfos) {
                    authorizationInfo.addStringPermission(perm.getFlag());
                    logger.info(String.format("PermName：%s, PermFlag：%s", perm.getName(), perm.getFlag()));
                }
            }

        } catch (Exception e) {
            logger.error("授权操作异常：", e);
        }

        logger.info("-------->>> Exit doGetAuthorizationInfo Method <<<--------");
        return authorizationInfo;
    }

    /**
     * 身份安全验证操作
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("-------->>> Enter doGetAuthenticationInfo Method <<<--------");

        try {
            String username = String.valueOf(token.getPrincipal());
            String password = new String((char[])token.getCredentials());
            // 通过身份验证
            final UserInfo authUser = userInfoService.authentication(new UserInfo(username, password));
            if (CommonUtil.isNull(authUser)) {
                throw new AuthenticationException("用户名或密码错误!");
            }

            //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 存储用户信息
            //UserUtil.storedUserToSession(request, authUser, SysCons.LOGIN_USER);

            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
            return authenticationInfo;
        } catch (Exception e) {
            logger.error("身份安全验证操作异常：", e);
        } finally {
            logger.info("-------->>> Exit doGetAuthenticationInfo Method <<<--------");
        }
        return null;
    }
}
