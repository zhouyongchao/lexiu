package com.thinkgem.jeesite.modules.drh.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.drh.entity.Gift;
import com.thinkgem.jeesite.modules.drh.entity.GiftRecord;
import com.thinkgem.jeesite.modules.drh.service.GiftRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "${adminPath}/drh/gift/record")
public class GiftRecordController extends BaseController {

    @Autowired
    private GiftRecordService giftRecordService;

    @ModelAttribute
    public GiftRecord get(@RequestParam(required=false) String id) {
        GiftRecord entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = giftRecordService.get(id);
        }
        if (entity == null){
            entity = new GiftRecord();
        }
        return entity;
    }

    @RequestMapping(value = {"list", ""})
    public String list(GiftRecord giftRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<GiftRecord> page = giftRecordService.findPage(new Page<GiftRecord>(request, response), giftRecord);
        model.addAttribute("page", page);
        model.addAttribute("giftRecord", giftRecord);
        return "modules/drh/giftRecordList";
    }
}
