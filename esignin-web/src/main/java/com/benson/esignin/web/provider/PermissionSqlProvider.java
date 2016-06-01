package com.benson.esignin.web.provider;

import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.dao.IPermissionInfoDao;
import com.benson.esignin.web.domain.vo.PermissionInfoQuery;

/**
 * 权限SQL提供者
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月01日 18:24
 */
public class PermissionSqlProvider {

    public String findPage(PermissionInfoQuery query) {

        StringBuffer sql = new StringBuffer("SELECT "+ IPermissionInfoDao.BASE_COLUMN_LIST + " FROM  "+ IPermissionInfoDao.TABLE_NAME +" where 1=1 ");
        if (CommonUtil.isNotNull(query.getName())) {
            sql.append("and name like '%"+ query.getName() +"%'");
        }

        //添加排序和分页
        int index = (query.getPage()-1) * query.getSize();
        sql.append(" order by create_time DESC LIMIT ").append(index).append(",").append(query.getSize());

        System.out.println("--->>>权限分页查询SQL：" + sql.toString());

        return sql.toString();
    }

    public String count(PermissionInfoQuery query) {
        StringBuffer sql = new StringBuffer("SELECT count(id) FROM "+ IPermissionInfoDao.TABLE_NAME +" where 1=1 ");
        if (CommonUtil.isNotNull(query.getName())) {
            sql.append("and name like '%"+ query.getName() +"%'");
        }
        System.out.println("--->>>权限count查询SQL：" + sql.toString());

        return sql.toString();
    }

}
