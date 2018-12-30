/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 折扣信息Entity
 * @author hl
 * @version 2017-09-20
 */
public class TSecurities extends DataEntity<TSecurities> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// type
	private String discount;		// discount
	private String mechanismid;		// mechanismid
	private Date endtime;		// endtime
	private String status;		// status
	
	public TSecurities() {
		super();
	}

	public TSecurities(String id){
		super(id);
	}

	@Length(min=0, max=10, message="type长度必须介于 0 和 10 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=11, message="discount长度必须介于 0 和 11 之间")
	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	@Length(min=0, max=20, message="mechanismid长度必须介于 0 和 20 之间")
	public String getMechanismid() {
		return mechanismid;
	}

	public void setMechanismid(String mechanismid) {
		this.mechanismid = mechanismid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="endtime不能为空")
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	@Length(min=0, max=10, message="status长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}