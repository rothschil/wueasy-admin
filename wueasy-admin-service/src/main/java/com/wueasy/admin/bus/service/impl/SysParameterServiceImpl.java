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

import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wueasy.admin.bus.service.SysParameterService;
import com.wueasy.admin.entity.SysParameterType;
import com.wueasy.admin.entity.SysParameterValue;
import com.wueasy.admin.mapper.SysParameterTypeMapper;
import com.wueasy.admin.mapper.SysParameterValueMapper;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.exception.InvokeException;
import com.wueasy.base.util.JsonHelper;
import com.wueasy.base.util.ZKHelper;
import com.wueasy.cache.constants.CacheConstants;

/**
 * 
 * @author: fallsea
 * @version 1.0
 */
@Service("SysParameterService")
public class SysParameterServiceImpl implements SysParameterService {

	@Autowired
    private SysParameterTypeMapper sysParameterTypeMapper;
	
	@Autowired
    private SysParameterValueMapper sysParameterValueMapper;
	
	@Override
	public void insertParameterType(DataMap paramMap) {
		String paramNo = paramMap.getString("paramNo");
		
		SysParameterType sysParameterType = sysParameterTypeMapper.selectByParamNo(paramNo);
		if(null!=sysParameterType){
			throw new InvokeException(-104601,"英文名称已存在");
		}
		sysParameterTypeMapper.insert(paramMap);
	}

	@Override
	public void updateParameterType(DataMap paramMap) {
		String paramNo = paramMap.getString("paramNo");
		Long id = paramMap.getLong("id");
		
		SysParameterType sysParameterType = sysParameterTypeMapper.selectByParamNo(paramNo);
		if(null!=sysParameterType && !id.equals(sysParameterType.getId())){
			throw new InvokeException(-104701,"英文名称已存在");
		}
		sysParameterTypeMapper.update(paramMap);
	}

	@Override
	public void deleteParameterType(DataMap paramMap) {
		Long id = paramMap.getLong("id");
		SysParameterType sysParameterType = sysParameterTypeMapper.select(id);
		if(sysParameterType == null){
			throw new InvokeException(-104901,"字典类型不存在");
		}
		
		if("1".equals(sysParameterType.getIsSystem())){
			throw new InvokeException(-104902,"字典类型是系统参数，不能删除");
		}
		
		int count = sysParameterValueMapper.selectByParamTypeId(id);
		if(count>0){
			throw new InvokeException(-104903,"字典类型存在数据，不能删除");
		}
		
		sysParameterTypeMapper.delete(id);
		
	}

	@Override
	public void insertParameterValue(DataMap paramMap) {
		Long paramTypeId = paramMap.getLong("paramTypeId");
		String itemKey = paramMap.getString("itemKey");
		SysParameterValue sysParameterValue = sysParameterValueMapper.selectByParamTypeIdAndItemValue(paramTypeId, itemKey);
		if(null!=sysParameterValue){
			throw new InvokeException(-104101,"枚举key已存在");
		}
		sysParameterValueMapper.insert(paramMap);
		
		publishNotify();
	}

	@Override
	public void updateParameterValue(DataMap paramMap) {
		Long id = paramMap.getLong("id");
		String itemKey = paramMap.getString("itemKey");
		SysParameterValue sysParameterValue = sysParameterValueMapper.select(id);
		if(null!=sysParameterValue){
			
			SysParameterValue sysParameterValue2 = sysParameterValueMapper.selectByParamTypeIdAndItemValue(sysParameterValue.getParamTypeId(), itemKey);
			
			if(null!=sysParameterValue2 && !sysParameterValue2.getId().equals(sysParameterValue.getId())){
				throw new InvokeException(-104201,"枚举key已存在");
			}
		}
		sysParameterValueMapper.update(paramMap);
		
		publishNotify();
	}
	
	/**
	 * 发布通知缓存更新
	 * @author: fallsea
	 */
	private void publishNotify(){
		
		List<DataMap> list = sysParameterValueMapper.queryValidList();
		
		if(null!=list && !list.isEmpty()){
			Map<String,String> map = new HashMap<String, String>();
			
			for (DataMap dataMap : list) {
				String key = dataMap.getString("param_no") + "." + dataMap.getString("item_key");
				String value =dataMap.getString("item_value");
				map.put(key, value);
			}
			//发布通知更新
			ZkClient zkClient = ZKHelper.getInstance();
			if(!ZKHelper.exists(CacheConstants.PARAMETER_CACHE_NODE_PATH)){
				zkClient.createPersistent(CacheConstants.PARAMETER_CACHE_NODE_PATH,true);
			}
			zkClient.writeData(CacheConstants.PARAMETER_CACHE_NODE_PATH,JsonHelper.toJSONString(map));
		}
	}

	@Override
	public void deleteParameterValue(DataMap paramMap) {
		sysParameterValueMapper.delete(paramMap);
		publishNotify();
	}

}
