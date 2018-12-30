/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * usercouponEntity
 * @author hl
 * @version 2017-11-07
 */
public class TUserCoupon extends DataEntity<TUserCoupon> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// userid
	private String couponid;		// couponid
	private String name;
	private BigDecimal  facevalue;
	private String orgid;
	private String orgname;
	private Date startdate;
	private Date enddate;
	private String type;
	private int status;
	private String couponorgid;
	
	public TUserCoupon() {
		super();
	}

	public TUserCoupon(String id){
		super(id);
	}

	public String getCouponorgid() {
		return couponorgid;
	}

	public void setCouponorgid(String couponorgid) {
		this.couponorgid = couponorgid;
	}

	@Length(min=1, max=64, message="userid长度必须介于 1 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=64, message="couponid长度必须介于 0 和 64 之间")
	public String getCouponid() {
		return couponid;
	}

	public void setCouponid(String couponid) {
		this.couponid = couponid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public BigDecimal getFacevalue() {
		return facevalue;
	}

	public void setFacevalue(BigDecimal facevalue) {
		this.facevalue = facevalue;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}