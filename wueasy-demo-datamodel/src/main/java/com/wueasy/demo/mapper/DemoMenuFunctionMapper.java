package com.wueasy.demo.mapper;
import java.util.List;

import com.wueasy.base.entity.DataMap;
import com.wueasy.demo.entity.DemoMenuFunction;

/**
 * @Description: 系统菜单功能
 * @Copyright: 2017 www.fallsea.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.0
 * @date: 2017年10月22日 下午9:23:58
 */
public interface DemoMenuFunctionMapper {
    
    int delete(DataMap paramMap);

    int insert(DataMap paramMap);

    DemoMenuFunction selectByPrimaryKey(DataMap paramMap);

    int update(DataMap paramMap);
    
    
    List<DemoMenuFunction> queryList(DataMap paramMap);
}