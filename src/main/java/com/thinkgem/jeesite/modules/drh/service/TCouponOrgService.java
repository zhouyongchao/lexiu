package com.thinkgem.jeesite.modules.drh.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.dao.TCouponOrgDao;
import com.thinkgem.jeesite.modules.drh.entity.TCouponOrg;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Niexuyang on 2017/11/15.
 */
@Service
public class TCouponOrgService extends CrudService<TCouponOrgDao,TCouponOrg> {

    @Override
    public TCouponOrg get(String id) {
        return super.get(id);
    }

    @Override
    public TCouponOrg get(TCouponOrg entity) {
        return super.get(entity);
    }

    @Override
    public List<TCouponOrg> findList(TCouponOrg entity) {
        return super.findList(entity);
    }

    @Override
    public Page<TCouponOrg> findPage(Page<TCouponOrg> page, TCouponOrg entity) {
        return super.findPage(page, entity);
    }

    @Override
    public void save(TCouponOrg entity) {
        super.save(entity);
    }

    @Override
    public void delete(TCouponOrg entity) {
        super.delete(entity);
    }
}
