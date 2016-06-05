package com.benson.esignin.web.provider;

import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.dao.ISignInTypeDao;
import com.benson.esignin.web.domain.vo.SignInTypeQuery;

/**
 *
 * 签到类型SQL提供者
 * @author: Benson Xu
 * @date: 2016年06月04日 16:30
 */
public class SignInTypeSqlProvider {

    public String findPage(SignInTypeQuery query) {

        StringBuffer sql = new StringBuffer("SELECT "+ ISignInTypeDao.BASE_COLUMN_LIST + " FROM  "+ ISignInTypeDao.TABLE_NAME +" where 1=1 ");
        addCondition(query, sql);

        //添加排序和分页
        int index = (query.getPage()-1) * query.getSize();
        sql.append(" order by id DESC LIMIT ").append(index).append(",").append(query.getSize());

        System.out.println("--->>>签到类型分页查询SQL：" + sql.toString());

        return sql.toString();
    }

    /**
     * 添加查询条件
     * @param query
     * @param sql
     */
    private void addCondition(SignInTypeQuery query, StringBuffer sql) {
        if (CommonUtil.isNotNull(query.getTypeName())) {
            sql.append("and type_name like '%"+ query.getTypeName() +"%'");
        }
    }

    public String count(SignInTypeQuery query) {
        StringBuffer sql = new StringBuffer("SELECT count(id) FROM "+ ISignInTypeDao.TABLE_NAME +" where 1=1 ");
        addCondition(query, sql);

        System.out.println("--->>>签到类型分页查询SQL：" + sql.toString());

        return sql.toString();
    }


    public String findAllByQuery(SignInTypeQuery query) {

        StringBuffer sql = new StringBuffer("SELECT " + ISignInTypeDao.BASE_COLUMN_LIST + " FROM  " + ISignInTypeDao.TABLE_NAME + " where 1=1 ");
        addCondition(query, sql);
        sql.append(" order by id ASC ");

        return sql.toString();
    }

}
