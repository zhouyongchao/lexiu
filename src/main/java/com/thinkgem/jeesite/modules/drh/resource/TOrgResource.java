package com.thinkgem.jeesite.modules.drh.resource;

import java.io.File;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.drh.entity.TCoupon;
import com.thinkgem.jeesite.modules.drh.service.TCouponService;
import com.thinkgem.jeesite.modules.drh.service.TUserService;
import com.thinkgem.jeesite.modules.gen.util.GenUtils;
import com.thinkgem.jeesite.modules.sys.entity.Org;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.TMycollect;

import com.thinkgem.jeesite.modules.drh.entity.TOrgComment;
import com.thinkgem.jeesite.modules.drh.entity.TUser;
import com.thinkgem.jeesite.modules.drh.service.TMycollectService;
import com.thinkgem.jeesite.modules.drh.service.TOrgCommentService;
import com.thinkgem.jeesite.modules.sys.service.OrgService;
import com.thinkgem.jeesite.modules.drh.util.LocationUtil;
import com.thinkgem.jeesite.modules.drh.util.UploadHelper;

@Controller
@RequestMapping("/org")
public class TOrgResource {
    @Autowired
    private OrgService tOrgService;
    @Autowired
    private TOrgCommentService tOrgCommentService;
    @Autowired
    private TMycollectService tMycollectService;
    @Autowired
    private TCouponService tCouponService;
    @Autowired
    private TUserService tUserService;

    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping("/add")
    @ResponseBody
    public ResultModel addOrg(String loginName, String password) {
        Org tOrg = new Org();
        tOrg.setLoginName(loginName);
        tOrg.setStatus("init");
        tOrg.setName("null");
        tOrg.setLoginFlag("0");
        tOrg.setDelFlag("0");
        tOrg.setCreateDate(new Date());
        tOrg.setPassword(SystemService.entryptPassword(password));
        if (tOrgService.get(tOrg) == null) {
            tOrgService.save(tOrg);
            return new ResultModel(0, "success", null);
        } else {
            return new ResultModel(304, "该账户已经被占用", null);
        }

    }

    @RequestMapping("/around/list")
    @ResponseBody
    public ResultModel aroundOrg(String token, Org tOrg) {
//		 TUser user = (TUser) JedisUtils.getObject(token);
//	        if (user==null){
//	            return new ResultModel(1000,"用户未登录",null);
//	        }else {
//
//	        }
        if (StringUtils.isEmpty(tOrg.getLongitude()) || StringUtils.isEmpty(tOrg.getLatitude())) {
            return new ResultModel(1001, "经纬度参数错误", null);

        }
        ResultModel rm = new ResultModel(0, "success", new LinkedHashMap());
        List<Org> orgList = tOrgService.findList(tOrg);
        //计算距离
        for (Org temp : orgList) {
            TCoupon tCoupon = new TCoupon();
            tCoupon.setOrgid(temp.getId());
            boolean coupon = (tCouponService.findList(tCoupon)!=null&&tCouponService.findList(tCoupon).size()>0);
            temp.setHaveCoupon(coupon);
            temp.setDistance(LocationUtil.getDistance(Double.parseDouble(tOrg.getLatitude()), Double.parseDouble(tOrg.getLongitude()), Double.parseDouble(temp.getLatitude()), Double.parseDouble(temp.getLongitude())));
        }
        //排序
        Collections.sort(orgList);
        return rm.put("orgList", orgList);

    }

    @RequestMapping("/around/detail")
    @ResponseBody
    public ResultModel aroundOrgDetail(String token, String id) {
        TUser user = (TUser) JedisUtils.getObject(token);
        ResultModel rm = new ResultModel(0, "success", new LinkedHashMap());

        Org org = tOrgService.get(id);
        if (user==null){
            org.setCollected(false);
        }else {
            TMycollect tMycollect = new TMycollect();
            tMycollect.setUserid(user.getId());
            tMycollect.setPeripheryids(id);
            tMycollect.setStatus("0");
            boolean flag = tMycollectService.findList(tMycollect)!=null&&tMycollectService.findList(tMycollect).size()>0;
            org.setCollected(flag);
        }
        return rm.put("org", org);

    }

