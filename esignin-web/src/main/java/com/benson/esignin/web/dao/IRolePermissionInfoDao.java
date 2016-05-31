package com.benson.esignin.web.dao;

import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.domain.entity.RolePermissionInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色权限关系信息DAO
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright BensonXu Copyright (c) 2016
 * @since 2016年05月31日 15:29
 */
@Repository("rolePermissionInfoDao")
public interface IRolePermissionInfoDao extends IBaseDao<RolePermissionInfo, String> {

    final String TABLE_NAME = "t_sys_role_permission";

    final String BASE_COLUMN_LIST = "id, role_id as roleId, permission_id as permissionId";

    final String INSERT_SQL = "INSERT into "+TABLE_NAME+"(id, role_id, permission_id) VALUES(#{id},#{roleId},#{permissionId})";


    @Insert(INSERT_SQL)
    int add(RolePermissionInfo entity);

    @Delete("DELETE FROM "+TABLE_NAME+" where id = #{id}")
    int delete(String id);

    @Update("UPDATE " + TABLE_NAME + " set role_id=#{roleId},permission_id=#{permissionId} WHERE id = #{id}")
    int update(RolePermissionInfo entity);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME+" WHERE id = #{id}")
    RolePermissionInfo findOne(@Param("id") String primaryKey);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME)
    List<RolePermissionInfo> findAll();

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME+" WHERE role_id = #{roleId}")
    List<RolePermissionInfo> findAllByRoleId(@Param("roleId") String roleId);
    
}
