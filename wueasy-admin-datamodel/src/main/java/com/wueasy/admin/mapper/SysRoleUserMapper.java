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

import com.wueasy.admin.entity.SysRoleUser;
import com.wueasy.base.entity.DataMap;

/**
 * 系统角色用户
 * @author: fallsea
 * @version 1.0
 */
public interface SysRoleUserMapper {
	
	/**
	 * 删除
	 * @author: fallsea
	 * @param id
	 * @return
	 */
    int delete(Long id);
    
    /**
     * 删除
     * @author: fallsea
     * @param paramMap
     * @return
     */
    int delete(DataMap paramMap);

    /**
     * 新增
     * @author: fallsea
     * @param paramMap
     * @return
     */
    int insert(DataMap paramMap);


    /**
     * 单个查询
     * @author: fallsea
     * @param id
     * @return
     */
    SysRoleUser select(Long id);

    /**
     * 列表查询
     * @author: fallsea
     * @param paramMap
     * @return
     */
    List<SysRoleUser> query(DataMap paramMap);
    
    /**
     * 查询用户角色列表
     * @author: fallsea
     * @param userId
     * @return
     */
    List<Long> queryByUserId(Long userId);
    
    /**
     * 查询角色用户数量
     * @author: fallsea
     * @param roleId
     * @return
     */
    Long queryRoleUserCount(Long roleId);
}