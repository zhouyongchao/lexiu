/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.drh.entity.TCoupon;
import com.thinkgem.jeesite.modules.drh.service.TCouponService;

/**
 * couponController
 * @author hl
 * @version 2017-11-07
 */
@Controller
@RequestMapping(value = "${adminPath}/drh/tCoupon")
public class TCouponController extends BaseController {

	@Autowired
	private TCouponService tCouponService;
	
	@ModelAttribute
	public TCoupon get(@RequestParam(required=false) String id) {
		TCoupon entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tCouponService.get(id);
		}
		if (entity == null){
			entity = new TCoupon();
		}
		return entity;
	}
	
//	@RequiresPermissions("drh:tCoupon:view")
	@RequestMapping(value = {"list", ""})
	public String list(TCoupon tCoupon, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TCoupon> page = tCouponService.findPage(new Page<TCoupon>(request, response), tCoupon);
		model.addAttribute("tCoupon",tCoupon);
		model.addAttribute("page", page);
		return "modules/drh/tCouponList";
	}

//	@RequiresPermissions("drh:tCoupon:view")
	@RequestMapping(value = "form")
	public String form(TCoupon tCoupon, Model model) {
		model.addAttribute("tCoupon", tCoupon);
		return "modules/drh/tCouponForm";
	}

//	@RequiresPermissions("drh:tCoupon:edit")
	@RequestMapping(value = "save")
	public String save(TCoupon tCoupon, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tCoupon)){
			return form(tCoupon, model);
		}
		tCoupon.setOrgid(UserUtils.getUser().getId());
		tCouponService.save(tCoupon);
		addMessage(redirectAttributes, "保存coupon成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tCoupon/?repage";
	}
	
//	@RequiresPermissions("drh:tCoupon:edit")
	@RequestMapping(value = "delete")
	public String delete(TCoupon tCoupon, RedirectAttributes redirectAttributes) {
		tCouponService.delete(tCoupon);
		addMessage(redirectAttributes, "删除coupon成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tCoupon/?repage";
	}

}