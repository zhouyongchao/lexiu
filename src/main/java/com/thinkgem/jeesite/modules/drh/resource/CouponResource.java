package com.thinkgem.jeesite.modules.drh.resource;


import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.*;
import com.thinkgem.jeesite.modules.drh.service.TCouponOrgService;
import com.thinkgem.jeesite.modules.drh.service.TCouponService;
import com.thinkgem.jeesite.modules.gen.util.GenUtils;
import com.thinkgem.jeesite.modules.sys.service.OrgService;
import com.thinkgem.jeesite.modules.drh.service.TUserCouponService;
import com.thinkgem.jeesite.modules.sys.entity.Org;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Niexuyang on 2017/10/29.
 */
@Controller
@RequestMapping("/coupon")
public class CouponResource {

    @Autowired
    private TCouponOrgService tCouponOrgService;
    @Autowired
    private TCouponService tCouponService;
    @Autowired
    private TUserCouponService tUserCouponService;
    @Autowired
    private OrgService tOrgService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 组织机构发放优惠券列表
     * @return
     * @dateout
     */
    @RequestMapping("/org/list")
    @ResponseBody
    public ResultModel orgCouponList(String orgId){
        logger.warn("orgId_"+orgId);
        TCoupon tCoupon = new TCoupon();
        tCoupon.setOrgid(orgId);
        return new ResultModel(0,"success", new LinkedHashMap())
                .put("couponList",tCouponService.findList(tCoupon));
    }
    /**
     * 领取优惠券
     * @param token
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public ResultModel getCoupon(String token,String couponId){
        logger.warn("token_couponId_"+token+"_"+couponId);
        TUser tUser = (TUser) JedisUtils.getObject(token);
        if(tUser==null){
            return new ResultModel(1000,"用户尚未登录",null);
        }
        TCoupon tCoupon = tCouponService.get(couponId);
        Org tOrg = tOrgService.get(tCoupon.getOrgid());
        TUserCoupon tmp = new TUserCoupon();
        tmp.setUserid(tUser.getId());
        tmp.setCouponid(couponId);
        List<TUserCoupon> tUsercouponList = tUserCouponService.findList(tmp);
        //查询用户是否已经领取过，领取过不可再领
        if(tUsercouponList!=null&&tUsercouponList.size()>0){
            return new ResultModel(2000,"不能重复领取",null);
        }
        TUserCoupon tUsercoupon = new TUserCoupon();
        tUsercoupon.setCouponid(couponId);
        tUsercoupon.setEnddate(tCoupon.getEnddate());
        tUsercoupon.setFacevalue(tCoupon.getFacevalue());
        tUsercoupon.setName(tCoupon.getName());
        tUsercoupon.setOrgid(tCoupon.getOrgid());
        tUsercoupon.setOrgname(tOrg.getTitle());
        tUsercoupon.setStartdate(tCoupon.getStartdate());
        tUsercoupon.setStatus(0);
        tUsercoupon.setType(tCoupon.getType());
        tUsercoupon.setUserid(tUser.getId());
        tUserCouponService.save(tUsercoupon);
        return new ResultModel(0,"success",null);
    }

    /**
     * 我的优惠券列表
     * @param token
     * @param status
     * @return
     */
    @RequestMapping("/myList")
    @ResponseBody
    public ResultModel listMyCoupon(String token,int status){
        logger.warn("token_status_"+token+"_"+status);
        TUser tUser = (TUser) JedisUtils.getObject(token);
        if(tUser==null){
            return new ResultModel(1000,"用户尚未登录",null);
        }
        TUserCoupon tUsercoupon = new TUserCoupon();
        tUsercoupon.setUserid(tUser.getId());
        tUsercoupon.setStatus(status);
        return new ResultModel(0,"success",new LinkedHashMap())
                .put("couponList",tUserCouponService.findList(tUsercoupon));
    }
}
