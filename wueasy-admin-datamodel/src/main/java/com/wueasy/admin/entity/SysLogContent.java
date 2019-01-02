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
package com.wueasy.admin.entity;

import java.io.Serializable;

public class SysLogContent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
  	/**
	 * 入参
	 */
	private String inParam;
	
  	/**
	 * 出参c
	 */
	private String outParam;
	
  	/**
	 * 系统信息
	 */
	private String systemInfo;
	
  
  
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
  
  	/**
	 * 入参
	 */
	public String getInParam() {
		return inParam;
	}
	
  	/**
	 * 入参
	 */
	public void setInParam(String inParam) {
		this.inParam = inParam;
	}
  
  	/**
	 * 出参c
	 */
	public String getOutParam() {
		return outParam;
	}
	
  	/**
	 * 出参c
	 */
	public void setOutParam(String outParam) {
		this.outParam = outParam;
	}
  
  	/**
	 * 系统信息
	 */
	public String getSystemInfo() {
		return systemInfo;
	}
	
  	/**
	 * 系统信息
	 */
	public void setSystemInfo(String systemInfo) {
		this.systemInfo = systemInfo;
	}

}