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
package com.wueasy.admin.template.constant;

import com.wueasy.base.util.FileHelper;
import com.wueasy.cache.util.ParameterHelper;

/**
 * 
 * @author: fallsea
 * @version 1.0
 */
public class TemplateConstants {

	//默认编码
	public static final String DEFAULT_ENCODING = "UTF-8";
	
	
	/**首页模板*/
	public static final String TYPE_1 ="1";
	
	/**信息列表*/
	public static final String TYPE_2 ="2";
	
	/**信息细览*/
	public static final String TYPE_3 ="3";
	
	/**其它模版*/
	public static final String TYPE_4 ="4";
	
	
	/**
	 * 获取文件绝对路径
	 * @author: fallsea
	 * @param filePath
	 * @return
	 */
	public static final String getPath(String filePath){
		String path = ParameterHelper.getString("template.publishPath");
		path = path + filePath;
		return FileHelper.normalize(path);
	}
	
}
