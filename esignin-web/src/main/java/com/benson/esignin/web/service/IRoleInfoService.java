package com.benson.esignin.web.service;

import com.benson.esignin.common.base.IBaseService;
import com.benson.esignin.web.domain.entity.RoleInfo;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.RoleInfoQuery;

import java.util.List;

/**
 * 角色信息Service接口
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年05月31日 15:55
 */
public interface IRoleInfoService extends IBaseService<RoleInfo, String> {

    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<RoleInfo> findAllByQuery(RoleInfoQuery query);

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    BensonPage<RoleInfo> findByPage(RoleInfoQuery query);

    /**
     * 根据ID数组批量删除记录
     * @param ids ID数组，多个值以逗号分隔
     * @return
     */
    int deleteByIds(String ids);

}
