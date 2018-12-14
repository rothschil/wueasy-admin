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
package com.wueasy.admin.bus.service;

import java.util.List;

import com.wueasy.base.entity.DataMap;
import com.wueasy.base.entity.Page;

/**
 * 定时任务管理服务类
 * @author: fallsea
 * @version 1.0
 */
public interface JobManageService {

	
	/**
	 * 查询作业维度列表
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
	Page queryJobs(DataMap paramMap);
	
	/**
	 * 查询服务器维度列表
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
	List<DataMap> queryServers(DataMap paramMap);
	
	/**
	 * 获取作业配置
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
	DataMap queryJobConfig(DataMap paramMap);
	
	/**
	 * 删除作业配置
	 * @author: fallsea
	 * @param paramMap
	 */
	void removeJobConfig(DataMap paramMap);
	
	/**
	 * 删除作业配置
	 * @author: fallsea
	 * @param jobName
	 */
	void removeJobConfig(String jobName);
	
	/**
	 * 修改作业配置
	 * @author: fallsea
	 * @param paramMap
	 */
	void updateJobConfig(DataMap paramMap);
	
	/**
	 * 修改作业配置
	 * @author: fallsea
	 * @param jobName 作业名称
	 * @param cron cron表达式
	 */
	void updateJobConfig(String jobName,String cron);
	
	/**
	 * 触发作业
	 * @author: fallsea
	 * @param paramMap
	 */
	void triggerJob(DataMap paramMap);
	
	/**
	 * 禁用作业
	 * @author: fallsea
	 * @param paramMap
	 */
	void disableJob(DataMap paramMap);
	
	/**
	 * 启用作业
	 * @author: fallsea
	 * @param paramMap
	 */
	void enableJob(DataMap paramMap);
	
	/**
	 * 终止作业
	 * @author: fallsea
	 * @param paramMap
	 */
	void shutdownJob(DataMap paramMap);
	
	/**
	 * 终止作业
	 * @author: fallsea
	 * @param jobName
	 */
	void shutdownJob(String jobName);
	
	
	/**
	 * 获取分片列表
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
	List<DataMap> getShardingInfo(DataMap paramMap);
	
	/**
	 * 禁用分片
	 * @author: fallsea
	 * @param paramMap
	 */
	void disableSharding(DataMap paramMap);
	
	/**
	 * 启用分片
	 * @author: fallsea
	 * @param paramMap
	 */
	void enableSharding(DataMap paramMap);
	
	/**
	 * 禁用服务器作业
	 * @author: fallsea
	 * @param paramMap
	 */
	void disableServer(DataMap paramMap);
	
	/**
	 * 启用服务器作业
	 * @author: fallsea
	 * @param paramMap
	 */
	void enableServer(DataMap paramMap);
	
	/**
	 * 终止服务器作业
	 * @author: fallsea
	 * @param paramMap
	 */
	void shutdownServer(DataMap paramMap);
	
	/**
	 * 清理服务器作业
	 * @author: fallsea
	 * @param paramMap
	 */
	void removeServer(DataMap paramMap);
	
	
	/**
	 * 获取该服务器上注册的作业列表
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
	List<DataMap> getServerJobs(DataMap paramMap);
	
	
	/**
	 * 禁用服务器作业
	 * @author: fallsea
	 * @param paramMap
	 */
	void disableServerJob(DataMap paramMap);
	
	/**
	 * 启用服务器作业
	 * @author: fallsea
	 * @param paramMap
	 */
	void enableServerJob(DataMap paramMap);
	
	/**
	 * 终止服务器作业
	 * @author: fallsea
	 * @param paramMap
	 */
	void shutdownServerJob(DataMap paramMap);
	
	/**
	 * 清理服务器作业
	 * @author: fallsea
	 * @param paramMap
	 */
	void removeServerJob(DataMap paramMap);
}
