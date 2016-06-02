package com.benson.esignin.web.service;

import com.benson.esignin.common.base.IBaseService;
import com.benson.esignin.web.domain.entity.QrCode;
import com.benson.esignin.web.domain.vo.BensonPage;
import com.benson.esignin.web.domain.vo.QrCodeQuery;

import java.util.List;

/**
 * 二维码Service接口
 *
 * @author: Benson Xu
 * @date: 2016年05月29日 00:42
 */
public interface IQrCodeService extends IBaseService<QrCode, String> {

    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<QrCode> findAllByQuery(QrCodeQuery query);

    /**
     * 分页查询
     * @param query 查询条件
     * @return
     */
    BensonPage<QrCode> findByPage(QrCodeQuery query);

    /**
     * 根据ID数组批量删除记录
     * @param ids ID数组，多个值以逗号分隔
     * @return
     */
    int deleteByIds(String ids);
}
