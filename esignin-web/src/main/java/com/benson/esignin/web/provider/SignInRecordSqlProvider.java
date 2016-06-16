package com.benson.esignin.web.provider;

import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.dao.ISignInRecordDao;
import com.benson.esignin.web.domain.vo.SignInRecordQuery;

/**
 * 签到记录SQL提供者
 *
 * @author: Benson Xu
 * @date: 2016年06月05日 01:00
 */
public class SignInRecordSqlProvider {

    /**
     * 添加查询条件
     * @param query 查询条件
     * @param sql SQL语句
     */
    private void addCondition(SignInRecordQuery query, StringBuffer sql) {
        if (CommonUtil.isNotNull(query.getMobile())) {
            sql.append("and mobile like '%"+ query.getMobile() +"%'");
        }
        // 日期逻辑判断
        if (CommonUtil.isNotNull(query.getStartTime(), query.getEndTime())) {
            sql.append(" and create_time between '"+ query.getStartTime() +"' and '"+ query.getEndTime() +"'");
        } else if (CommonUtil.isNotNull(query.getStartTime())) {
            sql.append(" and create_time >= '"+ query.getStartTime() +"'");
        } else if (CommonUtil.isNotNull(query.getEndTime())) {
            sql.append(" and create_time <= '"+ query.getEndTime() +"'");
        }
    }


    public String findPage(SignInRecordQuery query) {

        StringBuffer sql = new StringBuffer("SELECT "+ ISignInRecordDao.BASE_COLUMN_LIST + " FROM  "+ ISignInRecordDao.TABLE_NAME +" where 1=1 ");
        addCondition(query, sql);

        //添加排序和分页
        int index = (query.getPage()-1) * query.getSize();
        sql.append(" order by create_time DESC LIMIT ").append(index).append(",").append(query.getSize());

        System.out.println("--->>>签到记录分页查询SQL：" + sql.toString());

        return sql.toString();
    }


    public String count(SignInRecordQuery query) {
        StringBuffer sql = new StringBuffer("SELECT count(id) FROM "+ ISignInRecordDao.TABLE_NAME +" where 1=1 ");
        addCondition(query, sql);

        System.out.println("--->>>签到记录COUNT查询SQL：" + sql.toString());

        return sql.toString();
    }


    public String findAllByQuery(SignInRecordQuery query) {

        StringBuffer sql = new StringBuffer("SELECT " + ISignInRecordDao.BASE_COLUMN_LIST + " FROM  " + ISignInRecordDao.TABLE_NAME + " where 1=1 ");
        addCondition(query, sql);
        sql.append(" order by create_time ASC ");

        return sql.toString();
    }

    public String statisticsSignIn() {

        String sql = "select c.sign_in_type as signInType, count(r.id) as counts from t_sign_in_record r, t_qr_code c where r.qrid=c.id group by c.sign_in_type order by c.sign_in_type asc";

        return sql;
    }

}
