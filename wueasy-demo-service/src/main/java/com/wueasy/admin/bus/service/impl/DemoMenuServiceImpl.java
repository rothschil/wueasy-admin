package com.wueasy.admin.bus.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wueasy.admin.bus.service.DemoMenuService;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.exception.InvokeException;
import com.wueasy.demo.entity.DemoMenu;
import com.wueasy.demo.entity.Ztree;
import com.wueasy.demo.mapper.DemoMenuMapper;

/**
 * @Description: 
 * @Copyright: 2017 www.fallsea.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.0
 * @date: 2017年10月22日 下午9:21:46
 */
@Service("DemoMenuService")
public class DemoMenuServiceImpl implements DemoMenuService
{
    @Autowired
    private DemoMenuMapper demoMenuMapper;
    
    @Override
    public List<DemoMenu> queryList()
    {
        
        List<DemoMenu> list = demoMenuMapper.queryList();
        
        List<DemoMenu> rootList = new ArrayList<DemoMenu>();
        
        
        for (DemoMenu sysMenu : list)
        {
            if(BigDecimal.ONE.equals(sysMenu.getParentId()))
            {
                rootList.add(sysMenu);
            }
            
            for (DemoMenu t : list) {
                if(t.getParentId().equals(sysMenu.getMenuId())){
                    if(sysMenu.getChildrens() == null){
                        List<DemoMenu> myChildrens = new ArrayList<DemoMenu>();
                        myChildrens.add(t);
                        sysMenu.setChildrens(myChildrens);
                    }else{
                        sysMenu.getChildrens().add(t);
                    }
                }
            }
            
        }
        return rootList;
    }

    @Override
    public List<Ztree> queryTreeList(DataMap paramMap)
    {
        //查询全部菜单
        List<DemoMenu> list = demoMenuMapper.query(paramMap);
        
        //根集合
        List<Ztree> rootList = new ArrayList<Ztree>();
//        rootList.add(new Ztree("根节点",new BigDecimal("1"), new BigDecimal("0"), true));
        for (DemoMenu sysMenu : list)
        {
        	Ztree tree = new Ztree(sysMenu.getMenuName(), sysMenu.getMenuId(), sysMenu.getParentId());
        	/*if(sysMenu.getChildrennum()>0) {
        		tree.setIsParent(true);
        	}*/
           /* if(BigDecimal.ZERO.equals(sysMenu.getParentId()))
            {
                
                aa(sysMenu, list, tree);
                
                rootList.add(tree);
            }*/
        	rootList.add(tree);
        }
        return rootList;
    }
    
    
    /*private void aa(SysMenu sysMenu,List<SysMenu> list,Tree tree)
    {
        for (SysMenu t : list) 
        {
            if(t.getParentId().equals(sysMenu.getMenuId()))
            {
                Tree tree2 = new Tree(t.getMenuName(), t.getMenuId());
                if(tree.getChildren() == null)
                {
                    List<Tree> myChildrens = new ArrayList<Tree>();
                    myChildrens.add(tree2);
                    tree.setChildren(myChildrens);
                }else
                {
                    tree.getChildren().add(tree2);
                }
                if(t.getChildrennum()!=0)
                {
                    aa(t, list, tree2);
                }
            }
        }
    }*/

    @Override
    public DemoMenu findSysMenuByID(DataMap paramMap)
    {
        return demoMenuMapper.findSysMenuByID(paramMap.getLong("menuId"));
    }

    @Override
    @Transactional
    public void addMenuInfo(DataMap paramMap)
    {
        //新增
        demoMenuMapper.insert(paramMap);
        
        Long parentId = paramMap.getLong("parentId");
        
        //查询父菜单子菜单数量
        int childrennum = demoMenuMapper.findSysMenuChildrennum(parentId);
        
        //更新子菜单数量
        demoMenuMapper.updateChildrennum(parentId, childrennum);
        
    }

    @Override
    public void updateMenuInfo(DataMap paramMap)
    {
        demoMenuMapper.update(paramMap);
    }

    @Override
    public void deleteMenuInfo(DataMap paramMap)
    {
        //判断是否是子节点
        Long menuId = paramMap.getLong("menuId");//获取菜单id
        
        /*SysMenu sysMenu = demoMenuMapper.findSysMenuByID(menuId);
        if(null == sysMenu)
        {
            throw new InvokeException(-100701, "删除失败,菜单不存在！");
        }*/
        //查询父菜单子菜单数量
        int childrennum = demoMenuMapper.findSysMenuChildrennum(menuId);
        if(childrennum != 0)
        {
            throw new InvokeException(-100701, "删除失败,父节点不能直接删除,请先删除子节点！");
        }
        demoMenuMapper.delete(menuId);
    }
    
}
