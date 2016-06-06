package com.benson.esignin.web.service;

import com.benson.esignin.common.base.IBaseService;
import com.benson.esignin.web.domain.entity.SysExceptionLog;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.SysLogQuery;

import java.util.List;

/**
 * 系统异常日志接口
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月06日 15:24
 */
public interface ISysExceptionLogService extends IBaseService<SysExceptionLog, String> {

    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<SysExceptionLog> findAllByQuery(SysLogQuery query);

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    BensonPage<SysExceptionLog> findByPage(SysLogQuery query);

    /**
     * 根据ID数组批量删除记录
     * @param ids ID数组，多个值以逗号分隔
     * @return
     */
    int deleteByIds(String ids);
    
}
