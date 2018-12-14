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

import com.wueasy.admin.entity.Template;
import com.wueasy.admin.entity.TemplateCatalog;
import com.wueasy.admin.template.Context;
import com.wueasy.admin.template.TemplateParser;
import com.wueasy.admin.template.constant.TemplateConstants;
import com.wueasy.admin.template.service.TemplateService;
import com.wueasy.admin.template.util.CatalogHelper;
import com.wueasy.base.util.FileHelper;
import com.wueasy.base.util.SpringHelper;
import com.wueasy.base.util.StringHelper;

/**
 * 发布栏目
 * @author: fallsea
 * @version 1.0
 */
public class CatalogPromulgate extends BasePromulgate{

	
	private TemplateService templateService = SpringHelper.getBean("TemplatePublishService", TemplateService.class);
	
	
	/**
	 * 栏目的ID
	 */
	private Long catalogId = 0L;
	
	
	public CatalogPromulgate(Long catalogId)
	{
		this.catalogId = catalogId;
	}
	
	public void publish()
	{
		
        /**
         * 第一步：查看该栏下是否有首页模板，若有，则发布。
         * 第二步：若没有发布首页模板，则再查看该栏目下及其上级父目录中是否有信息列表模板，若有，则发布
         * 第三步：查看该栏目下是否有其它模板，若有，则发布所有其它模板
         */
        
        //先查询当前栏目有没有首页模板，没有查询当前栏目有没有信息列表，如果没有信息列表查询父栏目信息列表模板
        Template template = getTemplateByCid(catalogId, TemplateConstants.TYPE_1);
		if (template == null)
		{
			template = getTemplateByCid(catalogId, TemplateConstants.TYPE_2);
		}
        
		//发布栏目首页模板
		if(null!=template){
			publishMainTemplate(template);
		}
        
		
		
        //发布该栏目下的其它模板
		List<Template> templateList = templateService.queryTemplateListByCatalogIdAndType(catalogId, TemplateConstants.TYPE_4);
        if (templateList != null && !templateList.isEmpty())
        {
        	for (Template template2 : templateList) {
        		new TemplatePromulgate(template2.getId()).publish();
			}
        }
	}
	
	
	/**
	 * 发布首页模板
	 * @param catalogId
	 * @param template
	 */
	private void publishMainTemplate(Template template)
	{
		Long templateId = template.getId();
		String content = template.getContent();
		
		if (StringHelper.isEmpty(content))
		{
			String description = "用于发布文章的首页模板[templateId=" + templateId + "]为空，不能发布此栏目[catalogId=" + catalogId + "]";
			logger.error(description);
			return;
		}
		
		String encoding = template.getEncoding();//发布文件使用的编码
		if (StringHelper.isEmpty(encoding))
		{
			encoding = TemplateConstants.DEFAULT_ENCODING;
		}
		
		//处理模板，获得解析后的内容
		Context context = new Context();
		context.setCatalogId(catalogId);
		context.setSiteId(template.getSiteId());
		context.setTemplateUrl(template.getFilePath());
		TemplateParser parser = new TemplateParser(context);
		String result = parser.parse(content);
		
		//写入解析后的内容到文件
		String filePath = TemplateConstants.getPath(CatalogHelper.getFilePath(catalogId));
		FileHelper.createNewFile(filePath);
		FileHelper.writeToFile(filePath, result, encoding);
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
