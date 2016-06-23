package com.benson.esignin.common.cache;

import com.benson.esignin.common.entity.AdminInfo;
import com.benson.esignin.common.utils.StringUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.InputStream;
import java.util.Map;

/**
 * 管理员信息缓存管理类
 *
 * @author Benson Xu
 * @version 1.0
 * @since 2016年06月20日 11:14
 */
public class AdminInfoCacheManager {

    private static Map<String, AdminInfo> adminInfoMap = null;

    public static void init() {
        // 读取配置文件，并转化成对象
        InputStream input = AdminInfoCacheManager.class.getClassLoader().getResourceAsStream("admin-info.xml");

        XStream xStream = new XStream(new DomDriver());
        xStream.alias("root", Map.class);
        xStream.alias("key", String.class);
        xStream.alias("info", AdminInfo.class);

        // 解析配置文件信息
        adminInfoMap = (Map<String, AdminInfo>) xStream.fromXML(input);
    }

    public static Map<String, AdminInfo> getAdminInfoMap() {
        if (null==adminInfoMap) {
            synchronized (AdminInfoCacheManager.class) {
                // 防止并发，重复执行
                if (null==adminInfoMap) {
                    init();
                }
            }
        }
        return adminInfoMap;
    }

    /**
     * 根据key获取管理员信息
     * @param key
     * @return
     */
    public static AdminInfo getAdminInfo(String key) {
        if (StringUtil.isNullString(key)) {
            return null;
        }
        AdminInfo info = getAdminInfoMap().get(key);
        return info;
    }
}
