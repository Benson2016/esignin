package com.benson.esignin.web.service.impl;

import com.benson.esignin.common.base.BaseServiceImpl;
import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.dao.IUserInfoDao;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.UserInfoQuery;
import com.benson.esignin.web.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 用户信息Service接口实现类
 *
 * @author: Benson Xu
 * @date: 2016年05月21日 23:52
 */
@Service("userInfoService")
@Transactional
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo, String> implements IUserInfoService {

    @Autowired
    private IUserInfoDao userInfoDao;

    @Override
    public IBaseDao<UserInfo, String> getDao() {
        return userInfoDao;
    }

    /**
     * 用户验证
     * @param userInfo 需验证的用户
     * @return
     */
    public UserInfo authentication(UserInfo userInfo) {

        return userInfoDao.authentication(userInfo);
    }

    /**
     * 根据用户名查询用户信息
     * @param userName 用户名
     * @return
     */
    public UserInfo findByUserName(String userName) {

        return userInfoDao.findByUserName(userName);
    }

    /**
     * 根据手机号码查询用户信息
     * @param mobile
     * @return
     */
    public UserInfo findByMobile(String mobile) {
        return userInfoDao.findByMobile(mobile);
    }

    public BensonPage<UserInfo> findByPage(UserInfoQuery query) {

        int total = userInfoDao.count(query);

        List<UserInfo> list = userInfoDao.findPage(query);

        BensonPage<UserInfo> page = new BensonPage<UserInfo>(query.getPage(), query.getSize(), list, total);

        return page;
    }

    /**
     * 根据ID数组批量删除记录
     * @param ids ID数组，多个值以逗号分隔
     * @return
     */
    public int deleteByIds(String ids) {
        if (CommonUtil.isNull(ids)) {
            return -1;  // 如果参数为空，则直接返回-1
        }
        return userInfoDao.deleteByIds(ids);
    }
}
