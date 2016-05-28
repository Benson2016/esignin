package com.benson.esignin.web.service.impl;

import com.benson.esignin.common.base.BaseServiceImpl;
import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.dao.ISignInTypeDao;
import com.benson.esignin.web.domain.entity.SignInType;
import com.benson.esignin.web.service.ISignInTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 签到类型Service实现类
 *
 * @author: Benson Xu
 * @date: 2016年05月29日 00:53
 */
@Service("signInTypeService")
@Transactional
public class SignInTypeServiceImpl extends BaseServiceImpl<SignInType, Integer> implements ISignInTypeService {

    @Autowired
    private ISignInTypeDao signInTypeDao;

    @Override
    public IBaseDao<SignInType, Integer> getDao() {
        return signInTypeDao;
    }


}
