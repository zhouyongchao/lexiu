package com.thinkgem.jeesite.modules.drh.service;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.dao.ReportDao;
import com.thinkgem.jeesite.modules.drh.entity.Report;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ReportService extends CrudService<ReportDao,Report> {
    @Override
    public Report get(String id) {
        return super.get(id);
    }

    @Override
    public Report get(Report entity) {
        return super.get(entity);
    }

    @Override
    public List<Report> findList(Report entity) {
        return super.findList(entity);
    }

    @Override
    public Page<Report> findPage(Page<Report> page, Report entity) {
        return super.findPage(page, entity);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Report entity) {
        super.save(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Report entity) {
        super.delete(entity);
    }
}
