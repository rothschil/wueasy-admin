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
package com.wueasy.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wueasy.base.bus.client.Client;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.entity.Result;

/**
 * 预览
 * @author: fallsea
 * @version 1.0
 */
@Controller  
@RequestMapping("/preview")  
public class PreviewController {

	
	/**
	 * 预览文章
	 * @author: fallsea
	 * @return
	 */
	@RequestMapping(value={"/article"})
	@ResponseBody
    public String article(@RequestParam("articleId")Long articleId,@RequestParam("catalogId")Long catalogId){
		
		Client client = new Client("TaskClientService");
		DataMap paramMap = new DataMap();
		paramMap.put("articleId", articleId);
		paramMap.put("catalogId", catalogId);
		Result result = client.invoke("TASK1001", paramMap);
		if(result.getErrorNo()==0){
			return result.getResult().toString();
		}
        return result.getErrorInfo();
    }
	
	/**
	 * 预览模板
	 * @author: fallsea
	 * @param templateId
	 * @return
	 */
	@RequestMapping(value={"/template"})
	@ResponseBody
    public String template(@RequestParam("templateId")Long templateId){
		
		Client client = new Client("TaskClientService");
		DataMap paramMap = new DataMap();
		paramMap.put("templateId", templateId);
		Result result = client.invoke("TASK1002", paramMap);
		if(result.getErrorNo()==0){
			return result.getResult().toString();
		}
        return result.getErrorInfo();
    }
	
	/**
	 * 预览栏目
	 * @author: fallsea
	 * @param catalogId
	 * @return
	 */
	@RequestMapping(value={"/catalog"})
	@ResponseBody
    public String catalog(@RequestParam("catalogId")Long catalogId){
		
		Client client = new Client("TaskClientService");
		DataMap paramMap = new DataMap();
		paramMap.put("catalogId", catalogId);
		Result result = client.invoke("TASK1003", paramMap);
		if(result.getErrorNo()==0){
			return result.getResult().toString();
		}
        return result.getErrorInfo();
    }
}
