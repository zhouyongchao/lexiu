/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.entity.TMagazine;
import com.thinkgem.jeesite.modules.drh.dao.TMagazineDao;

/**
 * 杂志Service
 * @author hl
 * @version 2017-11-12
 */
@Service
@Transactional(readOnly = true)
public class TMagazineService extends CrudService<TMagazineDao, TMagazine> {

	public TMagazine get(String id) {
		return super.get(id);
	}
	
	public List<TMagazine> findList(TMagazine tMagazine) {
		return super.findList(tMagazine);
	}
	
	public Page<TMagazine> findPage(Page<TMagazine> page, TMagazine tMagazine) {
		return super.findPage(page, tMagazine);
	}
	
	@Transactional(readOnly = false)
	public void save(TMagazine tMagazine) {
		super.save(tMagazine);
	}
	
	@Transactional(readOnly = false)
	public void delete(TMagazine tMagazine) {
		super.delete(tMagazine);
	}
	
}