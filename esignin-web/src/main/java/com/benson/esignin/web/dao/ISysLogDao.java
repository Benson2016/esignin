package com.benson.esignin.web.dao;

import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.domain.entity.SysLog;
import com.benson.esignin.web.domain.vo.SysLogQuery;
import com.benson.esignin.web.provider.SysLogSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统日志DAO接口
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月06日 11:57
 */
@Repository("sysLogDao")
public interface ISysLogDao extends IBaseDao<SysLog, String> {

    final String TABLE_NAME = "t_sys_log";

    final String BASE_COLUMN_LIST = "id, ip, user_name as userName, module_name as moduleName, oper_content as operContent, oper_time as operTime, consume_time as consumeTime";

    final String INSERT_SQL = "INSERT into "+TABLE_NAME+"(id, ip, user_name, module_name, oper_content, oper_time, consume_time) VALUES(#{id},#{ip},#{userName},#{moduleName},#{operContent},#{operTime},#{consumeTime})";


    @Insert(INSERT_SQL)
    int add(SysLog entity);

    @Delete("DELETE FROM "+TABLE_NAME+" where id = #{id}")
    int delete(String id);

    @UpdateProvider(type = SysLogSqlProvider.class, method = "update")
    int update(SysLog entity);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME+" WHERE id = #{id}")
    SysLog findOne(@Param("id") String primaryKey);

    @Select("SELECT "+BASE_COLUMN_LIST+" FROM "+TABLE_NAME)
    List<SysLog> findAll();


    /**
     * 条件查询
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = SysLogSqlProvider.class, method = "findAllByQuery")
    List<SysLog> findAllByQuery(SysLogQuery query);

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = SysLogSqlProvider.class, method = "findPage")
    List<SysLog> findPage(SysLogQuery query);

    /**
     * 查询数量
     * 结合分页使用
     * @param query
     * @return
     */
    @SelectProvider(type = SysLogSqlProvider.class, method = "count")
    int count(SysLogQuery query);

}
