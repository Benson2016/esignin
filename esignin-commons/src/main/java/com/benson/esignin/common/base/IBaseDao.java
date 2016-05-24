package com.benson.esignin.common.base;

import java.io.Serializable;
import java.util.List;

/**
 * 所有自定义Dao的基类
 * DAO泛型接口，定义基本的DAO功能
 *
 * @since 2016年05月12日 19:16
 * @author Benson Xu
 * @version 1.0
 */
public interface IBaseDao<T, PK extends Serializable> {

    /**
     * 插入一个实体
     * @param entity 实体对象
     */
    public abstract int add(T entity);

    /**
     * 根据主键ID删除对象
     * @param id 主键ID
     * @return 执行结果
     */
    public abstract int delete(PK id);

    /**
     * 修改一个实体对象
     * @param entity 实体对象
     * @return 修改的实体个数
     */
    public abstract int update(T entity);

    /**
     * 根据主键获取记录
     * @param primaryKey 主键
     * @return 实体对象
     */
    public abstract T findOne(PK primaryKey);

    /**
     * 获取所有记录实体对象
     * @return 实体对象集合
     */
    public abstract List<T> findAll();

}
