/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.drh.entity.TMycollect;
import com.thinkgem.jeesite.modules.drh.service.TMycollectService;

/**
 * 收藏信息Controller
 * @author hl
 * @version 2017-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/drh/tMycollect")
public class TMycollectController extends BaseController {

	@Autowired
	private TMycollectService tMycollectService;
	
	@ModelAttribute
	public TMycollect get(@RequestParam(required=false) String id) {
		TMycollect entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tMycollectService.get(id);
		}
		if (entity == null){
			entity = new TMycollect();
		}
		return entity;
	}
	
	@RequiresPermissions("drh:tMycollect:view")
	@RequestMapping(value = {"list", ""})
	public String list(TMycollect tMycollect, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TMycollect> page = tMycollectService.findPage(new Page<TMycollect>(request, response), tMycollect); 
		model.addAttribute("page", page);
		return "modules/drh/tMycollectList";
	}

	@RequiresPermissions("drh:tMycollect:view")
	@RequestMapping(value = "form")
	public String form(TMycollect tMycollect, Model model) {
		model.addAttribute("tMycollect", tMycollect);
		return "modules/drh/tMycollectForm";
	}

	@RequiresPermissions("drh:tMycollect:edit")
	@RequestMapping(value = "save")
	public String save(TMycollect tMycollect, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tMycollect)){
			return form(tMycollect, model);
		}
		tMycollectService.save(tMycollect);
		addMessage(redirectAttributes, "保存收藏信息成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tMycollect/?repage";
	}
	
	@RequiresPermissions("drh:tMycollect:edit")
	@RequestMapping(value = "delete")
	public String delete(TMycollect tMycollect, RedirectAttributes redirectAttributes) {
		tMycollectService.delete(tMycollect);
		addMessage(redirectAttributes, "删除收藏信息成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tMycollect/?repage";
	}

}