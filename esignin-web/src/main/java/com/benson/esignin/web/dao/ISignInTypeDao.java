package com.benson.esignin.web.dao;

import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.domain.entity.SignInType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 签到类型DAO
 *
 * @author: Benson Xu
 * @date: 2016年05月29日 00:16
 */
@Repository("signInTypeDao")
public interface ISignInTypeDao extends IBaseDao<SignInType, Integer> {

    final String TABLE_NAME = "t_sign_in_type";

    final String BASE_COLUMN_LIST = "id, type_name as typeName";

    final String INSERT_SQL = "INSERT into "+TABLE_NAME+"(id, type_name) VALUES(#{id},#{typeName})";


    @Insert(INSERT_SQL)
    int add(SignInType entity);

    @Delete("DELETE FROM "+TABLE_NAME+" where id = #{id}")
    int delete(Integer id);

    @Update("UPDATE "+TABLE_NAME+" set type_name=#{typeName} WHERE id = #{id}")
    int update(SignInType entity);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME+" WHERE id = #{id}")
    SignInType findOne(@Param("id") Integer primaryKey);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME+" order by id asc")
    List<SignInType> findAll();

}
