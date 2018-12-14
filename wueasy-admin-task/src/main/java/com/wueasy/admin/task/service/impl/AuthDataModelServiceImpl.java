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
package com.wueasy.admin.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wueasy.admin.entity.SysAuthDataModel;
import com.wueasy.admin.mapper.SysAuthDataModelMapper;
import com.wueasy.admin.task.service.AuthDataModelService;

/**
 * 
 * @author: fallsea
 * @version 1.0
 */
@Service("AuthDataModelService")
public class AuthDataModelServiceImpl implements AuthDataModelService {

	@Autowired
	private SysAuthDataModelMapper sysAuthDataModelMapper;
	
	@Override
	public List<SysAuthDataModel> queryList() {
		return sysAuthDataModelMapper.queryListAll();
	}

}
