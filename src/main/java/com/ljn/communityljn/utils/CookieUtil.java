package com.ljn.communityljn.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
    //获取cookie的值
    public static String getValue(HttpServletRequest request, String name) {
        //1.先判null
        if (request == null || name == null) {
            throw new IllegalArgumentException("参数为空");
        }
        //2.
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }

        }
        return null;

    }

}