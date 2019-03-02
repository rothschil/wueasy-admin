package com.wueasy.admin.bus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wueasy.admin.bus.service.DemoService;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.entity.Page;
import com.wueasy.base.exception.InvokeException;
import com.wueasy.base.util.PageHelper;
import com.wueasy.base.util.StringHelper;
import com.wueasy.demo.entity.Demo;
import com.wueasy.demo.mapper.DemoMapper;

/**
 * @Description: 
 * @Copyright: 2017 www.fallsea.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.0
 * @date: 2017年10月22日 下午9:21:49
 */
@Service("DemoService")
public class DemoServiceImpl implements DemoService
{
    @Autowired
    private DemoMapper demoMapper;
    
    @Override
    public Page query(DataMap paramMap)
    {
        int pageNum = paramMap.getInt("pageNum",1);
        int pageSize = paramMap.getInt("pageSize",20);
        
        String createDate = paramMap.getString("createDate");//创建时间
        if(StringHelper.isNotEmpty(createDate) && !" - ".equals(createDate))
        {
            String createDates[] = createDate.split(" - ");
            paramMap.put("startDate", createDates[0]+" 00:00:00");
            paramMap.put("endDate", createDates[1]+" 23:59:59");
        }
        
        //验证排序格式
        String field = paramMap.getString("field");
        String order = paramMap.getString("order");
        if(StringHelper.isEmpty(field)){
        	field = "id";
        }
        if(StringHelper.isEmpty(order)){
        	field = "id";
        	order = "desc";
        }
        if(!StringHelper.isAlphaNumeric(field)){
        	throw new InvokeException(-10001,"排序字段格式不正确");
        }
        if(!StringHelper.isAlphaNumeric(order)){
        	throw new InvokeException(-10002,"排序不正确");
        }
        paramMap.put("field", field);
        paramMap.put("order", order);
        
        PageHelper.startPage(pageNum, pageSize);
        List<Demo> list = demoMapper.queryPage(paramMap);
        return PageHelper.getPage(list);
    }

	@Override
	@Transactional
	public void insert(DataMap paramMap) {
		demoMapper.insert(paramMap);
	}

	@Override
	public void insertOrUpdate(DataMap paramMap) {
		String _mode = paramMap.getString("_mode");
		if("new".equals(_mode)){
			demoMapper.insert(paramMap);
		}else if("edit".equals(_mode)){
			demoMapper.update(paramMap);
		}
	}

	@Override
	public DataMap test(DataMap paramMap) {
		return paramMap;
	}
    
}
