/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.drh.entity.TSignlnrecord;

import java.util.List;

/**
 * 签到表DAO接口
 * @author 签到表
 * @version 2017-11-07
 */
@MyBatisDao
public interface TSignlnrecordDao extends CrudDao<TSignlnrecord> {

    /**
     * 查询所有数据列表
     * @param entity
     * @return
     */
    public List<TSignlnrecord> findUserRecord(TSignlnrecord entity);
}