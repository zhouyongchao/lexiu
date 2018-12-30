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
import com.thinkgem.jeesite.modules.drh.entity.TLiveroom;
import com.thinkgem.jeesite.modules.drh.service.TLiveroomService;

/**
 * 直播间Controller
 * @author hl
 * @version 2017-11-07
 */
@Controller
@RequestMapping(value = "${adminPath}/drh/tLiveroom")
public class TLiveroomController extends BaseController {

	@Autowired
	private TLiveroomService tLiveroomService;
	
	@ModelAttribute
	public TLiveroom get(@RequestParam(required=false) String id) {
		TLiveroom entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tLiveroomService.get(id);
		}
		if (entity == null){
			entity = new TLiveroom();
		}
		return entity;
	}
	
	@RequiresPermissions("drh:tLiveroom:view")
	@RequestMapping(value = {"list", ""})
	public String list(TLiveroom tLiveroom, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TLiveroom> page = tLiveroomService.findPage(new Page<TLiveroom>(request, response), tLiveroom); 
		model.addAttribute("page", page);
		return "modules/drh/tLiveroomList";
	}

	@RequiresPermissions("drh:tLiveroom:view")
	@RequestMapping(value = "form")
	public String form(TLiveroom tLiveroom, Model model) {
		model.addAttribute("tLiveroom", tLiveroom);
		return "modules/drh/tLiveroomForm";
	}

	@RequiresPermissions("drh:tLiveroom:edit")
	@RequestMapping(value = "save")
	public String save(TLiveroom tLiveroom, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tLiveroom)){
			return form(tLiveroom, model);
		}
		tLiveroomService.save(tLiveroom);
		addMessage(redirectAttributes, "保存直播间成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tLiveroom/?repage";
	}
	
	@RequiresPermissions("drh:tLiveroom:edit")
	@RequestMapping(value = "delete")
	public String delete(TLiveroom tLiveroom, RedirectAttributes redirectAttributes) {
		tLiveroomService.delete(tLiveroom);
		addMessage(redirectAttributes, "删除直播间成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tLiveroom/?repage";
	}

}