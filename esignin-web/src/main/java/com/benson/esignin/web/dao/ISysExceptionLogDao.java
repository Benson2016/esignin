package com.benson.esignin.web.dao;

import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.domain.entity.SysExceptionLog;
import com.benson.esignin.web.domain.vo.SysLogQuery;
import com.benson.esignin.web.provider.SysExceptionLogSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统异常日志DAO
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月06日 14:53
 */
@Repository("sysExceptionLogDao")
public interface ISysExceptionLogDao extends IBaseDao<SysExceptionLog, String> {

    final String TABLE_NAME = "t_sys_exception_log";

    final String BASE_COLUMN_LIST = "id, ip, user_name as userName, module_name as moduleName, oper_content as opeContent, exception, oper_time as operTime, consume_time as consumeTime";

    final String INSERT_SQL = "INSERT into "+TABLE_NAME+"(id, ip, user_name, module_name, oper_content, exception, oper_time, consume_time) VALUES(#{id},#{ip},#{userName},#{moduleName},#{opeContent},#{exception},#{operTime},#{consumeTime})";


    @Insert(INSERT_SQL)
    int add(SysExceptionLog entity);

    @Delete("DELETE FROM "+TABLE_NAME+" where id = #{id}")
    int delete(String id);

    @UpdateProvider(type = SysExceptionLogSqlProvider.class, method = "update")
    int update(SysExceptionLog entity);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME+" WHERE id = #{id}")
    SysExceptionLog findOne(@Param("id") String primaryKey);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME)
    List<SysExceptionLog> findAll();


    /**
     * 条件查询
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = SysExceptionLogSqlProvider.class, method = "findAllByQuery")
    List<SysExceptionLog> findAllByQuery(SysLogQuery query);

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = SysExceptionLogSqlProvider.class, method = "findPage")
    List<SysExceptionLog> findPage(SysLogQuery query);

    /**
     * 查询数量
     * 结合分页使用
     * @param query
     * @return
     */
    @SelectProvider(type = SysExceptionLogSqlProvider.class, method = "count")
    int count(SysLogQuery query);
    
}
