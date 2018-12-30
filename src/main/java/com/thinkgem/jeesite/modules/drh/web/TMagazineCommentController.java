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
import com.thinkgem.jeesite.modules.drh.entity.TMagazineComment;
import com.thinkgem.jeesite.modules.drh.service.TMagazineCommentService;

/**
 * 杂志评论Controller
 * @author hl
 * @version 2017-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/drh/tMagazineComment")
public class TMagazineCommentController extends BaseController {

	@Autowired
	private TMagazineCommentService tMagazineCommentService;
	
	@ModelAttribute
	public TMagazineComment get(@RequestParam(required=false) String id) {
		TMagazineComment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tMagazineCommentService.get(id);
		}
		if (entity == null){
			entity = new TMagazineComment();
		}
		return entity;
	}
	
	@RequiresPermissions("drh:tMagazineComment:view")
	@RequestMapping(value = {"list", ""})
	public String list(TMagazineComment tMagazineComment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TMagazineComment> page = tMagazineCommentService.findPage(new Page<TMagazineComment>(request, response), tMagazineComment); 
		model.addAttribute("page", page);
		return "modules/drh/tMagazineCommentList";
	}

	@RequiresPermissions("drh:tMagazineComment:view")
	@RequestMapping(value = "form")
	public String form(TMagazineComment tMagazineComment, Model model) {
		model.addAttribute("tMagazineComment", tMagazineComment);
		return "modules/drh/tMagazineCommentForm";
	}

	@RequiresPermissions("drh:tMagazineComment:edit")
	@RequestMapping(value = "save")
	public String save(TMagazineComment tMagazineComment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tMagazineComment)){
			return form(tMagazineComment, model);
		}
		tMagazineCommentService.save(tMagazineComment);
		addMessage(redirectAttributes, "保存杂志评论成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tMagazineComment/?repage";
	}
	
	@RequiresPermissions("drh:tMagazineComment:edit")
	@RequestMapping(value = "delete")
	public String delete(TMagazineComment tMagazineComment, RedirectAttributes redirectAttributes) {
		tMagazineCommentService.delete(tMagazineComment);
		addMessage(redirectAttributes, "删除杂志评论成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tMagazineComment/?repage";
	}

}