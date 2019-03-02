package com.wueasy.demo.entity;

import java.io.Serializable;

/**
 * @Description: 系统菜单功能按钮
 * @Copyright: 2017 www.fallsea.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.0
 * @date: 2017年10月22日 下午9:16:53
 */
public class DemoMenuFunction implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long menuId;

    private String name;

    private String linkUrl;

    private String funcs;

    private Long orderline;

    private Long createdBy;

    private String createdTime;

    private Long modifiedBy;

    private String modifiedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public String getFuncs() {
        return funcs;
    }

    public void setFuncs(String funcs) {
        this.funcs = funcs == null ? null : funcs.trim();
    }

    public Long getOrderline() {
        return orderline;
    }

    public void setOrderline(Long orderline) {
        this.orderline = orderline;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime == null ? null : createdTime.trim();
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime == null ? null : modifiedTime.trim();
    }
}