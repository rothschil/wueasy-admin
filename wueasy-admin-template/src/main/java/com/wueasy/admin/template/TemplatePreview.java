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
package com.wueasy.admin.template;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wueasy.admin.entity.SysArticle;
import com.wueasy.admin.entity.Template;
import com.wueasy.admin.entity.TemplateCatalog;
import com.wueasy.admin.template.constant.TemplateConstants;
import com.wueasy.admin.template.service.TemplateService;
import com.wueasy.admin.template.util.CatalogHelper;
import com.wueasy.base.exception.InvokeException;
import com.wueasy.base.util.SpringHelper;
import com.wueasy.base.util.StringHelper;

/**
 * 模板预览
 * @author: fallsea
 * @version 1.0
 */
public class TemplatePreview {
	
	
	private static Logger logger = LoggerFactory.getLogger(TemplatePreview.class);

	
	private TemplateService templateService = SpringHelper.getBean("TemplatePublishService", TemplateService.class);
	
	private Long templateId = 0L;
	
	private Long catalogId = 0L;
	
	private Long articleId = 0L;
	
	private Map<String,String> variables = null;
	
	/**
	 * 0:根据文章ID预览模板
	 * 1:根据栏目ID预览模板
	 * 2:根据模板ID预览模板
	 */
	private int type = 0;
	
	public void setVariables(Map<String,String> variables)
	{
		this.variables = variables;
	}
	
	public void setArticleId(Long articleId)
	{
		this.articleId = articleId;
	}
	
	public void setCatalogId(Long catalogId)
	{
		this.catalogId = catalogId;
	}
	
	public TemplatePreview(Long templateId)
	{
		this.templateId = templateId;
		this.type = 2;
	}
	
	public TemplatePreview(Long catalogId, Long articleId)
	{
		this.catalogId = catalogId;
		this.articleId = articleId;
		if (catalogId > 0)
		{
			this.type = 1;
		}
		else
		{
			this.type = 0;
		}
	}
	
	/**
	 * 解析模板内容
	 * @author: fallsea
	 * @return
	 * @throws Exception
	 */
	public String parseTemplate()
	{
		String result = "";
		Template template = null;
		
		if (type == 0)//预览文章
		{
			//通过文章ID获取栏目ID
			SysArticle article = templateService.queryArticleById(articleId);
			if (article == null)
			{
				throw new InvokeException(-1,"文章不存在，不能预览此文章");
			}
			this.catalogId = article.getCatalogId();
			//查找信息细览模板
			template = getTemplateByCid(this.catalogId,TemplateConstants.TYPE_3);
		}
		else if (type == 1)
		{
			//先查询当前栏目有没有首页模板，没有查询当前栏目有没有信息列表，如果没有信息列表查询父栏目信息列表模板
			template = getTemplateByCid(catalogId, TemplateConstants.TYPE_1);
			if (template == null)
			{
				template = getTemplateByCid(catalogId, TemplateConstants.TYPE_2);
			}
		}
		else if (type == 2)
		{
			template = templateService.queryTemplateById(templateId);
		}
		
		if (template == null)
		{
			throw new InvokeException(-1,"找不到模板，不能预览此模板");
		}
		
		String content = template.getContent();
		if (StringHelper.isEmpty(content))
		{
			logger.warn("模板内容为空，不能发布此模板[templateId=" + templateId + "]");
			return "";
		}
		
		Context context = new Context();
		context.setCatalogId(template.getCatalogId());
		context.setArticleId(articleId);
		context.setSiteId(template.getSiteId());
		context.addVariable("isPreview", "1");//是否预览模式
		context.setTemplateUrl(template.getFilePath());
		
		if (variables != null)
		{
			for (Map.Entry<String,String> entry : variables.entrySet()) {
				context.addVariable(entry.getKey(), entry.getValue());
		    }
		}
		
		TemplateParser parser = new TemplateParser(context);
		result = parser.parse(content);
		
		return result;
	}
	
	
	/**
	 * 根据栏目ID，查找有效的详细细览模板，若当前栏目找不到，则需要往父目录继续寻找
	 * @author: fallsea
	 * @param catalogId
	 * @param type
	 * @return
	 */
	public Template getTemplateByCid(Long catalogId, String type)
	{
		
		Template template = templateService.queryTemplateByCatalogIdAndType(catalogId, type);
		if (template != null) //找到了，直接返回
		{
			return template;
		}
		else
		{
			//没找到，往父目录中寻找
			if (TemplateConstants.TYPE_1.equals(type))//首页模板不允许递归查询
			{
				return null;
			}
			
			TemplateCatalog catalog = CatalogHelper.getParentCatalog(catalogId);
			if (catalog == null) //已经找到了最顶层，还是没有找到，直接返回空
			{
				return null;
			}
			else
			{
				return getTemplateByCid(catalog.getCatalogId(), type);
			}
		}
		
	}
	
}
