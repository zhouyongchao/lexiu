package com.thinkgem.jeesite.modules.drh.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.TLiveroom;
import com.thinkgem.jeesite.modules.drh.entity.TUser;
import com.thinkgem.jeesite.modules.drh.entity.Watcher;
import com.thinkgem.jeesite.modules.drh.service.IMUserService;
import com.thinkgem.jeesite.modules.drh.service.TLiveroomService;
import com.thinkgem.jeesite.modules.drh.service.TUserService;
import com.thinkgem.jeesite.modules.drh.util.CacheUtil;
import com.thinkgem.jeesite.modules.drh.util.UploadHelper;
import com.thinkgem.jeesite.modules.gen.util.GenUtils;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.service.DictService;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Niexuyang on 2017/11/12.
 */
@Controller
@RequestMapping("/show")
public class ShowResource {

    @Autowired
    private TUserService userService;

    @Autowired
    private TLiveroomService tLiveroomService;

    @Autowired
    private DictService dictService;

    @Autowired
    private IMUserService imUserService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 直播房间列表
     *
     * @return
     */
    @RequestMapping("/user/list")
    @ResponseBody
    public ResultModel getShowUserList(TLiveroom tLiveroom, int pageNo, int pageSize) {
        if (NumberUtils.INTEGER_ZERO == pageSize) {
            pageSize = 10;
        }
        Page<TLiveroom> page = new Page<TLiveroom>(pageNo, pageSize);
        //tLiveroom.setStatus(20);
        List<TLiveroom> tLiverooms = tLiveroomService.findList(tLiveroom);
        for (TLiveroom temp : tLiverooms) {
            TUser tUser = userService.get(temp.getUserid());
            temp.settUser(tUser);
            temp.setWatcherCount(CacheUtil.getUserSet(temp.getUserid()).size());
        }
        return new ResultModel(0, "success", new LinkedHashMap())
                .put("roomList", tLiverooms);
    }

    /**
     * 直播房间类型列表
     *
     * @return
     */
    @RequestMapping("/user/glist")
    @ResponseBody
    public ResultModel getShowList(TLiveroom tLiveroom) {
        logger.warn("liveRoomType_title_status" + tLiveroom.getType() + "_" + tLiveroom.getTitle() + "_" + tLiveroom.getStatus());
        ResultModel resultModel = new ResultModel(0, "success", new LinkedHashMap());
//        if (NumberUtils.INTEGER_ZERO==pageSize){
//            pageSize = 10;
//        }
//        Page<TLiveroom> page= new Page<TLiveroom>(pageNo,pageSize);
        Dict dict = new Dict();
        dict.setType("show_type");
        List<Dict> dictList = dictService.findList(dict);
        List<TLiveroom> tLiverooms = tLiveroomService.findList(tLiveroom);
        for (TLiveroom temp : tLiverooms) {
//            Watcher watcher = (Watcher) JedisUtils.getObject("watcher" + temp.getUserid());
            TUser tUser = userService.get(temp.getUserid());
//            temp.setWatcher(watcher);
            temp.setWatcherCount(CacheUtil.getUserSet(temp.getUserid()).size());
            temp.settUser(tUser);
        }
        List allTypeList = new ArrayList();
        for (Dict temp : dictList) {
            List lt = new ArrayList();
            for (TLiveroom tlr : tLiverooms) {
                if (lt.size() == 10) break;
                if (temp.getValue().equals(tlr.getType())) {
                    lt.add(tlr);
                    allTypeList.add(tlr);
                }
            }
            resultModel.put(temp.getValue(), lt);
        }
        tLiverooms.removeAll(allTypeList);
        resultModel.put("other", tLiverooms);
        return resultModel;
    }

    /**
     * 直播房间详情
     *
     * @return
     */
    @RequestMapping("/user/detail")
    @ResponseBody
    public ResultModel getShowUserDetail(String roomId) {
        logger.warn("roomId_" + roomId);
        TLiveroom tLiveroom = tLiveroomService.get(roomId);
        tLiveroom.setWatcherCount(CacheUtil.getUserSet(tLiveroom.getUserid()).size());
        TUser user = userService.get(tLiveroom.getUserid());
//        Watcher watcher = (Watcher) JedisUtils.getObject("watcher" + tLiveroom.getUserid());
        return new ResultModel(0, "success", new LinkedHashMap())
                .put("watcher", CacheUtil.getUserSet(tLiveroom.getUserid()))
                .put("user", user)
                .put("room", tLiveroom);
    }

