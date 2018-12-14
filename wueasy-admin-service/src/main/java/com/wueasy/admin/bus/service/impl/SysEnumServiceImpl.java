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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wueasy.admin.bus.service.SysEnumService;
import com.wueasy.admin.entity.SysEnumType;
import com.wueasy.admin.entity.SysEnumValue;
import com.wueasy.admin.mapper.SysEnumTypeMapper;
import com.wueasy.admin.mapper.SysEnumValueMapper;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.exception.InvokeException;

/**
 * 数据字典服务类
 * @author: fallsea
 * @version 1.0
 */
@Service("SysEnumService")
public class SysEnumServiceImpl implements SysEnumService {
	
	
	@Autowired
    private SysEnumTypeMapper sysEnumTypeMapper;
	
	@Autowired
    private SysEnumValueMapper sysEnumValueMapper;
	

	@Override
	public void insertEnumType(DataMap paramMap) {
		
		String enumNo = paramMap.getString("enumNo");
		
		SysEnumType sysEnumType = sysEnumTypeMapper.selectByEnumNo(enumNo);
		if(null!=sysEnumType){
			throw new InvokeException(-103601,"英文名称已存在");
		}
		sysEnumTypeMapper.insert(paramMap);
	}


	@Override
	public void updateEnumType(DataMap paramMap) {
		String enumNo = paramMap.getString("enumNo");
		Long id = paramMap.getLong("id");
		SysEnumType sysEnumType = sysEnumTypeMapper.selectByEnumNo(enumNo);
		if(null!=sysEnumType && !id.equals(sysEnumType.getId())){
			throw new InvokeException(-103701,"英文名称已存在");
		}
		sysEnumTypeMapper.update(paramMap);
	}


	@Override
	public void deleteEnumType(DataMap paramMap) {
		Long id = paramMap.getLong("id");
		SysEnumType sysEnumType = sysEnumTypeMapper.select(id);
		if(sysEnumType == null){
			throw new InvokeException(-103901,"字典类型不存在");
		}
		
		if("1".equals(sysEnumType.getIsSystem())){
			throw new InvokeException(-103902,"字典类型是系统参数，不能删除");
		}
		
		int count = sysEnumValueMapper.selectByEnumTypeId(id);
		if(count>0){
			throw new InvokeException(-103903,"字典类型存在数据，不能删除");
		}
		
		sysEnumTypeMapper.delete(id);
		
	}


	@Override
	public void insertEnumValue(DataMap paramMap) {
		Long enumTypeId = paramMap.getLong("enumTypeId");
		String itemKey = paramMap.getString("itemKey");
		SysEnumValue sysEnumValue = sysEnumValueMapper.selectByEnumTypeIdAndItemValue(enumTypeId, itemKey);
		if(null!=sysEnumValue){
			throw new InvokeException(-104101,"枚举key已存在");
		}
		sysEnumValueMapper.insert(paramMap);
	}


	@Override
	public void updateEnumValue(DataMap paramMap) {
		Long id = paramMap.getLong("id");
		String itemKey = paramMap.getString("itemKey");
		
		
		SysEnumValue sysEnumValue = sysEnumValueMapper.select(id);
		if(null!=sysEnumValue){
			
			SysEnumValue sysEnumValue2 = sysEnumValueMapper.selectByEnumTypeIdAndItemValue(sysEnumValue.getEnumTypeId(), itemKey);
			
			if(null!=sysEnumValue2 && !sysEnumValue2.getId().equals(sysEnumValue.getId())){
				throw new InvokeException(-104201,"枚举key已存在");
			}
		}
		sysEnumValueMapper.update(paramMap);
	}
	

	@Override
	public void deleteEnumValue(DataMap paramMap) {
		sysEnumValueMapper.delete(paramMap);
	}
	
	
}
