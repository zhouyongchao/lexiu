package com.thinkgem.jeesite.modules.drh.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.drh.entity.City;
import com.thinkgem.jeesite.modules.drh.entity.Gift;

import com.thinkgem.jeesite.modules.drh.service.GiftService;
import com.thinkgem.jeesite.modules.sys.entity.Org;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Niexuyang on 2017/11/15.
 * 礼物信息
 */
@Controller
@RequestMapping(value = "${adminPath}/drh/gift")
public class GiftController extends BaseController {

    @Autowired
    private GiftService giftService;

    @ModelAttribute
    public Gift get(@RequestParam(required=false) String id) {
        Gift entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = giftService.get(id);
        }
        if (entity == null){
            entity = new Gift();
        }
        return entity;
    }

    @RequestMapping(value = {"list", ""})
    public String list(Gift gift, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Gift> page = giftService.findPage(new Page<Gift>(request, response), gift);
        model.addAttribute("page", page);
        model.addAttribute("gift", gift);
        return "modules/drh/giftList";
    }

    @RequestMapping(value = "form")
    public String form(Gift gift, Model model) {
        model.addAttribute("gift", gift);
        return "modules/drh/giftForm";
    }
    @RequestMapping(value = "add")
    public String add(Org tOrg, Model model) {
        model.addAttribute("tOrg", tOrg);
        return "modules/drh/gift";
    }

    @RequestMapping(value = "save")
    public String save(Gift gift, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, gift)){
            return form(gift, model);
        }
        giftService.save(gift);
        addMessage(redirectAttributes, "保存礼物信息成功");
        return "redirect:"+ Global.getAdminPath()+"/drh/gift/?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(Gift gift, RedirectAttributes redirectAttributes) {
        giftService.delete(gift);
        addMessage(redirectAttributes, "删除礼物信息成功");
        return "redirect:"+Global.getAdminPath()+"/drh/gift/?repage";
    }

}
