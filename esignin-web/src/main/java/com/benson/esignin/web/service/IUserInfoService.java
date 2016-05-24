package com.benson.esignin.web.service;

import com.benson.esignin.common.base.IBaseService;
import com.benson.esignin.web.domain.entity.UserInfo;

import java.util.List;

/**
 * 用户信息Service接口
 *
 * @author: Benson Xu
 * @date: 2016年05月21日 23:49
 */
public interface IUserInfoService extends IBaseService<UserInfo, String> {


    /**
     * 用户验证
     * @param userInfo
     * @return
     */
    UserInfo authentication(UserInfo userInfo);

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    UserInfo selectByUserName(String userName);

}
