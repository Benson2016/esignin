package com.benson.esignin.web.provider;

import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.domain.entity.UserInfo;

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
            sql.append(", full_name=#{fullName}");
        }
        if (CommonUtil.isNotNull(entity.getUpdateTime())) {
            sql.append(", update_time=#{updateTime}");
        }
        if (CommonUtil.isNotNull(entity.getIsValid())) {
            sql.append(", is_valid=#{isValid}");
        }
        sql.append("where id = #{id}");
        return sql.toString();
    }



}
