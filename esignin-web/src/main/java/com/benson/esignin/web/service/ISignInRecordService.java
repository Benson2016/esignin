package com.benson.esignin.web.service;

import com.benson.esignin.common.base.IBaseService;
import com.benson.esignin.web.domain.entity.SignInRecord;

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

}
