/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.entity.TNews;
import com.thinkgem.jeesite.modules.drh.dao.TNewsDao;

/**
 * 消息信息Service
 * @author hl
 * @version 2017-09-20
 */
@Service
@Transactional(readOnly = true)
public class TNewsService extends CrudService<TNewsDao, TNews> {

	public TNews get(String id) {
		return super.get(id);
	}
	
	public List<TNews> findList(TNews tNews) {
		return super.findList(tNews);
	}
	
	public Page<TNews> findPage(Page<TNews> page, TNews tNews) {
		return super.findPage(page, tNews);
	}
	
	@Transactional(readOnly = false)
	public void save(TNews tNews) {
		super.save(tNews);
	}
	
	@Transactional(readOnly = false)
	public void delete(TNews tNews) {
		super.delete(tNews);
	}
	
}