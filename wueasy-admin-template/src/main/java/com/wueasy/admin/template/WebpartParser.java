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

/**
 * 
 * @author: fallsea
 * @version 1.0
 */
public interface WebpartParser {

	/**
	 * 对部件进行解析，得到对应部件的HTML
	 * @author: fallsea
	 * @param context 解析上下文参数
	 * @param webpartProp 部件的属性
	 * @param viewStr 部件的视图字串
	 * @return
	 */
	public String parse(Context context, Map<String,String> webpartProp, String viewStr);
}
