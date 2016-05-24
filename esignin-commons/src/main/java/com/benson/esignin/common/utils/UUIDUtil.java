package com.benson.esignin.common.utils;

import java.util.UUID;

/**
 * UUID 工具类，统一生成数据库表主键
 *
 * @since 2016年05月12日 18:13
 * @author Benson Xu
 * @version 1.0
 */
public class UUIDUtil {

	/**
	 * 获取一个UUID字符串
	 * @return UUID字符串
	 */
	public static String getUUID() {
		//随机产生一个UUID
		UUID uuid = UUID.randomUUID();
		String suuid = uuid.toString().replaceAll("-", "");

		return suuid;
	}

}
