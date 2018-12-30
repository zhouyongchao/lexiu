/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 杂志评论Entity
 * @author hl
 * @version 2017-11-12
 */
public class TMagazineComment extends DataEntity<TMagazineComment> {
	
	private static final long serialVersionUID = 1L;
	private String magazineid;		// magazineid
	private String content;		// content
	private String userid;
	private String statute;		// statute
	private String commentdate;		// commentdate
	private TUser user;
	
	public TMagazineComment() {
		super();
	}

	public TMagazineComment(String id){
		super(id);
	}

	@Length(min=1, max=64, message="magazineid长度必须介于 1 和 64 之间")
	public String getMagazineid() {
		return magazineid;
	}

	public void setMagazineid(String magazineid) {
		this.magazineid = magazineid;
	}
	
	@Length(min=0, max=6000, message="content长度必须介于 0 和 6000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=10, message="statute长度必须介于 0 和 10 之间")
	public String getStatute() {
		return statute;
	}

	public void setStatute(String statute) {
		this.statute = statute;
	}
	
	@Length(min=0, max=20, message="commentdate长度必须介于 0 和 20 之间")
	public String getCommentdate() {
		return commentdate;
	}

	public void setCommentdate(String commentdate) {
		this.commentdate = commentdate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}
}