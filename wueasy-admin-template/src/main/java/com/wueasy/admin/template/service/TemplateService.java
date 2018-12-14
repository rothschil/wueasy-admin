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
package com.wueasy.admin.template.service;

import java.util.List;

import com.wueasy.admin.entity.SysArticle;
import com.wueasy.admin.entity.Template;
import com.wueasy.admin.entity.TemplateCatalog;
import com.wueasy.admin.entity.TemplatePublishPlan;
import com.wueasy.admin.entity.TemplateSite;
import com.wueasy.admin.entity.TemplateVar;
import com.wueasy.base.entity.DataMap;

/**
 * 
 * @author: fallsea
 * @version 1.0
 */
public interface TemplateService {

	/**
	 * 通过模板id查询模板信息
	 * @author: fallsea
	 * @param templateId
	 * @return
	 */
	Template queryTemplateById(Long templateId);
	
	/**
	 * 通过栏目id和栏目类型查询模板
	 * @author: fallsea
	 * @param catalogId
	 * @param type
	 * @return
	 */
	Template queryTemplateByCatalogIdAndType(Long catalogId, String type);
	
	/**
	 * 查询list结果
	 * @author: fallsea
	 * @param catalogId
	 * @param type
	 * @return
	 */
	List<Template> queryTemplateListByCatalogIdAndType(Long catalogId, String type);
	
	
	/**
	 * 查询站点列表
	 * @author: fallsea
	 * @return
	 */
	List<TemplateSite> querySiteList();
	
	/**
	 * 查询模板变量列表
	 * @author: fallsea
	 * @return
	 */
	List<TemplateVar> queryVarList();
	
	/**
	 * 查询栏目列表
	 * @author: fallsea
	 * @return
	 */
	List<TemplateCatalog> queryCatalogList();
	
	/**
	 * 查询发布计划列表
	 * @author: fallsea
	 * @return
	 */
	List<TemplatePublishPlan> queryPublishPlanList();
	
	
	/**
	 * 预览模板
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
	String previewTemplate(DataMap paramMap);
	
	/**
	 * 预览栏目
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
	String previewCatalog(DataMap paramMap);
	
	/**
	 * 预览文章
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
	String previewArticle(DataMap paramMap);
	
	/**
	 * 查询文章信息
	 * @author: fallsea
	 * @param articleId
	 * @return
	 */
	SysArticle queryArticleById(Long articleId);
	
	/**
	 * 修改文章路径
	 * @author: fallsea
	 * @param articleId
	 * @param url
	 * @return
	 */
	int updateArticleUrlById(Long articleId,String url);
	
	/**
	 * 查询栏目下所有文章
	 * @author: fallsea
	 * @param catalogId
	 * @return
	 */
	List<SysArticle> queryArticleByCatalogId(Long catalogId);
	
}
