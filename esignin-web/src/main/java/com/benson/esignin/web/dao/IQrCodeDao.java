package com.benson.esignin.web.dao;

import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.domain.entity.QrCode;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 二维码DAO
 *
 * @author: Benson Xu
 * @date: 2016年05月29日 00:14
 */
@Repository("qrCodeDao")
public interface IQrCodeDao extends IBaseDao<QrCode, String> {

    final String TABLE_NAME = "t_qr_code";

    final String BASE_COLUMN_LIST = "id, title, sign_in_type as signInType, create_user as createUser, image, effective_time_start as effectiveTimeStart, effective_time_end as effectiveTimeEnd, is_valid as isValid, description";

    final String INSERT_SQL = "INSERT into "+TABLE_NAME+"(id, title, sign_in_type, create_user, image, effective_time_start, effective_time_end, is_valid, description) VALUES(#{id},#{title},#{signInType},#{createUser},#{image},#{effectiveTimeStart},#{effectiveTimeEnd},#{isValid},#{description})";


    @Insert(INSERT_SQL)
    int add(QrCode entity);

    @Delete("DELETE FROM "+TABLE_NAME+" where id = #{id}")
    int delete(String id);

    @Update("UPDATE " + TABLE_NAME + " set is_valid=#{isValid},title=#{title},description=#{description} WHERE id = #{id}")
    int update(QrCode entity);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME+" WHERE id = #{id}")
    QrCode findOne(@Param("id") String primaryKey);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME)
    List<QrCode> findAll();

    // 根据签到类型查询
    @Select("select "+BASE_COLUMN_LIST+" from "+TABLE_NAME+" where sign_in_type=#{signInType} ")
    List<QrCode> findBySignType(Integer signInType);
    
}
