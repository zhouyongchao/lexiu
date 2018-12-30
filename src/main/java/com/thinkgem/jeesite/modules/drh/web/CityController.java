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
import com.thinkgem.jeesite.modules.drh.entity.City;
import com.thinkgem.jeesite.modules.drh.service.CityService;

/**
 * 城市信息Controller
 * @author hl
 * @version 2017-10-25
 */
@Controller
@RequestMapping(value = "${adminPath}/drh/city")
public class CityController extends BaseController {

	@Autowired
	private CityService cityService;
	
	@ModelAttribute
	public City get(@RequestParam(required=false) String id) {
		City entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cityService.get(id);
		}
		if (entity == null){
			entity = new City();
		}
		return entity;
	}
	
	@RequiresPermissions("drh:city:view")
	@RequestMapping(value = {"list", ""})
	public String list(City city, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<City> page = cityService.findPage(new Page<City>(request, response), city); 
		model.addAttribute("page", page);
		return "modules/drh/cityList";
	}

	@RequiresPermissions("drh:city:view")
	@RequestMapping(value = "form")
	public String form(City city, Model model) {
		model.addAttribute("city", city);
		return "modules/drh/cityForm";
	}

	@RequiresPermissions("drh:city:edit")
	@RequestMapping(value = "save")
	public String save(City city, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, city)){
			return form(city, model);
		}
		cityService.save(city);
		addMessage(redirectAttributes, "保存城市信息成功");
		return "redirect:"+Global.getAdminPath()+"/drh/city/?repage";
	}
	
	@RequiresPermissions("drh:city:edit")
	@RequestMapping(value = "delete")
	public String delete(City city, RedirectAttributes redirectAttributes) {
		cityService.delete(city);
		addMessage(redirectAttributes, "删除城市信息成功");
		return "redirect:"+Global.getAdminPath()+"/drh/city/?repage";
	}

}