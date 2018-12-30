/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import com.thinkgem.jeesite.modules.sys.dao.OrgDao;
import com.thinkgem.jeesite.modules.sys.entity.Org;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 机构信息Service
 * @author hl
 * @version 2017-10-24
 */
@Service
@Transactional(readOnly = true)
public class OrgService extends CrudService<OrgDao, Org> {

	public Org get(String id) {
		return super.get(id);
	}

	public List<Org> findList(Org tOrg) {
		return super.findList(tOrg);
	}

	public Page<Org> findPage(Page<Org> page, Org tOrg) {
		return super.findPage(page, tOrg);
	}

	@Transactional(readOnly = false)
	public void save(Org tOrg) {
		super.save(tOrg);
	}

	@Transactional(readOnly = false)
	public void delete(Org tOrg) {
		super.delete(tOrg);
	}

}