    @RequestMapping("/comment/save")
    @ResponseBody
    public ResultModel save(String token, TOrgComment tOrgComment, HttpServletRequest request) {
        TUser user = (TUser) JedisUtils.getObject(token);
        if (user == null) {
            return new ResultModel(1000, "用户未登录", null);
        } else {
            List<MultipartFile> multipartFiles = UploadHelper.getFileSet(request, 1024 * 20, null);
            String path = "/userfiles/orgcomment";

            String filePath = "";
            for (MultipartFile multipartFile : multipartFiles) {
                try {
                    filePath = UploadHelper.uploadFile(multipartFile, path);
                    System.out.println(filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            tOrgComment.setImageurl(filePath);
            tOrgComment.setUserid(user.getId());
            tOrgCommentService.save(tOrgComment);
            ResultModel rm = new ResultModel(0, "success", new LinkedHashMap());
            return rm;
        }

    }

    @RequestMapping("/comment/list")
    @ResponseBody
    public ResultModel list(String token, String orgid, HttpServletRequest request, HttpServletResponse response) {
        TUser user = (TUser) JedisUtils.getObject(token);
        if (user == null) {
            return new ResultModel(1000, "用户未登录", null);
        } else {
            TOrgComment tOrgComment = new TOrgComment();
            ResultModel rm = new ResultModel(0, "success", new LinkedHashMap());
            tOrgComment.setOrgid(orgid);
            List<TOrgComment> commentList = tOrgCommentService.findList(tOrgComment);
            for (TOrgComment temp: commentList){
                temp.setUser(tUserService.get(temp.getUserid()));
            }
            return rm.put("commentList", commentList);
        }

    }

    @RequestMapping("/around/collection")
    @ResponseBody
    public ResultModel collection(String token, TMycollect tMycollect) {
        logger.info("token_"+token);
        TUser user = (TUser) JedisUtils.getObject(token);
        tMycollect.setUserid(user.getId());
        List<TMycollect> list = tMycollectService.findList(tMycollect);
        if (user == null) {
            return new ResultModel(1000, "用户未登录", null);
        } else if(list!=null&&list.size()>0){
            return new ResultModel(1001, "不可重复收藏", null);
        } else {
            tMycollect.setStatus("0");
            tMycollectService.save(tMycollect);
            ResultModel rm = new ResultModel(0, "success", new LinkedHashMap());
            return rm;
        }

    }

    @RequestMapping("/around/canclecollection")
    @ResponseBody
    public ResultModel canclecollection(String token, TMycollect tMycollect, Model model, RedirectAttributes redirectAttributes) {
        TUser user = (TUser) JedisUtils.getObject(token);
        if (user == null) {
            return new ResultModel(1000, "用户未登录", null);
        } else {
            tMycollect.setStatus("0");
            tMycollectService.delete(tMycollect);
            ResultModel rm = new ResultModel(0, "success", new LinkedHashMap());
            return rm;
        }

    }

    @RequestMapping("/around/aroundlist")
    @ResponseBody
    public ResultModel collectionList(String token, Model model, RedirectAttributes redirectAttributes) {
        TUser user = (TUser) JedisUtils.getObject(token);
        if (user == null) {
            return new ResultModel(1000, "用户未登录", null);
        } else {
            TMycollect tMycollect = new TMycollect();
            tMycollect.setStatus("0");
            tMycollect.setUserid(user.getId());
            List<TMycollect> tMycollectList = tMycollectService.findList(tMycollect);
            List<Org> collectOrgs = tOrgService.findList(new Org());
            List<Org> results = new ArrayList<Org>();
            for (Org temp: collectOrgs){
                for(TMycollect coll: tMycollectList){
                    if (temp.getId().equals(coll.getPeripheryids())){
                        results.add(temp);
                        continue;
                    }
                }
            }
            ResultModel rm = new ResultModel(0, "success", new LinkedHashMap()).put("collectionlist", results);
            return rm;
        }

    }
}
