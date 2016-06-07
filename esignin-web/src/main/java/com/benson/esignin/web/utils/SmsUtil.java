package com.benson.esignin.web.utils;

import com.benson.esignin.common.utils.HttpClientUtil;
import com.benson.esignin.common.utils.JsonUtil;
import com.benson.esignin.web.domain.vo.SmsResponse;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * 短信发送工具类
 *
 * @author: Benson Xu
 * @date: 2016年06月07日 21:34
 */
public class SmsUtil {

    private final static Logger logger = Logger.getLogger(SmsUtil.class);

    private final static String SMS_URL = "http://apis.baidu.com/kingtto_media/106sms/106sms";

    private final static String SMS_CONTENT = "【E签到】您的验证码为：%s（动态验证码），有效时间5分钟。请勿向任何人提供您收到的验证码";


    /**
     * 发送短信验证码
     * @param mobiles 手机号码,多个号码以,分隔
     * @param verifyCode
     * @return
     */
    public static SmsResponse send(String mobiles, String verifyCode) {

        SmsResponse response = null;

        String content = String.format(SMS_CONTENT, verifyCode);

        String httpArgs = String.format("mobile=%s&content=%s&tag=%d", mobiles, content, 2);

        String result = HttpClientUtil.requestBaiduApiByGet(SMS_URL, httpArgs);

        logger.info("SMS-SEND响应结果：" + result);

        if (null!=result && !"-1".equals(result)) {
            try {
                response = JsonUtil.json2Bean(result, SmsResponse.class);
            } catch (IOException e) {
                logger.error("解析SMS响应信息时,发生异常:", e);
            }

        } else {
            logger.error("短信发送异常!");
            response = new SmsResponse();
            response.setMessage("Exception");
            response.setReturnstatus("Faild");
        }

        return response;
    }

}
