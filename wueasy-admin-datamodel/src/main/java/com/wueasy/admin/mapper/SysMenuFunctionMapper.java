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
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.wueasy.admin.entity.SysMenuFunction;
import com.wueasy.base.entity.DataMap;

/**
 * 系统菜单功能
 * @author: fallsea
 * @version 1.0
 */
public interface SysMenuFunctionMapper {
    
	int delete(DataMap paramMap);

    int insert(DataMap paramMap);

    SysMenuFunction selectByPrimaryKey(DataMap paramMap);

    int update(DataMap paramMap);
    
    List<SysMenuFunction> queryList(DataMap paramMap);
    
    /**
     * 查询菜单按钮id和菜单id组合
     * @author: fallsea
     * @param ids
     * @return
     */
    List<DataMap> queryMenuFunctionId(@Param("id")String id);
    
    /**
     * 查询有权限的菜单按钮
     * @author: fallsea
     * @param menuFunctionIdStr
     * @return
     */
    List<SysMenuFunction> queryUserAuthMenuFunction(@Param("menuFunctionIdSet")Set<String> menuFunctionIdSet);
    
}