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
package com.wueasy.admin.bus.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.wueasy.admin.bus.service.SysMenuService;
import com.wueasy.admin.mapper.SysMenuMapper;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.exception.InvokeException;

/**
 * 
 * @author: fallsea
 * @version 1.0
 */
@Service("SysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
	
	private Logger logger = LoggerFactory.getLogger(SysMenuServiceImpl.class);

	@Autowired
    private SysMenuMapper sysMenuMapper;
	 
	@Override
	public void addMenuInfo(DataMap paramMap) {
		//新增
        sysMenuMapper.insert(paramMap);
	}

	@Override
	public void deleteMenuInfo(DataMap paramMap) {
		//判断是否是子节点
        Long menuId = paramMap.getLong("menuId");//获取菜单id
        
        //查询父菜单子菜单数量
        int childrennum = sysMenuMapper.findSysMenuChildrennum(menuId);
        if(childrennum != 0)
        {
            throw new InvokeException(-100701, "删除失败,父节点不能直接删除,请先删除子节点！");
        }
        sysMenuMapper.delete(menuId);
	}

	@Override
	@Transactional
	public void updateMenuOrderline(DataMap paramMap) {
		String menuListStr = paramMap.getString("menuList");//获取菜单数据
		try {
			menuListStr = URLDecoder.decode(menuListStr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("",e);
		}
		
		List<DataMap> list = JSON.parseArray(menuListStr, DataMap.class);
		if(null!=list && !list.isEmpty()){
			for (DataMap dataMap : list) {
				sysMenuMapper.updateOrderline(dataMap.getLong("menuId"), dataMap.getLong("parentId"), dataMap.getLong("orderline"));
			}
		}
		
	}

}
