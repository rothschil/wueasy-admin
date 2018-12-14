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
package com.wueasy.admin.bus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdang.ddframe.job.lite.lifecycle.api.JobAPIFactory;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobOperateAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobSettingsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobStatisticsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.ServerStatisticsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.ShardingOperateAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.ShardingStatisticsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.domain.JobBriefInfo;
import com.dangdang.ddframe.job.lite.lifecycle.domain.JobSettings;
import com.dangdang.ddframe.job.lite.lifecycle.domain.ServerBriefInfo;
import com.dangdang.ddframe.job.lite.lifecycle.domain.ShardingInfo;
import com.google.common.base.Optional;
import com.wueasy.admin.bus.config.JobConfig;
import com.wueasy.admin.bus.service.JobManageService;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.entity.Page;
import com.wueasy.base.util.PageHelper;
import com.wueasy.base.util.StringHelper;

/**
 * 
 * @author: fallsea
 * @version 1.0
 */
@Service("JobManageService")
public class JobManageServiceImpl implements JobManageService {
	
	@Autowired
	private JobConfig jobConfig;
	
	@Override
	public Page queryJobs(DataMap paramMap) {
		
		List<JobBriefInfo> list = (List<JobBriefInfo>) getJobStatisticsAPI().getAllJobsBriefInfo();
		
		List<DataMap> dList = null;
		if(null!=list && !list.isEmpty()){
			dList = new ArrayList<DataMap>();
			
			String jobName = paramMap.getString("jobName");
			String status = paramMap.getString("status");
			
			for (JobBriefInfo jobBriefInfo : list) {
				
				if(StringHelper.isNotBlank(jobName) && !jobName.equals(jobBriefInfo.getJobName())){
					continue;
				}
				
				if(StringHelper.isNotBlank(status) && !status.equals(jobBriefInfo.getStatus().name())){
					continue;
				}
				
				DataMap dataMap = new DataMap();
				dataMap.set("jobName", jobBriefInfo.getJobName());
				dataMap.set("status", jobBriefInfo.getStatus().name());
				dataMap.set("description", jobBriefInfo.getDescription());
				dataMap.set("cron", jobBriefInfo.getCron());
				dataMap.set("instanceCount", jobBriefInfo.getInstanceCount());
				dataMap.set("shardingTotalCount", jobBriefInfo.getShardingTotalCount());
				dList.add(dataMap);
			}
		}
		return new Page(PageHelper.getPageNum(paramMap), PageHelper.getPageSize(paramMap), dList);
	}



	@Override
	public List<DataMap> queryServers(DataMap paramMap) {
		List<ServerBriefInfo> list = (List<ServerBriefInfo>) getServerStatisticsAPI().getAllServersBriefInfo();
		List<DataMap> dList = null;
		if(null!=list && !list.isEmpty()){
			dList = new ArrayList<DataMap>();
			String serverIp = paramMap.getString("serverIp");
			for (ServerBriefInfo serverBriefInfo : list) {
				if(StringHelper.isNotBlank(serverIp) && !serverIp.equals(serverBriefInfo.getServerIp())){
					continue;
				}
				DataMap dataMap = new DataMap();
				dataMap.set("serverIp", serverBriefInfo.getServerIp());
				dataMap.set("instancesNum", serverBriefInfo.getInstancesNum());
				dataMap.set("jobsNum", serverBriefInfo.getJobsNum());
				dataMap.set("disabledJobsNum", serverBriefInfo.getDisabledJobsNum());
				dList.add(dataMap);
			}
		}
		return dList;
	}

	/**
	 * 创建作业状态展示API对象
	 * @author: fallsea
	 * @return
	 */
	private JobStatisticsAPI getJobStatisticsAPI(){
		return JobAPIFactory.createJobStatisticsAPI(jobConfig.getZkConnectString(), jobConfig.getNamespace(), Optional.fromNullable(jobConfig.getDigest()));
	}

	/**
	 * 创建作业服务器状态展示API对象
	 * @author: fallsea
	 * @return
	 */
	private ServerStatisticsAPI getServerStatisticsAPI(){
		return JobAPIFactory.createServerStatisticsAPI(jobConfig.getZkConnectString(), jobConfig.getNamespace(), Optional.fromNullable(jobConfig.getDigest()));
	}
	
	/**
	 * 创建作业配置API对象
	 * @author: fallsea
	 * @return
	 */
	private JobSettingsAPI getJobSettingsAPI(){
		return JobAPIFactory.createJobSettingsAPI(jobConfig.getZkConnectString(), jobConfig.getNamespace(), Optional.fromNullable(jobConfig.getDigest()));
	}
	
