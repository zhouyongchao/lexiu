/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.entity.TLiveroom;
import com.thinkgem.jeesite.modules.drh.dao.TLiveroomDao;

/**
 * 直播间Service
 * @author hl
 * @version 2017-11-07
 */
@Service
@Transactional(readOnly = true)
public class TLiveroomService extends CrudService<TLiveroomDao, TLiveroom> {



	public TLiveroom get(String id) {
		return super.get(id);
	}
	
	public List<TLiveroom> findList(TLiveroom tLiveroom) {
		return super.findList(tLiveroom);
	}
	
	public Page<TLiveroom> findPage(Page<TLiveroom> page, TLiveroom tLiveroom) {
		return super.findPage(page, tLiveroom);
	}
	
	@Transactional(readOnly = false)
	public void save(TLiveroom tLiveroom) {
		super.save(tLiveroom);
	}
	
	@Transactional(readOnly = false)
	public void delete(TLiveroom tLiveroom) {
		super.delete(tLiveroom);
	}
	
}