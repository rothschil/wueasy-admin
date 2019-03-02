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
package com.wueasy.admin.template.bus.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.wueasy.admin.bus.service.AuthDataService;
import com.wueasy.admin.bus.service.JobManageService;
import com.wueasy.admin.entity.SysAuthDataModel;
import com.wueasy.admin.entity.SysAuthRoleData;
import com.wueasy.admin.entity.SysRole;
import com.wueasy.admin.mapper.SysAuthDataModelMapper;
import com.wueasy.admin.mapper.SysAuthRoleDataMapper;
import com.wueasy.admin.mapper.SysRoleMapper;
import com.wueasy.admin.pojo.Tree;
import com.wueasy.admin.template.bus.service.TemplateService;
import com.wueasy.admin.template.entity.Template;
import com.wueasy.admin.template.entity.TemplateCatalog;
import com.wueasy.admin.template.entity.TemplateSite;
import com.wueasy.admin.template.entity.TemplateVar;
import com.wueasy.admin.template.mapper.TemplateCatalogMapper;
import com.wueasy.admin.template.mapper.TemplateMapper;
import com.wueasy.admin.template.mapper.TemplatePublishPlanMapper;
import com.wueasy.admin.template.mapper.TemplateSiteMapper;
import com.wueasy.admin.template.mapper.TemplateVarMapper;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.exception.InvokeException;
import com.wueasy.base.util.SpringHelper;
import com.wueasy.base.util.StringHelper;

/**
 * 
 * @author: fallsea
 * @version 1.0
 */
@Service("TemplateService")
public class TemplateServiceImpl implements TemplateService,AuthDataService {

