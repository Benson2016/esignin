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
     * 根据用户ID查询用户拥有的角色
     * @param userId
     * @return
     */
    public List<UserRoleInfo> findAllByUserId(String userId) throws Exception;
}
