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
import com.thinkgem.jeesite.modules.drh.entity.TMyattentionorfans;
import com.thinkgem.jeesite.modules.drh.service.TMyattentionorfansService;

/**
 * 关注信息Controller
 * @author hl
 * @version 2017-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/drh/tMyattentionorfans")
public class TMyattentionorfansController extends BaseController {

	@Autowired
	private TMyattentionorfansService tMyattentionorfansService;
	
	@ModelAttribute
	public TMyattentionorfans get(@RequestParam(required=false) String id) {
		TMyattentionorfans entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tMyattentionorfansService.get(id);
		}
		if (entity == null){
			entity = new TMyattentionorfans();
		}
		return entity;
	}
	
	@RequiresPermissions("drh:tMyattentionorfans:view")
	@RequestMapping(value = {"list", ""})
	public String list(TMyattentionorfans tMyattentionorfans, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TMyattentionorfans> page = tMyattentionorfansService.findPage(new Page<TMyattentionorfans>(request, response), tMyattentionorfans); 
		model.addAttribute("page", page);
		return "modules/drh/tMyattentionorfansList";
	}

	@RequiresPermissions("drh:tMyattentionorfans:view")
	@RequestMapping(value = "form")
	public String form(TMyattentionorfans tMyattentionorfans, Model model) {
		model.addAttribute("tMyattentionorfans", tMyattentionorfans);
		return "modules/drh/tMyattentionorfansForm";
	}

	@RequiresPermissions("drh:tMyattentionorfans:edit")
	@RequestMapping(value = "save")
	public String save(TMyattentionorfans tMyattentionorfans, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tMyattentionorfans)){
			return form(tMyattentionorfans, model);
		}
		tMyattentionorfansService.save(tMyattentionorfans);
		addMessage(redirectAttributes, "保存关注信息成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tMyattentionorfans/?repage";
	}
	
	@RequiresPermissions("drh:tMyattentionorfans:edit")
	@RequestMapping(value = "delete")
	public String delete(TMyattentionorfans tMyattentionorfans, RedirectAttributes redirectAttributes) {
		tMyattentionorfansService.delete(tMyattentionorfans);
		addMessage(redirectAttributes, "删除关注信息成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tMyattentionorfans/?repage";
	}

}