/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.entity.TSignlnrecord;
import com.thinkgem.jeesite.modules.drh.dao.TSignlnrecordDao;

/**
 * 签到表Service
 * @author 签到表
 * @version 2017-11-07
 */
@Service
@Transactional(readOnly = true)
public class TSignlnrecordService extends CrudService<TSignlnrecordDao, TSignlnrecord> {

	@Autowired
	private TSignlnrecordDao tSignlnrecordDao;

	public TSignlnrecord get(String id) {
		return super.get(id);
	}
	
	public List<TSignlnrecord> findList(TSignlnrecord tSignlnrecord) {
		return super.findList(tSignlnrecord);
	}
	
	public Page<TSignlnrecord> findPage(Page<TSignlnrecord> page, TSignlnrecord tSignlnrecord) {
		return super.findPage(page, tSignlnrecord);
	}
	
	@Transactional(readOnly = false)
	public void save(TSignlnrecord tSignlnrecord) {
		super.save(tSignlnrecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(TSignlnrecord tSignlnrecord) {
		super.delete(tSignlnrecord);
	}

	public TSignlnrecord findUserRecord(TSignlnrecord entity){
		List<TSignlnrecord> list = tSignlnrecordDao.findUserRecord(entity);
		if (list!=null&&list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}

}