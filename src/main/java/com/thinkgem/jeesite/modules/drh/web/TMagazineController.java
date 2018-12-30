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
import com.thinkgem.jeesite.modules.drh.entity.TMagazine;
import com.thinkgem.jeesite.modules.drh.service.TMagazineService;

/**
 * 杂志Controller
 * @author hl
 * @version 2017-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/drh/tMagazine")
public class TMagazineController extends BaseController {

	@Autowired
	private TMagazineService tMagazineService;
	
	@ModelAttribute
	public TMagazine get(@RequestParam(required=false) String id) {
		TMagazine entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tMagazineService.get(id);
		}
		if (entity == null){
			entity = new TMagazine();
		}
		return entity;
	}
	
//	@RequiresPermissions("drh:tMagazine:view")
	@RequestMapping(value = {"list", ""})
	public String list(TMagazine tMagazine, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TMagazine> page = tMagazineService.findPage(new Page<TMagazine>(request, response), tMagazine); 
		model.addAttribute("page", page);
		model.addAttribute("tMagazine",tMagazine);
		return "modules/drh/tMagazineList";
	}

//	@RequiresPermissions("drh:tMagazine:view")
	@RequestMapping(value = "form")
	public String form(TMagazine tMagazine, Model model) {
		model.addAttribute("tMagazine", tMagazine);
		return "modules/drh/tMagazineForm";
	}

//	@RequiresPermissions("drh:tMagazine:edit")
	@RequestMapping(value = "save")
	public String save(TMagazine tMagazine, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tMagazine)){
			return form(tMagazine, model);
		}
		tMagazineService.save(tMagazine);
		addMessage(redirectAttributes, "保存杂志成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tMagazine/?repage";
	}
	
//	@RequiresPermissions("drh:tMagazine:edit")
	@RequestMapping(value = "delete")
	public String delete(TMagazine tMagazine, RedirectAttributes redirectAttributes) {
		tMagazineService.delete(tMagazine);
		addMessage(redirectAttributes, "删除杂志成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tMagazine/?repage";
	}

}