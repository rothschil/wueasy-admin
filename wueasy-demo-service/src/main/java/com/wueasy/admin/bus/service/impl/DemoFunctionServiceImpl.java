package com.wueasy.admin.bus.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.wueasy.admin.bus.service.DemoFunctionService;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.entity.Page;
import com.wueasy.base.exception.InvokeException;
import com.wueasy.base.util.DateHelper;
import com.wueasy.base.util.JsonHelper;
import com.wueasy.base.util.PageHelper;
import com.wueasy.base.util.StringHelper;
import com.wueasy.demo.entity.DemoFunction;
import com.wueasy.demo.entity.DemoFunctionParam;
import com.wueasy.demo.mapper.DemoFunctionMapper;
import com.wueasy.demo.mapper.DemoFunctionParamMapper;

/**
 * @Description: 功能号服务
 * @Copyright: 2017 www.fallsea.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.0
 * @date: 2017年10月29日 上午10:13:27
 */
@Service("DemoFunctionService")
public class DemoFunctionServiceImpl implements DemoFunctionService {
	
	private static Logger logger = LoggerFactory.getLogger(DemoFunctionServiceImpl.class);

	@Autowired
	private DemoFunctionMapper demoFunctionMapper;
	
	@Autowired
	private DemoFunctionParamMapper demoFunctionParamMapper;
	
	@Override
	public Page queryFunctionPage(DataMap paramMap) {
		
		int pageNum = paramMap.getInt("pageNum",1);
        int pageSize = paramMap.getInt("pageSize",20);
        
        String lastDate = paramMap.getString("createDate");//创建时间
        if(StringHelper.isNotEmpty(lastDate) && !" - ".equals(lastDate))
        {
            String lastDates[] = lastDate.split(" - ");
            paramMap.put("startDate", lastDates[0]+" 00:00:00");
            paramMap.put("endDate", lastDates[1]+" 23:59:59");
        }
        
        PageHelper.startPage(pageNum, pageSize);
        List<DemoFunction> list = demoFunctionMapper.queryPage(paramMap);
        return PageHelper.getPage(list);
	}

	@Override
	public void addFunction(DataMap paramMap) {
		
		//判断功能号是否存在
		DemoFunction sysFunction = demoFunctionMapper.selectByFuncNo(paramMap.getString("funcNo"),paramMap.getString("version"));
		
		if(sysFunction != null)
		{
			throw new InvokeException(-102401, "功能号/版本号已存在!");
		}
		
		demoFunctionMapper.insert(paramMap);
	}

	@Override
	public void updateFunction(DataMap paramMap) {
		
		DemoFunction sysFunction = demoFunctionMapper.selectById(paramMap.getLong("id"));
		
		if(null == sysFunction)
		{
			throw new InvokeException(-102501, "功能号不存在!");
		}
		//查询功能号版本是否存在
		DemoFunction sysFunction2 = demoFunctionMapper.selectByFuncNo(sysFunction.getFuncNo(),paramMap.getString("version"));
		//如果不存在，直接修改，如果存在，需要判断是否相等
		if(null!=sysFunction2 && !sysFunction.getId().equals(sysFunction2.getId()))
		{
			throw new InvokeException(-102502, "功能号/版本号已存在!");
		}
		demoFunctionMapper.update(paramMap);
		
	}

	@Override
	public void addFunctionParam(DataMap paramMap) {
		
		DemoFunctionParam sysFunctionParam = demoFunctionParamMapper.selectByFuncAttribute(paramMap.getLong("funcId"), paramMap.getString("attribute"));
		
		if(null != sysFunctionParam)
		{
			throw new InvokeException(-103101, "属性已存在!");
		}
		
		demoFunctionParamMapper.insert(paramMap);
		
	}

	@Override
	public void updateFunctionParam(DataMap paramMap) {
		
		DemoFunctionParam sysFunctionParam = demoFunctionParamMapper.selectById(paramMap.getLong("id"));
		if(null == sysFunctionParam)
		{
			throw new InvokeException(-103201, "记录不存在!");
		}
		
		DemoFunctionParam sysFunctionParam2 = demoFunctionParamMapper.selectByFuncAttribute(sysFunctionParam.getFuncId(), paramMap.getString("attribute"));
		if(null!=sysFunctionParam2 && !sysFunctionParam.getId().equals(sysFunctionParam2.getId()))
		{
			throw new InvokeException(-103202, "参数已存在!");
		}
		
		demoFunctionParamMapper.update(paramMap);
		
	}

