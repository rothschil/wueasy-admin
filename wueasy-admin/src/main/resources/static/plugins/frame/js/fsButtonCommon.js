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
 * 按钮拓展配置
 * @author: fallsea
 * @version 2.2.0
 */
layui.define(['fsConfig'], function (exports) {

	var fsConfig = layui.fsConfig,
	statusName = $.result(fsConfig,"global.result.statusName","errorNo"),
	msgName = $.result(fsConfig,"global.result.msgName","errorInfo"),
	dataName = $.result(fsConfig,"global.result.dataName","results.data"),
	successNo = $.result(fsConfig,"global.result.successNo","0"),
	FsButtonCommon = function (){
		
	};
	
	
	FsButtonCommon.prototype.saveCataLog = function(elem,data,datagrid,fsCommon){
		
		var treeId = elem.attr("treeId");
		
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		var nodes = treeObj.transformToArray(treeObj.getNodes());
		
		//重新获取排序信息
		var _funcNo = elem.attr("funcNo");
		var url = elem.attr("url");//请求url
    
    if($.isEmpty(_funcNo) && $.isEmpty(url)){
    	fsCommon.warnMsg("功能号或请求地址为空！");
      return;
    }
    if($.isEmpty(url)){
      url = "/fsbus/" + _funcNo;
    }
    
		var arr = new Array();
		
		var treeIdKey = $("#"+treeId).attr("treeIdKey");
		
		var rootIdColumn = elem.attr("rootIdColumn");
		
		$.each(nodes,function(index,item){
			if(item[treeIdKey]=="0"){
				return;
			}
			var obj = {};
			
			//根目录特殊处理
			var parentId = item["parentId"];
			if(parentId == "0"){
				parentId = $("#"+rootIdColumn).val();
			}
			
			obj[treeIdKey] = item[treeIdKey];
			obj["parentId"] = parentId;
			obj["orderline"] = index;
			arr.push(obj);
		});
		
		var paramColumn = elem.attr("paramColumn");
		
		var menuList = encodeURIComponent(JSON.stringify(arr));
		var param = {};
		param[paramColumn] = menuList;
		//请求数据
		fsCommon.invoke(url,param,function(data)
		{
    	if(data[statusName] == successNo)
    	{
    		fsCommon.setRefreshTable("1");
    		fsCommon.successMsg('操作成功!');
    		
    		//是否自动关闭，默认是
    		if(elem.attr("isClose") != "0"){
    			parent.layer.close(parent.layer.getFrameIndex(window.name));
    		}
    	}
    	else
    	{
    		//提示错误消息
    		fsCommon.errorMsg(data[msgName]);
    	}
		});
		
	}
	
	var fsButtonCommon = new FsButtonCommon();
	exports('fsButtonCommon', fsButtonCommon);
});