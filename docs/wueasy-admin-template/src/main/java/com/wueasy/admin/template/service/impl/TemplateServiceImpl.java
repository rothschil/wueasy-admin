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
package com.wueasy.admin.template.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wueasy.admin.entity.SysArticle;
import com.wueasy.admin.entity.Template;
import com.wueasy.admin.entity.TemplateCatalog;
import com.wueasy.admin.entity.TemplatePublishPlan;
import com.wueasy.admin.entity.TemplateSite;
import com.wueasy.admin.entity.TemplateVar;
import com.wueasy.admin.mapper.SysArticleMapper;
import com.wueasy.admin.mapper.TemplateCatalogMapper;
import com.wueasy.admin.mapper.TemplateMapper;
import com.wueasy.admin.mapper.TemplatePublishPlanMapper;
import com.wueasy.admin.mapper.TemplateSiteMapper;
import com.wueasy.admin.mapper.TemplateVarMapper;
import com.wueasy.admin.template.TemplatePreview;
import com.wueasy.admin.template.service.TemplateService;
import com.wueasy.base.entity.DataMap;

/**
 * 模板发布服务
 * @author: fallsea
 * @version 1.0
 */
@Service("TemplatePublishService")
public class TemplateServiceImpl implements TemplateService {
	
	@Autowired
	private TemplateMapper templateMapper;
	
	@Autowired
	private TemplateSiteMapper templateSiteMapper;
	
	@Autowired
	private TemplateVarMapper templateVarMapper;
	
	@Autowired
	private TemplateCatalogMapper templateCatalogMapper;
	
	@Autowired
	private TemplatePublishPlanMapper templatePublishPlanMapper;
	
	@Autowired
	private SysArticleMapper sysArticleMapper;
	

	@Override
	public Template queryTemplateById(Long templateId) {
		return templateMapper.select(templateId);
	}

	@Override
	public List<TemplateSite> querySiteList() {
		return templateSiteMapper.queryList();
	}

	@Override
	public List<TemplateVar> queryVarList() {
		return templateVarMapper.queryList();
	}

	@Override
	public List<TemplateCatalog> queryCatalogList() {
		return templateCatalogMapper.queryCatalogList();
	}

	@Override
	public Template queryTemplateByCatalogIdAndType(Long catalogId, String type) {
		
		return templateMapper.queryTemplateByCatalogIdAndType(catalogId, type);
	}

	@Override
	public List<Template> queryTemplateListByCatalogIdAndType(Long catalogId, String type) {
		
		return templateMapper.queryTemplateListByCatalogIdAndType(catalogId, type);
	}

	@Override
	public List<TemplatePublishPlan> queryPublishPlanList() {
		return templatePublishPlanMapper.queryList();
	}

	@Override
	public String previewTemplate(DataMap paramMap) {
		Long templateId = paramMap.getLong("templateId");
		return new TemplatePreview(templateId).parseTemplate();
	}

	@Override
	public String previewCatalog(DataMap paramMap) {
		Long catalogId = paramMap.getLong("catalogId");
		return new TemplatePreview(catalogId,0L).parseTemplate();
	}

	@Override
	public String previewArticle(DataMap paramMap) {
		Long articleId = paramMap.getLong("articleId");
		Long catalogId = paramMap.getLong("catalogId");
		
		SysArticle sysArticle = sysArticleMapper.select(catalogId, articleId);
		if(null==sysArticle){
			return "";
		}
		return new TemplatePreview(0L,articleId).parseTemplate();
	}

	@Override
	public SysArticle queryArticleById(Long articleId) {
		return sysArticleMapper.selectByArticleId(articleId);
	}

	@Override
	public int updateArticleUrlById(Long articleId, String url) {
		return sysArticleMapper.updateArticleUrlById(articleId, url);
	}

	@Override
	public List<SysArticle> queryArticleByCatalogId(Long catalogId) {
		DataMap paramMap = new DataMap();
		paramMap.set("catalogId", catalogId);
		paramMap.set("state", "1");
		return sysArticleMapper.query(paramMap);
	}
	
}
