package com.thinkgem.jeesite.modules.drh.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.dao.GiftDao;
import com.thinkgem.jeesite.modules.drh.entity.Gift;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Niexuyang on 2017/11/15.
 */
@Service
@Transactional(readOnly = true)
public class GiftService extends CrudService<GiftDao,Gift> {
    @Override
    public Gift get(String id) {
        return super.get(id);
    }

    @Override
    public Gift get(Gift entity) {
        return super.get(entity);
    }

    @Override
    public List<Gift> findList(Gift entity) {
        return super.findList(entity);
    }

    @Override
    public Page<Gift> findPage(Page<Gift> page, Gift entity) {
        return super.findPage(page, entity);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Gift entity) {
        super.save(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Gift entity) {
        super.delete(entity);
    }
}
