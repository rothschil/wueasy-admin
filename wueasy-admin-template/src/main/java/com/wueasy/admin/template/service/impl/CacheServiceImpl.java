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
package com.wueasy.admin.template.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wueasy.admin.entity.TemplateCatalog;
import com.wueasy.admin.entity.TemplateSite;
import com.wueasy.admin.entity.TemplateVar;
import com.wueasy.admin.mapper.TemplateCatalogMapper;
import com.wueasy.admin.mapper.TemplateSiteMapper;
import com.wueasy.admin.mapper.TemplateVarMapper;
import com.wueasy.admin.template.service.CacheService;

/**
 * 
 * @author: fallsea
 * @version 1.0
 */
@Service
public class CacheServiceImpl implements CacheService {
	
	@Autowired
	private TemplateSiteMapper templateSiteMapper;
	
	@Autowired
	private TemplateVarMapper templateVarMapper;
	
	@Autowired
	private TemplateCatalogMapper templateCatalogMapper;
	
	
	@Override
	public Map<Long, TemplateSite> getSiteMap() {
		Map<Long,TemplateSite> siteMap = new HashMap<Long, TemplateSite>();
		List<TemplateSite> list = templateSiteMapper.queryList();
		if(null!=list && !list.isEmpty()){
			for (TemplateSite templateSite : list) {
				siteMap.put(templateSite.getSiteId(), templateSite);
			}
		}
		return siteMap;
	}


	@Override
	public Map<Long, Map<String, String>> getVarMap() {
		List<TemplateVar> list = templateVarMapper.queryList();
		Map<Long,Map<String, String>> varMap = new HashMap<Long, Map<String,String>>();
		if(null!=list && !list.isEmpty()){
			
			Map<String, String> map = null;
			
			Long newSiteId = 0L;
			
			Long siteId = 0L;
			
			for (TemplateVar templateVar : list) {
				
				newSiteId = templateVar.getSiteId();
				if(!siteId.equals(newSiteId)){
					if(siteId != 0L){
						varMap.put(siteId, map);
					}
					//新的站点
					siteId = templateVar.getSiteId();
					map = new HashMap<String, String>();
				}
				
				map.put(templateVar.getItemKey(), templateVar.getItemValue());
			}
			if(newSiteId.equals(siteId)){
				varMap.put(siteId, map);
			}
		}
		return varMap;
	}


	@Override
	public Map<Long, TemplateCatalog> getCatalogMap() {
		List<TemplateCatalog> list = templateCatalogMapper.queryCatalogList();
		Map<Long,TemplateCatalog> catalogMap = new HashMap<Long, TemplateCatalog>();
		if(null!=list && !list.isEmpty()){
			for (TemplateCatalog templateCatalog : list) {
				catalogMap.put(templateCatalog.getCatalogId(), templateCatalog);
			}
		}
		return catalogMap;
	}
	
	

}
