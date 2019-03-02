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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wueasy.admin.entity.TemplateCatalog;
import com.wueasy.admin.template.service.CacheService;
import com.wueasy.base.util.SpringHelper;
import com.wueasy.base.util.StringHelper;

/**
 * 模板栏目缓存工具
 * @author: fallsea
 * @version 1.0
 */
public class CatalogHelper {
	
	
	private static Map<Long,TemplateCatalog> getCatalogMap(){
		
		return SpringHelper.getBean(CacheService.class).getCatalogMap();
	}
	
	/**
	 * 获取栏目信息
	 * @author: fallsea
	 * @param catalogId
	 * @return
	 */
	public static TemplateCatalog getCatalog(Long catalogId){
		Map<Long,TemplateCatalog> catalogMap = getCatalogMap();
		if(null==catalogMap){
			return null;
		}
		return catalogMap.get(catalogId);
	}
	
	/**
	 * 获取父栏目信息
	 * @author: fallsea
	 * @param catalogId
	 * @return
	 */
	public static TemplateCatalog getParentCatalog(Long catalogId){
		TemplateCatalog catalog = getCatalog(catalogId);
		if(null!=catalog){
			return getCatalog(catalog.getParentId());
		}
		return null;
	}
	
	
	/**
	 * 通过栏目id获取发布路径
	 * @author: fallsea
	 * @param catalogId
	 * @return
	 */
	public static String getFilePath(Long catalogId){
		String filePath = "";
		TemplateCatalog catalog = getCatalog(catalogId);
		if (catalog != null)
		{
			String siteNo = SiteHelper.getSite(catalog.getSiteId()).getSiteNo();//站点编号
			String route = catalog.getRoute();
			String fileType = catalog.getFileType();
			
			StringBuffer buffer = new StringBuffer();
			String[] catalogArray = route.split("\\|");
			buffer.append("/" + siteNo);
			
			for (int i = 0; i < catalogArray.length; i++) //跳过第一个节点,第一个为根目录
			{
				String catalogIdStr = catalogArray[i];
				if(StringHelper.isNotBlank(catalogIdStr)){
					buffer.append("/" + getCatalog(Long.valueOf(catalogIdStr)).getCatalogNo());
				}
			}
			buffer.append("/index." + fileType);
			
			return buffer.toString();
		}
		return filePath;
	}
	
	
	/**
	 * 获取子栏目
	 * @author: fallsea
	 * @param catalogId
	 * @return
	 */
	public static List<TemplateCatalog> getChildrenCatalog(Long catalogId){
		Map<Long,TemplateCatalog> catalogMap = getCatalogMap();
		if(null==catalogMap){
			return null;
		}
		
		List<TemplateCatalog> list = new ArrayList<TemplateCatalog>();
		
		for (Map.Entry<Long,TemplateCatalog> entry : catalogMap.entrySet()) {
			TemplateCatalog catalog = entry.getValue();
			if(catalog.getParentId().equals(catalogId)){
				list.add(catalog);
			}
		}
		return list;
	}
}
