package com.benson.esignin.web.service;

import com.benson.esignin.common.base.IBaseService;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.UserInfoQuery;

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
    UserInfo findByUserName(String userName);

    /**
     * 根据手机号码查询用户信息
     * @param mobile
     * @return
     */
    UserInfo findByMobile(String mobile);

    /**
     * 根据条件查询
     * @param query 查询条件
     * @return
     */
    List<UserInfo> findAllByQuery(UserInfoQuery query);

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    BensonPage<UserInfo> findByPage(UserInfoQuery query);

    /**
     * 根据ID数组批量删除记录
     * @param ids ID数组，多个值以逗号分隔
     * @return
     */
    int deleteByIds(String ids);

}