	private JobOperateAPI getJobOperatorAPI() {
        return JobAPIFactory.createJobOperateAPI(jobConfig.getZkConnectString(), jobConfig.getNamespace(), Optional.fromNullable(jobConfig.getDigest()));
    }

	
	private ShardingStatisticsAPI getShardingStatisticsAPI() {
        return JobAPIFactory.createShardingStatisticsAPI(jobConfig.getZkConnectString(), jobConfig.getNamespace(), Optional.fromNullable(jobConfig.getDigest()));
    }
	
	private ShardingOperateAPI getShardingOperateAPI() {
        return JobAPIFactory.createShardingOperateAPI(jobConfig.getZkConnectString(), jobConfig.getNamespace(), Optional.fromNullable(jobConfig.getDigest()));
    }


	@Override
	public DataMap queryJobConfig(DataMap paramMap) {
		
		String jobName = paramMap.getString("jobName");
		
		JobSettings jobSettings = getJobSettingsAPI().getJobSettings(jobName);
		
		DataMap dataMap = new DataMap();
		if(null!=jobSettings){
			dataMap.set("jobName", jobSettings.getJobName());
			dataMap.set("jobType", jobSettings.getJobType());
			dataMap.set("jobClass", jobSettings.getJobClass());
			dataMap.set("cron", jobSettings.getCron());
			dataMap.set("shardingTotalCount", jobSettings.getShardingTotalCount());
			dataMap.set("shardingItemParameters", jobSettings.getShardingItemParameters());
			dataMap.set("jobParameter", jobSettings.getJobParameter());
			dataMap.set("monitorExecution", jobSettings.isMonitorExecution());
			dataMap.set("streamingProcess", jobSettings.isStreamingProcess());
			dataMap.set("maxTimeDiffSeconds", jobSettings.getMaxTimeDiffSeconds());
			dataMap.set("monitorPort", jobSettings.getMonitorPort());
			dataMap.set("failover", jobSettings.isFailover());
			dataMap.set("misfire", jobSettings.isMisfire());
			dataMap.set("jobShardingStrategyClass", jobSettings.getJobShardingStrategyClass());
			dataMap.set("description", jobSettings.getDescription());
			dataMap.set("scriptCommandLine", jobSettings.getScriptCommandLine());
			dataMap.set("reconcileIntervalMinutes", jobSettings.getReconcileIntervalMinutes());
		}
		return dataMap;
	}



	@Override
	public void removeJobConfig(DataMap paramMap) {
		removeJobConfig(paramMap.getString("jobName"));
	}

	@Override
	public void removeJobConfig(String jobName) {
		getJobSettingsAPI().removeJobSettings(jobName);
	}

	@Override
	public void updateJobConfig(DataMap paramMap) {
		
		String jobName = paramMap.getString("jobName");
		
		//查询原始数据
		JobSettings jobSettings = getJobSettingsAPI().getJobSettings(jobName);
		
		if(null!=jobSettings){
			//替换
			jobSettings.setCron(paramMap.getString("cron"));
			jobSettings.setShardingTotalCount(paramMap.getInt("shardingTotalCount"));
			jobSettings.setJobParameter(paramMap.getString("jobParameter"));
			jobSettings.setMaxTimeDiffSeconds(paramMap.getInt("maxTimeDiffSeconds"));
			jobSettings.setMonitorPort(paramMap.getInt("monitorPort"));
			jobSettings.setReconcileIntervalMinutes(paramMap.getInt("reconcileIntervalMinutes"));
			jobSettings.setMonitorExecution(paramMap.getBoolean("monitorExecution"));
			jobSettings.setFailover(paramMap.getBoolean("failover"));
			jobSettings.setMisfire(paramMap.getBoolean("misfire"));
			jobSettings.setShardingItemParameters(paramMap.getString("shardingItemParameters"));
			jobSettings.setJobShardingStrategyClass(paramMap.getString("jobShardingStrategyClass"));
			jobSettings.setDescription(paramMap.getString("description"));
			
			//修改
			getJobSettingsAPI().updateJobSettings(jobSettings);
		}
		
	}

	@Override
	public void updateJobConfig(String jobName,String cron){
		
		//查询原始数据
		JobSettings jobSettings = getJobSettingsAPI().getJobSettings(jobName);
		
		if(null!=jobSettings){
			//替换
			jobSettings.setCron(cron);
			//修改
			getJobSettingsAPI().updateJobSettings(jobSettings);
		}
	}


