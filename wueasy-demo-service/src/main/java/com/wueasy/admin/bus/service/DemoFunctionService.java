package com.wueasy.admin.bus.service;

import com.wueasy.base.entity.DataMap;
import com.wueasy.base.entity.Page;

/**
 * @Description: 功能号服务类
 * @Copyright: 2017 www.fallsea.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.0
 * @date: 2017年10月29日 上午10:12:53
 */
public interface DemoFunctionService {

	/**
	 * @Description: 查询功能号分页信息
	 * @author: fallsea
	 * @date: 2017年10月29日 上午10:14:12
	 * @param paramMap
	 * @return
	 */
	Page queryFunctionPage(DataMap paramMap);
	
	/**
	 * @Description: 新增功能号
	 * @author: fallsea
	 * @date: 2017年10月29日 下午12:55:37
	 * @param paramMap
	 */
	void addFunction(DataMap paramMap);
	
	/**
	 * @Description: 修改功能号
	 * @author: fallsea
	 * @date: 2017年10月30日 下午2:28:26
	 * @param paramMap
	 */
	void updateFunction(DataMap paramMap);
	
	/**
	 * @Description: 新增功能号参数
	 * @author: fallsea
	 * @date: 2017年10月30日 下午4:16:36
	 * @param paramMap
	 */
	void addFunctionParam(DataMap paramMap);
	
	/**
	 * @Description: 修改功能号参数
	 * @author: fallsea
	 * @date: 2017年10月30日 下午4:16:59
	 * @param paramMap
	 */
	void updateFunctionParam(DataMap paramMap);
	
	/**
	 * @Description: 新增功能号
	 * @author: fallsea
	 * @date: 2017年12月19日 下午2:47:47
	 * @param paramMap
	 */
	void add(DataMap paramMap);
	
	/**
	 * @Description: 编辑功能号
	 * @author: fallsea
	 * @date: 2017年12月19日 下午2:47:57
	 * @param paramMap
	 */
	void edit(DataMap paramMap);
	
	/**
	 * @Description: 删除功能号
	 * @author: fallsea
	 * @date: 2017年12月19日 下午2:48:17
	 * @param paramMap
	 */
	void del(DataMap paramMap);
}
