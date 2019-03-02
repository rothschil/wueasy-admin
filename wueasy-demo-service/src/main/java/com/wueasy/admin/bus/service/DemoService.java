package com.wueasy.admin.bus.service;

import com.wueasy.base.entity.DataMap;
import com.wueasy.base.entity.Page;

/**
 * @Description: 
 * @Copyright: 2017 www.fallsea.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.0
 * @date: 2017年10月22日 下午9:21:33
 */
public interface DemoService
{
	/**
	 * @Description: 测试原文返回
	 * @author: fallsea
	 * @date: 2018年8月23日 下午7:07:34
	 * @param paramMap
	 * @return
	 */
	DataMap test(DataMap paramMap);
    
    public Page query(DataMap paramMap);
    
    
    public void insert(DataMap paramMap);
    
    /**
     * @Description: 新增或修改
     * @author: fallsea
     * @date: 2017年12月13日 下午5:37:06
     * @param paramMap
     */
    public void insertOrUpdate(DataMap paramMap);
    
}
