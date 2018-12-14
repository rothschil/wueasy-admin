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

import java.io.StringWriter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @author: fallsea
 * @version 1.0
 */
public class FMTemplateGenerator {

	private static Logger logger = LoggerFactory.getLogger(FMTemplateGenerator.class);
	
	/**
	 * 解析模板字串内容
	 * @author: fallsea
	 * @param model 模型数据
	 * @param templateStr 需要解析的模板字串
	 * @return 解析后的结果
	 */
	@SuppressWarnings("deprecation")
	public static String parseTemplate(Map<String,Object> model, String templateStr)
	{
		String result = "";
		try
		{
			Configuration cfg = new Configuration();
			StringTemplateLoader templateLoader = new StringTemplateLoader();
			templateLoader.putTemplate("view", templateStr);
			cfg.setTemplateLoader(templateLoader);
			cfg.setDefaultEncoding("UTF-8");
			
			Template template = cfg.getTemplate("view");
			StringWriter writer = new StringWriter();
			template.process(model, writer);
			result = writer.toString();
		}
		catch (Exception ex)
		{
			logger.error("", ex);
		}
		
		return result;
	}
	
}
