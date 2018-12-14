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
layui.use(['form','layer','jquery',/*'carousel',*/'fsCommon','fsConfig'], function () {
    var form = layui.form,
    	fsCommon = layui.fsCommon,
    	fsConfig = layui.fsConfig,
    	statusName = $.result(fsConfig,"global.result.statusName","errorNo"),
      msgName = $.result(fsConfig,"global.result.msgName","errorInfo"),
      dataName = $.result(fsConfig,"global.result.dataName","results.data"),
      servletUrl = $.result(fsConfig,"global.servletUrl");
    

    /**重新生成验证码*/
    function reqCaptcha() {
    	if($("#captchaType").val()=="1"){
    		var url = "/captcha-image?r=" + Math.random();
    		if(!$.isEmpty(servletUrl)){
    			url = servletUrl + url;
    		}
    		$('#captcha-image').attr("src",url);
    		$("#validateCode").val("");
    	}
    }
    /**点击验证码重新生成*/
    $('#captcha-image').on('click', function () {
        reqCaptcha();
    });
    
    reqCaptcha();

    /**监听登陆提交*/
    form.on('submit(login)', function (data) {
    	
    	
    	if($("#captchaType").val()=="2"){
    		
    		var captchaAppId = $("#captchaAppId").val();
    		
    		var captcha1 = new TencentCaptcha(captchaAppId, function(res) {
  	   		// res（未通过验证）= {ret: 1, ticket: null}
  		    // res（验证成功） = {ret: 0, ticket: "String", randstr: "String"}
  		    if(res.ret === 0){
  		    	data.field["aid"] = captchaAppId;
  		    	data.field["ticket"] = res.ticket;
  		    	data.field["randstr"] = res.randstr;
  		      login(data.field);
  		    }
  	   	});
  	   	captcha1.show(); // 显示验证码
    		
    	}else{
    		login(data.field);
    	}
    	
      return false;
    });
    
    
    function login(param){
    	
    	param["password"] = fsCommon.encryptRsa(param["password"]);
    	
    	fsCommon.invoke("/login",param,function(result)
  		{
    		if(result[statusName] == "0")
      	{
    			
    			var index_url = "/index";
    			if(!$.isEmpty(servletUrl)){
    				index_url = servletUrl + index_url;
          }
    			
    			var type = result["results"]["type"];
    			
    			if(!$.isEmpty(type)){
    				
    				if(type == "1"){
    					
    					fsCommon.updatePwd("[首次登录]修改密码",function(){
    						
    						top.window.location.reload();
    						
    					});
    					
    				}else if(type == "2"){
    					
    					fsCommon.updatePwd("[密码过期]修改密码",function(){
    						
    						top.window.location.reload();
    						
    					});
    					
    				}else if(type == "3"){//密码过期提醒
    					
    					var msg = "密码将于"+result["results"]["day"]+"天后过期，请尽快修改密码！";
    					top.layer.open({
    					  title: '温馨提示'
    					  ,content: msg
    					  ,end :function(){
    					  	top.window.location.href=index_url;
    					  }
    					});  
    					
    				}
    				
    			}else{
    				top.window.location.href=index_url;
    			}
    			
      	}
      	else
      	{
      		fsCommon.errorMsg(result[msgName]);
      		reqCaptcha();//刷新验证码
      	}
  		});
    }

});