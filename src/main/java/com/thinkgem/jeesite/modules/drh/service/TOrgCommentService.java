/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.entity.TOrgComment;
import com.thinkgem.jeesite.modules.drh.dao.TOrgCommentDao;

/**
 * 机构评论Service
 * @author hl
 * @version 2017-11-03
 */
@Service
@Transactional(readOnly = true)
public class TOrgCommentService extends CrudService<TOrgCommentDao, TOrgComment> {

	public TOrgComment get(String id) {
		return super.get(id);
	}
	
	public List<TOrgComment> findList(TOrgComment tOrgComment) {
		return super.findList(tOrgComment);
	}
	
	public Page<TOrgComment> findPage(Page<TOrgComment> page, TOrgComment tOrgComment) {
		return super.findPage(page, tOrgComment);
	}
	
	@Transactional(readOnly = false)
	public void save(TOrgComment tOrgComment) {
		super.save(tOrgComment);
	}
	
	@Transactional(readOnly = false)
	public void delete(TOrgComment tOrgComment) {
		super.delete(tOrgComment);
	}
	
}