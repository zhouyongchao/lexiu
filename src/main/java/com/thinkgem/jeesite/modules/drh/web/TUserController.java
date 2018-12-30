/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.service.IMUserService;
import com.thinkgem.jeesite.modules.sys.entity.Org;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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
import com.thinkgem.jeesite.modules.drh.entity.TUser;
import com.thinkgem.jeesite.modules.drh.service.TUserService;

import java.util.LinkedHashMap;

/**
 * 用户信息Controller
 * @author hl
 * @version 2017-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/drh/tUser")
public class TUserController extends BaseController {

	@Autowired
	private TUserService tUserService;
	@Autowired
	private IMUserService imUserService;
	
	@ModelAttribute
	public TUser get(@RequestParam(required=false) String id) {
		TUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tUserService.get(id);
		}
		if (entity == null){
			entity = new TUser();
		}
		return entity;
	}


	//@RequiresPermissions("drh:tUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(TUser tUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Org tOrg = UserUtils.getUser();
		if (tOrg!=null&&!"1".equals(tOrg.getId())){
			tUser.setSid(tOrg.getId());
		}
		Page<TUser> page = tUserService.findPage(new Page<TUser>(request, response), tUser);
		model.addAttribute("tUser", tUser);
		model.addAttribute("page", page);
		return "modules/drh/tUserList";
	}
	//@RequiresPermissions("drh:tUser:view")
	@RequestMapping(value = "form")
	public String form(TUser tUser, Model model) {
		model.addAttribute("tUser", tUser);
		return "modules/drh/tUserForm";
	}

	//@RequiresPermissions("drh:tUser:edit")
	@RequestMapping(value = "save")
	public String save(TUser tUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tUser)){
			return form(tUser, model);
		}
		if(StringUtils.isEmpty(tUser.getId())) {
			if(imUserService.regToIM(tUser.getUsername(),tUser.getUsername())!=null){
				tUserService.save(tUser);
				addMessage(redirectAttributes, "保存用户信息成功");
				return "redirect:"+Global.getAdminPath()+"/drh/tUser/?repage";
			}else {
				addMessage(redirectAttributes, "注册环信失败");
				return "redirect:"+Global.getAdminPath()+"/drh/tUser/?repage";
			}
		}
		tUserService.save(tUser);
		addMessage(redirectAttributes, "保存用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tUser/?repage";
	}
	
	//@RequiresPermissions("drh:tUser:edit")
	@RequestMapping(value = "delete")
	public String delete(TUser tUser, RedirectAttributes redirectAttributes) {
		tUserService.delete(tUser);
		addMessage(redirectAttributes, "删除用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tUser/?repage";
	}


}