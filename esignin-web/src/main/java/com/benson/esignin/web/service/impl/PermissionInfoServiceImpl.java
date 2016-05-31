package com.benson.esignin.web.service.impl;

import com.benson.esignin.common.base.BaseServiceImpl;
import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.dao.IPermissionInfoDao;
import com.benson.esignin.web.domain.entity.PermissionInfo;
import com.benson.esignin.web.service.IPermissionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限Service接口实现类
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright BensonXu Copyright (c) 2016
 * @since 2016年05月31日 16:01
 */
@Service("permissionInfoService")
@Transactional
public class PermissionInfoServiceImpl extends BaseServiceImpl<PermissionInfo, String> implements IPermissionInfoService {

    @Autowired
    private IPermissionInfoDao permissionInfoDao;

    @Override
    public IBaseDao<PermissionInfo, String> getDao() {
        return permissionInfoDao;
    }
}
