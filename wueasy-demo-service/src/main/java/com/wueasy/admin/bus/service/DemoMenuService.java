package com.wueasy.admin.bus.service;

import java.util.List;

import com.wueasy.base.entity.DataMap;
import com.wueasy.demo.entity.DemoMenu;
import com.wueasy.demo.entity.Ztree;

/**
 * @Description: 菜单服务类
 * @Copyright: 2017 www.fallsea.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.0
 * @date: 2017年10月22日 下午9:20:50
 */
public interface DemoMenuService
{
    
    List<DemoMenu> queryList();
    
    
    /**
     * @Description: 查询菜单树集合
     * @author: fallsea
     * @date: 2017年10月22日 下午9:20:56
     * @return
     */
    List<Ztree> queryTreeList(DataMap paramMap);
    
    
    /**
     * @Description: 查询菜单信息
     * @author: fallsea
     * @date: 2017年10月22日 下午9:21:01
     * @param menuId
     * @return
     */
    DemoMenu findSysMenuByID(DataMap paramMap);
    
    /**
     * @Description: 新增菜单信息
     * @author: fallsea
     * @date: 2017年10月22日 下午9:21:07
     * @param paramMap
     */
    void addMenuInfo(DataMap paramMap);
    
    /**
     * @Description: 修改菜单信息
     * @author: fallsea
     * @date: 2017年10月22日 下午9:21:13
     * @param paramMap
     */
    void updateMenuInfo(DataMap paramMap);
    
    /**
     * @Description: 删除菜单信息
     * @author: fallsea
     * @date: 2017年10月22日 下午9:21:17
     * @param paramMap
     */
    void deleteMenuInfo(DataMap paramMap);
    
    
}
