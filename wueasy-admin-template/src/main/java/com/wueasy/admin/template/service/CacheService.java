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

import java.util.Map;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.wueasy.admin.entity.TemplateCatalog;
import com.wueasy.admin.entity.TemplateSite;

/**
 * 缓存服务
 * @author: fallsea
 * @version 1.0
 */
@CacheConfig
public interface CacheService {
	
	
	/**
	 * 获取站点缓存
	 * @author: fallsea
	 * @return
	 */
	@Cacheable(value = "siteMap")
	Map<Long,TemplateSite> getSiteMap();
	
	/**
	 * 缓存模板变量
	 * @author: fallsea
	 * @return
	 */
	@Cacheable(value = "varMap")
	Map<Long,Map<String, String>> getVarMap();
	
	/**
	 * 获取栏目缓存
	 * @author: fallsea
	 * @return
	 */
	@Cacheable(value = "catalogMap")
	Map<Long,TemplateCatalog> getCatalogMap();
}
