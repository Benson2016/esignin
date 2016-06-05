package com.benson.esignin.web.service.impl;

import com.benson.esignin.common.base.BaseServiceImpl;
import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.dao.IRoleInfoDao;
import com.benson.esignin.web.domain.entity.PermissionInfo;
import com.benson.esignin.web.domain.entity.RoleInfo;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.PermissionInfoQuery;
import com.benson.esignin.web.domain.vo.RoleInfoQuery;
import com.benson.esignin.web.service.IRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


    /**
     * 根据条件查询
     * @param query
     * @return
     */
    public List<RoleInfo> findAllByQuery(RoleInfoQuery query) {
        return roleInfoDao.findAllByQuery(query);
    }

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    public BensonPage<RoleInfo> findByPage(RoleInfoQuery query) {
        int total = roleInfoDao.count(query);

        List<RoleInfo> list = roleInfoDao.findPage(query);

        BensonPage<RoleInfo> page = new BensonPage<RoleInfo>(query.getPage(), query.getSize(), list, total);

        return page;
    }

    /**
     * 批量删除
     * @param ids ID数组，多个值以逗号分隔
     * @return
     */
    public int deleteByIds(String ids) {
        if (CommonUtil.isNull(ids)) {
            return -1;  // 如果参数为空，则直接返回-1
        }
        // 循环遍历删除
        String[] idArray = ids.split(",");
        int result = 0;
        for (String id : idArray) {
            result += roleInfoDao.delete(id);
        }
        return result;
    }

}
