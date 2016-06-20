package com.benson.esignin.web.service.impl;

import com.benson.esignin.common.base.BaseServiceImpl;
import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.dao.IVerifyCodeDao;
import com.benson.esignin.web.domain.entity.VerifyCode;
import com.benson.esignin.web.service.IVerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 验证码Service接口实现类
 *
 * @author: Benson Xu
 * @date: 2016年05月28日 12:59
 */
@Service("verifyCodeService")
@Transactional
public class VerifyCodeServiceImpl extends BaseServiceImpl<VerifyCode, String> implements IVerifyCodeService {

    @Autowired
    private IVerifyCodeDao verifyCodeDao;

    @Override
    public IBaseDao<VerifyCode, String> getDao() {
        return verifyCodeDao;
    }

    /**
     * 根据手机号和有效时间查询最近一次有效的验证码
     * @param mobile
     * @return
     */
    public VerifyCode findByMobile(String mobile) throws Exception {
        return verifyCodeDao.findByMobile(mobile);
    }

}
