package com.benson.esignin.web.dao;

import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.domain.entity.SignInRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {enter your description}
 *
 * @author: Benson Xu
 * @date: 2016年05月29日 09:26
 */
@Repository("signInRecordDao")
public interface ISignInRecordDao extends IBaseDao<SignInRecord, String> {

    final String TABLE_NAME = "t_sign_in_record";

    final String BASE_COLUMN_LIST = "id, qrid, user_id as userId, mobile, create_time as createTime, is_valid as isValid ";

    final String INSERT_SQL = "INSERT into "+TABLE_NAME+"(id, qrid, user_id, mobile, create_time, is_valid) VALUES(#{id},#{qrid},#{userId},#{mobile},#{createTime},#{isValid})";


    @Insert(INSERT_SQL)
    int add(SignInRecord entity);

    @Delete("DELETE FROM "+TABLE_NAME+" where id = #{id}")
    int delete(String id);

    @Update("UPDATE " + TABLE_NAME + " set is_valid=#{isValid} WHERE id = #{id}")
    int update(SignInRecord entity);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME+" WHERE id = #{id}")
    SignInRecord findOne(String primaryKey);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME)
    List<SignInRecord> findAll();

    /**
     * 根据业务ID查询签到情况
     * 结果结按照签到先后顺序排列
     * @param qrid
     * @return
     */
    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME+" WHERE qrid = #{qrid} order by create_time asc")
    List<SignInRecord> findAllByRqid(String qrid);

    /**
     * 根据业务ID和用户ID查询签到记录
     * @param qrid
     * @param userId
     * @return
     */
    @Select("select "+BASE_COLUMN_LIST+" from "+TABLE_NAME+" where qrid=#{qrid} and user_id=#{userId} order by create_time desc limit 1")
    SignInRecord findByQridAndUserId(@Param("qrid") String qrid, @Param("userId") String userId);

}