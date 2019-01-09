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
package com.wueasy.admin.web.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.wueasy.admin.entity.SysGroup;
import com.wueasy.admin.entity.SysMenu;
import com.wueasy.admin.entity.SysMenuFunction;
import com.wueasy.admin.entity.SysUser;
import com.wueasy.base.bus.client.Client;
import com.wueasy.base.bus.plugin.sysparameter.filter.SysParameterContext;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.entity.Result;
import com.wueasy.base.exception.InvokeException;
import com.wueasy.base.util.RSAHelper;
import com.wueasy.base.util.StringHelper;
import com.wueasy.base.web.security.pojo.Group;
import com.wueasy.base.web.security.pojo.Menu;
import com.wueasy.base.web.security.pojo.Session;
import com.wueasy.base.web.security.pojo.User;
import com.wueasy.base.web.security.service.LoginService;
import com.wueasy.base.web.util.SysUtil;

/**
 * 登录接口
 * @author: fallsea
 * @version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService
{
	
	@Override
    public Session login(HttpServletRequest request, HttpServletResponse response)
    {
        String loginNo = request.getParameter("loginNo");//用户名
        String password = request.getParameter("password");//密码
        
        
        if(StringHelper.isBlank(password)){
        	throw new InvokeException(-2,"密码不能为空!");
        }
        password = RSAHelper.decrypt(password);
        
        Client client = new Client();
        DataMap paramMap = new DataMap();
        paramMap.put("loginNo", loginNo);
        paramMap.put("password", password);
        Result result = client.invoke("S1001", paramMap);
        if( 0 != result.getErrorNo() )//登录失败
        {
        	throw new InvokeException(result.getErrorNo(),result.getErrorInfo());
        }
        
        SysUser sysUser = (SysUser) result.getResult();
        
        
        Session session = new Session();
        
        User user = new User();
        user.setDay(sysUser.getDay());
        user.setEmail(sysUser.getEmail());
        user.setHeadImage(sysUser.getHeadImage());
        user.setIsSystem(sysUser.getIsSystem());
        user.setLoginNo(sysUser.getLoginNo());
        user.setPhone(sysUser.getPhone());
        user.setState(sysUser.getState());
        user.setType(sysUser.getType());
        user.setUserId(sysUser.getUserId());
        user.setUsername(sysUser.getUsername());
		session.setUser(user);
        
        
        if(result.getErrorNo() == 0){
        	
        	//查询权限
        	paramMap = new DataMap();
        	paramMap.set("userId", user.getUserId());
        	Result result3 = new Client().invoke("S1022",paramMap);
        	if(result3.getErrorNo() == 0){
        		
        		//处理权限session
        		List<SysMenu> sysMenuList = (List<SysMenu>) result3.getResult();
        		
        		List<SysMenuFunction> menuFunctionList = (List<SysMenuFunction>) result3.getResult("menuFunction");//菜单按钮
        		
        		List<Menu> menuList  = null;
        		
        		Set<String> linkUrlSet = new HashSet<String>();
        		Set<String> linkUrlSetAll = new HashSet<String>();
        		if(null!=sysMenuList && !sysMenuList.isEmpty()){
        			
        			menuList = new ArrayList<>();
        			for (SysMenu sysMenu : sysMenuList) {
        				Menu menu = new Menu();
        				menu.setFontIcon(sysMenu.getFontIcon());
        				menu.setGroupId(sysMenu.getGroupId());
        				menu.setLinkUrl(sysMenu.getLinkUrl());
        				menu.setMenuId(sysMenu.getMenuId());
        				menu.setMenuName(sysMenu.getMenuName());
        				menu.setParentId(sysMenu.getParentId());
        				menuList.add(menu);
        				
        				String linkUrl = sysMenu.getLinkUrl();
        				if(StringHelper.isNotBlank(linkUrl)){
        					linkUrlSet.add(linkUrl);
        				}
    				}
        		}
        		
        		Set<String> funcNoSet = new HashSet<String>();
        		if(null!=menuFunctionList && !menuFunctionList.isEmpty()){
        			for (SysMenuFunction sysMenuFunction : menuFunctionList) {
        				String linkUrl = sysMenuFunction.getLinkUrl();
        				if(StringHelper.isNotBlank(linkUrl)){
        					linkUrlSet.add(linkUrl);
        				}
        				String funcs = sysMenuFunction.getFuncs();
        				if(StringHelper.isNotBlank(funcs)){
        					List<String> list = Splitter.on(",").omitEmptyStrings().splitToList(funcs);
        					for (String funcNo : list) {
        						if(funcNo.startsWith("/")){
        							linkUrlSetAll.add(funcNo);//直接是url地址
        						}else{
        							linkUrlSetAll.add("/fsbus/" + funcNo);
        						}
    						}
        					funcNoSet.addAll(list);
        				}
    				}
        		}
        		linkUrlSetAll.addAll(linkUrlSet);
        		
        		
        		List<SysGroup> sysGroupList = (List<SysGroup>)result3.getResult("groupList");
        		
        		List<Group> groupList = null;
        		if(null!=sysGroupList && !sysGroupList.isEmpty()){
        			groupList = new ArrayList<>();
        			for (SysGroup sysGroup : sysGroupList) {
        				Group group = new Group();
        				group.setFontIcon(sysGroup.getFontIcon());
        				group.setGroupId(sysGroup.getGroupId());
        				group.setGroupName(sysGroup.getGroupName());
        				groupList.add(group);
        			}
        		}
        		
        		//更新缓存
        		session.setMenuList(menuList);
        		session.setUrlAuth(linkUrlSetAll);
        		session.setGroupList(groupList);
        		session.setAuthRoleDataMap((Map<Long, Set<String>>)result3.getResult("authRoleData"));
        	}
        }
        
        return session;
    }
    
    
}
