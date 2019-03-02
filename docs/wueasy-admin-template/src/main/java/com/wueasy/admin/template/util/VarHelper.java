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
package com.wueasy.admin.template.util;

import java.util.Map;

import com.wueasy.admin.template.service.CacheService;
import com.wueasy.base.util.SpringHelper;

/**
 * 模板变量缓存工具
 * @author: fallsea
 * @version 1.0
 */
public class VarHelper {

	
	/**
	 * 获取站点变量列表
	 * @author: fallsea
	 * @param siteId 站点id
	 * @return
	 */
	public static Map<String, String> getVarMap(Long siteId){
		Map<Long,Map<String, String>> varMap = SpringHelper.getBean(CacheService.class).getVarMap();
		if(null==varMap){
			return null;
		}
		return varMap.get(siteId);
	}
	
}
