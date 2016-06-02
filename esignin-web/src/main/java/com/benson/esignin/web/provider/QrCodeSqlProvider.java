package com.benson.esignin.web.provider;

import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.dao.IQrCodeDao;
import com.benson.esignin.web.domain.vo.QrCodeQuery;

/**
 * 二维码SQL提供者
 *
 * @author: Benson Xu
 * @date: 2016年06月02日 07:37
 */
public class QrCodeSqlProvider {

    public String findPage(QrCodeQuery query) {

        StringBuffer sql = new StringBuffer("SELECT "+ IQrCodeDao.BASE_COLUMN_LIST + " FROM  "+ IQrCodeDao.TABLE_NAME +" where 1=1 ");
        if (CommonUtil.isNotNull(query.getTitle())) {
            sql.append("and title like '%"+ query.getTitle() +"%'");
        }
        if (CommonUtil.isNotNull(query.getSignInType())) {
            sql.append(" and sign_in_type = "+ query.getSignInType());
        }
        // 日期逻辑判断
        if (CommonUtil.isNotNull(query.getStartTime(), query.getEndTime())) {
            sql.append(" and effective_time_start between '"+ query.getStartTime() +"' and '"+ query.getEndTime() +"'");
        } else if (CommonUtil.isNotNull(query.getStartTime())) {
            sql.append(" and effective_time_start >= '"+ query.getStartTime() +"'");
        } else if (CommonUtil.isNotNull(query.getEndTime())) {
            sql.append(" and effective_time_start <= '"+ query.getEndTime() +"'");
        }

        //添加排序和分页
        int index = (query.getPage()-1) * query.getSize();
        sql.append(" order by effective_time_start DESC LIMIT ").append(index).append(",").append(query.getSize());

        System.out.println("--->>>二维码分页查询SQL：" + sql.toString());

        return sql.toString();
    }

    public String count(QrCodeQuery query) {
        StringBuffer sql = new StringBuffer("SELECT count(id) FROM "+ IQrCodeDao.TABLE_NAME +" where 1=1 ");
        if (CommonUtil.isNotNull(query.getTitle())) {
            sql.append("and name like '%"+ query.getTitle() +"%'");
        }
        System.out.println("--->>>二维码count查询SQL：" + sql.toString());

        return sql.toString();
    }
    
}
