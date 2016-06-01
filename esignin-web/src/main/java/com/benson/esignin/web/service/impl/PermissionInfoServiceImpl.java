package com.benson.esignin.web.service.impl;

import com.benson.esignin.common.base.BaseServiceImpl;
import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.dao.IPermissionInfoDao;
import com.benson.esignin.web.domain.entity.PermissionInfo;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.PermissionInfoQuery;
import com.benson.esignin.web.service.IPermissionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限Service接口实现类
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright BensonXu Copyright (c) 2016
 * @since 2016年05月31日 16:01
 */
@Service("permissionInfoService")
@Transactional
public class PermissionInfoServiceImpl extends BaseServiceImpl<PermissionInfo, String> implements IPermissionInfoService {

    @Autowired
    private IPermissionInfoDao permissionInfoDao;

    @Override
    public IBaseDao<PermissionInfo, String> getDao() {
        return permissionInfoDao;
    }


    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    public BensonPage<PermissionInfo> findByPage(PermissionInfoQuery query) {
        int total = permissionInfoDao.count(query);

        List<PermissionInfo> list = permissionInfoDao.findPage(query);

        BensonPage<PermissionInfo> page = new BensonPage<PermissionInfo>(query.getPage(), query.getSize(), list, total);

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
            result += permissionInfoDao.delete(id);
        }
        return result;
    }

}