    /**
     * 直播房间点赞
     *
     * @return
     */
    @RequestMapping("/user/praise")
    @ResponseBody
    public ResultModel praise(String token, String roomId) {
        logger.warn("token_roomId_" + token + "_" + roomId);
        TUser tuser = (TUser) JedisUtils.getObject(token);
        if (tuser == null) {
            return new ResultModel(1000, "用户未登录", null);
        }
        TLiveroom tLiveroom = tLiveroomService.get(roomId);
        tLiveroom.addPraise();
        tLiveroomService.save(tLiveroom);
        return new ResultModel(0, "success", null);
    }

    /**
     * 创建直播房间-推流
     *
     * @param token
     * @param tLiveroom
     * @return
     */
    @RequestMapping("/user/room/create")
    @ResponseBody
    public ResultModel pushStorm(String token, TLiveroom tLiveroom, HttpServletRequest request) {
        logger.warn("token_liveroom_" + token);
        //tLiveroom.setStatus(10);
        TUser tuser = (TUser) JedisUtils.getObject(token);
        if (tuser == null) {
            return new ResultModel(1000, "用户未登录", null);
        }
        TLiveroom tLiveroom1 = new TLiveroom();
        tLiveroom1.setUserid(tuser.getId());
        List<TLiveroom> list = tLiveroomService.findList(tLiveroom1);
        if (list != null && list.size() > 0) {
            tLiveroom.setId(list.get(0).getId());
//            return new ResultModel(1001, "直播间已经创建，不可重复创建", null);
        }
        tLiveroom.setPushUrl("rtmp://video-center-bj.alivecdn.com/" + Global.APPLICATION + "/" + tLiveroom.getUserid() + "?vhost=push.shidaiqizhi.com");

        List<MultipartFile> multipartFiles = UploadHelper.getFileSet(request, 1024 * 20, null);
        String path = "/userfiles/liveroom";
        String filePath = "";
        for (MultipartFile multipartFile : multipartFiles) {
            try {
                filePath = UploadHelper.uploadFile(multipartFile, path);
                System.out.println(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        tLiveroom.setBaseurl(filePath);
        tLiveroomService.save(tLiveroom);
        return new ResultModel(0, "success", null).put("id", tLiveroom.getId());
    }

//    @RequestMapping("/user/room/update")
//    @ResponseBody
//    public ResultModel updateStorm(String token, TLiveroom tLiveroom) {
//        logger.warn("token_" + token);
//        TUser tuser = (TUser) JedisUtils.getObject(token);
//        if (tuser == null) {
//            return new ResultModel(1000, "用户未登录", null);
//        }
//        tuser.setStatus(3);
//        tLiveroom.setUserid(tuser.getId());
//        tLiveroomService.save(tLiveroom);
//        userService.save(tuser);
//        return new ResultModel(0, "success", null);
//
//    }

    @RequestMapping("/user/room/verify")
    @ResponseBody
    public ResultModel verifyRoom(String token) {
        logger.warn("token_" + token);
        TUser tuser = (TUser) JedisUtils.getObject(token);
        if (tuser == null) {
            return new ResultModel(1000, "用户未登录", null);
        }
        TLiveroom tLiveroom = new TLiveroom();
        tLiveroom.setUserid(tuser.getId());
        List<TLiveroom> list = tLiveroomService.findList(tLiveroom);
        if (list != null && list.size() > 0) {
            return new ResultModel(0, "直播间已经创建，不可重复创建", new LinkedHashMap())
                    .put("exist", true).put("chatRoomId", list.get(0).getId());
        } else {
            return new ResultModel(0, "直播间未创建", new LinkedHashMap())
                    .put("exist", false);
        }

    }

    @RequestMapping("/user/room/openOrClose")
    @ResponseBody
    public ResultModel openOrCloseRoom(String token, String roomId, int status) {
        logger.warn("token_" + token);
        TUser tuser = (TUser) JedisUtils.getObject(token);
        if (tuser == null) {
            return new ResultModel(1000, "用户未登录", null);
        }
        TLiveroom tLiveroom = tLiveroomService.get(roomId);
        tLiveroom.setStatus(status);
        if (status == 1) {
            String resp = imUserService.createChatRoom(tLiveroom.getUsername(), tLiveroom.getTitle(), 200, tuser
                    .getUsername());
            JSONObject json = JSON.parseObject(resp);
            try {
                String ids = json.getJSONObject("data").get("id").toString();
                tLiveroom.setChatId(ids);
                tLiveroomService.save(tLiveroom);
                return new ResultModel(0, "success", new LinkedHashMap())
                        .put("chatRoomId", ids);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResultModel(1001, "创建聊天室失败", null);
            }
        } else if (status == 0) {

            imUserService.delChatRoom(tLiveroom.getChatId());
            tLiveroomService.save(tLiveroom);
            return new ResultModel(0, "success", null);

        } else {
            return new ResultModel(1002, "当前状态不允许", null);

        }
    }
}
