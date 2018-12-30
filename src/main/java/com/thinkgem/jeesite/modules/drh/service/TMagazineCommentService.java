/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.entity.TMagazineComment;
import com.thinkgem.jeesite.modules.drh.dao.TMagazineCommentDao;

/**
 * 杂志评论Service
 * @author hl
 * @version 2017-11-12
 */
@Service
@Transactional(readOnly = true)
public class TMagazineCommentService extends CrudService<TMagazineCommentDao, TMagazineComment> {

	public TMagazineComment get(String id) {
		return super.get(id);
	}
	
	public List<TMagazineComment> findList(TMagazineComment tMagazineComment) {
		return super.findList(tMagazineComment);
	}
	
	public Page<TMagazineComment> findPage(Page<TMagazineComment> page, TMagazineComment tMagazineComment) {
		return super.findPage(page, tMagazineComment);
	}
	
	@Transactional(readOnly = false)
	public void save(TMagazineComment tMagazineComment) {
		super.save(tMagazineComment);
	}
	
	@Transactional(readOnly = false)
	public void delete(TMagazineComment tMagazineComment) {
		super.delete(tMagazineComment);
	}
	
}