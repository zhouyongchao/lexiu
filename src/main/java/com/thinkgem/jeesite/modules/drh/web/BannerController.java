package com.thinkgem.jeesite.modules.drh.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.drh.entity.Banner;
import com.thinkgem.jeesite.modules.drh.service.BannerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "${adminPath}/drh/banner")
public class BannerController extends BaseController {

    @Autowired
    private BannerService bannerService;

    @ModelAttribute
    public Banner get(@RequestParam(required = false) String id) {
        Banner entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = bannerService.get(id);
        }
        if (entity == null) {
            entity = new Banner();
        }
        return entity;
    }

    @RequestMapping(value = {"list", ""})
    public String list(Banner banner, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Banner> page = bannerService.findPage(new Page<Banner>(request, response), banner);
        model.addAttribute("page", page);
        model.addAttribute("banner", banner);
        return "modules/drh/bannerList";
    }

    //@RequiresPermissions("drh:tOrg:view")
    @RequestMapping(value = "form")
    public String form(Banner banner, Model model) {

        Banner banner1 = bannerService.get(banner);
        model.addAttribute("banner", banner1);
        return "modules/drh/bannerForm";
    }
	@RequestMapping(value = "add")
	public String add(Banner banner, Model model) {

		model.addAttribute("banner", banner);
		return "modules/drh/bannerForm";
	}

    //@RequiresPermissions("drh:tOrg:edit")
    @RequestMapping(value = "save")
    public String save(Banner banner, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, banner)) {
            return form(banner, model);
        }
        bannerService.save(banner);
        addMessage(redirectAttributes, "保存导航图信息成功");
        return "redirect:" + Global.getAdminPath() + "/drh/banner/?repage";
    }

    //@RequiresPermissions("drh:tOrg:edit")
    @RequestMapping(value = "delete")
    public String delete(Banner banner, RedirectAttributes redirectAttributes) {
        bannerService.delete(banner);
        addMessage(redirectAttributes, "删除导航图信息成功");
        return "redirect:" + Global.getAdminPath() + "/drh/banner/?repage";
    }
}
