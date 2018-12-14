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

import com.wueasy.base.entity.DataMap;

/**
 * 数据字典服务类
 * @author: fallsea
 * @version 1.0
 */
public interface SysEnumService {

	/**
	 * 新增字典类型
	 * @author: fallsea
	 * @param paramMap
	 */
	void insertEnumType(DataMap paramMap);
	
	/**
	 * 修改字典类型
	 * @author: fallsea
	 * @param paramMap
	 */
	void updateEnumType(DataMap paramMap);
	
	/**
	 * 删除字典类型
	 * @author: fallsea
	 * @param paramMap
	 */
	void deleteEnumType(DataMap paramMap);
	
	/**
	 * 新增枚举信息 
	 * @author: fallsea
	 * @param paramMap
	 */
	void insertEnumValue(DataMap paramMap);
	
	/**
	 * 修改枚举信息
	 * @author: fallsea
	 * @param paramMap
	 */
	void updateEnumValue(DataMap paramMap);
	
	/**
	 * 删除枚举信息
	 * @author: fallsea
	 * @param paramMap
	 */
	void deleteEnumValue(DataMap paramMap);
	
}
