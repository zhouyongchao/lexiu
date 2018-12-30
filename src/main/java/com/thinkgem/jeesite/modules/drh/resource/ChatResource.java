package com.thinkgem.jeesite.modules.drh.resource;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.TLiveroom;
import com.thinkgem.jeesite.modules.drh.entity.TUser;
import com.thinkgem.jeesite.modules.drh.service.IMUserService;
import com.thinkgem.jeesite.modules.drh.service.TLiveroomService;
import com.thinkgem.jeesite.modules.gen.util.GenUtils;
import com.thinkgem.jeesite.modules.sys.entity.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;

@Controller
@RequestMapping("/chat")
public class ChatResource {

    @Autowired
    private IMUserService imUserService;
    @Autowired
    private TLiveroomService tLiveroomService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/create")
    @ResponseBody
    public ResultModel createChatRoom(String token, String name, String desc, int members){
        logger.warn("token_"+token+"name_"+name+"desc_"+desc+"members_"+members);
        TUser tUser = (TUser) JedisUtils.getObject(token);
        if(tUser==null){
            return new ResultModel(1000,"用户尚未登录",null);
        }
//        TUser tUser =  new TUser();
//        tUser.setId("1");
//        tUser.setUsername("admin");
        ResultModel result =null;
        String resp = imUserService.createChatRoom(name, desc, members, tUser.getUsername());
        JSONObject json = JSON.parseObject(resp);
        try{
            TLiveroom tLiveroom = new TLiveroom();
            tLiveroom.setUserid(tUser.getId());
            tLiveroom = tLiveroomService.get(tLiveroom);
            String id = json.getJSONObject("data").get("id").toString();
            tLiveroom.setChatId(id);
            tLiveroomService.save(tLiveroom);
            return new ResultModel(0,"success",new LinkedHashMap())
                    .put("chatRoomId",id);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultModel(1001,"创建聊天室失败",null);
        }

    }

    @RequestMapping("/get")
    @ResponseBody
    public ResultModel getChatRoom(String chatRoomId){
        logger.warn("chatRoomId_"+chatRoomId);
        return new ResultModel(0,"success",new LinkedHashMap())
                .put("chatRoom",imUserService.getChatRoom(chatRoomId));
    }

}
