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
package com.wueasy.admin.template.publish;

import java.util.List;

import com.wueasy.admin.entity.SysArticle;
import com.wueasy.admin.entity.TemplateCatalog;
import com.wueasy.admin.template.service.TemplateService;
import com.wueasy.admin.template.util.CatalogHelper;
import com.wueasy.base.util.SpringHelper;

/**
 * 发布回调
 * @author: fallsea
 * @version 1.0
 */
public class PublishCallBack {
	
	/**
	 * 发布文章
	 * @author: fallsea
	 * @param articleId
	 */
	public void publishArticle(Long articleId){
		new ArticlePromulgate(articleId).publish();;
	}
	
	/**
	 * 发布某栏目及其所有子栏目的文章（递归发布）
	 * @author: fallsea
	 * @param catalogId
	 */
	public void publishArticleByCatalogRecursion(Long catalogId){
		
		//查询栏目下所有文章
		publishArticleByCatalog(catalogId);
		
		
		//发布其所有子目录
		List<TemplateCatalog> dataList = CatalogHelper.getChildrenCatalog(catalogId);
		if(null!=dataList && !dataList.isEmpty()){
			for (TemplateCatalog templateCatalog : dataList) {
				publishArticleByCatalogRecursion(templateCatalog.getCatalogId());
			}
		}
	}
	
	/**
	 * 发布某栏目文章
	 * @author: fallsea
	 * @param catalogId
	 */
	public void publishArticleByCatalog(Long catalogId){
		
		TemplateService templateService = SpringHelper.getBean("TemplatePublishService", TemplateService.class);
		
		List<SysArticle> articleList = templateService.queryArticleByCatalogId(catalogId);
		if(null!=articleList && !articleList.isEmpty()){
			for (SysArticle sysArticle : articleList) {
				publishArticle(sysArticle.getArticleId());
			}
		}
	}
	
	/**
	 * 发布某模板
	 * @author: fallsea
	 * @param templateId 模板ID
	 */
	public void publishTemplate(Long templateId)
	{
		new TemplatePromulgate(templateId).publish();
	}
	
	/**
	 * 发布栏目
	 * @author: fallsea
	 * @param catalogId 栏目ID
	 */
	public void publishCatalog(Long catalogId)
	{
		new CatalogPromulgate(catalogId).publish();
	}
	
	/**
	 * 发布某栏目及其所有子栏目
	 * @param queueId   队列ID
	 * @param catalogId 栏目ID
	 */
	public void publishCatalogRecursion(Long catalogId)
	{
		new CatalogPromulgate(catalogId).publish();
		
		//发布其所有子目录
		List<TemplateCatalog> dataList = CatalogHelper.getChildrenCatalog(catalogId);
		if(null!=dataList && !dataList.isEmpty()){
			for (TemplateCatalog templateCatalog : dataList) {
				publishCatalogRecursion(templateCatalog.getCatalogId());
			}
		}
	}
	
	
}
