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

import com.wueasy.admin.entity.SysRole;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.entity.Page;

/**
 * 角色服务类
 * @author: fallsea
 * @version 1.0
 */
public interface SysRoleService {

	/**
	 * 新增角色用户
	 * @author: fallsea
	 * @param paramMap
	 */
	void insertRoleUser(DataMap paramMap);
	
	/**
	 * 新增或修改角色权限
	 * @author: fallsea
	 * @param paramMap
	 */
	void insertOrUpdateRolePermission(DataMap paramMap);
	
	/**
	 * 查询角色权限
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
	List<DataMap> queryRoleMenu(DataMap paramMap);
	
	/**
	 * 查询角色列表
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
	List<SysRole> queryRoleList(DataMap paramMap);
	
	/**
	 * 新增角色
	 * @author: fallsea
	 * @param paramMap
	 */
	void insertRole(DataMap paramMap);
	
	/**
	 * 修改角色
	 * @author: fallsea
	 * @param paramMap
	 */
	void updateRole(DataMap paramMap);
	
	/**
	 * 查询角色
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
	SysRole selectRole(DataMap paramMap);
	
	/**
	 * 删除角色
	 * @author: fallsea
	 * @param paramMap
	 */
	void deleteRole(DataMap paramMap);
	
	/**
	 * 查询角色用户列表
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
	Page queryRoleUserList(DataMap paramMap);
	
	/**
	 * 删除角色用户
	 * @author: fallsea
	 * @param paramMap
	 */
	void deleteRoleUser(DataMap paramMap);
	
	/**
	 * 验证角色权限
	 * @author: fallsea
	 * @param paramMap
	 */
	public void checkRoleAuth(DataMap paramMap);
	
}
