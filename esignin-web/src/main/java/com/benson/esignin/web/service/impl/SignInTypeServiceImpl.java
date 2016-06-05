package com.benson.esignin.web.service.impl;

import com.benson.esignin.common.base.BaseServiceImpl;
import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.dao.ISignInTypeDao;
import com.benson.esignin.web.domain.entity.SignInType;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.SignInTypeQuery;
import com.benson.esignin.web.service.ISignInTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<SignInType> findAllByQuery(SignInTypeQuery query) {
        return signInTypeDao.findAllByQuery(query);
    }

    public BensonPage<SignInType> findByPage(SignInTypeQuery query) {
        int total = signInTypeDao.count(query);

        List<SignInType> list = signInTypeDao.findPage(query);

        BensonPage<SignInType> page = new BensonPage<SignInType>(query.getPage(), query.getSize(), list, total);

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
            result += signInTypeDao.delete(Integer.valueOf(id));
        }
        return result;
    }
}
