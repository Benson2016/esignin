package com.benson.esignin.web.domain.entity;

import com.benson.esignin.common.entity.IdEntity;

/**
 * 角色权限
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright BensonXu Copyright (c) 2016
 * @since 2016年05月31日 14:53
 */
public class RolePermissionInfo extends IdEntity {

    private String roleId;  // 角色ID

    private String permissionId;    // 权限ID

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}
