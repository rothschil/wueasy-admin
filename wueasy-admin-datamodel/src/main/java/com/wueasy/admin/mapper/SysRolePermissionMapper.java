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
package com.wueasy.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wueasy.admin.entity.SysRolePermission;
import com.wueasy.base.entity.DataMap;

/**
 * 系统权限表
 * @author: fallsea
 * @version 1.0
 */
public interface SysRolePermissionMapper {
	
	/**
	 * 删除
	 * @author: fallsea
	 * @param id
	 * @return
	 */
    int delete(Long id);

    /**
     * 新增
     * @author: fallsea
     * @param paramMap
     * @return
     */
    int insert(DataMap paramMap);
    
    /**
     * 修改
     * @author: fallsea
     * @param paramMap
     * @return
     */
    int update(DataMap paramMap);
    
    /**
     * 查询
     * @author: fallsea
     * @param id
     * @return
     */
    SysRolePermission select(Long id);
    
    /**
     * 通过角色id查询
     * @author: fallsea
     * @param roleId
     * @return
     */
    SysRolePermission selectByRoleId(Long roleId);
    
    /**
	 * 查询用户权限
	 * @author: fallsea
	 * @param userId
	 * @return
	 */
	List<SysRolePermission> queryUserRole(@Param("userId")Long userId);
	
	/**
	 * 删除角色id
	 * @author: fallsea
	 * @param roleId
	 * @return
	 */
	int deleteByRoleId(@Param("roleId")Long roleId);
}