	private static Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);
	
	@Autowired
	private TemplateSiteMapper templateSiteMapper;
	
	
	@Autowired
	private TemplateVarMapper templateVarMapper;
	
	@Autowired
	private TemplateCatalogMapper templateCatalogMapper;
	
	@Autowired
	private TemplateMapper templateMapper;
	
	@Autowired
	private TemplatePublishPlanMapper templatePublishPlanMapper;
	
	@Autowired
	private SysAuthRoleDataMapper sysAuthRoleDataMapper;
	
	@Autowired
	private SysAuthDataModelMapper sysAuthDataModelMapper;
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	
	@Override
	public void insertSite(DataMap paramMap) {
		
		String siteNo = paramMap.getString("siteNo");
		TemplateSite templateSite = templateSiteMapper.selectBySiteNo(siteNo);
		
		if(null!=templateSite)
        {
            throw new InvokeException(-100201, "站点编号已存在!");
        }
		templateSiteMapper.insert(paramMap);
	}


	@Override
	public void updateSite(DataMap paramMap) {
		String siteNo = paramMap.getString("siteNo");
		Long siteId = paramMap.getLong("siteId");
		TemplateSite templateSite = templateSiteMapper.selectBySiteNo(siteNo);
		if(null!=templateSite && !siteId.equals(templateSite.getSiteId())){
			throw new InvokeException(-100301,"站点编号已存在!");
		}
		templateSiteMapper.update(paramMap);
	}


	@Override
	public void insertVar(DataMap paramMap) {
		Long siteId = paramMap.getLong("siteId");
		String itemKey = paramMap.getString("itemKey");
		TemplateVar templateVar = templateVarMapper.selectByItemKey(siteId, itemKey);
		if(null!=templateVar)
        {
            throw new InvokeException(-100801, "模板变量属性已存在!");
        }
		templateVarMapper.insert(paramMap);
	}


	@Override
	public void updateVar(DataMap paramMap) {
		Long id = paramMap.getLong("id");
		String itemKey = paramMap.getString("itemKey");
		Long siteId = paramMap.getLong("siteId");
		TemplateVar templateVar = templateVarMapper.selectByItemKey(siteId, itemKey);
		if(null!=templateVar && !id.equals(templateVar.getId())){
			throw new InvokeException(-100901,"模板变量属性已存在!");
		}
		templateVarMapper.update(paramMap);
	}


	@Override
	public void insertCatalog(DataMap paramMap) {
		
		Long parentId = paramMap.getLong("parentId");
		List<String> siteIdList = templateCatalogMapper.selectBySiteId(parentId);
		if(null==siteIdList || siteIdList.isEmpty()){
			throw new InvokeException(-101301,"父栏目不存在!");
		}
		String siteId = siteIdList.get(0);
		
		paramMap.set("siteId", siteId);
		
		
		String catalogNo = paramMap.getString("catalogNo");
		
		TemplateCatalog templateCatalog = templateCatalogMapper.selectByNo(siteId, catalogNo);
		
		if(null!=templateCatalog){
			throw new InvokeException(-101302,"栏目编号已存在!");
		}
		
		templateCatalogMapper.insert(paramMap);
		
		templateCatalogMapper.updateRoute(paramMap.getLong("catalogId"), getCatalogRoute("",paramMap.getLong("catalogId")));
	}
	
	
	


	@Override
	public void updateCatalog(DataMap paramMap) {
		
		Long catalogId = paramMap.getLong("catalogId");
		//查询栏目信息是否存在
		TemplateCatalog templateCatalog = templateCatalogMapper.select(catalogId);
		if(null==templateCatalog){
			throw new InvokeException(-101401,"栏目信息不存在!");
		}
		
		
		//查询栏目编号是否存在
		String catalogNo = paramMap.getString("catalogNo");
		
		TemplateCatalog templateCatalog2 = templateCatalogMapper.selectByNo(templateCatalog.getSiteId().toString(), catalogNo);
		
		if(null!=templateCatalog2 && !catalogId.equals(templateCatalog2.getCatalogId())){
			throw new InvokeException(-101402,"栏目编号已存在!");
		}
		
		paramMap.set("route", getCatalogRoute("", catalogId));
		
		templateCatalogMapper.update(paramMap);
	}


	@Override
	public void deleteCatalog(DataMap paramMap) {
		// 删除前判断是否是子栏目，只有叶子节点可以删除
		
		Long catalogId = paramMap.getLong("catalogId");
		int count = templateCatalogMapper.queryChildCatalogCount(catalogId);
		if(count>0){
			throw new InvokeException(-101601,"当前栏目存在子栏目，不能删除!");
		}
		
		templateCatalogMapper.delete(paramMap);
	}

	
	/**
	 * 获取栏目路由信息
	 * @author: fallsea
	 * @param siteId 站点id
	 * @return
	 */
	private String getCatalogRoute(String route,Long catalogId){
		//查询全部站点下数据，计算路由信息
		TemplateCatalog templateCatalog = templateCatalogMapper.select(catalogId);
		if(null!=templateCatalog){
			if(StringHelper.isEmpty(route)){
				route = catalogId+"";
			}else{
				route =  catalogId + "|" + route;
			}
			return getCatalogRoute(route, templateCatalog.getParentId());
		}
		return route;
	}

	private String getCatalogRouteByLocal(String route,Long catalogId,List<TemplateCatalog> catalogList){
		//查询全部站点下数据，计算路由信息
		TemplateCatalog templateCatalog = null;
		
		for (TemplateCatalog catalog : catalogList) {
			if(catalog.getCatalogId() == catalogId){
				templateCatalog = catalog;
				break;
			}
		}
		
		if(null!=templateCatalog){
			if(StringHelper.isEmpty(route)){
				route = catalogId+"";
			}else{
				route =  catalogId + "|" + route;
			}
			return getCatalogRoute(route, templateCatalog.getParentId());
		}
		return route;
	}

	@Override
	@Transactional
	public void updateCatalogOrderline(DataMap paramMap) {
		
		String catalogListStr = paramMap.getString("catalogList");//获取菜单数据
		try {
			catalogListStr = URLDecoder.decode(catalogListStr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("",e);
		}
		
		List<TemplateCatalog> list = JSON.parseArray(catalogListStr, TemplateCatalog.class);
		if(null!=list && !list.isEmpty()){
			for (TemplateCatalog catalog : list) {
				
				//需要重新计算路由信息
				String route = getCatalogRouteByLocal("", catalog.getCatalogId(), list);
				templateCatalogMapper.updateOrderline(catalog.getCatalogId(), catalog.getParentId(), catalog.getOrderline(),route);
			}
		}
	}


	@Override
	public void addTemplate(DataMap paramMap) {
		// 新增前，查询同类型是否只有一个（首页模板，列表模板，细缆模板）
		Long catalogId = paramMap.getLong("catalogId");
		String type = paramMap.getString("type");
		if("1".equals(type) || "2".equals(type) || "3".equals(type)){
			int count = templateMapper.queryCatalogTypeValidCount(catalogId, type);
			if(count>0){
				throw new InvokeException(-102101,"栏目类型已存在有效记录!"); 
			}
		}
		templateMapper.insert(paramMap);
	}


	@Override
	public void updateTemplate(DataMap paramMap) {
		
		Long id = paramMap.getLong("id");
		
		Long catalogId = paramMap.getLong("catalogId");
		String type = paramMap.getString("type");
		
		if("1".equals(type) || "2".equals(type) || "3".equals(type)){
			
			Template template = templateMapper.selectValidByCatalogType(catalogId, type);
			
			if(null!=template && !id.equals(template.getId())){
				throw new InvokeException(-102201,"栏目类型已存在有效记录!");
			}
			
		}
		templateMapper.update(paramMap);
		
	}


	@Override
	public void addPublishPlan(DataMap paramMap) {
		
		templatePublishPlanMapper.insert(paramMap);
	}


	@Override
	public void updatePublishPlan(DataMap paramMap) {
		
		templatePublishPlanMapper.update(paramMap);
		
		//修改定时任务引擎信息
		
		JobManageService jobManageService = SpringHelper.getBean("JobManageService", JobManageService.class);
		
		String jobName = "wueasy-template-publis-"+paramMap.getLong("id");
		
		jobManageService.updateJobConfig(jobName, paramMap.getString("cron"));
	}


	@Override
	public void deletePublishPlan(DataMap paramMap) {
		
		templatePublishPlanMapper.delete(paramMap);
		
		//删除定时任务引擎信息，删除前，先停止引擎
		
		JobManageService jobManageService = SpringHelper.getBean("JobManageService", JobManageService.class);
		String jobName = "wueasy-template-publis-"+paramMap.getLong("id");
		
		//终止作业
		jobManageService.shutdownJob(jobName);
		
		//删除作业
		jobManageService.removeJobConfig(jobName);
		
	}


	


	@Override
	public List<Tree> findList(DataMap paramMap) {
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
		
		List<TemplateCatalog> catalogList = null;
		
		if(!authRoleId.equals(0L)){
			
			//查询有权限的
			Long modelId = paramMap.getLong("modelId");
			SysAuthRoleData sysAuthRoleData = sysAuthRoleDataMapper.selectBymodelIdAndRoleId(modelId, authRoleId);
			
			if(null!=sysAuthRoleData){
				
				if(StringHelper.isNotEmpty(sysAuthRoleData.getAuthDataIds())){
					catalogList = templateCatalogMapper.queryValid(null,sysAuthRoleData.getAuthDataIds());
				}
			}
			
		}else{
			catalogList = templateCatalogMapper.queryValid(null);
		}
		
		if(null!=catalogList && !catalogList.isEmpty()){
			List<Tree> list = new ArrayList<Tree>();
			for (TemplateCatalog templateCatalog : catalogList) {
				Tree tree = new Tree();
				tree.setId(templateCatalog.getCatalogId().toString());
				tree.setParentId(templateCatalog.getParentId().toString());
				tree.setName(templateCatalog.getCatalogName());
				list.add(tree);
			}
			return list;
		}
		return null;
	}


	@Override
	public void deleteSite(DataMap paramMap) {
		templateSiteMapper.delete(paramMap);
	}


	@Override
	public void deleteVar(DataMap paramMap) {
		templateVarMapper.delete(paramMap);
	}


	@Override
	public TemplateCatalog selectTemplateCatalog(Long catalogId) {
		return templateCatalogMapper.select(catalogId);
	}


	@Override
	public Map<String, SysAuthDataModel> queryAuthDataModel(DataMap paramMap) {
		List<SysAuthDataModel> list = sysAuthDataModelMapper.queryListAll();
		//组装数据权限功能号字段集合
		Map<String, SysAuthDataModel> map = new HashMap<String, SysAuthDataModel>();
		if(null!=list && !list.isEmpty()){
			for (SysAuthDataModel sysAuthDataModel : list) {
				
				String funcNos = sysAuthDataModel.getFuncNos();
				if(StringHelper.isNotEmpty(funcNos)){
					String[] funcs= funcNos.split(",");
					for (int i = 0; i < funcs.length; i++) {
						String funcNo = funcs[i];
						if(StringHelper.isNotEmpty(funcNo)){
							SysAuthDataModel dataModel = new SysAuthDataModel();
							dataModel.setField(sysAuthDataModel.getField());
							dataModel.setModelId(sysAuthDataModel.getModelId());
							if(!funcNo.startsWith("/")){
								funcNo = "/fsbus/"+funcNo;
							}
							map.put(funcNo, dataModel);
						}
					}
				}
			}
		}
		return map;
	}
	
}
