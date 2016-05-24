package com.benson.esigin.test;

import com.benson.esignin.common.utils.MD5Util;

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
        String pwd = "123";
        System.out.println("123: " + MD5Util.md5(pwd));
        pwd = "123456";
        System.out.println("123456: " + MD5Util.md5(pwd));
    }

}
