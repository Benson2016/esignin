package com.benson.esignin.common.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * 数据工具类
 *
 * @since 2016年05月12日 18:37
 * @author Benson Xu
 * @version 1.0
 */
public class DataUtil {

	/**
	 * 以打印流的方式，将数据以JSON格式返回页面，适用于异步请求
	 * @param obj 需要转换成JSON字符串的对象
	 * @throws IOException
	 */
	public static void writeToJson(HttpServletResponse response, Object obj) throws IOException {
		String json = JsonUtil.toJson(obj);
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
	}
	
	/**
	 * 
	 * 以打印流的方式，将数据以JSON格式返回页面，适用于异步请求
	 * @param obj 需要转换成JSON字符串的对象
	 * @param notBackslash 没有反斜杠
	 * @throws IOException
	 */
	public static void writeToJson(HttpServletResponse response, Object obj, boolean notBackslash) throws IOException {
		String json = JsonUtil.toJson(obj);
		if (notBackslash) {
			json = fmtJsonString(json); //格式化字符串JSON格式
		}
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
	}
	
	/**
	 * 格式化字符串JSON格式
	 * 格式化内容：去掉去掉反斜杠、去掉“[”前的双引号、去掉“{”前的双引号\去掉“}”后的双引号
	 * @param json JSON格式的字符串
	 */
	public static String fmtJsonString(String json) {
		if (StringUtil.isNullString(json)) {
			return json;
		}
		
		json = json.replaceAll("\\\\", "");	//去掉反斜杠
		json = json.replaceAll("\"\\[", "[");	// 去掉“[”前的双引号
		json = json.replaceAll("\\]\"", "]");	// 去掉“]”后的双引号
		json = json.replaceAll("\"\\{", "{");	// 去掉“{”前的双引号
		json = json.replaceAll("\\}\"", "}");	// 去掉“}”后的双引号
		
		return json;
	}
	
	
	/**
	 * 替换占位符%s
	 * @param text 源文本信息
	 * @param values 替换占位符的值，多值时以逗号隔开
	 * @return
	 */
	public static String replacePlaceholder(String text, String values) {
		// 若动态值为空，则直接返回
		if (StringUtil.isNullString(values)) {
			// 去掉多余的占位符
			if (!StringUtil.isNullString(text) && text.contains("%s")) {
				text = text.replaceAll("%s", "");
			}
			return text;
		}
		
		// 分解多个值
		String[] vals = values.split(",");
		
		return replacePlaceholder(text, vals);
	}
	
	/**
	 * 替换占位符%s
	 * @param text 源文本信息
	 * @param values 替换占位符的值的数组
	 * @return 替换后的文本
	 */
	public static String replacePlaceholder(String text, Object[] values) {
		// 文本内容为空或者不包含占位符“%s”则返回
		if (StringUtil.isNullString(text) || !text.contains("%s")) {
			return text;
		}
		
		if (!CommonUtil.isNull(values)) {
			// 遍历替换占位符
			for (Object param : values) {
				text = text.replaceFirst("%s", param.toString());
			}
		}
		
		// 去掉多余的占位符
		if (text.contains("%s")) {
			text = text.replaceAll("%s", "");
		}
		
		return text;
	}
	
}
