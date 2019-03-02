/*
 * wueasy - A Java Distributed Rapid Development Platform.
 * Copyright (C) 2017-2019 wueasy.com

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.wueasy.admin.template.entity;

import java.io.Serializable;
import java.util.Date;

public class TemplateVar implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
  	/**
	 * 站点
	 */
	private Long siteId;
	
  	/**
	 * 变量key
	 */
	private String itemKey;
	
  	/**
	 * 变量value
	 */
	private String itemValue;
	
  	/**
	 * 状态,1.有效,0.无效
	 */
	private String state;
	
  	/**
	 * 描述
	 */
	private String description;
	
  	/**
	 * 创建人
	 */
	private Long createdBy;
	
  	/**
	 * 创建时间
	 */
	private Date createdTime;
	
  	/**
	 * 修改人
	 */
	private Long modifiedBy;
	
  	/**
	 * 修改时间
	 */
	private Date modifiedTime;
	
  
  
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
  
  	/**
	 * 站点
	 */
	public Long getSiteId() {
		return siteId;
	}
	
  	/**
	 * 站点
	 */
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
  
  	/**
	 * 变量key
	 */
	public String getItemKey() {
		return itemKey;
	}
	
  	/**
	 * 变量key
	 */
	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}
  
  	/**
	 * 变量value
	 */
	public String getItemValue() {
		return itemValue;
	}
	
  	/**
	 * 变量value
	 */
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
  
  	/**
	 * 状态,1.有效,0.无效
	 */
	public String getState() {
		return state;
	}
	
  	/**
	 * 状态,1.有效,0.无效
	 */
	public void setState(String state) {
		this.state = state;
	}
  
  	/**
	 * 描述
	 */
	public String getDescription() {
		return description;
	}
	
  	/**
	 * 描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
  
  	/**
	 * 创建人
	 */
	public Long getCreatedBy() {
		return createdBy;
	}
	
  	/**
	 * 创建人
	 */
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
  
  	/**
	 * 创建时间
	 */
	public Date getCreatedTime() {
		return createdTime;
	}
	
  	/**
	 * 创建时间
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
  
  	/**
	 * 修改人
	 */
	public Long getModifiedBy() {
		return modifiedBy;
	}
	
  	/**
	 * 修改人
	 */
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
  
  	/**
	 * 修改时间
	 */
	public Date getModifiedTime() {
		return modifiedTime;
	}
	
  	/**
	 * 修改时间
	 */
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

}