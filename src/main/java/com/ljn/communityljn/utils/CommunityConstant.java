package com.ljn.communityljn.utils;

/**
 * @Author li
 * @Date 11/4/22 10:08 AM
 * @Version 1.0
 * 描述 ：重复激活的码
 * 名称：CommunityConstant
 */
public interface CommunityConstant {

    /**
     * 激活成功
     */
    int ACTIVATION_SUCCESS = 0;

    /**
     * 重复激活
     */
    int ACTIVATION_REPEAT = 1;

    /**
     * 激活失败
     */
    int ACTIVATION_FAILURE = 2;

    /**
     *默认状态的登录凭证的超时时间
     */
    int DEFAULT_EXPIRED_SECONDS = 3600*12;

    /**
     * 记住状态下的登录凭证时间
     */
    int REMEMBER_EXPIRED_SECONDS = 3600*24*100;
}