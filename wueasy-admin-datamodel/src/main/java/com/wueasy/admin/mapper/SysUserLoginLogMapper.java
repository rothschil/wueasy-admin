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

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wueasy.admin.entity.SysUserLoginLog;
import com.wueasy.base.entity.DataMap;

/**
 * 系统用户登录日志
 * @author: fallsea
 * @version 1.0
 */
public interface SysUserLoginLogMapper {
    
	/**
	 * 新增记录
	 * @author: fallsea
	 * @param record
	 * @return
	 */
	int insert(SysUserLoginLog record);
    
	/**
	 * 查询分页
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
    List<SysUserLoginLog> queryPage(DataMap paramMap);
    
    /**
     * 查询用户连续登录失败次数
     * @author: fallsea
     * @param loginNo
     * @return
     */
    int queryUserLogCount(@Param("loginNo")String loginNo,@Param("createdTime")Date createdTime);
    
}