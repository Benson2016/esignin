package com.benson.esignin.web.provider;

import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.dao.ISysExceptionLogDao;
import com.benson.esignin.web.domain.entity.SysExceptionLog;
import com.benson.esignin.web.domain.vo.SysLogQuery;

/**
 * 系统异常日志SQL提供者
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月06日 14:56
 */
public class SysExceptionLogSqlProvider {

    /**
     * 根据条件查询
     * @param query
     * @return
     */
    public String findAllByQuery(SysLogQuery query) {

        StringBuffer sql = new StringBuffer("SELECT "+ ISysExceptionLogDao.BASE_COLUMN_LIST + " FROM  "+ ISysExceptionLogDao.TABLE_NAME +" where 1=1 ");
        addCondition(query, sql);

        //添加排序
        sql.append(" order by oper_time DESC");

        return sql.toString();
    }

    public String findPage(SysLogQuery query) {

        StringBuffer sql = new StringBuffer("SELECT "+ ISysExceptionLogDao.BASE_COLUMN_LIST + " FROM  "+ ISysExceptionLogDao.TABLE_NAME +" where 1=1 ");
        addCondition(query, sql);

        //添加排序和分页
        int index = (query.getPage()-1) * query.getSize();
        sql.append(" order by oper_time DESC LIMIT ").append(index).append(",").append(query.getSize());

        System.out.println("--->>>系统异常日志分页查询SQL：" + sql.toString());

        return sql.toString();
    }

    public String count(SysLogQuery query) {
        StringBuffer sql = new StringBuffer("SELECT count(id) FROM "+ ISysExceptionLogDao.TABLE_NAME +" where 1=1 ");
        addCondition(query, sql);
        System.out.println("--->>>系统异常日志COUNT查询SQL：" + sql.toString());

        return sql.toString();
    }

    /**
     * 添加查询条件
     * @param query
     * @param sql
     */
    private void addCondition(SysLogQuery query, StringBuffer sql) {
        if (CommonUtil.isNotNull(query.getUserName())) {
            sql.append("and user_name like '%"+ query.getUserName() +"%'");
        }
        if (CommonUtil.isNotNull(query.getModuleName())) {
            sql.append(" and module_name like '%"+ query.getModuleName() +"%'");
        }
        if (CommonUtil.isNotNull(query.getOperContent())) {
            sql.append(" and oper_content like '%"+ query.getOperContent() +"%'");
        }
        // 日期逻辑判断
        if (CommonUtil.isNotNull(query.getStartTime(), query.getEndTime())) {
            sql.append(" and oper_time between '"+ query.getStartTime() +"' and '"+ query.getEndTime() +"'");
        } else if (CommonUtil.isNotNull(query.getStartTime())) {
            sql.append(" and oper_time >= '"+ query.getStartTime() +"'");
        } else if (CommonUtil.isNotNull(query.getEndTime())) {
            sql.append(" and oper_time <= '"+ query.getEndTime() +"'");
        }
    }


    public String update(SysExceptionLog entity) {
        StringBuffer sql = new StringBuffer("update "+ ISysExceptionLogDao.TABLE_NAME +" set ip=" + entity.getIp());
        if (CommonUtil.isNotNull(entity.getUserName())) {
            sql.append(", user_name='"+ entity.getUserName() + "'");
        }
        if (CommonUtil.isNotNull(entity.getModuleName())) {
            sql.append(", module_name='" + entity.getModuleName()+"'");
        }
        if (CommonUtil.isNotNull(entity.getOperContent())) {
            sql.append(", oper_content='"+ entity.getOperContent() +"'");
        }
        if (CommonUtil.isNotNull(entity.getException())) {
            sql.append(", exception='"+ entity.getException() +"'");
        }
        /*if (CommonUtil.isNotNull(entity.getOperTime())) { // 操作时间作为创建时间不做修改
            sql.append(", oper_time='"+ entity.getOperTimeStr() +"'");
        }*/
        if (CommonUtil.isNotNull(entity.getConsumeTime())) {
            sql.append(", consume_time='"+ entity.getConsumeTime() +"'");
        }

        sql.append(String.format(" where id = '%s'", entity.getId()));

        System.out.println("SysExceptionLog-Update-SQL: " + sql.toString());
        return sql.toString();
    }

}
