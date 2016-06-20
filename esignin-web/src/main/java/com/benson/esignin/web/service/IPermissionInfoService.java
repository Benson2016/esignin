package com.benson.esignin.web.service;

import com.benson.esignin.common.base.IBaseService;
import com.benson.esignin.web.domain.entity.PermissionInfo;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.PermissionInfoQuery;

import java.util.List;

/**
 * 权限信息Service接口
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright BensonXu Copyright (c) 2016
 * @since 2016年05月31日 15:50
 */
public interface IPermissionInfoService extends IBaseService<PermissionInfo, String> {

    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<PermissionInfo> findAllByQuery(PermissionInfoQuery query) throws Exception;

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    BensonPage<PermissionInfo> findByPage(PermissionInfoQuery query) throws Exception;

    /**
     * 根据ID数组批量删除记录
     * @param ids ID数组，多个值以逗号分隔
     * @return
     */
    int deleteByIds(String ids) throws Exception;

    /**
     * 查询角色拥有的权限
     * @param roleId 角色ID
     * @return
     * @throws Exception
     */
    List<PermissionInfo> findAllByRoleId(String roleId) throws Exception;
}
