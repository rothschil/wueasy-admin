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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wueasy.base.entity.Result;
import com.wueasy.base.util.JsonHelper;
import com.wueasy.base.web.security.service.SessionService;
import com.wueasy.base.web.security.util.SessionUtil;
import com.wueasy.base.web.util.ResponseHelper;
import com.wueasy.base.web.util.SysUtil;

/**
 * 首页
 * @author: fallsea
 * @version 1.0
 */
@Controller
public class IndexController {
    
    /**
     * 默认页
     * @author: fallsea
     * @param response
     */
    @RequestMapping(value={"/"})
    public void index(HttpServletResponse response){
        ResponseHelper.sendRedirect("/index",response);
    }
    
    /**
     * 主页
     * @author: fallsea
     * @return
     */
    @RequestMapping(value={"/index"})
    public String index(){
        return "index";
    }
    
    /**
     * 首页
     * @author: fallsea
     * @return
     */
    @RequestMapping(value={"/home"})
    public String home(){
        return "home";
    }
    
    /**
     * 查询菜单列表
     * @author: fallsea
     * @param request
     * @return
     */
    @RequestMapping(value={"/menulist"})
    @ResponseBody
    public String menulist(HttpServletRequest request){
    	Result result = new Result();
    	SessionService sessionService = SessionUtil.getSessionService();
    	result.setResult(sessionService.getSession(SysUtil.getToken(request)).getMenuList());
        return JsonHelper.toJSONString(result);
    }
}