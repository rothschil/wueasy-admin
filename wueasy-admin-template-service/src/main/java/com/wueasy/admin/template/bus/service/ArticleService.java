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
package com.wueasy.admin.template.bus.service;

import java.util.List;

import com.wueasy.admin.template.entity.TemplateCatalog;
import com.wueasy.base.entity.DataMap;

/**
 * 文章服务类
 * @author: fallsea
 * @version 1.0
 */
public interface ArticleService {

	/**
	 * 新增文章
	 * @author: fallsea
	 * @param paramMap
	 */
	void addArticle(DataMap paramMap);
	
	/**
	 * 修改文章
	 * @author: fallsea
	 * @param paramMap
	 */
	void updateArticle(DataMap paramMap);
	
	/**
	 * 删除文章
	 * @author: fallsea
	 * @param paramMap
	 */
	void deleteArticle(DataMap paramMap);
	
	/**
	 * 修改文章状态
	 * @author: fallsea
	 * @param paramMap
	 */
	void updateArticleState(DataMap paramMap);
	
	/**
	 * 查询栏目列表
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
	List<TemplateCatalog> queryCatalogList(DataMap paramMap);
	
	/**
	 * 发布栏目
	 * @author: fallsea
	 * @param paramMap
	 */
	void publishCatalog(DataMap paramMap);
	
	/**
	 * 发布栏目及子栏目
	 * @author: fallsea
	 * @param paramMap
	 */
	void publishCatalogRecursion(DataMap paramMap);
	
}
