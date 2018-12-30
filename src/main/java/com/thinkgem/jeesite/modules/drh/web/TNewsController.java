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
import com.thinkgem.jeesite.modules.drh.entity.TNews;
import com.thinkgem.jeesite.modules.drh.service.TNewsService;

/**
 * 消息信息Controller
 * @author hl
 * @version 2017-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/drh/tNews")
public class TNewsController extends BaseController {

	@Autowired
	private TNewsService tNewsService;
	
	@ModelAttribute
	public TNews get(@RequestParam(required=false) String id) {
		TNews entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tNewsService.get(id);
		}
		if (entity == null){
			entity = new TNews();
		}
		return entity;
	}
	
	@RequiresPermissions("drh:tNews:view")
	@RequestMapping(value = {"list", ""})
	public String list(TNews tNews, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TNews> page = tNewsService.findPage(new Page<TNews>(request, response), tNews); 
		model.addAttribute("page", page);
		return "modules/drh/tNewsList";
	}

	@RequiresPermissions("drh:tNews:view")
	@RequestMapping(value = "form")
	public String form(TNews tNews, Model model) {
		model.addAttribute("tNews", tNews);
		return "modules/drh/tNewsForm";
	}

	@RequiresPermissions("drh:tNews:edit")
	@RequestMapping(value = "save")
	public String save(TNews tNews, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tNews)){
			return form(tNews, model);
		}
		tNewsService.save(tNews);
		addMessage(redirectAttributes, "保存消息信息成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tNews/?repage";
	}
	
	@RequiresPermissions("drh:tNews:edit")
	@RequestMapping(value = "delete")
	public String delete(TNews tNews, RedirectAttributes redirectAttributes) {
		tNewsService.delete(tNews);
		addMessage(redirectAttributes, "删除消息信息成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tNews/?repage";
	}

}