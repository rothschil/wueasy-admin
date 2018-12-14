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

public class SysArticle implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long articleId;
	
  	/**
	 * 标题
	 */
	private String title;
	
  	/**
	 * 发布时，生成的文章的URL链接
	 */
	private String url;
	
  	/**
	 * 文章作者
	 */
	private String author;
	
  	/**
	 * 文章来源
	 */
	private Long source;
	
  	/**
	 * 是否推荐文章 1:是，0：不是(缺省)
	 */
	private String isHot;
	
  	/**
	 * 设为头条 1：是，0：不是
	 */
	private String isHead;
	
  	/**
	 * 发布时间
	 */
	private Date publishDate;
	
  	/**
	 * 栏目id
	 */
	private Long catalogId;
	
  	/**
	 * 站点id
	 */
	private Long siteId;
	
  	/**
	 * 状态：0：未发布，1：已发布
	 */
	private String state;
	
  	/**
	 * 文章点击次数
	 */
	private Long hits;
	
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
	
  
  
	public Long getArticleId() {
		return articleId;
	}
	
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
  
  	/**
	 * 标题
	 */
	public String getTitle() {
		return title;
	}
	
  	/**
	 * 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
  
  	/**
	 * 发布时，生成的文章的URL链接
	 */
	public String getUrl() {
		return url;
	}
	
  	/**
	 * 发布时，生成的文章的URL链接
	 */
	public void setUrl(String url) {
		this.url = url;
	}
  
  	/**
	 * 文章作者
	 */
	public String getAuthor() {
		return author;
	}
	
  	/**
	 * 文章作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
  
  	/**
	 * 文章来源
	 */
	public Long getSource() {
		return source;
	}
	
  	/**
	 * 文章来源
	 */
	public void setSource(Long source) {
		this.source = source;
	}
  
  	/**
	 * 是否推荐文章 1:是，0：不是(缺省)
	 */
	public String getIsHot() {
		return isHot;
	}
	
  	/**
	 * 是否推荐文章 1:是，0：不是(缺省)
	 */
	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}
  
  	/**
	 * 设为头条 1：是，0：不是
	 */
	public String getIsHead() {
		return isHead;
	}
	
  	/**
	 * 设为头条 1：是，0：不是
	 */
	public void setIsHead(String isHead) {
		this.isHead = isHead;
	}
  
  	/**
	 * 发布时间
	 */
	public Date getPublishDate() {
		return publishDate;
	}
	
  	/**
	 * 发布时间
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
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
	 * 状态：0：未发布，1：已发布
	 */
	public String getState() {
		return state;
	}
	
  	/**
	 * 状态：0：未发布，1：已发布
	 */
	public void setState(String state) {
		this.state = state;
	}
  
  	/**
	 * 文章点击次数
	 */
	public Long getHits() {
		return hits;
	}
	
  	/**
	 * 文章点击次数
	 */
	public void setHits(Long hits) {
		this.hits = hits;
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