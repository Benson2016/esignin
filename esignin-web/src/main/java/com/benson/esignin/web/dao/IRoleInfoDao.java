package com.benson.esignin.web.dao;

import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.domain.entity.RoleInfo;
import com.benson.esignin.web.domain.vo.RoleInfoQuery;
import com.benson.esignin.web.provider.RoleSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色DAO
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright BensonXu Copyright (c) 2016
 * @since 2016年05月31日 15:15
 */
@Repository("roleInfoDao")
public interface IRoleInfoDao extends IBaseDao<RoleInfo, String> {

    final String TABLE_NAME = "t_sys_role";

    final String BASE_COLUMN_LIST = "id, name, flag, description, create_time as createTime";

    final String INSERT_SQL = "INSERT into "+TABLE_NAME+"(id, name, flag, description, create_time) VALUES(#{id},#{name},#{flag},#{description},#{createTime})";


    @Insert(INSERT_SQL)
    int add(RoleInfo entity);

    @Delete("DELETE FROM "+TABLE_NAME+" where id = #{id}")
    int delete(String id);

    @Update("UPDATE " + TABLE_NAME + " set name=#{name},flag=#{flag},description=#{description} WHERE id = #{id}")
    int update(RoleInfo entity);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME+" WHERE id = #{id}")
    RoleInfo findOne(@Param("id") String primaryKey);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME)
    List<RoleInfo> findAll();

    /**
     * 根据条件查询
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = RoleSqlProvider.class, method = "findAllByQuery")
    List<RoleInfo> findAllByQuery(RoleInfoQuery query);

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = RoleSqlProvider.class, method = "findPage")
    List<RoleInfo> findPage(RoleInfoQuery query);

    /**
     * 查询数量
     * 结合分页使用
     * @param query
     * @return
     */
    @SelectProvider(type = RoleSqlProvider.class, method = "count")
    int count(RoleInfoQuery query);
    
}
