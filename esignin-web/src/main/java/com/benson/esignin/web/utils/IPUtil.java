package com.benson.esignin.web.utils;

import com.benson.esignin.common.cons.CommonCons;
import com.benson.esignin.common.utils.CommonUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * IP 地址工具类
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 徐彬森 Copyright (c) 2016
 * @since 2016年06月06日 21:36
 */
public class IPUtil {

    /**
     * 获取客户端IP地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (CommonUtil.isNull(request)) {
            return CommonCons.NOT_FOUND;
        }

        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        /*
        * 如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
        * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
        * 如：X-Forwarded-For：192.168.1.100，192.168.1.110， 192.168.1.120， 192.168.1.130 用户真实IP为： 192.168.1.100
        * */
        if (null!=ip && ip.contains(",")) {
            ip = ip.split(",")[0];
        }

        return ip;
    }

}
