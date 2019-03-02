package com.wueasy.demo.mapper;

import java.util.List;

import com.wueasy.base.entity.DataMap;
import com.wueasy.demo.entity.Demo;

/**
 * @Description: demo
 * @Copyright: 2017 www.wueasy.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.0
 * @date: 2017年10月22日 下午9:24:13
 */
public interface DemoMapper {
	
    
    int insert(DataMap paramMap);
    
    List<Demo> queryPage(DataMap paramMap);
    
    int delete(DataMap paramMap);
    
    
    int update(DataMap paramMap);
    
    
    Demo findById(DataMap paramMap);
    
}