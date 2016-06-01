package com.benson.esignin.web.provider;

import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.dao.IUserInfoDao;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.domain.vo.UserInfoQuery;

import java.util.List;

/**
 * 用户SQL提供者
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 深圳市华阳信通科技发展有限公司 Copyright (c) 2016
 * @since 2016年05月23日 14:52
 */
public class UserSqlProvider {

    public String update(UserInfo entity) {
        StringBuffer sql = new StringBuffer("update t_sys_user set user_name=#{userName} ");
        if (CommonUtil.isNotNull(entity.getFullName())) {
            sql.append(", full_name='"+ entity.getFullName() +"'");
        }
        if (CommonUtil.isNotNull(entity.getUpdateTime())) {
            sql.append(", update_time='"+ entity.getUpdateTime() +"'");
        }
        if (CommonUtil.isNotNull(entity.getIsValid())) {
            sql.append(", is_valid=" + entity.getIsValid());
        }
        if (CommonUtil.isNotNull(entity.getMobile())) {
            sql.append(", mobile='"+ entity.getMobile() +"'");
        }
        sql.append("where id = #{id}");
        return sql.toString();
    }

    public String findPage(UserInfoQuery query) {

        StringBuffer sql = new StringBuffer("SELECT "+ IUserInfoDao.BASE_COLUMN_LIST + " FROM  t_sys_user where 1=1 ");
        if (CommonUtil.isNotNull(query.getMobile())) {
            sql.append("and mobile like '%"+ query.getMobile() +"%'");
        }
        if (CommonUtil.isNotNull(query.getFullName())) {
            sql.append("and full_name like '%"+ query.getFullName() +"%'");
        }
        if (CommonUtil.isNotNull(query.getUserName())) {
            sql.append("and user_name like '%"+ query.getUserName() +"%'");
        }

        //添加排序和分页
        int index = (query.getPage()-1) * query.getSize();
        sql.append(" order by create_time DESC LIMIT ").append(index).append(",").append(query.getSize());

        System.out.println("--->>>用户分页查询SQL：" + sql.toString());

        return sql.toString();
    }

    public String count(UserInfoQuery query) {
        StringBuffer sql = new StringBuffer("SELECT count(id) FROM t_sys_user where 1=1 ");
        if (CommonUtil.isNotNull(query.getMobile())) {
            sql.append("and mobile like '%"+ query.getMobile() +"%'");
        }
        if (CommonUtil.isNotNull(query.getFullName())) {
            sql.append("and full_name like '%"+ query.getFullName() +"%'");
        }
        if (CommonUtil.isNotNull(query.getUserName())) {
            sql.append("and user_name like '%"+ query.getUserName() +"%'");
        }
        System.out.println("--->>>用户count查询SQL：" + sql.toString());

        return sql.toString();
    }

    public String deleteByBatch(String ids) {

        StringBuffer sql = new StringBuffer("DELETE FROM t_sys_user WHERE id in (''");

        String[] idArray = ids.split(",");

        for (String id : idArray) {
            sql.append(", '" + id + "'");
        }
        sql.append(")");

        System.out.println("--->>>deleteByIds-SQL：" + sql.toString());

        return sql.toString();
    }

}
