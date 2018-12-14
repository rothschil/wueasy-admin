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

import com.wueasy.admin.entity.SysMenu;
import com.wueasy.base.entity.DataMap;

/**
 * 系统菜单
 * @author: fallsea
 * @version 1.0
 */
public interface SysMenuMapper {
	
	/**
	 * 查询树列表
	 * @author: fallsea
	 * @return
	 */
    List<SysMenu> queryMenuTreeList(DataMap paramMap);
    
    
    /**
     * 
     * @author: fallsea
     * @param paramMap
     * @return
     */
    List<SysMenu> queryList(DataMap paramMap);
    
    
    /**
     * 查询有效的菜单列表
     * @author: fallsea
     * @param paramMap
     * @return
     */
    List<SysMenu> queryValidList(@Param("menuIdSet")Set<String> menuIdSet);
    
    
    
    /**
     * 查询菜单信息
     * @author: fallsea
     * @param menuId
     * @return
     */
    SysMenu findSysMenuByID(Long menuId);
    
    /**
     * 查询菜单信息
     * @author: fallsea
     * @param paramMap
     * @return
     */
    SysMenu findSysMenuByID(DataMap paramMap);
    
    /**
     * 查询子栏目数量
     * @author: fallsea
     * @param menuId
     * @return
     */
    int findSysMenuChildrennum(Long menuId);
    
    
    /**
     * 删除菜单
     * @author: fallsea
     * @param menuId
     * @return
     */
    int delete(Long menuId);

    /**
     * 新增菜单
     * @author: fallsea
     * @param paramMap
     * @return
     */
    int insert(DataMap paramMap);

    /**
     * 修改菜单信息
     * @author: fallsea
     * @param paramMap
     * @return
     */
    int update(DataMap paramMap);
    
    /**
     * 查询所有的菜单列表
     * @author: fallsea
     * @param paramMap
     * @return
     */
    List<DataMap> queryMenuAll(DataMap paramMap);
    
    /**
     * 查询所有的菜单列表
     * @author: fallsea
     * @param menuIdStr
     * @return
     */
    List<DataMap> queryMenuAll(@Param("menuIdStr")String menuIdStr);
    
    
    /**
     * 查询所有的菜单按钮列表
     * @author: fallsea
     * @param paramMap
     * @return
     */
    List<DataMap> queryMenuAll2(DataMap paramMap);
    
    /**
     * 查询所有的菜单按钮列表
     * @author: fallsea
     * @param menuFunctionIds
     * @return
     */
    List<DataMap> queryMenuAll2(@Param("menuFunctionIds")String menuFunctionIds);
    
    /**
     * 修改排序和结构
     * @author: fallsea
     * @param menuId
     * @param parentId
     * @param orderline
     * @return
     */
    int updateOrderline(@Param("menuId")Long menuId,@Param("parentId")Long parentId,@Param("orderline")Long orderline);
}