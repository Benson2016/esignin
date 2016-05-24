package com.benson.esignin.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring 工具类
 * @since 2014-7-7
 * @author BENSON
 * @version 1.0
 */
public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext context = null;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}
	
	
	/**
	 * 根据beanId获取Spring容器中对应的Bean对象
	 * @param beanId 目标Bean的id
	 * @return 目标Bean对象
	 */
	public static Object getBean(String beanId) {
		return context.getBean(beanId);
	}

}
