package com.thinkgem.jeesite.modules.drh.service;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.dao.BannerDao;
import com.thinkgem.jeesite.modules.drh.entity.Banner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BannerService extends CrudService<BannerDao,Banner> {

    @Override
    public Banner get(String id) {
        return super.get(id);
    }

    @Override
    public Banner get(Banner entity) {
        return super.get(entity);
    }

    @Override
    public List<Banner> findList(Banner entity) {
        return super.findList(entity);
    }

    @Override
    public Page<Banner> findPage(Page<Banner> page, Banner entity) {
        return super.findPage(page, entity);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Banner entity) {
        super.save(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Banner entity) {
        super.delete(entity);
    }
}
