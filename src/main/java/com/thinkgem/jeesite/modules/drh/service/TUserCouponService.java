/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.service;

import java.util.List;

import com.thinkgem.jeesite.modules.drh.dao.TUserCouponDao;
import com.thinkgem.jeesite.modules.drh.entity.TUserCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;


/**
 * usercouponService
 * @author hl
 * @version 2017-11-07
 */
@Service
@Transactional(readOnly = true)
public class TUserCouponService extends CrudService<TUserCouponDao, TUserCoupon> {

	@Autowired
	private TUserCouponDao tUserCouponDao;

	public TUserCoupon get(String id) {
		return super.get(id);
	}
	
	public List<TUserCoupon> findList(TUserCoupon tUsercoupon) {
		return super.findList(tUsercoupon);
	}
	
	public Page<TUserCoupon> findPage(Page<TUserCoupon> page, TUserCoupon tUsercoupon) {
		return super.findPage(page, tUsercoupon);
	}

	public TUserCouponService() {
	}

	@Transactional(readOnly = false)
	public void save(TUserCoupon tUsercoupon) {
		super.save(tUsercoupon);
	}
	
	@Transactional(readOnly = false)
	public void delete(TUserCoupon tUsercoupon) {
		super.delete(tUsercoupon);
	}
	
}