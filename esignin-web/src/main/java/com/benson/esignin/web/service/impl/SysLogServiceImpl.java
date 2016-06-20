package com.benson.esignin.web.service.impl;

import com.benson.esignin.common.base.BaseServiceImpl;
import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.annotation.SysServiceLog;
import com.benson.esignin.web.dao.ISysLogDao;
import com.benson.esignin.web.domain.entity.SysLog;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.SysLogQuery;
import com.benson.esignin.web.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统日志接口实现类
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月06日 14:23
 */
@Service("sysLogService")
@Transactional
public class SysLogServiceImpl extends BaseServiceImpl<SysLog, String> implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public IBaseDao<SysLog, String> getDao() {
        return sysLogDao;
    }


    /**
     * 根据条件查询
     * @param query 查询条件
     * @return
     */
    @SysServiceLog(content = "根据条件查询系统日志")
    public List<SysLog> findAllByQuery(SysLogQuery query) throws Exception {
        return sysLogDao.findAllByQuery(query);
    }

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    public BensonPage<SysLog> findByPage(SysLogQuery query) throws Exception {
        int total = sysLogDao.count(query);

        List<SysLog> list = sysLogDao.findPage(query);

        BensonPage<SysLog> page = new BensonPage<SysLog>(query.getPage(), query.getSize(), list, total);

        return page;
    }

    /**
     * 批量删除
     * @param ids ID数组，多个值以逗号分隔
     * @return
     */
    @SysServiceLog(content = "批量删除系统日志记录")
    public int deleteByIds(String ids) throws Exception {
        if (CommonUtil.isNull(ids)) {
            return -1;  // 如果参数为空，则直接返回-1
        }

        // 循环遍历删除
        String[] idArray = ids.split(",");
        int result = 0;
        for (String id : idArray) {
            result += sysLogDao.delete(id);
        }
        return result;
    }
}
