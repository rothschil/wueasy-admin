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
package com.wueasy.admin.template.util;

import java.util.Date;

import com.wueasy.admin.entity.SysArticle;
import com.wueasy.admin.entity.TemplateCatalog;
import com.wueasy.base.util.DateHelper;

/**
 * 文章工具
 * @author: fallsea
 * @version 1.0
 */
public class ArticleHelper {

	/**
	 * 获取文章发布路径
	 * @author: fallsea
	 * @param article
	 * @return
	 */
	public static String getArticleStorePath(SysArticle article){
		
		String path = "";
		
		String siteNo = SiteHelper.getSite(article.getSiteId()).getSiteNo();
		Date createTime = article.getCreatedTime();
		
		TemplateCatalog catalog = CatalogHelper.getCatalog(article.getCatalogId());
		String fileType = catalog.getFileType();
		
		String datePath = DateHelper.formatDate(createTime, "yyyy/MM/dd");
		
		path = "/" + siteNo + "/article/" + datePath +"/" + article.getArticleId() + "." + fileType;
		
		return path;
	}
}
