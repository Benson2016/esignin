package com.benson.esigin.test;

import com.benson.esignin.common.utils.EncryptionUtil;
import com.benson.esignin.common.utils.MD5Util;
import com.benson.esignin.web.utils.QRCodeUtil;

import java.util.Scanner;

/**
 * { enter your description }
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright 深圳市华阳信通科技发展有限公司 Copyright (c) 2016
 * @since 2016年05月24日 15:53
 */
public class Test {

    public static void main(String[] args) {
        /*String msg = "123";
        System.out.println("MD5: " +MD5Util.md5(msg));
        System.out.println("MD5: " +EncryptionUtil.md5Encode(msg));
        System.out.println("SHA-256: " + EncryptionUtil.sha256Encode(msg));*/

        //String loginUrl = "http://xubstest.ematong.com/esignin/user/loginByQR.bs";
        /*String loginUrl = "http://192.168.31.135:8080/esignin/index.jsp";
        String imgPath = "/Users/benson/Documents/workspace/temp/loginByQR.png";

        QRCodeUtil.encode(loginUrl, 300, 300, imgPath);*/

        String loginUrl = "http://xubstest.ematong.com/esignin/index.jsp";

        String savePath = "D:/temp/loginByQR.png";

        QRCodeUtil.encode(loginUrl, 300, 300, savePath);

        System.out.println("执行完毕！");

        //getMacAddr();
    }


    private static void getMacAddr() {

        try {

            Process process = Runtime.getRuntime().exec(new String[] { "wmic", "cpu", "get", "ProcessorId" });
            process.getOutputStream().close();
            Scanner sc = new Scanner(process.getInputStream());
            String property = sc.next();
            String serial = sc.next();
            System.out.println(property + ": " + serial);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
