package com.benson.esignin.web.service.impl;

import com.benson.esignin.common.base.BaseServiceImpl;
import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.web.dao.IQrCodeDao;
import com.benson.esignin.web.domain.entity.QrCode;
import com.benson.esignin.web.service.IQrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 二维码Service接口实现类
 *
 * @author: Benson Xu
 * @date: 2016年05月29日 00:44
 */
@Service("qrCodeService")
@Transactional
public class QrCodeServiceImpl extends BaseServiceImpl<QrCode, String> implements IQrCodeService {

    @Autowired
    private IQrCodeDao qrCodeDao;


    @Override
    public IBaseDao<QrCode, String> getDao() {
        return qrCodeDao;
    }


}
