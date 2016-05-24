package com.benson.esignin.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 深圳市华阳信通科技发展有限公司 Copyright (c) 2016
 * @since 2016年05月24日 17:06
 */
public class EncryptionUtil {

    /**
     * 将摘要信息转换为相应的编码
     * @param code 编码类型
     * @param message 摘要信息
     * @return 相应的编码字符串
     */
    public static String ENCODE(String code, String message) {
        MessageDigest md;
        String encode = null;
        try {
            md = MessageDigest.getInstance(code);
            encode = byteArrayToHexString(md.digest(message.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encode;
    }

    /**
     * 将byte[]转化为十六进制字符串格式
     * @param bytes
     * @return 返回十六进制字符串
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; ++i) {
            sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    /**
     * 将摘要信息转换成MD5编码
     * @param message 摘要信息
     * @return MD5编码之后的字符串
     */
    public static String md5Encode(String message){
        return ENCODE("MD5", message);
    }
    /**
     * 将摘要信息转换成SHA编码
     * @param message 摘要信息
     * @return SHA编码之后的字符串
     */
    public static String shaEncode(String message){
        return ENCODE("SHA", message);
    }
    /**
     * 将摘要信息转换成SHA-256编码
     * @param message 摘要信息
     * @return SHA-256编码之后的字符串
     */
    public static String sha256Encode(String message){
        return ENCODE("SHA-256", message);
    }
    /**
     * 将摘要信息转换成SHA-512编码
     * @param message 摘要信息
     * @return SHA-512编码之后的字符串
     */
    public static String sha512Encode(String message){
        return ENCODE("SHA-512", message);
    }

}
