package com.thinkgem.jeesite.modules.drh.resource;

import java.util.Set;

import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.TUser;
import com.thinkgem.jeesite.modules.drh.entity.Watcher;
import com.thinkgem.jeesite.modules.drh.service.TUserService;
import com.thinkgem.jeesite.modules.drh.util.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Niexuyang on 2017/11/12.
 */
@Controller
@RequestMapping("/watcher")
public class WatcherResource {

    @Autowired
    private TUserService tUserService;

    @RequestMapping("/add")
    @ResponseBody
    public ResultModel addWatcher(String userId, String watcherId) {
//        Watcher watcher = (Watcher) JedisUtils.getObject("watcher"+userId);
//
//        if (watcher==null){
//            watcher = new Watcher();
//        }
//        if (!StringUtils.isEmpty(watcherId)){
//            TUser tUser = tUserService.get(watcherId);
//            watcher.addOnWatcher(tUser);
//        }else {
//            watcher.addOffWatcher();
//        }
//
//        JedisUtils.setObject("watcher"+userId,watcher,0);
        CacheUtil.addWathcher(userId, tUserService.get(watcherId));
        return new ResultModel(0, "success", null);
    }

    @RequestMapping("/sub")
    @ResponseBody
    public ResultModel subWatcher(String userId, String watcherId) {
//        Watcher watcher = (Watcher) JedisUtils.getObject("watcher"+userId);
//
////        if (watcher==null){
////            watcher = new Watcher();
////        }
//        if (!StringUtils.isEmpty(watcherId)){
//            TUser tUser = tUserService.get(watcherId);
//            watcher.subOnWatcher(tUser);
//        }else {
//            watcher.subOffWatcher();
//        }
//
//        JedisUtils.setObject("watcher"+userId,watcher,0);
        CacheUtil.subWathcher(userId, tUserService.get(watcherId));
        return new ResultModel(0, "success", null);

    }
}
