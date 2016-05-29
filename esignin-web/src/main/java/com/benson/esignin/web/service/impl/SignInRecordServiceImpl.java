package com.benson.esignin.web.service.impl;

import com.benson.esignin.common.base.BaseServiceImpl;
import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.dao.ISignInRecordDao;
import com.benson.esignin.web.domain.entity.SignInRecord;
import com.benson.esignin.web.service.ISignInRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {enter your description}
 *
 * @author: Benson Xu
 * @date: 2016年05月29日 09:38
 */
@Service("signInRecordService")
@Transactional
public class SignInRecordServiceImpl extends BaseServiceImpl<SignInRecord, String> implements ISignInRecordService {

    @Autowired
    private ISignInRecordDao signInRecordDao;


    @Override
    public IBaseDao<SignInRecord, String> getDao() {
        return signInRecordDao;
    }

    /**
     * 根据业务ID查询签到情况
     * @param businessId
     * @return
     */
    public List<SignInRecord> findAllByBusinessId(String businessId) {
        return signInRecordDao.findAllByRqid(businessId);
    }

    /**
     * 根据业务ID和用户ID查询签到记录
     * @param qrid
     * @param userId
     * @return
     */
    public SignInRecord findByQridAndUserId(String qrid, String userId) {
        return signInRecordDao.findByQridAndUserId(qrid, userId);
    }

}
