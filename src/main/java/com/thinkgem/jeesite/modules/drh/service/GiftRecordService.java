package com.thinkgem.jeesite.modules.drh.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.dao.GiftRecordDao;
import com.thinkgem.jeesite.modules.drh.entity.GiftRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Niexuyang on 2017/11/15.
 */
@Service
public class GiftRecordService extends CrudService<GiftRecordDao,GiftRecord> {

    @Override
    public GiftRecord get(String id) {
        return super.get(id);
    }

    @Override
    public GiftRecord get(GiftRecord entity) {
        return super.get(entity);
    }

    @Override
    public List<GiftRecord> findList(GiftRecord entity) {
        return super.findList(entity);
    }

    @Override
    public Page<GiftRecord> findPage(Page<GiftRecord> page, GiftRecord entity) {
        return super.findPage(page, entity);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(GiftRecord entity) {
        super.save(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(GiftRecord entity) {
        super.delete(entity);
    }
}
