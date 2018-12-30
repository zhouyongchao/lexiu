/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 城市信息Entity
 * @author hl
 * @version 2017-10-25
 */
public class City extends DataEntity<City> {
	
	private static final long serialVersionUID = 1L;
	private String city;		// 城市
	
	public City() {
		super();
	}

	public City(String id){
		super(id);
	}

	@Length(min=0, max=50, message="城市长度必须介于 0 和 50 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}