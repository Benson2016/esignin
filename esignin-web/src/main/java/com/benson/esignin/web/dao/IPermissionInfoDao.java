package com.benson.esignin.web.dao;

import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.domain.entity.PermissionInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限DAO
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright BensonXu Copyright (c) 2016
 * @since 2016年05月31日 15:03
 */
@Repository("permissionInfoDao")
public interface IPermissionInfoDao extends IBaseDao<PermissionInfo, String> {

    final String TABLE_NAME = "t_sys_permission";

    final String BASE_COLUMN_LIST = "id, name, flag, description, create_time as createTime";

    final String INSERT_SQL = "INSERT into "+TABLE_NAME+"(id, name, flag, description, create_time) VALUES(#{id},#{name},#{flag},#{description},#{createTime})";


    @Insert(INSERT_SQL)
    int add(PermissionInfo entity);

    @Delete("DELETE FROM "+TABLE_NAME+" where id = #{id}")
    int delete(String id);

    @Update("UPDATE " + TABLE_NAME + " set name=#{name},flag=#{flag},description=#{description} WHERE id = #{id}")
    int update(PermissionInfo entity);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME+" WHERE id = #{id}")
    PermissionInfo findOne(@Param("id") String primaryKey);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME)
    List<PermissionInfo> findAll();

}
