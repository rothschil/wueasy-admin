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

import com.wueasy.admin.entity.Template;
import com.wueasy.admin.template.Context;
import com.wueasy.admin.template.TemplateParser;
import com.wueasy.admin.template.constant.TemplateConstants;
import com.wueasy.admin.template.service.TemplateService;
import com.wueasy.admin.template.util.CatalogHelper;
import com.wueasy.admin.template.util.SiteHelper;
import com.wueasy.base.util.FileHelper;
import com.wueasy.base.util.SpringHelper;
import com.wueasy.base.util.StringHelper;

/**
 * 发布模板
 * @author: fallsea
 * @version 1.0
 */
public class TemplatePromulgate extends BasePromulgate{
	
	private TemplateService templateService = SpringHelper.getBean("TemplatePublishService", TemplateService.class);
	
	/**
	 * 需要发布的模板的ID
	 */
	private Long templateId = 0L;
	
	public TemplatePromulgate(Long templateId)
	{
		this.templateId = templateId;
	}
	
	/**
	 * 发布某模板
	 * @author: fallsea
	 */
	public void publish()
	{
        //得到需要发布的模板内容
        Template template = templateService.queryTemplateById(templateId);
        if (template == null)
        {
            String description = "找不到模板，不能发布此模板[templateId=" + templateId + "]";
            logger.error(description);
            return;
        }
        
        String encoding = template.getEncoding();//发布文件使用的编码
        if (StringHelper.isEmpty(encoding))
        {
            encoding = TemplateConstants.DEFAULT_ENCODING;
        }
        
        String state = template.getState();
        if (!"1".equals(state))
        {
            String description = "模板为无效状态，不能发布此模板[templateId=" + templateId + "]";
            logger.error(description);
            return;
        }
        
        String content =template.getContent();
        
        if (StringHelper.isEmpty(content))
        {
            String description = "模板内容为空，不能发布此模板[templateId=" + templateId + "]";
            logger.error(description);
            return;
        }
        
        //处理模板，获得解析后的内容
        Long catalogId = template.getCatalogId();
        Context context = new Context();
        context.setCatalogId(catalogId);
        context.setSiteId(template.getSiteId());
        context.setTemplateUrl(template.getFilePath());
        TemplateParser parser = new TemplateParser(context);
        String result = parser.parse(content);
        
        String filePath = template.getFilePath();
        if(StringHelper.isEmpty(filePath)){
        	filePath = CatalogHelper.getFilePath(catalogId);
        }else{
        	//需要加上站点路径
        	filePath ="/" + SiteHelper.getSite(template.getSiteId()).getSiteNo() +"/" + filePath;
        }
        filePath = TemplateConstants.getPath(filePath);
        
        //写入解析后的内容到文件
		FileHelper.createNewFile(filePath);
		FileHelper.writeToFile(filePath, result, encoding);
		
	}

}
