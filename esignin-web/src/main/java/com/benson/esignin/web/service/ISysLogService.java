package com.benson.esignin.web.service;

import com.benson.esignin.common.base.IBaseService;
import com.benson.esignin.web.domain.entity.SysLog;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.SysLogQuery;

import java.util.List;

/**
 * 系统日志接口
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月06日 14:16
 */
public interface ISysLogService extends IBaseService<SysLog, String> {

    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<SysLog> findAllByQuery(SysLogQuery query) throws Exception;

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    BensonPage<SysLog> findByPage(SysLogQuery query) throws Exception;

    /**
     * 根据ID数组批量删除记录
     * @param ids ID数组，多个值以逗号分隔
     * @return
     */
    int deleteByIds(String ids) throws Exception;
    
}
