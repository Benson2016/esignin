package com.benson.esignin.web.service;

import com.benson.esignin.common.base.IBaseService;
import com.benson.esignin.web.domain.entity.RolePermissionInfo;

import java.util.List;

/**
 * 角色权限关系Service接口
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright BensonXu Copyright (c) 2016
 * @since 2016年05月31日 15:48
 */
public interface IRolePermissionInfoService extends IBaseService<RolePermissionInfo, String> {
    /**
     * 根据角色ID查询角色拥有的权限
     * @param roleId
     * @return
     */
    List<RolePermissionInfo> findAllByRoleId(String roleId);
}
