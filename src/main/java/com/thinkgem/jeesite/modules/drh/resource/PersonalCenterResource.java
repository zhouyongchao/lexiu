package com.thinkgem.jeesite.modules.drh.resource;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.thinkgem.jeesite.modules.drh.entity.TMycollect;
import com.thinkgem.jeesite.modules.drh.service.TMycollectService;
import com.thinkgem.jeesite.modules.gen.util.GenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.TMyattentionorfans;
import com.thinkgem.jeesite.modules.drh.entity.TUser;
import com.thinkgem.jeesite.modules.drh.service.TMyattentionorfansService;
import com.thinkgem.jeesite.modules.drh.service.TUserService;

@Controller
@RequestMapping("/personal")
public class PersonalCenterResource {
    @Autowired
    private TMyattentionorfansService tMyattentionorfansService;
    @Autowired
    private TUserService tUserService;
    @Autowired
    private TMycollectService tMycollectService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 查询粉丝个数
     *
     * @param token
     * @return
     */
    @RequestMapping("/fans/count")
    @ResponseBody
    public ResultModel fansCount(String token) {
        logger.warn("token_" + token);
        TUser user = (TUser) JedisUtils.getObject(token);
        if (user == null) {
            return new ResultModel(1000, "用户未登录", null);
        } else {
            String count = tMyattentionorfansService.getFansCount(user.getId());
            return new ResultModel(0, "success", new LinkedHashMap()).put("count", count);
        }
    }

    /**
     * 用户粉丝列表
     *
     * @param token
     * @return
     */
    @RequestMapping("/fans/list")
    @ResponseBody
    public ResultModel fansList(String token) {
        logger.warn("token_" + token);
        TUser user = (TUser) JedisUtils.getObject(token);
        if (user == null) {
            return new ResultModel(1000, "用户未登录", null);
        } else {
            ResultModel rm = new ResultModel(0, "success", new LinkedHashMap());
            TMyattentionorfans tMyattentionorfans = new TMyattentionorfans();
            tMyattentionorfans.setAttentionid(user.getId());
            List<TMyattentionorfans> tMyattentionorfansList = tMyattentionorfansService.findList(tMyattentionorfans);

            return rm.put("fansList", tMyattentionorfansList);
        }
    }

    /**
     * 查询我关注个数
     *
     * @param token
     * @return
     */
    @RequestMapping("/attention/count")
    @ResponseBody
    public ResultModel attentionCount(String token) {
        logger.warn("token_" + token);
        TUser user = (TUser) JedisUtils.getObject(token);
        if (user == null) {
            return new ResultModel(1000, "用户未登录", null);
        } else {
            String count = tMyattentionorfansService.getAttentionCount(user.getId());
            return new ResultModel(0, "success", new LinkedHashMap()).put("code", count);
        }
    }
//	 /**
//	  * 查询关注着列表
//	  * @param token
//	  * @return
//	  */
//	 @RequestMapping("/attention/list")
//	 @ResponseBody
//	 public ResultModel attentionList( String token){
//	 	logger.warn("token_"+token);
//		 TUser user = (TUser) JedisUtils.getObject(token);
//	        if (user==null){
//	            return new ResultModel(1000,"用户未登录",null);
//	        }else {
//	        	ResultModel rm=new ResultModel(0,"success",new LinkedHashMap());
//	        	TMyattentionorfans tMyattentionorfans=new TMyattentionorfans();
//	        	tMyattentionorfans.setFansid(user.getId());
//	        	List<TMyattentionorfans> tMyattentionorfansList=tMyattentionorfansService.findList(tMyattentionorfans);
//	        	return rm.put("attentionList", tMyattentionorfansList);
//	        }
//	 }

    /**
     * 用户关注
     *
     * @param attentionId 被关注者id
     * @return
     */
    @RequestMapping("/attention")
    @ResponseBody
    public ResultModel attention(String token, String attentionId) {
        logger.warn("token_attentionId_fansId_" + token + "_" + attentionId);
        TUser user = (TUser) JedisUtils.getObject(token);
        if (user == null) {
            return new ResultModel(1000, "用户未登录", null);
        } else {
            TMyattentionorfans query = new TMyattentionorfans();
            query.setFansid(user.getId());
            query.setAttentionid(attentionId);
            List<TMyattentionorfans> result = tMyattentionorfansService.findList(query);
            if (result != null && result.size() > 0) {
                return new ResultModel(1001, "不可重复关注", null);
            }
            if (StringUtils.isNoneEmpty(attentionId)) {
                TMyattentionorfans tMyattentionorfans = new TMyattentionorfans();
                tMyattentionorfans.setAttentionid(attentionId);
                tMyattentionorfans.setFansid(user.getId());
                tMyattentionorfansService.save(tMyattentionorfans);
                return new ResultModel(0, "success", new LinkedHashMap());
            } else {
                return new ResultModel(1, "参数不正确", new LinkedHashMap());
            }
        }
    }

    @RequestMapping("/attention/exist")
    @ResponseBody
    public ResultModel attentionExist(String token, String attentionId) {
        logger.warn("token_attentionId_fansId_" + token + "_" + attentionId);
        TUser user = (TUser) JedisUtils.getObject(token);
        if (user == null) {
            return new ResultModel(1000, "用户未登录", null);
        } else {
            TMyattentionorfans query = new TMyattentionorfans();
            query.setFansid(user.getId());
            query.setAttentionid(attentionId);
            List<TMyattentionorfans> result = tMyattentionorfansService.findList(query);
            if (result != null && result.size() > 0) {
                return new ResultModel(0, "success", null).put("exist", true);
            } else {
                return new ResultModel(0, "success", null).put("exist", false);
            }
        }
    }

    @RequestMapping("/attention/cancel")
    @ResponseBody
    public ResultModel attentionCancel(String token, String attentionId) {
        logger.warn("token_attentionId_fansId_" + token + "_" + attentionId);
        TUser user = (TUser) JedisUtils.getObject(token);
        if (user == null) {
            return new ResultModel(1000, "用户未登录", null);
        } else {
            TMyattentionorfans query = new TMyattentionorfans();
            query.setFansid(user.getId());
            query.setAttentionid(attentionId);
            List<TMyattentionorfans> result = tMyattentionorfansService.findList(query);
            if (result != null && result.size() > 0) {
                tMyattentionorfansService.delete(query);
                return new ResultModel(0, "success", null);
            } else {
                return new ResultModel(1001, "尚未关注", null);
            }
        }
    }

    @RequestMapping("/collection/count")
    @ResponseBody
    public ResultModel collectionCount(String token) {
        TUser user = (TUser) JedisUtils.getObject(token);
        if (user == null) {
            return new ResultModel(1000, "用户未登录", null);
        } else {
            TMycollect query = new TMycollect();
            query.setUserid(user.getId());
            query.setStatus("0");
            List orgList = tMycollectService.findList(query);
            query.setStatus("1");
            List mageineList = tMycollectService.findList(query);
            return new ResultModel(0, "success", new LinkedHashMap())
                    .put("orgCount", orgList == null ? 0 : orgList.size())
                    .put("magazineCount", mageineList == null ? 0 : mageineList.size());

        }
    }
}
