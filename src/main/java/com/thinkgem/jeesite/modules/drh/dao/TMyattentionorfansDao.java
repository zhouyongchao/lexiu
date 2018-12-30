/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.drh.entity.TMyattentionorfans;

/**
 * 关注信息DAO接口
 * @author hl
 * @version 2017-09-20
 */
@MyBatisDao
public interface TMyattentionorfansDao extends CrudDao<TMyattentionorfans> {
	
	public  String getFansCount(String attentionid);
	public  String getAttentionCount(String fansid);
}