package com.thinkgem.jeesite.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 2017/10/12.
 */
public class TokenUtil {
    private static String ACCESS_TOKEN;
    private static Long EXPIREDAT = -1L;

    public static void initTokenByProp() {
        String url = "http://a1.easemob.com/1109170925178943/ushow/token";

//        String url = "http://a1.easemob.com/${ORG_NAME}/${APP_NAME}/token";
//        url=url.replace("${ORG_NAME}",Global.getConfig("orgName"))
//           .replace("${APP_NAME}",Global.getConfig("appName"));
        Map body = new HashMap<String,String>();
        body.put("grant_type","client_credentials");
        body.put("client_id",Global.getConfig("clientId"));
        body.put("client_secret",Global.getConfig("client_secret"));
        String resp =  HttpReqUtils.postReq(url,body,null);
        JSONObject jsonObject =JSONObject.parseObject(resp);
        ACCESS_TOKEN = "Bearer " + jsonObject.get("access_token");
        EXPIREDAT = System.currentTimeMillis() + (Integer) jsonObject.get("expires_in");
    }

    public static String getAccessToken() {
        if (ACCESS_TOKEN == null || isExpired()) {
            initTokenByProp();
        }
        return ACCESS_TOKEN;
    }

    private static Boolean isExpired() {
        return System.currentTimeMillis() > EXPIREDAT;
    }

}

