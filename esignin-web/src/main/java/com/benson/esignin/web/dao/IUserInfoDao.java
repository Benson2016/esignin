package com.benson.esignin.web.dao;


import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.domain.vo.UserInfoQuery;
import com.benson.esignin.web.provider.UserSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userInfoDao")
public interface IUserInfoDao extends IBaseDao<UserInfo, String> {

    final String TABLE_NAME = "t_sys_user";

    final String BASE_COLUMN_LIST = "id, user_name as userName, full_name as fullName, password, sex, mobile, email, age, create_time as createTime, update_time as updateTime, flag, is_valid as isValid";

    final String INSERT_SQL = "INSERT into t_sys_user(id, user_name, full_name, password, sex, mobile, email, age, create_time, update_time, flag, is_valid) VALUES(#{id},#{userName},#{fullName},#{password},#{sex},#{mobile},#{email},#{age},#{createTime},#{updateTime},#{flag},#{isValid})";

    @Insert(INSERT_SQL)
    int add(UserInfo entity);

    @Delete("DELETE FROM "+ TABLE_NAME +" where id = #{id}")
    int delete(@Param("id") String id);

    @UpdateProvider(type=UserSqlProvider.class, method = "update")
    int update(UserInfo entity);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM  "+ TABLE_NAME +"  WHERE id = #{id}")
    UserInfo findOne(@Param("id") String primaryKey);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+ TABLE_NAME)
    List<UserInfo> findAll();

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+ TABLE_NAME +" WHERE user_name = #{userName} and password=#{password}")
    public UserInfo authentication(UserInfo userInfo);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+ TABLE_NAME +" WHERE user_name = #{userName}")
    public UserInfo findByUserName(@Param("userName") String userName);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+ TABLE_NAME +" WHERE mobile = #{mobile}")
    public UserInfo findByMobile(@Param("mobile") String mobile);

    /**
     * 根据条件查询
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = UserSqlProvider.class, method = "findAllByQuery")
    List<UserInfo> findAllByQuery(UserInfoQuery query);

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = UserSqlProvider.class, method = "findPage")
    List<UserInfo> findPage(UserInfoQuery query);

    /**
     * 查询用户数量
     * 结合分页使用
     * @param query
     * @return
     */
    @SelectProvider(type = UserSqlProvider.class, method = "count")
    int count(UserInfoQuery query);

    /**
     * 根据ID数组批量删除记录
     * @param ids ID数组，多个值以逗号分隔
     * @return
     */
    @DeleteProvider(type = UserSqlProvider.class, method = "deleteByBatch")
    @Options(flushCache =true, timeout =20000)
    int deleteByIds(@Param("ids") String ids);

}