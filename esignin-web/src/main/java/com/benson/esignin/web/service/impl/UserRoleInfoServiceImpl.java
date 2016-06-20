package com.benson.esignin.web.service.impl;

import com.benson.esignin.common.base.BaseServiceImpl;
import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.dao.IUserRoleInfoDao;
import com.benson.esignin.web.domain.entity.UserRoleInfo;
import com.benson.esignin.web.service.IUserRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * { enter your description }
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright BensonXu Copyright (c) 2016
 * @since 2016年05月31日 16:08
 */
@Service("userRoleInfoService")
@Transactional
public class UserRoleInfoServiceImpl extends BaseServiceImpl<UserRoleInfo, String> implements IUserRoleInfoService {

    @Autowired
    private IUserRoleInfoDao userRoleInfoDao;

    @Override
    public IBaseDao<UserRoleInfo, String> getDao() {
        return userRoleInfoDao;
    }

    public List<UserRoleInfo> findAllByUserId(String userId) throws Exception {
        return userRoleInfoDao.findAllByUserId(userId);
    }

}
