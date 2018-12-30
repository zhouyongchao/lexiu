/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 机构评论Entity
 * @author hl
 * @version 2017-11-03
 */
public class TOrgComment extends DataEntity<TOrgComment> {
	
	private static final long serialVersionUID = 1L;
	private String orgid;		// orgid
	private String userid;		// userid
	private TUser user;
	private String content;		// content
	private String commentdate;
	private String imageurl;		// imageurl
	private String statute;		// statute
	private float grade;
	
	public TOrgComment() {
		super();
	}

	public TOrgComment(String id){
		super(id);
	}

	@Length(min=1, max=64, message="orgid长度必须介于 1 和 64 之间")
	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	
	@Length(min=1, max=64, message="userid长度必须介于 1 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=1, max=6000, message="content长度必须介于 1 和 6000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=3000, message="imageurl长度必须介于 0 和 3000 之间")
	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	@Length(min=0, max=10, message="statute长度必须介于 0 和 10 之间")
	public String getStatute() {
		return statute;
	}

	public void setStatute(String statute) {
		this.statute = statute;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public String getCommentdate() {
		return commentdate;
	}

	public void setCommentdate(String commentdate) {
		this.commentdate = commentdate;
	}
}