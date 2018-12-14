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

import com.wueasy.admin.entity.SysUser;
import com.wueasy.base.entity.DataMap;

/**
 * 系统用户
 * @author: fallsea
 * @version 1.0
 */
public interface SysUserMapper {
    
	/**
	 * 查询用户信息
	 * @author: fallsea
	 * @param loginNo 登录名
	 * @return
	 */
    SysUser findByLoginNo(String loginNo);
    
    /**
     * 查询用户信息
     * @author: fallsea
     * @param phone 手机号码
     * @return
     */
    SysUser findByPhone(String phone);
    
    /**
     * 查询用户信息
     * @author: fallsea
     * @param email 邮箱
     * @return
     */
    SysUser findByEmail(String email);
    
    /**
     * 修改登录次数和最后登录时间
     * @author: fallsea
     * @param userId
     * @param lastTime
     * @param lastIp
     * @return
     */
    int updateByLoginCountAndLastTime(@Param("userId")Long userId,@Param("lastTime")Date lastTime,@Param("lastIp") String lastIp);
    

    /**
     * 查询用户列表，分页查询
     * @author: fallsea
     * @param paramMap
     * @return
     */
    List<SysUser> queryPage(DataMap paramMap);
    
    /**
     * 查询角色下没有分配的用户列表
     * @author: fallsea
     * @param paramMap
     * @return
     */
    List<SysUser> queryUserListByRoleId(DataMap paramMap);
    
    /**
     * 查询机构下未分配用户列表
     * @author: fallsea
     * @param paramMap
     * @return
     */
    List<SysUser> queryUserListByOrgId(DataMap paramMap);
    
    /**
     * 新增用户
     * @author: fallsea
     * @param paramMap
     * @return
     */
    int insert(DataMap paramMap);

    
    /**
     * 修改用户
     * @author: fallsea
     * @param paramMap
     * @return
     */
    int update(DataMap paramMap);
    
    /**
     * 查询用户信息
     * @author: fallsea
     * @param paramMap
     * @return
     */
    SysUser findByUserId(DataMap paramMap);
    
    /**
     * 查询用户信息
     * @author: fallsea
     * @param userId
     * @return
     */
    SysUser findByUserId(@Param("userId")Long userId);
    
    /**
     * 查询用户详细信息
     * @author: fallsea
     * @param userId
     * @return
     */
    SysUser findDetailByUserId(@Param("userId")Long userId);
    
    /**
     * 修改用户状态
     * @author: fallsea
     * @param userId 用户id
     * @param state 状态
     * @return
     */
    int updateState(@Param("userId")Long userId,@Param("state")String state);
    
    /**
     * 修改用户密码
     * @author: fallsea
     * @param userId 用户id
     * @param password 新密码
     * @param salt 加密盐
     * @return
     */
    int updatePassword(@Param("userId")Long userId,@Param("password")String password,@Param("salt")String salt);
    
    /**
     * 修改用户头像
     * @author: fallsea
     * @param paramMap
     * @return
     */
    int updateHeadImage(DataMap paramMap);
}