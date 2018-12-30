/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.entity.TMyattentionorfans;
import com.thinkgem.jeesite.modules.drh.dao.TMyattentionorfansDao;

/**
 * 关注信息Service
 * @author hl
 * @version 2017-09-20
 */
@Service
@Transactional(readOnly = true)
public class TMyattentionorfansService extends CrudService<TMyattentionorfansDao, TMyattentionorfans> {
	@Autowired
	private TMyattentionorfansDao tMyattentionorfansDao;
	public TMyattentionorfans get(String id) {
		return super.get(id);
	}
	
	public List<TMyattentionorfans> findList(TMyattentionorfans tMyattentionorfans) {
		return super.findList(tMyattentionorfans);
	}
	
	public Page<TMyattentionorfans> findPage(Page<TMyattentionorfans> page, TMyattentionorfans tMyattentionorfans) {
		return super.findPage(page, tMyattentionorfans);
	}
	
	@Transactional(readOnly = false)
	public void save(TMyattentionorfans tMyattentionorfans) {
		super.save(tMyattentionorfans);
	}
	
	@Transactional(readOnly = false)
	public void delete(TMyattentionorfans tMyattentionorfans) {
		super.delete(tMyattentionorfans);
	}
	@Transactional(readOnly = false)
	public  String getFansCount(String attentionid){
		return tMyattentionorfansDao.getFansCount(attentionid);
	}
	@Transactional(readOnly = false)
	public  String getAttentionCount(String fansid){
		return tMyattentionorfansDao.getAttentionCount(fansid);
	}
	
}