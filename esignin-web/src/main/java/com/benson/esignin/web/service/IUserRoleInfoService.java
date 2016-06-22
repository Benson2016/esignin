package com.benson.esignin.web.service;

import com.benson.esignin.common.base.IBaseService;
import com.benson.esignin.web.domain.entity.UserRoleInfo;

import java.util.List;

/**
 * 用户角色关系Service接口
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright BensonXu Copyright (c) 2016
 * @since 2016年05月31日 15:44
 */
public interface IUserRoleInfoService extends IBaseService<UserRoleInfo, String> {

    /**
     * 通过用户ID查询用户拥有的角色
     * @param userId
     * @return
     */
    public List<UserRoleInfo> findAllByUserId(String userId) throws Exception;

    /**
     * 通过角色ID查找角色对应的用户
     * @param roleId
     * @return
     * @throws Exception
     */
    public List<UserRoleInfo> findAllByRoleId(String roleId) throws Exception;

    /**
     * 删除角色下所有用户关系
     * @param roleId
     * @return
     * @throws Exception
     */
    public int deleteByRoleId(String roleId) throws Exception;

}
