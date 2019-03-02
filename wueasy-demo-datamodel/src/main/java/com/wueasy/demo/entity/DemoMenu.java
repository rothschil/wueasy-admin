package com.wueasy.demo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 系统菜单
 * @Copyright: 2017 www.fallsea.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.0
 * @date: 2017年10月22日 下午9:17:07
 */
public class DemoMenu implements Serializable
{
    
    private static final long serialVersionUID = 1L;

    private Long menuId;

    private String menuName;

    private String description;

    private String state;

    private Long parentId;

    private String linkUrl;

    private String logoUrl;

    private String route;
    
    private Long childrennum;

    private Long orderline;

    private Long createdBy;

    private String createdTime;

    private Long modifiedBy;

    private String modifiedTime;
    
    private List<DemoMenu> childrens;

    public Long getMenuId()
    {
        return menuId;
    }

    public void setMenuId(Long menuId)
    {
        this.menuId = menuId;
    }

    public String getMenuName()
    {
        return menuName;
    }

    public void setMenuName(String menuName)
    {
        this.menuName = menuName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getLinkUrl()
    {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl)
    {
        this.linkUrl = linkUrl;
    }

    public String getLogoUrl()
    {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl)
    {
        this.logoUrl = logoUrl;
    }

    public String getRoute()
    {
        return route;
    }

    public void setRoute(String route)
    {
        this.route = route;
    }

    public Long getChildrennum()
    {
        return childrennum;
    }

    public void setChildrennum(Long childrennum)
    {
        this.childrennum = childrennum;
    }

    public Long getOrderline()
    {
        return orderline;
    }

    public void setOrderline(Long orderline)
    {
        this.orderline = orderline;
    }

    public Long getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy)
    {
        this.createdBy = createdBy;
    }

    public String getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime(String createdTime)
    {
        this.createdTime = createdTime;
    }

    public Long getModifiedBy()
    {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy)
    {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedTime()
    {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime)
    {
        this.modifiedTime = modifiedTime;
    }

    public List<DemoMenu> getChildrens()
    {
        return childrens;
    }

    public void setChildrens(List<DemoMenu> childrens)
    {
        this.childrens = childrens;
    }

    
}