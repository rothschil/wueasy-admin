package com.wueasy.demo.entity;

import java.io.Serializable;

/**
 * @Description: ztree树对象
 * @Copyright: 2017 www.fallsea.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.0
 * @date: 2017年11月3日 下午9:45:37
 */
public class Ztree implements Serializable
{
	
    private static final long serialVersionUID = 3369302725363170689L;
    
    /**
     * 节点名称
     */
    private String name;
    
    /**
     * 节点id
     */
    private Long id;
    
   
    private Long pId;
    
    private boolean open;
    
    
    private boolean isParent;
    

	public Ztree(String name, Long id, Long pId, boolean open) {
		this.name = name;
		this.id = id;
		this.pId = pId;
		this.open = open;
	}
	
	public Ztree(String name, Long id, Long pId) {
		this.name = name;
		this.id = id;
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
    
}
