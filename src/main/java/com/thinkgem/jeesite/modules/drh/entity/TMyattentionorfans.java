/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 关注信息Entity
 * @author hl
 * @version 2017-09-20
 */
public class TMyattentionorfans extends DataEntity<TMyattentionorfans> {
	
	private static final long serialVersionUID = 1L;
	private String attentionid;		// 关注者id
	private String fansid;		// 粉丝id
	private String status;		// status
	
	public TMyattentionorfans() {
		super();
	}

	public TMyattentionorfans(String id){
		super(id);
	}

	@Length(min=0, max=64, message="关注者id长度必须介于 0 和 64 之间")
	public String getAttentionid() {
		return attentionid;
	}

	public void setAttentionid(String attentionid) {
		this.attentionid = attentionid;
	}
	
	@Length(min=0, max=64, message="粉丝id长度必须介于 0 和 64 之间")
	public String getFansid() {
		return fansid;
	}

	public void setFansid(String fansid) {
		this.fansid = fansid;
	}
	
	@Length(min=0, max=10, message="status长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}