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
 * 系统参数服务类
 * @author: fallsea
 * @version 1.0
 */
public interface SysParameterService {

	/**
	 * 新增参数类型
	 * @author: fallsea
	 * @param paramMap
	 */
	void insertParameterType(DataMap paramMap);
	
	/**
	 * 修改参数类型
	 * @author: fallsea
	 * @param paramMap
	 */
	void updateParameterType(DataMap paramMap);
	
	/**
	 * 删除参数类型
	 * @author: fallsea
	 * @param paramMap
	 */
	void deleteParameterType(DataMap paramMap);
	
	/**
	 * 新增参数枚举信息 
	 * @author: fallsea
	 * @param paramMap
	 */
	void insertParameterValue(DataMap paramMap);
	
	/**
	 * 修改参数枚举信息
	 * @author: fallsea
	 * @param paramMap
	 */
	void updateParameterValue(DataMap paramMap);
	
	/**
	 * 删除参数枚举信息
	 * @author: fallsea
	 * @param paramMap
	 */
	void deleteParameterValue(DataMap paramMap);
	
}
