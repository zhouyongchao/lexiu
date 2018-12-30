/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.entity.TSecurities;
import com.thinkgem.jeesite.modules.drh.dao.TSecuritiesDao;

/**
 * 折扣信息Service
 * @author hl
 * @version 2017-09-20
 */
@Service
@Transactional(readOnly = true)
public class TSecuritiesService extends CrudService<TSecuritiesDao, TSecurities> {

	public TSecurities get(String id) {
		return super.get(id);
	}
	
	public List<TSecurities> findList(TSecurities tSecurities) {
		return super.findList(tSecurities);
	}
	
	public Page<TSecurities> findPage(Page<TSecurities> page, TSecurities tSecurities) {
		return super.findPage(page, tSecurities);
	}
	
	@Transactional(readOnly = false)
	public void save(TSecurities tSecurities) {
		super.save(tSecurities);
	}
	
	@Transactional(readOnly = false)
	public void delete(TSecurities tSecurities) {
		super.delete(tSecurities);
	}
	
}