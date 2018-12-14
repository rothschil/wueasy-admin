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
package com.wueasy.admin.bus.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Splitter;
import com.wueasy.admin.bus.service.SysRoleService;
import com.wueasy.admin.entity.SysRole;
import com.wueasy.admin.entity.SysRolePermission;
import com.wueasy.admin.entity.SysRoleUser;
import com.wueasy.admin.mapper.SysAuthRoleDataMapper;
import com.wueasy.admin.mapper.SysMenuFunctionMapper;
import com.wueasy.admin.mapper.SysMenuMapper;
import com.wueasy.admin.mapper.SysRoleMapper;
import com.wueasy.admin.mapper.SysRolePermissionMapper;
import com.wueasy.admin.mapper.SysRoleUserMapper;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.entity.Page;
import com.wueasy.base.exception.InvokeException;
import com.wueasy.base.util.PageHelper;
import com.wueasy.base.util.StringHelper;

/**
 * 
 * @author: fallsea
 * @version 1.0
 */
@Service("SysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
    private SysRoleUserMapper sysRoleUserMapper;
	
	@Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;
	
	@Autowired
	private SysMenuFunctionMapper sysMenuFunctionMapper;
	
	@Autowired
    private SysMenuMapper sysMenuMapper;
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysAuthRoleDataMapper sysAuthRoleDataMapper;

	@Override
	@Transactional
	public void insertRoleUser(DataMap paramMap) {
		
		//新增前判断权限
		checkRoleAuth(paramMap);
		
		String  userIds = paramMap.getString("userId");
		
		List<String> userIdList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(userIds);
		
		for (String userId : userIdList) {
			paramMap.set("userId", userId);
			sysRoleUserMapper.insert(paramMap);
		}
	}
	
	/**
	 * 验证角色权限
	 * @author: fallsea
	 * @param paramMap
	 */
	@Override
	public void checkRoleAuth(DataMap paramMap){
		
		DataMap dataMap = new DataMap();
		dataMap.set("isSystem", paramMap.getString("isSystem"));
		dataMap.set("userId", paramMap.getLong("_userId"));
		
		if(!"1".equals(paramMap.getString("isSystem"))){
			List<SysRole> roleList = queryRoleList(dataMap);
			if(null==roleList || roleList.isEmpty()){
				throw new InvokeException(-102901, "无权限操作！");
			}
			
			boolean b = false;
			for (SysRole sysRole : roleList) {
				if(sysRole.getRoleId().equals(paramMap.getLong("roleId"))){
					b = true;
				}
			}
			if(!b){
				throw new InvokeException(-102902, "无权限操作！");
			}
		}
	}

	@Override
	public void insertOrUpdateRolePermission(DataMap paramMap) {
		
		checkRoleAuth(paramMap);
		
		Long roleId = paramMap.getLong("roleId");
		SysRolePermission sysRolePermission = sysRolePermissionMapper.selectByRoleId(roleId);
		
		String ids = paramMap.getString("ids");
		
		String menuIds = "";
		String menuFunctionIds = "";
		
		if(StringHelper.isNotBlank(ids)){
			
			List<String> idArr = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(ids);
			
			for (String id : idArr) {
				
				List<String> strs = Splitter.on("_").trimResults().omitEmptyStrings().splitToList(id);
				if(strs.size() == 1){//菜单权限
					
					if(StringHelper.isNotBlank(menuIds)){
						menuIds += ",";
					}
					
					menuIds += strs.get(0);
					
				}else if(strs.size() == 2){//按钮权限
					
					if(StringHelper.isNotBlank(menuFunctionIds)){
						menuFunctionIds += ",";
					}
					menuFunctionIds += strs.get(1);
				}
			}
		}
		paramMap.set("menuIds",menuIds);
		paramMap.set("menuFunctionIds",menuFunctionIds);
		paramMap.remove("ids");
		if(null == sysRolePermission){
			sysRolePermissionMapper.insert(paramMap);
		}else{
			paramMap.set("id", sysRolePermission.getId());
			sysRolePermissionMapper.update(paramMap);
		}
		
	}

	@Override
	public List<DataMap> queryRoleMenu(DataMap paramMap) {
		
		/**
		 * 实现流程：
		 * 1.判断用户是否超级管理员
		 * 2.获取当前角色的父角色的权限
		 */
		
		Long authRoleId = 0L;//权限父角色id
		
		if(StringHelper.isNotEmpty(paramMap.getString("roleId")) && !"0".equals(paramMap.getString("roleId"))){
			//查询当前角色的父角色id
			SysRole sysRole = sysRoleMapper.select(paramMap.getLong("roleId"));
			if(null!=sysRole){
				authRoleId = sysRole.getParentId();
			}
		}
		
		//菜单列表
		List<DataMap> menuList = null;
		//按钮列表
		List<DataMap> menuList2 = null;
		
		if(!authRoleId.equals(0L)){
			//查询父角色权限
			
			//查询用户有权限的信息
			SysRolePermission permission = sysRolePermissionMapper.selectByRoleId(authRoleId);
			
			if(null!=permission){
				String menuIds = permission.getMenuIds();
				String menuFunctionIds = permission.getMenuFunctionIds();
				
				if(StringHelper.isNotBlank(menuIds)){
					menuList = sysMenuMapper.queryMenuAll(menuIds);
				}
				
				if(StringHelper.isNotBlank(menuFunctionIds)){
					
					//查询按钮id
					menuList2 = sysMenuMapper.queryMenuAll2(menuFunctionIds);
				}				
			}
			
		}else{
			//查询菜单列表
			menuList = sysMenuMapper.queryMenuAll(paramMap);
			//查询按钮列表
			menuList2 = sysMenuMapper.queryMenuAll2(paramMap);
		}
		
		if(null!=menuList2 && !menuList2.isEmpty()){
			if(null==menuList){
				menuList = menuList2;
			}
			
			for (DataMap map : menuList2) {
				//组装id
				String id= map.getString("menu_id")+"_"+map.getString("id");
				map.set("id", id);
				map.set("icon", "/plugins/ztree/css/zTreeStyle/img/diy/5.png");//按钮的图标
				map.remove("menu_id");
				menuList.add(map);
			}
		}
		
		
		if(null!=menuList && !menuList.isEmpty()){
			
			Long roleId = paramMap.getLong("roleId");
			//查询用户有权限的信息
			SysRolePermission permission = sysRolePermissionMapper.selectByRoleId(roleId);
			
			
			if(null!=permission){
				String menuIds = permission.getMenuIds();
				String menuFunctionIds = permission.getMenuFunctionIds();
				
				List<String> menuIdList = null;
				
				List<String> menuFunctionIdList = null;
				
				if(StringHelper.isNotBlank(menuIds)){
					menuIdList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(menuIds);  
				}
				
				if(StringHelper.isNotBlank(menuFunctionIds)){
					
					//查询按钮id
					List<DataMap> list = sysMenuFunctionMapper.queryMenuFunctionId(menuFunctionIds);
					
					if(null!=list && !list.isEmpty()){
						menuFunctionIdList = new ArrayList<String>();
						for (DataMap dataMap : list) {
							menuFunctionIdList.add(dataMap.getString("menu_id")+"_"+dataMap.getString("id"));
						}
					}
				}
				
				if(null!=menuIdList || null!=menuFunctionIdList){
					for (DataMap map : menuList) {
						String id = map.getString("id");
						if(null!=menuIdList && menuIdList.contains(id)){
							map.put("checked", "true");
						}else if(null!=menuFunctionIdList && menuFunctionIdList.contains(id)){
							map.put("checked", "true");
						}
						
					}
				}
				
			}
			
		}
		
		return menuList;
	}

	@Override
	public List<SysRole> queryRoleList(DataMap paramMap) {
		/**
		 * 超级管理员查询全部列表
		 * 普通用户只能查询自己有权限的子列表
		 */
		
		if(!"1".equals(paramMap.getString("isSystem"))){
			
			//查询当前登录用户角色权限列表
			List<Long> userRoleList = sysRoleUserMapper.queryByUserId(paramMap.getLong("userId"));
			
			if(userRoleList!=null && !userRoleList.isEmpty()){
				//查询全部角色列表
				List<SysRole> roleList = sysRoleMapper.query(paramMap);
				
				//循环组装用户有权限的角色树列表
				return getUserRoleList(userRoleList, roleList);
			}
			
			return null;
			
		}
		List<SysRole> list = sysRoleMapper.query(paramMap);
		if(null==list){
			list = new ArrayList<SysRole>();
		}
		SysRole sysRole = new SysRole();
		sysRole.setRoleId(0L);
		sysRole.setRoleName("根目录");
		list.add(sysRole);
		return list;
	}
	
	/**
	 * 查询有权限的列表
	 * @author: fallsea
	 * @param userRoleList
	 * @param roleList
	 * @return
	 */
	private List<SysRole> getUserRoleList(List<Long> userRoleList,List<SysRole> roleList){
		/**
		 * 1.筛选出用户有权限的角色，所有根节点列表，
		 * 2.筛选出所有的子节点列表
		 */
		
		if(null!=roleList && !roleList.isEmpty()){
			Set<Long> set = new HashSet<Long>();
			for (Long roleId : userRoleList) {
				Set<Long> set2=getRoleChildrenList(roleList, roleId);
				if(null!=set2 && !set2.isEmpty()){
					set.addAll(set2);
				}
			}
			//只能操作有权限的子角色
			
//			set.addAll(userRoleList);
			
			if(null!=set && !set.isEmpty()){
				List<SysRole> list = new ArrayList<SysRole>();
				for (SysRole sysRole : roleList) {
					if(set.contains(sysRole.getRoleId())){
						list.add(sysRole);
					}
				}
				return list;
			}
		}
		return null;
	}
	
	
	private Set<Long> getRoleChildrenList(List<SysRole> roleList,Long roleId){
		
		if(null==roleId || roleId.equals(0L)){
			return null;
		}
		Set<Long> set = new HashSet<Long>();
		
		for (SysRole sysRole : roleList) {
			
			if(roleId.equals(sysRole.getParentId())){
				set.add(sysRole.getRoleId());
				//获取子节点
				Set<Long> set2 = getRoleChildrenList(roleList, sysRole.getRoleId());
				if(null!=set2 && !set2.isEmpty()){
					set.addAll(set2);
				}
			}
		}
		return set;
	}

	@Override
	public void insertRole(DataMap paramMap) {
		checkRoleAuth(paramMap);
		
		sysRoleMapper.insert(paramMap);
	}
	
	@Override
	public void updateRole(DataMap paramMap) {
		checkRoleAuth(paramMap);
		
		sysRoleMapper.update(paramMap);
	}
	
	@Override
	public SysRole selectRole(DataMap paramMap) {
		checkRoleAuth(paramMap);
		
		return sysRoleMapper.select(paramMap);
	}
	
	@Override
	@Transactional
	public void deleteRole(DataMap paramMap) {
		checkRoleAuth(paramMap);
		
		//角色下有用户不能删
		
		Long count = sysRoleUserMapper.queryRoleUserCount(paramMap.getLong("roleId"));
		if(count>0){
			throw new InvokeException(-102701,"删除角色失败，角色下存在用户！");
		}
		
		//删除前要先删除数据权限和菜单权限
		
		sysRolePermissionMapper.deleteByRoleId(paramMap.getLong("roleId"));
		
		sysAuthRoleDataMapper.deleteRole(paramMap.getLong("roleId"));
		
		sysRoleMapper.delete(paramMap);
	}
	
	@Override
	public Page queryRoleUserList(DataMap paramMap){
		checkRoleAuth(paramMap);
		
		PageHelper.startPage(PageHelper.getPageNum(paramMap), PageHelper.getPageSize(paramMap));
		List<SysRoleUser> list = sysRoleUserMapper.query(paramMap);
		return PageHelper.getPage(list);
	}

	@Override
	public void deleteRoleUser(DataMap paramMap) {
		
		checkRoleAuth(paramMap);
		sysRoleUserMapper.delete(paramMap);
		
	}
	
}
