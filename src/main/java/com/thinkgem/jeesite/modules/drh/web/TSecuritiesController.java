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
import com.thinkgem.jeesite.modules.drh.entity.TSecurities;
import com.thinkgem.jeesite.modules.drh.service.TSecuritiesService;

/**
 * 折扣信息Controller
 * @author hl
 * @version 2017-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/drh/tSecurities")
public class TSecuritiesController extends BaseController {

	@Autowired
	private TSecuritiesService tSecuritiesService;
	
	@ModelAttribute
	public TSecurities get(@RequestParam(required=false) String id) {
		TSecurities entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tSecuritiesService.get(id);
		}
		if (entity == null){
			entity = new TSecurities();
		}
		return entity;
	}
	
	@RequiresPermissions("drh:tSecurities:view")
	@RequestMapping(value = {"list", ""})
	public String list(TSecurities tSecurities, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TSecurities> page = tSecuritiesService.findPage(new Page<TSecurities>(request, response), tSecurities); 
		model.addAttribute("page", page);
		return "modules/drh/tSecuritiesList";
	}

	@RequiresPermissions("drh:tSecurities:view")
	@RequestMapping(value = "form")
	public String form(TSecurities tSecurities, Model model) {
		model.addAttribute("tSecurities", tSecurities);
		return "modules/drh/tSecuritiesForm";
	}

	@RequiresPermissions("drh:tSecurities:edit")
	@RequestMapping(value = "save")
	public String save(TSecurities tSecurities, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tSecurities)){
			return form(tSecurities, model);
		}
		tSecuritiesService.save(tSecurities);
		addMessage(redirectAttributes, "保存折扣信息成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tSecurities/?repage";
	}
	
	@RequiresPermissions("drh:tSecurities:edit")
	@RequestMapping(value = "delete")
	public String delete(TSecurities tSecurities, RedirectAttributes redirectAttributes) {
		tSecuritiesService.delete(tSecurities);
		addMessage(redirectAttributes, "删除折扣信息成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tSecurities/?repage";
	}

}