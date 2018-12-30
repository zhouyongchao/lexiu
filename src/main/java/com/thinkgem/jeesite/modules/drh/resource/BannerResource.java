package com.thinkgem.jeesite.modules.drh.resource;

import java.util.List;

import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.Banner;
import com.thinkgem.jeesite.modules.drh.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/banner")
public class BannerResource {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/list")
    @ResponseBody
    public ResultModel listBanner(){
        List<Banner> bannerList = bannerService.findList(new Banner());
        return new ResultModel(0,"success",null).put("bannerList",bannerList);
    }
}
