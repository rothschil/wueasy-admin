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
package com.wueasy.admin.entity;

import java.io.Serializable;
import java.util.Date;

public class SysOrg implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
  	/**
	 * 机构编号
	 */
	private String orgNo;
	
  	/**
	 * 机构名称
	 */
	private String orgName;
	
  	/**
	 * 父id
	 */
	private Long parentId;
	
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
	 * 机构编号
	 */
	public String getOrgNo() {
		return orgNo;
	}
	
  	/**
	 * 机构编号
	 */
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
  
  	/**
	 * 机构名称
	 */
	public String getOrgName() {
		return orgName;
	}
	
  	/**
	 * 机构名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
  
  	/**
	 * 父id
	 */
	public Long getParentId() {
		return parentId;
	}
	
  	/**
	 * 父id
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
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