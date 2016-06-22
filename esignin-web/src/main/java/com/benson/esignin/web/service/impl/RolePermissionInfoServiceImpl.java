package com.benson.esignin.web.service.impl;

import com.benson.esignin.common.base.BaseServiceImpl;
import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.dao.IRolePermissionInfoDao;
import com.benson.esignin.web.domain.entity.RolePermissionInfo;
import com.benson.esignin.web.service.IRolePermissionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色权限关系Service实现类
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright BensonXu Copyright (c) 2016
 * @since 2016年05月31日 16:05
 */
@Service("rolePermissionInfoService")
@Transactional
public class RolePermissionInfoServiceImpl extends BaseServiceImpl<RolePermissionInfo, String> implements IRolePermissionInfoService {

    @Autowired
    private IRolePermissionInfoDao rolePermissionInfoDao;

    @Override
    public IBaseDao<RolePermissionInfo, String> getDao() {
        return rolePermissionInfoDao;
    }

    public List<RolePermissionInfo> findAllByRoleId(String roleId) throws Exception {
        return rolePermissionInfoDao.findAllByRoleId(roleId);
    }

    public int deleteByRoleId(String roleId) throws Exception {
        return rolePermissionInfoDao.deleteByRoleId(roleId);
    }
}
