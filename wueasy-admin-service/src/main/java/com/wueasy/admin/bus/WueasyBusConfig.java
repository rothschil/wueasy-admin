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
package com.wueasy.admin.bus;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.wueasy.admin.bus.config.JobConfig;

/**
 * 基础配置中心
 * @author: fallsea
 * @version 1.0
 */
@Configuration
public class WueasyBusConfig {
	
	@Bean(name = "JobConfig")
    @ConfigurationProperties(prefix = "wueasy.job")
    public JobConfig JobConfig() {
		JobConfig config = new JobConfig();
        return config;
    }
	
	/**
	 * 自定义异步线程池
	 * @author: fallsea
	 * @return
	 */
    @Bean
    public AsyncTaskExecutor taskExecutor() {  
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor(); 
        executor.setThreadNamePrefix("wueasy-log-executor");
        executor.setMaxPoolSize(20);  

        // 设置拒绝策略
//        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
//            @Override
//            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//                // .....
//            }
//        });
        // 使用预定义的异常处理类
        // executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;  
    } 
}
