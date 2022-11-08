package com.ljn.communityljn.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author li
 * @Date 11/4/22 9:20 AM
 * @Version 1.0
 * 描述 ：
 * 名称：CommunityUtil
 */
public class CommunityUtil {

    // 生成随机字符串
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    // MD5加密
    // hello -> abc123def456
    // hello + 3e4a8 -> abc123def456abc
    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    //ajax请求，封装json数据，局部刷新
    public static String getJSONString(int code, String msg, Map<String,Object> map){
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("msg",msg);
        if (map!=null){
            for (String key : map.keySet()){
                json.put(key,map.get(key));
            }
        }
        return json.toJSONString();
    }
    //没有map
    public static  String getJSONString (int code,String msg){
        return getJSONString(code,msg,null);
    }
    //没有msg，map，只有code
    public static String getJSONString(int code){
        return getJSONString(code,null,null);
    }

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("age",25);
        System.out.println(getJSONString(200,"ok",map));
    }
}
