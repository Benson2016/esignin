package com.benson.esignin.web.provider;

import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.dao.IRoleInfoDao;
import com.benson.esignin.web.domain.vo.RoleInfoQuery;

/**
 * 角色信息SQL提供者
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月01日 18:17
 */
public class RoleSqlProvider {

    public String findAllByQuery(RoleInfoQuery query) {

        StringBuffer sql = new StringBuffer("SELECT "+ IRoleInfoDao.BASE_COLUMN_LIST + " FROM  "+ IRoleInfoDao.TABLE_NAME +" where 1=1 ");
        addCondition(query, sql);

        //添加排序
        sql.append(" order by create_time DESC ");

        System.out.println("--->>>根据查询条件查询角色信息SQL：" + sql.toString());

        return sql.toString();
    }

    public String findPage(RoleInfoQuery query) {

        StringBuffer sql = new StringBuffer("SELECT "+ IRoleInfoDao.BASE_COLUMN_LIST + " FROM  "+ IRoleInfoDao.TABLE_NAME +" where 1=1 ");
        addCondition(query, sql);

        //添加排序和分页
        int index = (query.getPage()-1) * query.getSize();
        sql.append(" order by create_time DESC LIMIT ").append(index).append(",").append(query.getSize());

        System.out.println("--->>>角色分页查询SQL：" + sql.toString());

        return sql.toString();
    }

    public String count(RoleInfoQuery query) {
        StringBuffer sql = new StringBuffer("SELECT count(id) FROM "+ IRoleInfoDao.TABLE_NAME +" where 1=1 ");
        addCondition(query, sql);
        System.out.println("--->>>角色count查询SQL：" + sql.toString());

        return sql.toString();
    }

    /**
     * 添加查询条件
     * @param query
     * @param sql
     */
    private void addCondition(RoleInfoQuery query, StringBuffer sql) {
        if (CommonUtil.isNotNull(query.getName())) {
            sql.append("and name like '%"+ query.getName() +"%'");
        }
    }

}
