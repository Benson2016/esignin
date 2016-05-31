package com.benson.esignin.web.service.impl;

import com.benson.esignin.common.base.BaseServiceImpl;
import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.dao.IRoleInfoDao;
import com.benson.esignin.web.domain.entity.RoleInfo;
import com.benson.esignin.web.service.IRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色信息Service接口实现类
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright BensonXu Copyright (c) 2016
 * @since 2016年05月31日 15:57
 */
@Service("roleInfoService")
@Transactional
public class RoleInfoServiceImpl extends BaseServiceImpl<RoleInfo, String> implements IRoleInfoService {

    @Autowired
    private IRoleInfoDao roleInfoDao;

    @Override
    public IBaseDao<RoleInfo, String> getDao() {
        return roleInfoDao;
    }

}
