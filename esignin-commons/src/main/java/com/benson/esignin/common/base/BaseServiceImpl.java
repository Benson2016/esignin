package com.benson.esignin.common.base;

import java.io.Serializable;
import java.util.List;

/**
 * Service接口的基本实现类
 * 主要用于实现DB的CRUD基本操作，简化每个业务的Service定义
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 深圳市华阳信通科技发展有限公司 Copyright (c) 2016
 * @since 2016年05月23日 12:47
 */
public abstract class BaseServiceImpl<T, PK extends Serializable> implements IBaseService<T, PK> {

    /**
     * 获取业务Dao接口
     * 该方法由子类去实现
     * @return Dao接口
     */
    public abstract IBaseDao<T, PK> getDao();

    public int add(T entity) throws Exception {
        return getDao().add(entity);
    }

    public int delete(PK id) throws Exception {
        return getDao().delete(id);
    }

    public int delete(List<PK> idList) throws Exception {
        int result = 0;
        // 批量删除，暂未实现
        // 暂定循环遍历删除
        for (PK id : idList) {
            result += delete(id);
        }
        return result;
    }

    public int update(T entity) throws Exception {
        return getDao().update(entity);
    }

    public T findOne(PK id) throws Exception {
        return getDao().findOne(id);
    }

    public List<T> findAll() throws Exception {
        return getDao().findAll();
    }

}
