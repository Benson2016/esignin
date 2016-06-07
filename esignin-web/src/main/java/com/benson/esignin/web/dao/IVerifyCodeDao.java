package com.benson.esignin.web.dao;

import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.domain.entity.VerifyCode;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 验证码DAO接口
 *
 * @author: Benson Xu
 * @date: 2016年05月28日 10:37
 */
@Repository("verifyCodeDao")
public interface IVerifyCodeDao extends IBaseDao<VerifyCode, String> {

    final String TABLE_NAME = "t_verify_code";

    final String BASE_COLUMN_LIST = "id, mobile, verify_code as verifyCode, create_time as createTime, effective_time_end as effectiveTimeEnd, is_valid as isValid, send_status as sendStatus ";

    final String INSERT_SQL = "INSERT into "+TABLE_NAME+"(id, mobile, verify_code, create_time, effective_time_end, is_valid, send_status) VALUES(#{id},#{mobile},#{verifyCode},#{createTime},#{effectiveTimeEnd},#{isValid},#{sendStatus})";


    @Insert(INSERT_SQL)
    int add(VerifyCode entity);

    @Delete("DELETE FROM "+TABLE_NAME+" where id = #{id}")
    int delete(String id);

    @Update("UPDATE " + TABLE_NAME + " set send_status=#{sendStatus} WHERE id = #{id}")
    int update(VerifyCode entity);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME+" WHERE id = #{id}")
    VerifyCode findOne(String primaryKey);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME)
    List<VerifyCode> findAll();

    // 根据手机号和有效时间查询最近一次有效的验证码
    @Select("select "+BASE_COLUMN_LIST+" from "+TABLE_NAME+" where mobile=#{mobile} and effective_time_end > sysdate() order by effective_time_end desc limit 1")
    VerifyCode findByMobile(String mobile);
}
