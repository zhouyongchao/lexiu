/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 消息信息Entity
 * @author hl
 * @version 2017-09-20
 */
public class TNews extends DataEntity<TNews> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// userid
	private String range;		// range
	private String type;		// type
	
	public TNews() {
		super();
	}

	public TNews(String id){
		super(id);
	}

	@Length(min=1, max=64, message="userid长度必须介于 1 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=20, message="range长度必须介于 0 和 20 之间")
	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}
	
	@Length(min=0, max=10, message="type长度必须介于 0 和 10 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}