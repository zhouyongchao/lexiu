package com.thinkgem.jeesite.modules.drh.resource;

import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.*;
import com.thinkgem.jeesite.modules.drh.service.*;
import com.thinkgem.jeesite.modules.gen.util.GenUtils;
import com.thinkgem.jeesite.modules.sys.entity.Org;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/magazine")
public class MagazineResource {

    @Autowired
    private TMagazineService tMagazineService;
    @Autowired
    private TMagazineCommentService tMagazineCommentService;
    @Autowired
    private TMycollectService tMycollectService;
    @Autowired
    private TUserService userService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping("/list")
    @ResponseBody
    public ResultModel list(String title){
        logger.warn("title_"+title);
        TMagazine tMagazine = new TMagazine();
        tMagazine.setTitle(title);
        return new ResultModel(0,"success",new LinkedHashMap())
                .put("magazineList",tMagazineService.findList(tMagazine));
    }

    @RequestMapping("/detail")
    @ResponseBody
    public ResultModel detail(String token,String id){
        logger.warn("token_id_"+token+"_"+id);
        TUser tUser = (TUser) JedisUtils.getObject(token);
        TMagazine tMagazine = tMagazineService.get(id);
        if (tUser==null){
            tMagazine.setCollected(false);
        }else {
            TMycollect tMycollect = new TMycollect();
            tMycollect.setStatus("1");
            tMycollect.setPeripheryids(tMagazine.getId());
            tMycollect.setUserid(tUser.getId());
            List list = tMycollectService.findList(tMycollect);
            if (list!=null&&list.size()>0){
                tMagazine.setCollected(true);
            }else {
                tMagazine.setCollected(false);
            }
        }
        return new ResultModel(0,"success",new LinkedHashMap())
                .put("magazine",tMagazine);
    }

    @RequestMapping("/comment/save")
    @ResponseBody
    public ResultModel save(String token, TMagazineComment tMagazineComment, HttpServletRequest request) {
        logger.warn("token_comment_"+token);
        TUser user = (TUser) JedisUtils.getObject(token);
        if (user == null) {
            return new ResultModel(1000, "用户未登录", null);
        } else {
            tMagazineComment.setUserid(user.getId());
            tMagazineCommentService.save(tMagazineComment);
            ResultModel rm = new ResultModel(0, "success", new LinkedHashMap());
            return rm;
        }

    }

    @RequestMapping("/comment/list")
    @ResponseBody
    public ResultModel list(String token, String magazineid) {
        logger.warn("token_magazineid_"+token+"_"+magazineid);
//        TUser user = (TUser) JedisUtils.getObject(token);
//        if (user == null) {
//            return new ResultModel(1000, "用户未登录", null);
//        } else {
//
//        }
        TMagazineComment tMagazineComment = new TMagazineComment();
        ResultModel rm = new ResultModel(0, "success", new LinkedHashMap());
        tMagazineComment.setMagazineid(magazineid);
        List<TMagazineComment> commentList = tMagazineCommentService.findList(tMagazineComment);
        for (TMagazineComment temp: commentList){
            temp.setUser(userService.get(temp.getUserid()));
        }
        return rm.put("commentList", commentList);
    }
    @RequestMapping("/collection")
    @ResponseBody
    public ResultModel collection(String token, TMycollect tMycollect) {
        logger.warn("token_collect_"+token);
        TUser user = (TUser) JedisUtils.getObject(token);
        tMycollect.setUserid(user.getId());
        List<TMycollect> list = tMycollectService.findList(tMycollect);
        if (user == null) {
            return new ResultModel(1000, "用户未登录", null);
        } else if(list!=null&&list.size()>0){
            return new ResultModel(1001, "不可重复收藏", null);
        } else {
            tMycollect.setStatus("1");
            tMycollectService.save(tMycollect);
            ResultModel rm = new ResultModel(0, "success", new LinkedHashMap());
            return rm;
        }

    }

    @RequestMapping("/uncollection")
    @ResponseBody
    public ResultModel uncollection(String token, TMycollect tMycollect) {
        logger.warn("token_collect_"+token);
        TUser user = (TUser) JedisUtils.getObject(token);
        tMycollect.setUserid(user.getId());
        List<TMycollect> list = tMycollectService.findList(tMycollect);
        if (user == null) {
            return new ResultModel(1000, "用户未登录", null);
        } else if(list==null||list.size()==0){
            return new ResultModel(1001, "未收藏", null);
        } else {
            tMycollectService.delete(tMycollect);
            ResultModel rm = new ResultModel(0, "success", new LinkedHashMap());
            return rm;
        }

    }

    @RequestMapping("/collection/list")
    @ResponseBody
    public ResultModel collectionList(String token) {
        logger.warn("token_"+token);
        TUser user = (TUser) JedisUtils.getObject(token);
        if (user == null) {
            return new ResultModel(1000, "用户未登录", null);
        }
        TMycollect tMycollect = new TMycollect();
        tMycollect.setUserid(user.getId());
        tMycollect.setStatus("1");
        List<TMycollect> list = tMycollectService.findList(tMycollect);
        List<TMagazine> tMagazines = tMagazineService.findList(new TMagazine());
        List<TMagazine> results = new ArrayList<TMagazine>();
        for (TMagazine temp: tMagazines){
            for(TMycollect coll: list){
                if (temp.getId().equals(coll.getPeripheryids())){
                    results.add(temp);
                    continue;
                }
            }
        }
        ResultModel rm = new ResultModel(0, "success", new LinkedHashMap())
                .put("collectList",results);
        return rm;

    }
}
