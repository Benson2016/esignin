package com.benson.esignin.common.utils;

import com.benson.esignin.common.cache.AdminInfoCacheManager;
import com.benson.esignin.common.cons.CommonCons;
import com.benson.esignin.common.cons.SysCons;
import com.benson.esignin.common.entity.AdminInfo;
import org.apache.commons.mail.EmailException;

import java.util.Map;

/**
 * 系统管理员消息通知工具类
 *
 * @author Benson Xu
 * @version 1.0
 * @since 2016年06月20日 11:34
 */
public class NoticeAdminUtil {

    /**
     * 向系系统管理员发送消息通知（邮件和手机短信）
     * @param title 标题
     * @param content 内容
     */
    public static void sendNotice(String title, String content) {
        String sendKey = DateUtil.getCurrentDateTimeStr(CommonCons.D_FMT_DATE_TIME_CN);
        sendNotice(title, content, sendKey);
    }

    /**
     * 向系系统管理员发送消息通知（邮件和手机短信）
     * @param title 标题
     * @param content 内容
     * @param sendKey 发送标识，唯一标识，作用于同一天同一异常信息只发一次。
     * 		格式：datetime+特殊字符，默认：yyyyMMddHHmmss
     */
    public static void sendNotice(String title, String content, String sendKey) {
        title += "_" +sendKey;
        try {
            final Map<String, AdminInfo> adminInfoMap = AdminInfoCacheManager.getAdminInfoMap();
            if (CommonUtil.isNotNull(adminInfoMap)) {
                for (String key : adminInfoMap.keySet()) {
                    AdminInfo adminInfo = adminInfoMap.get(key);
                    EmailUtil.sendMail(CommonCons.EMAIL_HOST_NAME_OF_126, SysCons.EMIAL_USER, SysCons.EMIAL_PASS, adminInfo.getEmail(), adminInfo.getName(), title, content, "Sign in System", true);
                }
            } else {
                System.out.println("\n--->>>没有找到管理员信息，无法发送消息通知，请检测是否已配置！<<<---\n");
            }
        } catch (EmailException e) {
            System.out.println("发送消息通知时，发生异常：" + e.getMessage());
            e.printStackTrace();
        }
    }

}
