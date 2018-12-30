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
import com.thinkgem.jeesite.modules.drh.entity.TSignlnrecord;
import com.thinkgem.jeesite.modules.drh.service.TSignlnrecordService;

/**
 * 签到表Controller
 * @author 签到表
 * @version 2017-11-07
 */
@Controller
@RequestMapping(value = "${adminPath}/drh/tSignlnrecord")
public class TSignlnrecordController extends BaseController {

	@Autowired
	private TSignlnrecordService tSignlnrecordService;
	
	@ModelAttribute
	public TSignlnrecord get(@RequestParam(required=false) String id) {
		TSignlnrecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tSignlnrecordService.get(id);
		}
		if (entity == null){
			entity = new TSignlnrecord();
		}
		return entity;
	}
	
	@RequiresPermissions("drh:tSignlnrecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(TSignlnrecord tSignlnrecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TSignlnrecord> page = tSignlnrecordService.findPage(new Page<TSignlnrecord>(request, response), tSignlnrecord); 
		model.addAttribute("page", page);
		return "modules/drh/tSignlnrecordList";
	}

	@RequiresPermissions("drh:tSignlnrecord:view")
	@RequestMapping(value = "form")
	public String form(TSignlnrecord tSignlnrecord, Model model) {
		model.addAttribute("tSignlnrecord", tSignlnrecord);
		return "modules/drh/tSignlnrecordForm";
	}

	@RequiresPermissions("drh:tSignlnrecord:edit")
	@RequestMapping(value = "save")
	public String save(TSignlnrecord tSignlnrecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tSignlnrecord)){
			return form(tSignlnrecord, model);
		}
		tSignlnrecordService.save(tSignlnrecord);
		addMessage(redirectAttributes, "保存hl成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tSignlnrecord/?repage";
	}
	
	@RequiresPermissions("drh:tSignlnrecord:edit")
	@RequestMapping(value = "delete")
	public String delete(TSignlnrecord tSignlnrecord, RedirectAttributes redirectAttributes) {
		tSignlnrecordService.delete(tSignlnrecord);
		addMessage(redirectAttributes, "删除hl成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tSignlnrecord/?repage";
	}

}