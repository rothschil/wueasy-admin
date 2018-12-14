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

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.wueasy.admin.template.publish.PublishCallBack;
import com.wueasy.base.util.StringHelper;

/**
 * 模板发布任务
 * @author: fallsea
 * @version 1.0
 */
public class TemplatePublishPlanJob implements SimpleJob{

	@Override
	public void execute(ShardingContext shardingContext) {
		String cmdStr = shardingContext.getJobParameter();
		if (StringHelper.isNotEmpty(cmdStr))
		{
			String[] itemArray = cmdStr.split(":");
			if (itemArray.length == 2)
			{
				String flag = itemArray[0];
				Long id = Long.valueOf(itemArray[1]);
				
				switch (flag) {
					case "T": 
						//发布模板
						new PublishCallBack().publishTemplate(id);
						break;
					case "C":
						//发布栏目
						new PublishCallBack().publishCatalog(id);
						break;
					case "CR":
						//发布栏目及子栏目
						new PublishCallBack().publishCatalogRecursion(id);
						break;
					default:
						break;
				}
			}
		}
		
	}

	
}
