/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.config.Global;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户信息Entity
 * @author hl
 * @version 2017-09-20
 */
public class 	TUser extends DataEntity<TUser> {
	
	private static final long serialVersionUID = 1L;
	private String sid;		// sid
	private String username;		// username
	private String password;		// password
	private int type;//0 普通用户 1主播
	private String nickname;		// nickname
	private String signature;		// 	签名
	private String sex;		// sex 1男 2 女
	private int age;		// age
	private String province;		// province
	private String city;		// city
	private String area;		// area
	private String address;		// address
	private String useravatar;		// useravatar头像
	private String professional;		// professional职业
	private String interest;		// interest爱好
	private long umoney;		// umoney
	private String idcard;		// idcard
	private String bankcard;		// bankcard
	private String source;//用户来源
	private String openid;
	private int praise;//点赞数
	private String logintype;//登录方式:mobile third
	private int status;//审核状态 3待审核 1审核通过 2审核不通过


	public TUser() {
		super();
	}

	public TUser(String id){
		super(id);
	}

	@Length(min=0, max=64, message="sid长度必须介于 0 和 64 之间")
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	
	@Length(min=1, max=100, message="username长度必须介于 1 和 100 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=1, max=100, message="password长度必须介于 1 和 100 之间")
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=100, message="nickname长度必须介于 0 和 100 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Length(min=0, max=100, message="signature长度必须介于 0 和 100 之间")
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	@Length(min=0, max=10, message="sex长度必须介于 0 和 10 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Length(min=0, max=20, message="province长度必须介于 0 和 20 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=20, message="city长度必须介于 0 和 20 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=100, message="area长度必须介于 0 和 100 之间")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=100, message="address长度必须介于 0 和 100 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=100, message="useravatar长度必须介于 0 和 100 之间")
	public String getUseravatar() {
		return useravatar;
	}

	public void setUseravatar(String useravatar) {
		this.useravatar = useravatar;
	}
	
	@Length(min=0, max=100, message="professional长度必须介于 0 和 100 之间")
	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}
	
	@Length(min=0, max=100, message="interest长度必须介于 0 和 100 之间")
	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}
	
	public long getUmoney() {
		return umoney;
	}

	public void setUmoney(long umoney) {
		this.umoney = umoney;
	}
	
	@Length(min=0, max=32, message="idcard长度必须介于 0 和 32 之间")
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	@Length(min=0, max=32, message="bankcard长度必须介于 0 和 32 之间")
	public String getBankcard() {
		return bankcard;
	}

	public void setBankcard(String bankcard) {
		this.bankcard = bankcard;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}


	public int getPraise() {
		return praise;
	}

	public void setPraise(int praise) {
		this.praise = praise;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getLogintype() {
		return logintype;
	}

	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}