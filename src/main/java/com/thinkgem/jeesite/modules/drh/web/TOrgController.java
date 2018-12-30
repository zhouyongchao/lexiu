/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.sys.entity.Org;
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
import com.thinkgem.jeesite.modules.drh.entity.City;

import com.thinkgem.jeesite.modules.drh.service.CityService;
import com.thinkgem.jeesite.modules.sys.service.OrgService;

/**
 * 机构信息Controller
 * @author hl
 * @version 2017-10-24
 */
@Controller
@RequestMapping(value = "${adminPath}/drh/tOrg")
public class TOrgController extends BaseController {

	@Autowired
	private OrgService tOrgService;
	@Autowired
	private CityService cityService;
	@ModelAttribute
	public Org get(@RequestParam(required=false) String id) {
		Org entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tOrgService.get(id);
		}
		if (entity == null){
			entity = new Org();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Org tOrg, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Org> page = tOrgService.findPage(new Page<Org>(request, response), tOrg);
		model.addAttribute("page", page);
		model.addAttribute("tOrg", tOrg);
		return "modules/drh/tOrgList";
	}

	//@RequiresPermissions("drh:tOrg:view")
	@RequestMapping(value = "form")
	public String form(Org tOrg, Model model) {
		City c=new City();
		List<City> cityList=cityService.findList(c);
		Org org = tOrgService.get(tOrg);
		model.addAttribute("cityList",cityList);
		model.addAttribute("org", org);
		return "modules/drh/tOrgForm";
	}
//	@RequestMapping(value = "add")
//	public String add(Org tOrg, Model model) {
//		City c=new City();
//		List<City> cityList=cityService.findList(c);
//		model.addAttribute("cityList",cityList);
//		model.addAttribute("tOrg", tOrg);
//		return "modules/drh/tOrgForm";
//	}

	//@RequiresPermissions("drh:tOrg:edit")
	@RequestMapping(value = "save")
	public String save(Org tOrg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tOrg)){
			return form(tOrg, model);
		}
		tOrg.setCity(cityService.get(tOrg.getCityid()).getCity());
		tOrgService.save(tOrg);
		addMessage(redirectAttributes, "保存机构信息成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tOrg/?repage";
	}
	
	//@RequiresPermissions("drh:tOrg:edit")
	@RequestMapping(value = "delete")
	public String delete(Org tOrg, RedirectAttributes redirectAttributes) {
		tOrgService.delete(tOrg);
		addMessage(redirectAttributes, "删除机构信息成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tOrg/?repage";
	}

}