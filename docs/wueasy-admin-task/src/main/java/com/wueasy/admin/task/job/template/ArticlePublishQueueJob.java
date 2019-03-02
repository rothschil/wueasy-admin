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
package com.wueasy.admin.task.job.template;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.wueasy.admin.template.publish.PublishCallBack;
import com.wueasy.base.util.ZKHelper;
import com.wueasy.cache.constants.CacheConstants;

/**
 * 文章发布队列
 * @author: fallsea
 * @version 1.0
 */
public class ArticlePublishQueueJob implements SimpleJob{

	@Override
	public void execute(ShardingContext shardingContext) {
		
		if(!ZKHelper.exists(CacheConstants.ARTICLE_PUBLISH_QUEUE_NODE_PATH)){
			ZKHelper.createPersistent(CacheConstants.ARTICLE_PUBLISH_QUEUE_NODE_PATH);
		}
		//查询文章发布队列
		List<String> list = ZKHelper.getChildren(CacheConstants.ARTICLE_PUBLISH_QUEUE_NODE_PATH);
		if(null!=list && !list.isEmpty()){
			
			ZkClient zkClient = ZKHelper.getInstance();
			
			
			for (String cmdStr : list) {
				String[] itemArray = cmdStr.split(":");
				if (itemArray.length == 2)
				{
					String flag = itemArray[0];
					Long id = Long.valueOf(itemArray[1]);
					
					switch (flag) {
						case "A": 
							//发布文章
							new PublishCallBack().publishArticle(id);
							break;
						case "CA":
							//发布栏目文章
							new PublishCallBack().publishArticleByCatalog(id);
							break;
						case "CAR":
							//发布栏目及子栏目文章
							new PublishCallBack().publishArticleByCatalogRecursion(id);
							break;
						default:
							break;
					}
				}
				
				//删除队列
				zkClient.delete(CacheConstants.ARTICLE_PUBLISH_QUEUE_NODE_PATH+"/"+cmdStr);
			}
		}
		
	}

}
