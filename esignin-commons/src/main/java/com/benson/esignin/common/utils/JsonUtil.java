package com.benson.esignin.common.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

/**
 * JSON工具类，提供常用的JSON类型转化
 *
 * @since 2016年05月12日 18:37
 * @author Benson Xu
 * @version 1.0
 */
public class JsonUtil {

	private static final Gson gson = new Gson();
	
	/**
	 * 将JSON数据转换为实体对象
	 * @param json JSON格式的字符串
	 * @param cls 泛型类的对象.class
	 * @return 数据转换后的实体对象
	 */
	public static <T> T fromJson(String json, Class<T> cls) {
		
		T t = gson.fromJson(json, cls);
		
		return t;
	}

	/**
	 * 将JSON数据转换为实体对象
	 * @param json JSON格式的字符串
	 * @param c 类型标记
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T, C> T fromJson(String json, C c) {
		T t = null;
		if(c instanceof Class){
			t = gson.fromJson(json, (Class<T>) c);
		}else if(c instanceof TypeToken) {
			t = gson.fromJson(json, ((TypeToken) c).getType());
		}
		return t;
	}
	
	/**
	 * JSON转换为带泛型的List集合
	 * @param json JSON格式的字符串
	 * @param typeToken 类型标记（例如：传入new TypeToken<List<SysLog>>(){}）
	 * @return 泛型对象集合
	 */
	public static <T> List<T> fromJson(String json, TypeToken<List<T>> typeToken) {
		
		List<T> list = gson.fromJson(json, typeToken.getType());
		
		return list;
	}
	
	/**
	 * 转换为JSON数据
	 * @param t 泛型实体类
	 * @return JSON格式字符串数据
	 */
	public static <T> String toJson(T t) {
		String json = null;
		if (null != t) {
			json = gson.toJson(t);
		}
		return json;
	}
	
	/**
	 * 转换为JSON数据
	 * @param list 泛型实体类集合
	 * @return JSON格式字符串数据
	 */
	public static <T> String toJson(List<T> list) {
		String json = null;
		if (null != list) {
			json = gson.toJson(list);
		}
		return json;
	}


	// add by benson on 2016/5/16
	public static <T> T json2Bean(String json, Class<T> clazz) throws IOException {
		if(StringUtils.isBlank(json)){
			return null;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		return objectMapper.readValue(json, clazz);
	}

	public static String bean2Json(Object obj) {
		if (obj == null) {
			return null;
		}

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(obj);
		} catch (IOException e) {
			System.out.println("bean2Json发生异常：");
			e.printStackTrace();
			return null;
		}
	}

}