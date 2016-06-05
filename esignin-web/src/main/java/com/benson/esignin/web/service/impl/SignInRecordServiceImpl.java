package com.benson.esignin.web.service.impl;

import com.benson.esignin.common.base.BaseServiceImpl;
import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.dao.ISignInRecordDao;
import com.benson.esignin.web.domain.entity.SignInRecord;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.SignInRecordQuery;
import com.benson.esignin.web.service.ISignInRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 签到记录Service接口实现类
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


    public List<SignInRecord> findAllByQuery(SignInRecordQuery query) {
        return signInRecordDao.findAllByQuery(query);
    }

    public BensonPage<SignInRecord> findByPage(SignInRecordQuery query) {
        int total = signInRecordDao.count(query);

        List<SignInRecord> list = signInRecordDao.findPage(query);

        BensonPage<SignInRecord> page = new BensonPage<SignInRecord>(query.getPage(), query.getSize(), list, total);

        return page;
    }

    public int deleteByIds(String ids) {
        if (CommonUtil.isNull(ids)) {
            return -1;  // 如果参数为空，则直接返回-1
        }

        // 循环遍历删除
        String[] idArray = ids.split(",");
        int result = 0;
        for (String id : idArray) {
            result += signInRecordDao.delete(id);
        }
        return result;
    }
}
