package com.benson.esignin.web.dao;

import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.domain.entity.UserRoleInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户角色关系DAO
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright BensonXu Copyright (c) 2016
 * @since 2016年05月31日 15:21
 */
@Repository("userRoleInfoDao")
public interface IUserRoleInfoDao extends IBaseDao<UserRoleInfo, String> {

    final String TABLE_NAME = "t_sys_user_role";

    final String BASE_COLUMN_LIST = "id, user_id as userId, role_id as roleId";

    final String INSERT_SQL = "INSERT into "+TABLE_NAME+"(id, user_id, role_id) VALUES(#{id},#{userId},#{roleId})";


    @Insert(INSERT_SQL)
    int add(UserRoleInfo entity);

    @Delete("DELETE FROM "+TABLE_NAME+" where id = #{id}")
    int delete(String id);

    @Update("UPDATE " + TABLE_NAME + " set user_id=#{userId},role_id=#{roleId} WHERE id = #{id}")
    int update(UserRoleInfo entity);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME+" WHERE id = #{id}")
    UserRoleInfo findOne(@Param("id") String primaryKey);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME)
    List<UserRoleInfo> findAll();

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME+" WHERE user_id = #{userId}")
    List<UserRoleInfo> findAllByUserId(@Param("userId") String userId);

}
