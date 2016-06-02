package com.benson.esignin.web.service.impl;

import com.benson.esignin.common.base.BaseServiceImpl;
import com.benson.esignin.common.base.IBaseDao;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.web.dao.IQrCodeDao;
import com.benson.esignin.web.domain.entity.QrCode;
import com.benson.esignin.web.domain.entity.QrCode;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.QrCodeQuery;
import com.benson.esignin.web.service.IQrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    public BensonPage<QrCode> findByPage(QrCodeQuery query) {
        int total = qrCodeDao.count(query);

        List<QrCode> list = qrCodeDao.findPage(query);

        BensonPage<QrCode> page = new BensonPage<QrCode>(query.getPage(), query.getSize(), list, total);

        return page;
    }

    /**
     * 批量删除
     * @param ids ID数组，多个值以逗号分隔
     * @return
     */
    public int deleteByIds(String ids) {
        if (CommonUtil.isNull(ids)) {
            return -1;  // 如果参数为空，则直接返回-1
        }

        // 循环遍历删除
        String[] idArray = ids.split(",");
        int result = 0;
        for (String id : idArray) {
            result += qrCodeDao.delete(id);
        }
        return result;
    }
    
}
