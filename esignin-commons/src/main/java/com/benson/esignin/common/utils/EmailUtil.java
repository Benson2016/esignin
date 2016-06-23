package com.benson.esignin.common.utils;

import com.benson.esignin.common.cons.CommonCons;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 常用邮件工具类
 * @author Benson Xu
 * @version 1.0
 * @since 2016年06月20日 11:14
 */
public class EmailUtil {

	/**
	 * 发送邮件
	 * @param mailHostName 收件服务器名称
	 * @param fromUserAddr 发送方Email地址
	 * @param fromUserPass 发送方账号密码
	 * @param toUserAddr 接收方Email地址
	 * @param toUserName 接收方姓名
	 * @param subject 邮件标题
	 * @param content 邮件内容 （如果是HTML格式，只需写<body>里面的内容，不包含<body>和</body>部分）
	 * @param sysName 系统名称 ，表示发起邮件的系统（例如服务接口管理系统：ServiceMS）
	 * @param isHTML 是否是HTML格式内容
	 * 		isHTML为true表示邮件内容为HTML格式，即支持HTML标签；反之，默认为普通文本格式。
	 * @return 
	 */
	public static boolean sendMail(String mailHostName, String fromUserAddr,
			String fromUserPass, String toUserAddr, String toUserName, String subject,
			String content, String sysName, boolean isHTML) throws EmailException {

		// HTML格式邮件
		HtmlEmail email = new HtmlEmail();
		// 设置发送服务器
		email.setHostName(mailHostName);
		// 设置接收者
		email.addTo(toUserAddr, toUserName);
		// 设置发送者
		email.setFrom(fromUserAddr, sysName);
		// 设置编码
		email.setCharset(CommonCons.CHARSET_GBK);
		// 设置认证
		email.setAuthentication(fromUserAddr, fromUserPass);
		// 设置标题
		email.setSubject(subject);
		// 设置内容
		if (isHTML) {
			StringBuffer emailContent = new StringBuffer();
			emailContent.append("<html><body>");
			emailContent.append(content);
			emailContent.append("</body></html>");
			email.setHtmlMsg(emailContent.toString());
		} else {
			email.setMsg(content);
		}
		// 发送
		email.send();
			
		return true;
	}
	
}
