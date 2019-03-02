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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.wueasy.admin.bus.service.AuthDataService;
import com.wueasy.admin.bus.service.SysAuthRoleDataService;
import com.wueasy.admin.bus.service.SysRoleService;
import com.wueasy.admin.entity.SysAuthDataModel;
import com.wueasy.admin.entity.SysAuthRoleData;
import com.wueasy.admin.mapper.SysAuthDataModelMapper;
import com.wueasy.admin.mapper.SysAuthRoleDataMapper;
import com.wueasy.admin.pojo.Tree;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.util.SpringHelper;
import com.wueasy.base.util.StringHelper;

/**
 * 
 * @author: fallsea
 * @version 1.0
 */
@Service("SysAuthRoleDataService")
public class SysAuthRoleDataServiceImpl implements SysAuthRoleDataService {

	@Autowired
	private SysAuthRoleDataMapper sysAuthRoleDataMapper;
	
	@Autowired
	private SysAuthDataModelMapper sysAuthDataModelMapper;
	
	@Override
	public List<Tree> queryRoleDataPermission(DataMap paramMap) {
		
		//查询数据权限模型信息
		Long modelId = paramMap.getLong("modelId");
		SysAuthDataModel sysAuthDataModel = sysAuthDataModelMapper.select(modelId); 
		if(null!=sysAuthDataModel){
			
			String serviceName = sysAuthDataModel.getServiceName();
			
			if(StringHelper.isNotEmpty(serviceName)){
				
				AuthDataService authDataService = SpringHelper.getBean(serviceName, AuthDataService.class);
				List<Tree> list = authDataService.findList(paramMap);
				if(null!=list && !list.isEmpty()){
					
					Long roleId = paramMap.getLong("roleId");
					
					SysAuthRoleData sysAuthRoleData = sysAuthRoleDataMapper.selectBymodelIdAndRoleId(modelId, roleId);
					
					
					List<String> authRoleDataList = null;
					if(null!=sysAuthRoleData){
						
						if(StringHelper.isNotEmpty(sysAuthRoleData.getAuthDataIds())){
							authRoleDataList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(sysAuthRoleData.getAuthDataIds());
						}
						
					}
					
					if(null!=authRoleDataList){
						for (Tree tree : list) {
							if(authRoleDataList.contains(tree.getId())){
								tree.setChecked(true);
							}
						}
					}
					
					return list;
					
				}
			}
			
			
		}
		
		return null;
	}


	@Override
	public void insertOrUpdateRoleDataPermission(DataMap paramMap) {
		
		
		SysRoleService sysRoleService = SpringHelper.getBean(SysRoleService.class);
		sysRoleService.checkRoleAuth(paramMap);
		
		Long roleId = paramMap.getLong("roleId");
		
		Long modelId = paramMap.getLong("modelId");
		
		String ids = paramMap.getString("ids");
		
		String authDataIds = "";
		
		if(StringHelper.isNotBlank(ids)){
			List<String> idArr = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(ids);
			for (String id : idArr) {
				if(StringHelper.isNotBlank(authDataIds)){
					authDataIds += ",";
				}
				authDataIds += id;
			}
		}
		paramMap.set("authDataIds",authDataIds);
		paramMap.remove("ids");
		
		SysAuthRoleData sysAuthRoleData = sysAuthRoleDataMapper.selectBymodelIdAndRoleId(modelId, roleId);
		
		if(null == sysAuthRoleData){
			sysAuthRoleDataMapper.insert(paramMap);
		}else{
			paramMap.set("id", sysAuthRoleData.getId());
			sysAuthRoleDataMapper.update(paramMap);
		}
		
	}
	
	@Override
	public Map<String, SysAuthDataModel> queryAuthDataModel(DataMap paramMap) {
		List<SysAuthDataModel> list = sysAuthDataModelMapper.queryListAll();
		//组装数据权限功能号字段集合
		Map<String, SysAuthDataModel> map = new HashMap<String, SysAuthDataModel>();
		if(null!=list && !list.isEmpty()){
			for (SysAuthDataModel sysAuthDataModel : list) {
				
				String funcNos = sysAuthDataModel.getFuncNos();
				if(StringHelper.isNotEmpty(funcNos)){
					String[] funcs= funcNos.split(",");
					for (int i = 0; i < funcs.length; i++) {
						String funcNo = funcs[i];
						if(StringHelper.isNotEmpty(funcNo)){
							SysAuthDataModel dataModel = new SysAuthDataModel();
							dataModel.setField(sysAuthDataModel.getField());
							dataModel.setModelId(sysAuthDataModel.getModelId());
							if(!funcNo.startsWith("/")){
								funcNo = "/fsbus/"+funcNo;
							}
							map.put(funcNo, dataModel);
						}
					}
				}
			}
		}
		return map;
	}
}
