/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.drh.entity.TMycollect;

/**
 * 收藏信息DAO接口
 * @author hl
 * @version 2017-09-20
 */
@MyBatisDao
public interface TMycollectDao extends CrudDao<TMycollect> {
	
}