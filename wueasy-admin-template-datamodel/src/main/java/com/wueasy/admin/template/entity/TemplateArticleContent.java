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

public class TemplateArticleContent extends TemplateArticle implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long articleId;
	
  	/**
	 * 摘要
	 */
	private String brief;
	
  	/**
	 * 内容
	 */
	private String content;
	
  
  
	public Long getArticleId() {
		return articleId;
	}
	
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
  
  	/**
	 * 摘要
	 */
	public String getBrief() {
		return brief;
	}
	
  	/**
	 * 摘要
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}
  
  	/**
	 * 内容
	 */
	public String getContent() {
		return content;
	}
	
  	/**
	 * 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

}