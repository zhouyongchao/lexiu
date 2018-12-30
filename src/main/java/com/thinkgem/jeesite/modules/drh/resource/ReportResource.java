package com.thinkgem.jeesite.modules.drh.resource;

import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.Report;
import com.thinkgem.jeesite.modules.drh.entity.TUser;
import com.thinkgem.jeesite.modules.drh.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/report")
public class ReportResource {
    @Autowired
    private ReportService reportService;

    @RequestMapping("/send")
    @ResponseBody
    public ResultModel reportUser(String token, Report report){
        TUser tUser = (TUser) JedisUtils.getObject(token);
        if (tUser == null)
            return new ResultModel(1000,"用户未登陆",null);
        report.setReportId(tUser.getId());
        reportService.save(report);
        return new ResultModel(0,"success",null);
    }
}
