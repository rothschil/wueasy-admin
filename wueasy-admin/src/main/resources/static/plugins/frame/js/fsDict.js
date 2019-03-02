/*
 * fsLayui - A Front-end Rapid Development Framework.
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
/**
 * 字典配置
 * @author: fallsea
 * @version 2.2.0
 */
layui.fsDict = {
		userState : {//用户状态
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":0,"name":"未激活","style":"color:#c2c2c2;"},
				{"code":1,"name":"正常","style":"color:#5FB878;"},
				{"code":2,"name":"锁定","style":"color:#FFB800;"},
				{"code":3,"name":"注销","style":"color:#FF5722;"}
			]
		},
		userLoginSuccessType : {//用户登录成功类型
			formatType : "local",
			labelField : "name",
			valueField : "code",
			otherValue : "其他错误",//都不匹配后，展示的值
			data:[{"code":1,"name":"登录成功"},
				{"code":2,"name":"用户名不存在"},
				{"code":3,"name":"密码不正确"},
				{"code":4,"name":"用户已经锁定"},
				{"code":5,"name":"用户已经注销"},
				{"code":6,"name":"用户状态不正常"}
			]
		},
		userLoginType : {//用户登录类型
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":"1","name":"登录名"},
				{"code":"2","name":"手机号码"},
				{"code":"3","name":"邮箱"}
			]
		},
		jobType : {//作业类型
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":"simple","name":"简单作业"},
				{"code":"dataflow","name":"数据流作业"},
				{"code":"script","name":"脚本作业"}
			]
		},
		state : {//通用状态
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":"1","name":"有效"},
				{"code":"0","name":"无效","style":"color:#FF5722;"}
			]
		},
		publishState : {//发布状态
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":"1","name":"已发布"},
				{"code":"0","name":"未发布","style":"color:#FF5722;"}
			]
		},
		whether: {//是否
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":"1","name":"是"},
				{"code":"0","name":"否","style":"color:#FF5722;"}
			]
		},
		catalogFileType : {//栏目生成类型
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":"html","name":"html"},
				{"code":"shtml","name":"shtml"}
			]
		},
		success : {//通用状态
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":"1","name":"成功","css":"layui-badge layui-bg-green"},
				{"code":"0","name":"失败","css":"layui-badge"}
			]
		},
		jobState:{//任务运行状态
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[
				{"code":"TASK_STAGING","name":"等待运行","css":"layui-badge layui-bg-gray"},
				{"code":"TASK_FAILED","name":"运行失败","css":"layui-badge"},
				{"code":"TASK_FINISHED","name":"已完成","css":"layui-badge layui-bg-green"},
				{"code":"TASK_RUNNING","name":"运行中","css":"layui-badge layui-bg-blue"},
				{"code":"TASK_ERROR","name":"启动失败","css":"layui-badge layui-bg-orange"},
				{"code":"TASK_KILLED","name":"主动终止","css":"layui-badge layui-bg-cyan"}
			]
		},
		jobWdState:{//作业维度运行状态
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[
				{"code":"OK","name":"正常","css":"layui-badge layui-bg-green"},
				{"code":"DISABLED","name":"已失效","css":"layui-badge layui-bg-orange"},
				{"code":"SHARDING_FLAG","name":"分片待调整","css":"layui-badge layui-bg-blue"},
				{"code":"CRASHED","name":"已下线","css":"layui-badge layui-bg-gray"}
			]
		},
		jobShardingState:{//分片运行状态
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[
				{"code":"RUNNING","name":"运行中","css":"layui-badge layui-bg-green"},
				{"code":"DISABLED","name":"已失效","css":"layui-badge layui-bg-orange"},
				{"code":"SHARDING_FLAG","name":"分片待调整","css":"layui-badge layui-bg-blue"},
				{"code":"PENDING","name":"等待运行","css":"layui-badge layui-bg-gray"}
			]
		},
		encoding:{//编码
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[
				{"code":"UTF-8","name":"UTF-8"},
				{"code":"GBK","name":"GBK"}
			]
		},
		templatetype:{//模板类型
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[
				{"code":"1","name":"首页模板"},
				{"code":"2","name":"信息列表"},
				{"code":"3","name":"信息细览"},
				{"code":"4","name":"其它模板"}
			]
		},
		templateSite:{//站点
			formatType : "server",
			loadUrl : "/fsbus/D1002",
			labelField : "siteName",
			valueField : "siteId"
		},
		templateCatalog:{//栏目
			formatType : "server",
			loadUrl : "/fsbus/D1003",
			labelField : "catalogName",
			valueField : "catalogId"
		},
		authDataModel:{//数据权限模型
			formatType : "server",
			loadUrl : "/fsbus/D1001",
			labelField : "modelName",
			valueField : "modelId"
		}
};