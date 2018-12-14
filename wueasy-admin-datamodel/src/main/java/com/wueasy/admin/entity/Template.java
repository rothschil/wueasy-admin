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

public class Template implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
  	/**
	 * 模板名称
	 */
	private String name;
	
  	/**
	 * 栏目id
	 */
	private Long catalogId;
	
  	/**
	 * 站点id
	 */
	private Long siteId;
	
  	/**
	 * 状态,1.有效,0.无效
	 */
	private String state;
	
  	/**
	 * 发布文件路径　其它模板使用
	 */
	private String filePath;
	
  	/**
	 * 模板类型　1:首页模板 2:信息列表 3:信息细览 4:其它模板
	 */
	private String type;
	
  	/**
	 * 字符编码
	 */
	private String encoding;
	
	private String content;
	
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
	 * 模板名称
	 */
	public String getName() {
		return name;
	}
	
  	/**
	 * 模板名称
	 */
	public void setName(String name) {
		this.name = name;
	}
  
  	/**
	 * 栏目id
	 */
	public Long getCatalogId() {
		return catalogId;
	}
	
  	/**
	 * 栏目id
	 */
	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}
  
  	/**
	 * 站点id
	 */
	public Long getSiteId() {
		return siteId;
	}
	
  	/**
	 * 站点id
	 */
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
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
	 * 发布文件路径　其它模板使用
	 */
	public String getFilePath() {
		return filePath;
	}
	
  	/**
	 * 发布文件路径　其它模板使用
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
  
  	/**
	 * 模板类型　1:首页模板 2:信息列表 3:信息细览 4:其它模板
	 */
	public String getType() {
		return type;
	}
	
  	/**
	 * 模板类型　1:首页模板 2:信息列表 3:信息细览 4:其它模板
	 */
	public void setType(String type) {
		this.type = type;
	}
  
  	/**
	 * 字符编码
	 */
	public String getEncoding() {
		return encoding;
	}
	
  	/**
	 * 字符编码
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
  
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
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