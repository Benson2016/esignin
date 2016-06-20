package com.benson.esignin.web.service;

import com.benson.esignin.common.base.IBaseService;
import com.benson.esignin.web.domain.entity.VerifyCode;

/**
 * 验证码Service接口
 *
 * @author: Benson Xu
 * @date: 2016年05月28日 12:57
 */
public interface IVerifyCodeService extends IBaseService<VerifyCode, String> {

    public VerifyCode findByMobile(String mobile) throws Exception;

}
