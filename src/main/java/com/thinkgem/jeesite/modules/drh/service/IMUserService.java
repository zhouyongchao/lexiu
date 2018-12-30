package com.thinkgem.jeesite.modules.drh.service;


import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.HttpReqUtils;
import com.thinkgem.jeesite.common.utils.TokenUtil;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 2017/9/28.
 */
@Service("imService")
public class IMUserService {

    //    private String url ="http://a1.easemob.com/${ORG_NAME}/${APP_NAME}/users";
    private String url = "http://a1.easemob.com/1109170925178943/ushow/users";
    private String createUrl = "http://a1.easemob.com/1109170925178943/ushow/chatrooms";
    private String getUrl = "http://a1.easemob.com/1109170925178943/ushow/chatrooms/${CHATROOM_ID}";
    private String joinUrl = "http://a1.easemob.com/1109170925178943/ushow/chatrooms";
    private String quitUrl = "http://a1.easemob.com/1109170925178943/ushow/chatrooms";
    private String sendUrl = "http://a1.easemob.com/1109170925178943/ushow/chatrooms";


    public String regToIM(String username, String password) {
//        url.replace("${ORG_NAME}", Global.getConfig("orgName"))
//           .replace("${APP_NAME}",Global.getConfig("appName"));
        Map body = new HashMap<String, String>();
        body.put("username", username);
        body.put("password", password);
        String resp = HttpReqUtils.postReq(url, body, TokenUtil.getAccessToken());
        return resp;
    }

    public String createChatRoom(String name, String desc, int num, String owner) {
        Map body = new HashMap<String, String>();
        body.put("name", name);
        body.put("description", desc);
        body.put("maxusers", num);
        body.put("owner", owner);
        String resp = HttpReqUtils.postReq(createUrl, body, TokenUtil.getAccessToken());
        return resp;
    }

    public String getChatRoom(String chatRoomId) {
        String url = getUrl.replace("${CHATROOM_ID}", chatRoomId);
        String resp = HttpReqUtils.getReq(url, TokenUtil.getAccessToken());
        return resp;
    }

    public String delChatRoom(String chatRoomId) {
        String url = getUrl.replace("${CHATROOM_ID}", chatRoomId);
        String resp = HttpReqUtils.delReq(url, TokenUtil.getAccessToken());
        return resp;
    }

//    public String joinChatRoom(){
//
//    }
//
//    public String quitChatRoom(){
//
//    }
//
//    public String sendToChat(){
//
//    }

}