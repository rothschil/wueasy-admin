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
package com.wueasy.admin.template.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wueasy.admin.template.entity.TemplateCatalog;
import com.wueasy.base.entity.DataMap;

public interface TemplateCatalogMapper {

	
    TemplateCatalog select(DataMap paramMap);
    
    TemplateCatalog select(@Param("catalogId")Long catalogId);
    
    
    /**
     * 查询栏目编号是否存在
     * @author: fallsea
     * @param siteId 站点id
     * @param catalogNo 栏目编号
     * @return
     */
    TemplateCatalog selectByNo(@Param("siteId")String siteId,@Param("catalogNo")String catalogNo);
    
    
    /**
     * 通过栏目id查询站点id
     * @author: fallsea
     * @param catalogId
     * @return
     */
    List<String> selectBySiteId(@Param("catalogId")Long catalogId);
    
    /**
     * 查询站点下栏目列表
     * @author: fallsea
     * @param siteId
     * @return
     */
    List<TemplateCatalog> queryList(@Param("siteId")String siteId);
    
    List<TemplateCatalog> queryList(DataMap paramMap);
   
    
    /**
	 * 查询栏目列表
	 * @author: fallsea
	 * @return
	 */
    List<TemplateCatalog> queryCatalogList();
    
    /**
     * 修改路由信息
     * @author: fallsea
     * @param catalogId
     * @param route
     * @return
     */
    int updateRoute(@Param("catalogId")Long catalogId,@Param("route")String route);
    
    /**
     * 查询栏目列表
     * @author: fallsea
     * @param paramMap
     * @return
     */
    List<TemplateCatalog> query(DataMap paramMap);
    
    /**
     * 查询有效栏目列表
     * @author: fallsea
     * @param paramMap
     * @return
     */
    List<TemplateCatalog> queryValid(DataMap paramMap);
    
    /**
     * 查询有权限的栏目列表
     * @author: fallsea
     * @param siteId
     * @param authDataStr
     * @return
     */
    List<TemplateCatalog> queryValid(@Param("siteId")Long siteId,@Param("authDataStr")String authDataStr);
    
    /**
     * 查询子栏目数量
     * @author: fallsea
     * @param catalogId 栏目id
     * @return
     */
    int queryChildCatalogCount(@Param("catalogId")Long catalogId);
    
    int delete(DataMap paramMap);
    
    int deleteMultiple(DataMap paramMap);
    
    int insert(DataMap paramMap);
    
    int update(DataMap paramMap);

    /**
     * 修改排序和结构
     * @author: fallsea
     * @param catalogId
     * @param parentId
     * @param orderline
     * @return
     */
    int updateOrderline(@Param("catalogId")Long catalogId,@Param("parentId")Long parentId,@Param("orderline")Long orderline,@Param("route")String route);
}