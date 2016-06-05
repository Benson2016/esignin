package com.benson.esignin.web.dao;

import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.domain.entity.QrCode;
import com.benson.esignin.web.domain.vo.QrCodeQuery;
import com.benson.esignin.web.provider.QrCodeSqlProvider;
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

    final String BASE_COLUMN_LIST = "id, title, sign_in_type as signInType, create_user as createUser, image, effective_time_start as effectiveTimeStart, effective_time_end as effectiveTimeEnd,create_time as createTime, is_valid as isValid, description";

    final String INSERT_SQL = "INSERT into "+TABLE_NAME+"(id, title, sign_in_type, create_user, image, effective_time_start, effective_time_end, create_time, is_valid, description) VALUES(#{id},#{title},#{signInType},#{createUser},#{image},#{effectiveTimeStart},#{effectiveTimeEnd},#{createTime},#{isValid},#{description})";


    @Insert(INSERT_SQL)
    int add(QrCode entity);

    @Delete("DELETE FROM "+TABLE_NAME+" where id = #{id}")
    int delete(String id);

    @UpdateProvider(type = QrCodeSqlProvider.class, method = "update")
    int update(QrCode entity);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME+" WHERE id = #{id}")
    QrCode findOne(@Param("id") String primaryKey);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME)
    List<QrCode> findAll();

    // 根据签到类型查询
    @Select("select "+BASE_COLUMN_LIST+" from "+TABLE_NAME+" where sign_in_type=#{signInType} ")
    List<QrCode> findBySignType(Integer signInType);


    /**
     * 条件查询
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = QrCodeSqlProvider.class, method = "findAllByQuery")
    List<QrCode> findAllByQuery(QrCodeQuery query);

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = QrCodeSqlProvider.class, method = "findPage")
    List<QrCode> findPage(QrCodeQuery query);

    /**
     * 查询数量
     * 结合分页使用
     * @param query
     * @return
     */
    @SelectProvider(type = QrCodeSqlProvider.class, method = "count")
    int count(QrCodeQuery query);
    
}
