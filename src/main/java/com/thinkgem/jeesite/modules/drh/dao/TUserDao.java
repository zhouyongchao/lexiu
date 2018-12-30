/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.drh.entity.TUser;

import java.util.List;

/**
 * 用户信息DAO接口
 * @author hl
 * @version 2017-09-20
 */
@MyBatisDao
public interface TUserDao extends CrudDao<TUser> {
	public  TUser getByObj(TUser tUser);

	public List<TUser> findShowUsers(TUser tUser);
}