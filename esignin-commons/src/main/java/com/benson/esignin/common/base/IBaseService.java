package com.benson.esignin.common.base;

import java.io.Serializable;
import java.util.List;

/**
 * Service基类
 *
 * @since 2016年05月12日 19:16
 * @author Benson Xu
 * @version 1.0
 *
 * @param <T> 实体类
 * @param <PK> 主键类型
 */
public interface IBaseService<T, PK extends Serializable> {

	/**
	 * 添加一个实体对象信息
	 * @param entity 实体对象
	 */
	public abstract int add(T entity) throws Exception;
	
	/**
	 * 根据主键ID删除对象
	 * @param id 主键ID
	 * @return 执行结果
	 * @throws Exception
	 */
	public abstract int delete(PK id) throws Exception;

	/**
	 * 批量删除记录
	 * @param idList 主键ID集合
	 * @return 删除记录的个数
	 */
	public abstract int delete(List<PK> idList) throws Exception;

	/**
	 * 修改一个实体对象信息
	 * @param entity 实体对象
	 * @return 修改的实体个数
	 */
	public abstract int update(T entity) throws Exception;
	
	/**
	 * 根据主键获取一个实体对象信息
	 * @param id 主键
	 * @return 实体对象
	 */
	public abstract T findOne(PK id) throws Exception;
	
	/**
	 * 获取所有记录实体对象
	 * @return 实体对象集合
	 */
	public abstract List<T> findAll() throws Exception;


}
