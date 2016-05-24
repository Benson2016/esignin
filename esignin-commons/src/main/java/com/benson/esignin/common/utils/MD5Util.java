package com.benson.esignin.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * @since 2014-6-17
 * @author BENSON
 * @version 1.0
 */
public class MD5Util {
	
	/**
	 * MD5加密函数
	 * @param inputText 需要加密的字符串文本
	 * @return 加密后的字符串文本
	 */
	public static String md5(String inputText) {
		return encrypt(inputText);
	}
	
	/**
	 * MD5加密函数
	 * @param binaryData 需要加密的内容
	 * @return 加密后的字符串文本
	 */
	public static String md5(byte[] binaryData) {
		return encrypt(binaryData);
	}
	
	/**
	 * 比较明文与密文是否相等
	 * @param encryptText MD5加密过的密文
	 * @param plaintext 未加密的明文
	 * @return 比较结果(true or false)
	 */
	public static boolean compareToPlaintext(String encryptText, String plaintext) {
		
		if (null == plaintext || "".equals(plaintext)) {
			return false;
		}
		
		String encryptStr = md5(plaintext);
		if (encryptStr.equals(encryptText)) {
			return true;
		}
		
		return false;
	}

	
	/**
	 * 加密方法
	 * @param inputText 需要加密的字符串文本
	 * @return 加密后的字符串文本
	 */
	private static String encrypt(String inputText) {
		
        if (inputText == null || "".equals(inputText.trim())) {  
            throw new IllegalArgumentException("请输入要加密的内容");  
        }  
        
        try {  
            MessageDigest m = MessageDigest.getInstance("md5");  
            m.update(inputText.getBytes("UTF8"));  
            byte s[] = m.digest();  
            return hex(s);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
	
	/**
	 * 加密方法
	 * @param binaryData 需要加密的内容
	 * @return 加密后的字符串文本
	 */
	private static String encrypt(byte[] binaryData) {
		try {  
			MessageDigest m = MessageDigest.getInstance("md5");  
			m.update(binaryData);  
			byte s[] = m.digest();  
			return hex(s);  
		} catch (NoSuchAlgorithmException e) {  
			e.printStackTrace();  
		}
		return null;  
	}  
  
   
	/**
	 * 将byte[]转化为十六进制字符串格式
	 * @param bytes
	 * @return 返回十六进制字符串
	 */
    private static String hex(byte[] bytes) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < bytes.length; ++i) {  
            sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1, 3));  
        }  
        return sb.toString();  
    } 
    
}
