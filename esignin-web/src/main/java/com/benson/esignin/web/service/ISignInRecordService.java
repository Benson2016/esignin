package com.benson.esignin.web.service;

import com.benson.esignin.common.base.IBaseService;
import com.benson.esignin.web.domain.entity.SignInRecord;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.SignInRecordQuery;

import java.util.List;

/**
 * 签到记录Service接口
 *
 * @author: Benson Xu
 * @date: 2016年05月29日 09:35
 */
public interface ISignInRecordService extends IBaseService<SignInRecord, String> {

    public List<SignInRecord> findAllByBusinessId(String businessId);

    public SignInRecord findByQridAndUserId(String qrid, String userId);


    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<SignInRecord> findAllByQuery(SignInRecordQuery query);

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    BensonPage<SignInRecord> findByPage(SignInRecordQuery query);

    /**
     * 根据ID数组批量删除记录
     * @param ids ID数组，多个值以逗号分隔
     * @return
     */
    int deleteByIds(String ids);

}