	@Override
	public void triggerJob(DataMap paramMap) {
		getJobOperatorAPI().trigger(Optional.of(paramMap.getString("jobName")), Optional.<String>absent());
	}



	@Override
	public void disableJob(DataMap paramMap) {
		getJobOperatorAPI().disable(Optional.of(paramMap.getString("jobName")), Optional.<String>absent());
	}



	@Override
	public void enableJob(DataMap paramMap) {
		getJobOperatorAPI().enable(Optional.of(paramMap.getString("jobName")), Optional.<String>absent());
	}



	@Override
	public void shutdownJob(DataMap paramMap) {
		shutdownJob(paramMap.getString("jobName"));
	}

	@Override
	public void shutdownJob(String jobName) {
		getJobOperatorAPI().shutdown(Optional.of(jobName), Optional.<String>absent());
	}

	@Override
	public List<DataMap> getShardingInfo(DataMap paramMap) {
		List<ShardingInfo> list = (List<ShardingInfo>) getShardingStatisticsAPI().getShardingInfo(paramMap.getString("jobName"));
		
		List<DataMap> result = null;
		if(null!=list && !list.isEmpty()){
			result = new ArrayList<DataMap>();
			for (ShardingInfo shardingInfo : list) {
				
				DataMap dataMap = new DataMap();
				dataMap.set("item", shardingInfo.getItem());
				dataMap.set("serverIp", shardingInfo.getServerIp());
				dataMap.set("instanceId", shardingInfo.getInstanceId());
				dataMap.set("status", shardingInfo.getStatus().name());
				dataMap.set("failover", shardingInfo.isFailover());
				result.add(dataMap);
			}
		}
		return result;
	}



	@Override
	public void disableSharding(DataMap paramMap) {
		getShardingOperateAPI().disable(paramMap.getString("jobName"), paramMap.getString("item"));
	}



	@Override
	public void enableSharding(DataMap paramMap) {
		getShardingOperateAPI().enable(paramMap.getString("jobName"), paramMap.getString("item"));
	}



	@Override
	public void disableServer(DataMap paramMap) {
		getJobOperatorAPI().disable(Optional.<String>absent(), Optional.of(paramMap.getString("serverIp")));
	}



	@Override
	public void enableServer(DataMap paramMap) {
		getJobOperatorAPI().enable(Optional.<String>absent(), Optional.of(paramMap.getString("serverIp")));
	}



	@Override
	public void shutdownServer(DataMap paramMap) {
		getJobOperatorAPI().shutdown(Optional.<String>absent(), Optional.of(paramMap.getString("serverIp")));
	}



	@Override
	public void removeServer(DataMap paramMap) {
		getJobOperatorAPI().remove(Optional.<String>absent(), Optional.of(paramMap.getString("serverIp")));
	}



	@Override
	public List<DataMap> getServerJobs(DataMap paramMap) {
		List<JobBriefInfo> list = (List<JobBriefInfo>) getJobStatisticsAPI().getJobsBriefInfo(paramMap.getString("serverIp"));
		List<DataMap> result = null;
		if(null!=list && !list.isEmpty()){
			result = new ArrayList<DataMap>();
			for (JobBriefInfo jobBriefInfo : list) {
				DataMap dataMap = new DataMap();
				dataMap.set("jobName", jobBriefInfo.getJobName());
				dataMap.set("status", jobBriefInfo.getStatus().name());
				dataMap.set("description", jobBriefInfo.getDescription());
				dataMap.set("cron", jobBriefInfo.getCron());
				dataMap.set("instanceCount", jobBriefInfo.getInstanceCount());
				dataMap.set("shardingTotalCount", jobBriefInfo.getShardingTotalCount());
				result.add(dataMap);
			}
		}
		
		return result;
	}



	@Override
	public void disableServerJob(DataMap paramMap) {
		getJobOperatorAPI().disable(Optional.of(paramMap.getString("jobName")), Optional.of(paramMap.getString("serverIp")));
	}



	@Override
	public void enableServerJob(DataMap paramMap) {
		getJobOperatorAPI().enable(Optional.of(paramMap.getString("jobName")), Optional.of(paramMap.getString("serverIp")));
	}



	@Override
	public void shutdownServerJob(DataMap paramMap) {
		getJobOperatorAPI().shutdown(Optional.of(paramMap.getString("jobName")), Optional.of(paramMap.getString("serverIp")));
	}



	@Override
	public void removeServerJob(DataMap paramMap) {
		getJobOperatorAPI().remove(Optional.of(paramMap.getString("jobName")), Optional.of(paramMap.getString("serverIp")));
	}
}
