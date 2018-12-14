/*
 * wueasy - A Java Distributed Rapid Development Platform.
 * Copyright (C) 2017-2019 wueasy.com

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.wueasy.admin.pojo;

import java.io.Serializable;

/**
 * 数据权限模型
 * @author: fallsea
 * @version 1.0
 */
public class SysAuthDataModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long modelId;
	
  	/**
	 * 模型编号
	 */
	private String modelNo;
	
  	/**
	 * 模型名称
	 */
	private String modelName;
	
  	/**
	 * service名称
	 */
	private String serviceName;
	
  	/**
	 * 那些功能号需要验证数据权限
	 */
	private String funcNos;
	
	
	private String field;
	
  
  
	public Long getModelId() {
		return modelId;
	}
	
	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}
  
  	/**
	 * 模型编号
	 */
	public String getModelNo() {
		return modelNo;
	}
	
  	/**
	 * 模型编号
	 */
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
  
  	/**
	 * 模型名称
	 */
	public String getModelName() {
		return modelName;
	}
	
  	/**
	 * 模型名称
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
  
  	/**
	 * service名称
	 */
	public String getServiceName() {
		return serviceName;
	}
	
  	/**
	 * service名称
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
  
  	/**
	 * 那些功能号需要验证数据权限
	 */
	public String getFuncNos() {
		return funcNos;
	}
	
  	/**
	 * 那些功能号需要验证数据权限
	 */
	public void setFuncNos(String funcNos) {
		this.funcNos = funcNos;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}