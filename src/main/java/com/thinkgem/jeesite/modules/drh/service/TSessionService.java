package com.thinkgem.jeesite.modules.drh.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.dao.TSessionDao;
import com.thinkgem.jeesite.modules.drh.entity.TSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by root on 2017/9/26.
 */
@Service
@Transactional(readOnly = true)
public class TSessionService extends CrudService<TSessionDao,TSession>{
    @Override
    public TSession get(String id) {
        return super.get(id);
    }

    @Override
    public TSession get(TSession entity) {
        return super.get(entity);
    }

    @Override
    public List<TSession> findList(TSession entity) {
        return super.findList(entity);
    }

    @Override
    public Page<TSession> findPage(Page<TSession> page, TSession entity) {
        return super.findPage(page, entity);
    }

    @Override
    public void save(TSession entity) {
        super.save(entity);
    }

    @Override
    public void delete(TSession entity) {
        super.delete(entity);
    }

}
