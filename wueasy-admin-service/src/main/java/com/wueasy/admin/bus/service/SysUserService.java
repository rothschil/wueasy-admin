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

import com.wueasy.admin.entity.SysUser;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.entity.Page;
import com.wueasy.base.entity.Result;

/**
 * 用户服务接口
 * @author: fallsea
 * @version 1.0
 */
public interface SysUserService {

	/**
	 * 用户登录
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
	SysUser login(DataMap paramMap);
	
	
	/**
	 * 新增用户
	 * @author: fallsea
	 * @param paramMap
	 */
	void insertUser(DataMap paramMap);
	
	/**
	 * 修改用户
	 * @author: fallsea
	 * @param paramMap
	 */
	void updateUser(DataMap paramMap);
	
	/**
	 * 修改密码
	 * @author: fallsea
	 * @param paramMap
	 */
	void updatePwd(DataMap paramMap);
	
	/**
	 * 查询用户分页信息
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
	Page queryUserPage(DataMap paramMap);
	
	/**
	 * 修改用户状态
	 * @author: fallsea
	 * @param paramMap
	 */
	void updateUserState(DataMap paramMap);
	
	/**
	 * 重置密码
	 * @author: fallsea
	 * @param paramMap
	 */
	void resetPwd(DataMap paramMap);
	
	/**
	 * 查询用户登录记录
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
	Page queryUserLoginLog(DataMap paramMap);
	
	/**
     * 查询用户权限
     * @author: fallsea
     * @param paramMap
     * @return
     */
    Result queryUserAuth(DataMap paramMap);
    
    /**
     * 验证密码
     * @author: fallsea
     * @param paramMap
     * @return
     */
    Result verifyPwd(DataMap paramMap);
    
}
