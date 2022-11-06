package com.ljn.communityljn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author li
 * @Date 11/5/22 10:25 AM
 * @Version 1.0
 * 描述 ：登录状态检测注解
 * 名称：Loginrequired
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {

}
