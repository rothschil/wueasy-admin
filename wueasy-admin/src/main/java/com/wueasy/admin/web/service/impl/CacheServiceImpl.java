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
package com.wueasy.admin.web.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wueasy.admin.entity.SysAuthDataModel;
import com.wueasy.base.bus.client.Client;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.entity.Result;
import com.wueasy.base.web.security.pojo.AuthDataModel;
import com.wueasy.base.web.security.service.CacheService;

/**
 * 缓存服务
 * @author: fallsea
 * @version 1.0
 */
@Service
public class CacheServiceImpl implements CacheService {

	@Override
	public Map<String, AuthDataModel> getAuthDataModelMap() {
		Result result = new Client().invoke("S1107", new DataMap());
		if(result.getErrorNo()==0){
			Map<String, SysAuthDataModel> map = (HashMap<String, SysAuthDataModel>)result.getResult();
			if(null!=map && !map.isEmpty()) {
				
				Map<String, AuthDataModel> authMap = new HashMap<>();
				for(Map.Entry<String, SysAuthDataModel> entry :  map.entrySet()) {
					
					AuthDataModel authDataModel = new AuthDataModel();
					authDataModel.setField(entry.getValue().getField());
					authDataModel.setFuncNos(entry.getValue().getFuncNos());
					authDataModel.setModelId(entry.getValue().getModelId());
					authDataModel.setModelName(entry.getValue().getModelName());
					authDataModel.setModelNo(entry.getValue().getModelNo());
					authDataModel.setServiceName(entry.getValue().getServiceName());
					authMap.put(entry.getKey(), authDataModel);
				}
				return authMap;
			}
		}
		return null;
	}

}
