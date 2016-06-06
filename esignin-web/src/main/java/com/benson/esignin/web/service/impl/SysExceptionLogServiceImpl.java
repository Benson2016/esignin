package com.benson.esignin.web.service.impl;

import com.benson.esignin.common.base.BaseServiceImpl;
import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.dao.ISysExceptionLogDao;
import com.benson.esignin.web.domain.entity.SysExceptionLog;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.SysLogQuery;
import com.benson.esignin.web.service.ISysExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统异常接口实现类
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月06日 15:26
 */
@Service("sysExceptionLogService")
@Transactional
public class SysExceptionLogServiceImpl extends BaseServiceImpl<SysExceptionLog, String> implements ISysExceptionLogService {

    @Autowired
    private ISysExceptionLogDao SysExceptionLogDao;

    @Override
    public IBaseDao<SysExceptionLog, String> getDao() {
        return SysExceptionLogDao;
    }


    /**
     * 根据条件查询
     * @param query 查询条件
     * @return
     */
    public List<SysExceptionLog> findAllByQuery(SysLogQuery query) {
        return SysExceptionLogDao.findAllByQuery(query);
    }

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    public BensonPage<SysExceptionLog> findByPage(SysLogQuery query) {
        int total = SysExceptionLogDao.count(query);

        List<SysExceptionLog> list = SysExceptionLogDao.findPage(query);

        BensonPage<SysExceptionLog> page = new BensonPage<SysExceptionLog>(query.getPage(), query.getSize(), list, total);

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
            result += SysExceptionLogDao.delete(id);
        }
        return result;
    }
    
}
