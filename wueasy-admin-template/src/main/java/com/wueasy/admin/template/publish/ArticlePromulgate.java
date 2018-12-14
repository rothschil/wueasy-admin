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

import com.wueasy.admin.entity.SysArticle;
import com.wueasy.admin.entity.Template;
import com.wueasy.admin.entity.TemplateCatalog;
import com.wueasy.admin.template.Context;
import com.wueasy.admin.template.TemplateParser;
import com.wueasy.admin.template.constant.TemplateConstants;
import com.wueasy.admin.template.service.TemplateService;
import com.wueasy.admin.template.util.ArticleHelper;
import com.wueasy.admin.template.util.CatalogHelper;
import com.wueasy.base.util.FileHelper;
import com.wueasy.base.util.SpringHelper;
import com.wueasy.base.util.StringHelper;

/**
 * 发布文章
 * @author: fallsea
 * @version 1.0
 */
public class ArticlePromulgate extends BasePromulgate{

	
	private TemplateService templateService = SpringHelper.getBean("TemplatePublishService", TemplateService.class);
	
	/**
	 * 文章的ID
	 */
	private Long articleId = 0L;
	
	
	public ArticlePromulgate(Long articleId)
	{
		this.articleId = articleId;
	}
	
	public void publish()
	{
		
		SysArticle article = templateService.queryArticleById(articleId);
		if (article == null)
		{
			String description = "文章不存在，不能发布此文章[articleId=" + articleId + "]";
			logger.error(description);
			return;
		}
		
		//得到文章所在栏目
		Long catalogId = article.getCatalogId();
		
		Template template = cycFindDetailedTemplateByCatalogId(catalogId);
		
		
		if (template == null) //没有找到信息细览模板
		{
			String description = "没有找到用于发布该文章的信息细览模板，不能发布此文章[articleId=" + articleId + "]";
			logger.error(description);
			return;
		}
		
		String content = template.getContent();
		if (StringHelper.isEmpty(content))
		{
			String description = "用于发布文章的信息细览模板[templateId=" + template.getId() + "]为空，不能发布此文章[articleId=" + articleId + "]";
			logger.error(description);
			return;
		}
		
		//处理模板，获得解析后的内容
		Context context = new Context();
		context.setCatalogId(catalogId);
		context.setArticleId(articleId);
		context.setSiteId(article.getSiteId());
		TemplateParser parser = new TemplateParser(context);
		String result = parser.parse(content);
		
		
		//写入文件到磁盘中
		String filePath = article.getUrl();
		
		if(StringHelper.isEmpty(filePath)){
			filePath = ArticleHelper.getArticleStorePath(article);
		}
		
		String rootPath = TemplateConstants.getPath(filePath);
		
		String encoding = template.getEncoding();//发布文件使用的编码
		if (StringHelper.isEmpty(encoding))
		{
			encoding = TemplateConstants.DEFAULT_ENCODING;
		}
		FileHelper.createNewFile(rootPath);
		FileHelper.writeToFile(rootPath, result, encoding);
		
		templateService.updateArticleUrlById(article.getArticleId(), filePath);
		
	}
	
	
	/**
	 * 根据栏目ID，查找有效的详细细览模板，若当前栏目找不到，则需要往父目录继续寻找
	 * @author: fallsea
	 * @param catalogId
	 * @return
	 */
	private Template cycFindDetailedTemplateByCatalogId(Long catalogId)
	{
		
		Template template = templateService.queryTemplateByCatalogIdAndType(catalogId,TemplateConstants.TYPE_3);
		if (null!=template) //找到了，直接返回
		{
			return template;
		}
		else
		//没找到，往父目录中寻找
		{
			TemplateCatalog catalog = CatalogHelper.getParentCatalog(catalogId);
			if (catalog == null) //已经找到了最顶层，还是没有找到，直接返回空
			{
				return null;
			}
			else
			{
				return cycFindDetailedTemplateByCatalogId(catalog.getCatalogId());
			}
		}
	}
	
}
