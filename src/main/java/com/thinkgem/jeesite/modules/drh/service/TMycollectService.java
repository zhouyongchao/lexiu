/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.entity.TMycollect;
import com.thinkgem.jeesite.modules.drh.dao.TMycollectDao;

/**
 * 收藏信息Service
 * @author hl
 * @version 2017-09-20
 */
@Service
@Transactional(readOnly = true)
public class TMycollectService extends CrudService<TMycollectDao, TMycollect> {

	public TMycollect get(String id) {
		return super.get(id);
	}
	
	public List<TMycollect> findList(TMycollect tMycollect) {
		return super.findList(tMycollect);
	}
	
	public Page<TMycollect> findPage(Page<TMycollect> page, TMycollect tMycollect) {
		return super.findPage(page, tMycollect);
	}
	
	@Transactional(readOnly = false)
	public void save(TMycollect tMycollect) {
		super.save(tMycollect);
	}
	
	@Transactional(readOnly = false)
	public void delete(TMycollect tMycollect) {
		super.delete(tMycollect);
	}
	
}