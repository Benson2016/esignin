package com.benson.esignin.web.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解 拦截Controller并记录日志
 *
 * @author: Benson Xu
 * @date: 2016年06月08日 14:57
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysControllerLog {

    String content() default "";

}
