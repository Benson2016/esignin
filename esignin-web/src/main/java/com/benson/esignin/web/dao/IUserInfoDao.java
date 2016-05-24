package com.benson.esignin.web.dao;


import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.provider.UserSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userInfoDao")
public interface IUserInfoDao extends IBaseDao<UserInfo, String> {

    final String BASE_COLUMN_LIST = "id, user_name as userName, full_name as fullName, password, sex, mobile, email, age, create_time as createTime, update_time as updateTime, flag, is_valid as isValid";
    final String INSERT_SQL = "INSERT into t_sys_user(id, user_name, full_name, password, sex, mobile, email, age, create_time, update_time, flag, is_valid) VALUES(#{id},#{userName},#{fullName},#{password},#{sex},#{mobile},#{email},#{age},#{createTime},#{updateTime},#{flag},#{isValid})";

    @Insert(INSERT_SQL)
    int add(UserInfo entity);

    @Delete("DELETE FROM t_sys_user where id = #{id}")
    int delete(@Param("id") String id);

    @UpdateProvider(type=UserSqlProvider.class, method = "update")
    int update(UserInfo entity);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM  t_sys_user  WHERE id = #{id}")
    UserInfo findOne(@Param("id") String primaryKey);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM  t_sys_user")
    List<UserInfo> findAll();

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM  t_sys_user  WHERE user_name = #{userName} and password=#{password}")
    public UserInfo authentication(UserInfo userInfo);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM  t_sys_user  WHERE user_name = #{userName}")
    public UserInfo findByUserName(@Param("userName") String userName);

}