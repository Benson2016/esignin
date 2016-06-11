package com.benson.esignin.web.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解 拦截Service并记录日志
 *
 * @author: Benson Xu
 * @date: 2016年06月08日 15:01
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysServiceLog {

    String content() default "";

}
