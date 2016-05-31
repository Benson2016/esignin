package com.benson.esignin.web.domain.entity;

import com.benson.esignin.common.entity.IdEntity;

/**
 * 用户角色关系信息
 *
 * @author Benson Xu
 * @version 1.0
 * @since 2016年05月31日 15:00
 */
public class UserRoleInfo extends IdEntity {

    private String userId;  // 主键ID

    private String roleId;  // 角色ID

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
