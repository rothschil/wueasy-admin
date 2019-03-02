package com.wueasy.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wueasy.base.entity.DataMap;
import com.wueasy.demo.entity.DemoMenu;

/**
 * @Description: 系统菜单
 * @Copyright: 2017 www.fallsea.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.0
 * @date: 2017年10月22日 下午9:23:05
 */
public interface DemoMenuMapper {
    
    /**
     * @Description: 查询列表
     * @author: fallsea
     * @date: 2017年10月22日 下午9:23:11
     * @return
     */
    List<DemoMenu> queryList();
    
    
    /**
     * @Description: 查询树列表
     * @author: fallsea
     * @date: 2017年10月22日 下午9:23:18
     * @return
     */
    List<DemoMenu> query(DataMap paramMap);
    
    /**
     * @Description: 查询菜单信息
     * @author: fallsea
     * @date: 2017年10月22日 下午9:23:23
     * @param menuId
     * @return
     */
    DemoMenu findSysMenuByID(Long menuId);
    
    
    /**
     * @Description: 查询子栏目数量
     * @author: fallsea
     * @date: 2017年10月22日 下午9:23:31
     * @param menuId
     * @return
     */
    int findSysMenuChildrennum(Long menuId);
    
    /**
     * @Description: 修改菜单  子菜单数量
     * @author: fallsea
     * @date: 2017年10月22日 下午9:23:36
     * @param menuId
     * @param childrennum
     */
    void updateChildrennum(@Param("menuId")Long menuId,@Param("childrennum")int childrennum);
    
    
    /**
     * @Description: 删除菜单
     * @author: fallsea
     * @date: 2017年10月22日 下午9:23:41
     * @param menuId
     * @return
     */
    int delete(Long menuId);

    /**
     * @Description: 新增菜单
     * @author: fallsea
     * @date: 2017年10月22日 下午9:23:46
     * @param paramMap
     * @return
     */
    int insert(DataMap paramMap);

    /**
     * @Description: 修改菜单信息
     * @author: fallsea
     * @date: 2017年10月22日 下午9:23:51
     * @param paramMap
     * @return
     */
    int update(DataMap paramMap);
}