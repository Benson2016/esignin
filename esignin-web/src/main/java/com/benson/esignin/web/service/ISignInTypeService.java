package com.benson.esignin.web.service;

import com.benson.esignin.common.base.IBaseService;
import com.benson.esignin.web.domain.entity.SignInType;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.SignInTypeQuery;

import java.util.List;

/**
 * 签到类型Service接口
 *
 * @author: Benson Xu
 * @date: 2016年05月29日 00:51
 */
public interface ISignInTypeService extends IBaseService<SignInType, Integer> {

    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<SignInType> findAllByQuery(SignInTypeQuery query);

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    BensonPage<SignInType> findByPage(SignInTypeQuery query);

    /**
     * 根据ID数组批量删除记录
     * @param ids ID数组，多个值以逗号分隔
     * @return
     */
    int deleteByIds(String ids);

}