	@Override
	@Transactional
	public void add(DataMap paramMap) {
		String fsFormData = paramMap.getString("fsFormData");//获取form表单数据
		String fsTableData = paramMap.getString("fsTableData");//获取table数据
		
		Long funcId = 0L;
		
		String date = DateHelper.formatDate(new Date(), DateHelper.PATTERN_DATE_TIME);
		if(StringHelper.isNotEmpty(fsFormData)){
			try {
				fsFormData = URLDecoder.decode(fsFormData, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("",e);
			}
			DataMap dataMap = JsonHelper.parseObject(fsFormData,DataMap.class);
			if(StringHelper.isEmpty(dataMap.getString("version"))){
				dataMap.put("version", "1.0");
			}
			//判断功能号是否存在
			DemoFunction sysFunction = demoFunctionMapper.selectByFuncNo(dataMap.getString("funcNo"),dataMap.getString("version"));
			if(sysFunction != null)
			{
				throw new InvokeException(-102401, "功能号/版本号已存在!");
			}
			dataMap.put("id", funcId);
			dataMap.put("createdTime", date);
			dataMap.put("modifiedTime", date);
			demoFunctionMapper.insert(dataMap);
			
			funcId = dataMap.getLong("id");
		}
		
		if(StringHelper.isNotEmpty(fsTableData)){
			try {
				fsTableData = URLDecoder.decode(fsTableData, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("",e);
			}
			
			List<DataMap> list = JSON.parseArray(fsTableData, DataMap.class);
			
			Set<String> set = new HashSet<String>();
			
			if(null!=list && !list.isEmpty()){
				for (DataMap dataMap : list) {
					String attribute = dataMap.getString("attribute");
					if(StringHelper.isNotEmpty(attribute)){
						if(set.contains(attribute)){
							throw new InvokeException(-102402, "第"+(dataMap.getInt("LAY_TABLE_INDEX")+1)+"行参数已存在!");
						}
						set.add(attribute);
						
						dataMap.put("funcId", funcId);
						dataMap.put("createdTime", date);
						dataMap.put("modifiedTime", date);
						demoFunctionParamMapper.insert(dataMap);
					}
					
				}
			}
			
		}
		
	}

	@Override
	@Transactional
	public void edit(DataMap paramMap) {
		String fsFormData = paramMap.getString("fsFormData");//获取form表单数据
		String fsTableData = paramMap.getString("fsTableData");//获取table数据
		
		String funcId = "";
		
		String date = DateHelper.formatDate(new Date(), DateHelper.PATTERN_DATE_TIME);
		if(StringHelper.isNotEmpty(fsFormData)){
			try {
				fsFormData = URLDecoder.decode(fsFormData, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("",e);
			}
			DataMap dataMap = JsonHelper.parseObject(fsFormData,DataMap.class);
			if(StringHelper.isEmpty(dataMap.getString("version"))){
				dataMap.put("version", "1.0");
			}
			funcId = dataMap.getString("id");
			
			//判断功能号是否存在
			DemoFunction sysFunction = demoFunctionMapper.selectByFuncNo(dataMap.getString("funcNo"),dataMap.getString("version"));
			if(sysFunction != null && !funcId.equals(sysFunction.getId().toString()))
			{
				throw new InvokeException(-102401, "功能号/版本号已存在!");
			}
			
			dataMap.put("createdTime", date);
			dataMap.put("modifiedTime", date);
			demoFunctionMapper.update(dataMap);
		}
		
		//删除所有的参数
		demoFunctionParamMapper.deleteAll(funcId);
		
		if(StringHelper.isNotEmpty(fsTableData)){
			try {
				fsTableData = URLDecoder.decode(fsTableData, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("",e);
			}
			
			List<DataMap> list = JSON.parseArray(fsTableData, DataMap.class);
			
			Set<String> set = new HashSet<String>();
			
			if(null!=list && !list.isEmpty()){
				for (DataMap dataMap : list) {
					String attribute = dataMap.getString("attribute");
					if(StringHelper.isNotEmpty(attribute)){
						if(set.contains(attribute)){
							throw new InvokeException(-102402, "第"+(dataMap.getInt("LAY_TABLE_INDEX")+1)+"行参数已存在!");
						}
						set.add(attribute);
						
						dataMap.put("funcId", funcId);
						dataMap.put("createdTime", date);
						dataMap.put("modifiedTime", date);
						demoFunctionParamMapper.insert(dataMap);
					}
					
				}
			}
			
		}
	}

	@Override
	public void del(DataMap paramMap) {
		// TODO Auto-generated method stub
		
	}

	
}
