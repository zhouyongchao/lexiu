package com.thinkgem.jeesite.modules.drh.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * Created by Niexuyang on 2017/11/15.
 */
public class TCouponOrg extends DataEntity<TCouponOrg>{

    private static final long serialVersionUID = 1L;

    private String couponid;
    private String orgid;
    private Date startdate;		// startdate
    private Date enddate;		// enddate

    public TCouponOrg() {
    }

    public TCouponOrg(String id) {
        super(id);
    }


    public String getCouponid() {
        return couponid;
    }

    public void setCouponid(String couponid) {
        this.couponid = couponid;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}
