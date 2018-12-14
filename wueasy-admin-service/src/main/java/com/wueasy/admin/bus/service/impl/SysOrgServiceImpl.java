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

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.wueasy.admin.bus.service.SysOrgService;
import com.wueasy.admin.entity.SysOrg;
import com.wueasy.admin.mapper.SysOrgMapper;
import com.wueasy.admin.mapper.SysOrgUserMapper;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.exception.InvokeException;

/**
 * 
 * @author: fallsea
 * @version 1.0
 */
@Service("SysOrgService")
public class SysOrgServiceImpl implements SysOrgService {

	@Autowired
	private SysOrgMapper sysOrgMapper;
	
	@Autowired
	private SysOrgUserMapper sysOrgUserMapper;
	
	@Override
	public void insert(DataMap paramMap) {
		String orgNo = paramMap.getString("orgNo");
		SysOrg sysOrg = sysOrgMapper.selectByNo(orgNo);
		if(null!=sysOrg){
			throw new InvokeException(-106101, "机构编号已存在!");
		}
		sysOrgMapper.insert(paramMap);
	}

	@Override
	public void update(DataMap paramMap) {
		String orgNo = paramMap.getString("orgNo");
		SysOrg sysOrg = sysOrgMapper.selectByNo(orgNo);
		if(null!=sysOrg && !sysOrg.getId().equals(paramMap.getLong("id"))){
			throw new InvokeException(-106201, "机构编号已存在!");
		}
		sysOrgMapper.update(paramMap);
	}

	@Override
	public void delete(DataMap paramMap) {
		Long id = paramMap.getLong("id");
		
		//删除，先判断组织机构下是否存在用户
		int count = sysOrgUserMapper.queryUserCount(id);
		if(count>0){
			throw new InvokeException(-106401, "删除失败,组织机构下存在用户!");
		}
		sysOrgMapper.delete(id);
	}

	@Override
	@Transactional
	public void insertOrgUser(DataMap paramMap) {
		
		String  userIds = paramMap.getString("userId");
		List<String> userIdList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(userIds);
		for (String userId : userIdList) {
			paramMap.set("userId", userId);
			sysOrgUserMapper.insert(paramMap);
		}
		
	}

	
	
}
