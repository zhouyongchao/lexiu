/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 收藏信息Entity
 * @author hl 
 * @version 2017-09-20
 */
public class TMycollect extends DataEntity<TMycollect> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// userid
	private String peripheryids;		// 周边信息
	private String status;		// 1代表杂志0 代表周边
	
	public TMycollect() {
		super();
	}

	public TMycollect(String id){
		super(id);
	}

	@Length(min=1, max=64, message="userid长度必须介于 1 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=64, message="周边信息长度必须介于 0 和 64 之间")
	public String getPeripheryids() {
		return peripheryids;
	}

	public void setPeripheryids(String peripheryids) {
		this.peripheryids = peripheryids;
	}
	
	@Length(min=0, max=10, message="1已收藏0 取消收藏长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}