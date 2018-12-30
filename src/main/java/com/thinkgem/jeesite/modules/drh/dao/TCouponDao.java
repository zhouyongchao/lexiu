/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.drh.entity.TCoupon;

/**
 * couponDAO接口
 * @author hl
 * @version 2017-11-07
 */
@MyBatisDao
public interface TCouponDao extends CrudDao<TCoupon> {
	
}