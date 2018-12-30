/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.drh.entity.City;

/**
 * 城市信息DAO接口
 * @author hl
 * @version 2017-10-25
 */
@MyBatisDao
public interface CityDao extends CrudDao<City> {
	
}