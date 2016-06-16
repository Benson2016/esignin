package com.benson.esignin.web.service.impl;

import com.benson.esignin.common.base.BaseServiceImpl;
import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.common.utils.DateUtil;
import com.benson.esignin.web.annotation.SysServiceLog;
import com.benson.esignin.web.dao.IUserInfoDao;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.StatisticsQuery;
import com.benson.esignin.web.domain.vo.UserInfoQuery;
import com.benson.esignin.web.domain.vo.UserStatisticsVo;
import com.benson.esignin.web.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public UserInfo authentication(UserInfo userInfo) throws Exception {

        return userInfoDao.authentication(userInfo);
    }

    /**
     * 根据用户名查询用户信息
     * @param userName 用户名
     * @return
     */
    public UserInfo findByUserName(String userName) throws Exception {

        return userInfoDao.findByUserName(userName);
    }

    /**
     * 根据手机号码查询用户信息
     * @param mobile
     * @return
     */
    public UserInfo findByMobile(String mobile) throws Exception {
        return userInfoDao.findByMobile(mobile);
    }

    /**
     * 根据条件查询
     * @param query
     * @return
     */
    @SysServiceLog(content = "按条件查询用户信息列表")
    public List<UserInfo> findAllByQuery(UserInfoQuery query) throws Exception {
        /*// 手动添加空指针异常--测试
        UserInfo user = null;
        String fullName = user.getFullName();*/

        return userInfoDao.findAllByQuery(query);
    }

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    public BensonPage<UserInfo> findByPage(UserInfoQuery query) throws Exception {

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
    public int deleteByIds(String ids) throws Exception {
        if (CommonUtil.isNull(ids)) {
            return -1;  // 如果参数为空，则直接返回-1
        }
        return userInfoDao.deleteByIds(ids);
    }

    /**
     * 统计用户注册
     * @param query
     * @return
     */
    public Map<String, Integer> statisticsRegister(StatisticsQuery query) throws Exception {

        List<UserStatisticsVo> list = userInfoDao.statisticsRegister(query);

        Map<String, Integer> map = getMonthsForYear(query.getYear());

        if (CommonUtil.isNotNull(list)) {
            for (UserStatisticsVo usv : list) {
                map.put(usv.getMonth(), usv.getCounts());
            }
        }

        return map;
    }

    private Map<String, Integer> getMonthsForYear(String year) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(year+"-01", 0);
        map.put(year+"-02", 0);
        map.put(year+"-03", 0);
        map.put(year+"-04", 0);
        map.put(year+"-05", 0);
        map.put(year+"-06", 0);
        map.put(year+"-07", 0);
        map.put(year+"-08", 0);
        map.put(year+"-09", 0);
        map.put(year+"-10", 0);
        map.put(year+"-11", 0);
        map.put(year+"-12", 0);
        return map;
    }

}
