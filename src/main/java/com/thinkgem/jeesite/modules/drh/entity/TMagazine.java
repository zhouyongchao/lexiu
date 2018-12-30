/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 杂志Entity
 * @author hl
 * @version 2017-11-12
 */
public class TMagazine extends DataEntity<TMagazine> {
	
	private static final long serialVersionUID = 1L;
	private String imageurl;		// imageurl
	private String title;		// title
	private String description;
	private String content;		// content
//	private String skipUrl;
	private String type;
	private boolean collected; //是否已经收藏
	
	public TMagazine() {
		super();
	}

	public TMagazine(String id){
		super(id);
	}

	@Length(min=0, max=1000, message="imageurl长度必须介于 0 和 1000 之间")
	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	@Length(min=0, max=1000, message="title长度必须介于 0 和 1000 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=6000, message="content长度必须介于 0 和 6000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


//	public String getSkipUrl() {
//		return skipUrl;
//	}
//
//	public void setSkipUrl(String skipUrl) {
//		this.skipUrl = skipUrl;
//	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isCollected() {
		return collected;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}