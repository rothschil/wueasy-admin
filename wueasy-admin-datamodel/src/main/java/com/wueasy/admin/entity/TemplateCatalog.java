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

public class TemplateCatalog implements Serializable {

	private static final long serialVersionUID = 1L;
	
  	/**
	 * 栏目id
	 */
	private Long catalogId;
	
  	/**
	 * 父id
	 */
	private Long parentId;
	
  	/**
	 * 站点
	 */
	private Long siteId;
	
  	/**
	 * 英文名称
	 */
	private String catalogNo;
	
  	/**
	 * 中文名称
	 */
	private String catalogName;
	
  	/**
	 * 状态,1.有效,0.无效
	 */
	private String state;
	
  	/**
	 * 排序值
	 */
	private Long orderline;
	
  	/**
	 * 描述
	 */
	private String description;
	
  	/**
	 * 目录链接　链接时，指向的页面，若没有设定，则栏目页面链接到发布的页面
	 */
	private String linkUrl;
	
  	/**
	 * 发布生成的文件类型　目前分为三类，shtml和html
	 */
	private String fileType;
	
  	/**
	 * 路由，记录其所有的上级ID号，用|分开　若是根，则为空
	 */
	private String route;
	
  	/**
	 * 栏目图片(小)
	 */
	private String smallImage;
	
  	/**
	 * 栏目图片(大)
	 */
	private String largeImage;
	
  	/**
	 * 标题
	 */
	private String seoTitle;
	
  	/**
	 * meta关键字
	 */
	private String seoKeywords;
	
  	/**
	 * meta描述
	 */
	private String seoDescription;
	
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
	 * 英文名称
	 */
	public String getCatalogNo() {
		return catalogNo;
	}
	
  	/**
	 * 英文名称
	 */
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
  
  	/**
	 * 中文名称
	 */
	public String getCatalogName() {
		return catalogName;
	}
	
  	/**
	 * 中文名称
	 */
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
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
	 * 排序值
	 */
	public Long getOrderline() {
		return orderline;
	}
	
  	/**
	 * 排序值
	 */
	public void setOrderline(Long orderline) {
		this.orderline = orderline;
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
	 * 目录链接　链接时，指向的页面，若没有设定，则栏目页面链接到发布的页面
	 */
	public String getLinkUrl() {
		return linkUrl;
	}
	
  	/**
	 * 目录链接　链接时，指向的页面，若没有设定，则栏目页面链接到发布的页面
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
  
  	/**
	 * 发布生成的文件类型　目前分为三类，shtml和html
	 */
	public String getFileType() {
		return fileType;
	}
	
  	/**
	 * 发布生成的文件类型　目前分为三类，shtml和html
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
  
  	/**
	 * 路由，记录其所有的上级ID号，用|分开　若是根，则为空
	 */
	public String getRoute() {
		return route;
	}
	
  	/**
	 * 路由，记录其所有的上级ID号，用|分开　若是根，则为空
	 */
	public void setRoute(String route) {
		this.route = route;
	}
  
  	/**
	 * 栏目图片(小)
	 */
	public String getSmallImage() {
		return smallImage;
	}
	
  	/**
	 * 栏目图片(小)
	 */
	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}
  
  	/**
	 * 栏目图片(大)
	 */
	public String getLargeImage() {
		return largeImage;
	}
	
  	/**
	 * 栏目图片(大)
	 */
	public void setLargeImage(String largeImage) {
		this.largeImage = largeImage;
	}
  
  	/**
	 * 标题
	 */
	public String getSeoTitle() {
		return seoTitle;
	}
	
  	/**
	 * 标题
	 */
	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}
  
  	/**
	 * meta关键字
	 */
	public String getSeoKeywords() {
		return seoKeywords;
	}
	
  	/**
	 * meta关键字
	 */
	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}
  
  	/**
	 * meta描述
	 */
	public String getSeoDescription() {
		return seoDescription;
	}
	
  	/**
	 * meta描述
	 */
	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
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