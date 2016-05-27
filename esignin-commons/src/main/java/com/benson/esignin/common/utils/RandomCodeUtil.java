package com.benson.esignin.common.utils;

/**
 * 随机码工具类
 *
 * @author Benson Xu
 * @version 1.0
 * @since 2016年05月27日 15:10
 */
public class RandomCodeUtil {

    /**
     * 获取6位随机码
     * @return
     */
    public static String getRandomCode() {

        int randomNum = (int)((Math.random()*9+1)*100000);

        String randomCode = "" + randomNum;

        return randomCode;
    }


    /*public static void main(String[] args) {
        for (int i=0; i<100; i++) {
            System.out.println(getRandomCode());
        }
    }*/

}
