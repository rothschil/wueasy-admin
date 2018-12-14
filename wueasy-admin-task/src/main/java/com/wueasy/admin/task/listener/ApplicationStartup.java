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
package com.wueasy.admin.task.listener;

import java.util.List;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import com.wueasy.admin.entity.TemplatePublishPlan;
import com.wueasy.admin.task.job.template.TemplatePublishPlanJob;
import com.wueasy.admin.template.service.TemplateService;
import com.wueasy.base.util.SpringHelper;
import com.wueasy.base.util.ZKHelper;
import com.wueasy.cache.constants.CacheConstants;

/**
 * 启动成功后执行
 * @author: fallsea
 * @version 1.0
 */
@Component
@Order(1)
public class ApplicationStartup implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);
	
	
	@Override
	public void run(String... arg0) throws Exception {
		
		MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
        
		startTemplateTask();
		
		logger.warn("启动成功...");
		
	}
	
	
	/**
	 * 启动模板发布计划
	 * @author: fallsea
	 * @date: 2018年4月11日 下午10:40:42
	 */
	private void startTemplateTask(){
		//启动成功后，通过数据查询模板发布引擎计划，
		
		TemplateService service = SpringHelper.getBean("TemplatePublishService", TemplateService.class);
		
		List<TemplatePublishPlan> publishPlanList = service.queryPublishPlanList();
		
		if(null!=publishPlanList && !publishPlanList.isEmpty()){
			
			// 定义日志数据库事件溯源配置
			JobEventConfiguration jobEventRdbConfig = new JobEventRdbConfiguration(SpringHelper.getBean("taskDataSource", DruidDataSource.class));
			for (TemplatePublishPlan templatePublishPlan : publishPlanList) {
				new JobScheduler(createRegistryCenter(), createJobConfiguration(templatePublishPlan),jobEventRdbConfig).init();
				
			}
		}
		
		
		//启动数据监听
		
		ZkClient zkClient = ZKHelper.getInstance();
		zkClient.subscribeDataChanges(CacheConstants.TEMPLATE_TASK_NODE_PATH, new IZkDataListener() {
			
			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				
				
			}
			
			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				
				if(data instanceof TemplatePublishPlan){
					
					TemplatePublishPlan templatePublishPlan = (TemplatePublishPlan)data;
					
					new JobScheduler(createRegistryCenter(), createJobConfiguration(templatePublishPlan),new JobEventRdbConfiguration(SpringHelper.getBean("taskDataSource", DruidDataSource.class))).init();
					
				}
				
			}
		});
	} 
	
	
	
	/**
	 * 注册中心
	 * @author: fallsea
	 * @return
	 */
	private CoordinatorRegistryCenter createRegistryCenter() {
        return SpringHelper.getBean("regCenter", ZookeeperRegistryCenter.class);
    }

	/**
     * 创建任务
     * @author: fallsea
     * @param templatePublishPlan
     * @return
     */
    private LiteJobConfiguration createJobConfiguration(TemplatePublishPlan templatePublishPlan) {
        // 创建作业配置
    	// 定义作业核心配置
    	
    	String jobName = "wueasy-template-publis-"+templatePublishPlan.getId();
    	
    	String cron = templatePublishPlan.getCron();
    	
    	String jobParameter = "";
    	
    	if("1".equals(templatePublishPlan.getRecursion())){
    		jobParameter = "CR:"+templatePublishPlan.getCatalogId();
    	}else{
    		jobParameter = "C:"+templatePublishPlan.getCatalogId();
    	}
    	
    	String description = "模板发布计划[id:"+templatePublishPlan.getId()+"]";
    	
	    JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder(jobName,cron,1).jobParameter(jobParameter).failover(true).description(description).build();
	    
	    // 定义SIMPLE类型配置
	    SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, TemplatePublishPlanJob.class.getCanonicalName());
	    
	    // 定义Lite作业根配置
	    LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).overwrite(true).build();
	    
	    return simpleJobRootConfig;
    }

}
