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
import com.thinkgem.jeesite.modules.drh.entity.TOrgComment;
import com.thinkgem.jeesite.modules.drh.service.TOrgCommentService;

/**
 * 机构评论Controller
 * @author hl
 * @version 2017-11-03
 */
@Controller
@RequestMapping(value = "${adminPath}/drh/tOrgComment")
public class TOrgCommentController extends BaseController {

	@Autowired
	private TOrgCommentService tOrgCommentService;
	
	@ModelAttribute
	public TOrgComment get(@RequestParam(required=false) String id) {
		TOrgComment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tOrgCommentService.get(id);
		}
		if (entity == null){
			entity = new TOrgComment();
		}
		return entity;
	}
	
	@RequiresPermissions("drh:tOrgComment:view")
	@RequestMapping(value = {"list", ""})
	public String list(TOrgComment tOrgComment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TOrgComment> page = tOrgCommentService.findPage(new Page<TOrgComment>(request, response), tOrgComment); 
		model.addAttribute("page", page);
		return "modules/drh/tOrgCommentList";
	}

	@RequiresPermissions("drh:tOrgComment:view")
	@RequestMapping(value = "form")
	public String form(TOrgComment tOrgComment, Model model) {
		model.addAttribute("tOrgComment", tOrgComment);
		return "modules/drh/tOrgCommentForm";
	}

	@RequiresPermissions("drh:tOrgComment:edit")
	@RequestMapping(value = "save")
	public String save(TOrgComment tOrgComment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tOrgComment)){
			return form(tOrgComment, model);
		}
		tOrgCommentService.save(tOrgComment);
		addMessage(redirectAttributes, "保存机构评论成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tOrgComment/?repage";
	}
	
	@RequiresPermissions("drh:tOrgComment:edit")
	@RequestMapping(value = "delete")
	public String delete(TOrgComment tOrgComment, RedirectAttributes redirectAttributes) {
		tOrgCommentService.delete(tOrgComment);
		addMessage(redirectAttributes, "删除机构评论成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tOrgComment/?repage";
	}

}