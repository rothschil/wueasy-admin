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
package com.wueasy.admin.bus.service;

import java.util.List;

import com.wueasy.admin.entity.TemplateCatalog;
import com.wueasy.admin.pojo.Tree;
import com.wueasy.base.entity.DataMap;

/**
 * 模板服务类
 * @author: fallsea
 * @version 1.0
 */
public interface TemplateService {
	
	
	/**
	 * 新增站点
	 * @author: fallsea
	 * @param paramMap
	 */
	void insertSite(DataMap paramMap);

	
	/**
	 * 编辑站点
	 * @author: fallsea
	 * @param paramMap
	 */
	void updateSite(DataMap paramMap);
	
	/**
	 * 删除站点
	 * @author: fallsea
	 * @param paramMap
	 */
	void deleteSite(DataMap paramMap);
	
	/**
	 * 新增模板变量
	 * @author: fallsea
	 * @param paramMap
	 */
	void insertVar(DataMap paramMap);
	
	/**
	 * 编辑模板变量
	 * @author: fallsea
	 * @param paramMap
	 */
	void updateVar(DataMap paramMap);
	
	/**
	 * 删除模板变量
	 * @author: fallsea
	 * @param paramMap
	 */
	void deleteVar(DataMap paramMap);
	
	/**
	 * 新增栏目信息
	 * @author: fallsea
	 * @param paramMap
	 */
	void insertCatalog(DataMap paramMap);
	
	
	/**
	 * 编辑栏目信息
	 * @author: fallsea
	 * @param paramMap
	 */
	void updateCatalog(DataMap paramMap);
	
	/**
	 * 删除栏目信息
	 * @author: fallsea
	 * @param paramMap
	 */
	void deleteCatalog(DataMap paramMap);
	
	/**
	 * 修改栏目排序
	 * @author: fallsea
	 * @param paramMap
	 */
	void updateCatalogOrderline(DataMap paramMap);
	
	/**
	 * 新增模板
	 * @author: fallsea
	 * @param paramMap
	 */
	void addTemplate(DataMap paramMap);
	
	
	/**
	 * 修改模板
	 * @author: fallsea
	 * @param paramMap
	 */
	void updateTemplate(DataMap paramMap);
	
	/**
	 * 新增发布计划
	 * @author: fallsea
	 * @param paramMap
	 */
	void addPublishPlan(DataMap paramMap);
	
	/**
	 * 修改发布计划
	 * @author: fallsea
	 * @param paramMap
	 */
	void updatePublishPlan(DataMap paramMap);
	
	/**
	 * 删除发布计划
	 * @author: fallsea
	 * @param paramMap
	 */
	void deletePublishPlan(DataMap paramMap);
	
	/**
	 * 查询栏目数据权限
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
	List<Tree> queryRoleDataPermission(DataMap paramMap);
	
	/**
	 * 新增或修改数据权限
	 * @author: fallsea
	 * @param paramMap
	 */
	void insertOrUpdateRoleDataPermission(DataMap paramMap);
	
	/**
	 * 查询栏目信息
	 * @author: fallsea
	 * @param catalogId
	 * @return
	 */
	TemplateCatalog selectTemplateCatalog(Long catalogId);
}
