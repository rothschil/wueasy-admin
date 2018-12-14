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

import java.util.List;

import javax.transaction.Transactional;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wueasy.admin.bus.service.ArticleService;
import com.wueasy.admin.bus.service.TemplateService;
import com.wueasy.admin.entity.SysArticle;
import com.wueasy.admin.entity.SysArticleContent;
import com.wueasy.admin.entity.TemplateCatalog;
import com.wueasy.admin.mapper.SysArticleContentMapper;
import com.wueasy.admin.mapper.SysArticleMapper;
import com.wueasy.admin.mapper.TemplateCatalogMapper;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.exception.InvokeException;
import com.wueasy.base.util.StringHelper;
import com.wueasy.base.util.ZKHelper;
import com.wueasy.cache.constants.CacheConstants;

/**
 * 文章服务类
 * @author: fallsea
 * @version 1.0
 */
@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private SysArticleMapper sysArticleMapper;
	
	@Autowired
	private SysArticleContentMapper sysArticleContentMapper;
	
	@Autowired
	private TemplateCatalogMapper templateCatalogMapper;
	
	@Autowired
	private TemplateService templateService;
	
	
	@Override
	@Transactional
	public void addArticle(DataMap paramMap) {
		
		String brief = paramMap.getString("brief");
		String content = paramMap.getString("content");
		
		
		paramMap.remove("brief");
		paramMap.remove("content");
		
		//判断栏目id是否存在
		Long catalogId = paramMap.getLong("catalogId");
		
		TemplateCatalog catalog = templateService.selectTemplateCatalog(catalogId);
		
		if(null==catalog){
			throw new InvokeException(-109401, "栏目不存在或根目录栏目！");
		}
		
		sysArticleMapper.insert(paramMap);
		Long articleId = paramMap.getLong("articleId");
		
		SysArticleContent articleContent = new SysArticleContent();
		articleContent.setArticleId(articleId);
		articleContent.setBrief(brief);
		articleContent.setContent(content);
		sysArticleContentMapper.insert(articleContent);
	}

	@Override
	@Transactional
	public void updateArticle(DataMap paramMap) {
		
		SysArticle sysArticle =sysArticleMapper.select(paramMap);
		if(null!=sysArticle){
			String brief = paramMap.getString("brief");
			String content = paramMap.getString("content");
			
			paramMap.remove("brief");
			paramMap.remove("content");
			
			sysArticleMapper.update(paramMap);
			
			Long articleId = paramMap.getLong("articleId");
			
			SysArticleContent articleContent = new SysArticleContent();
			articleContent.setArticleId(articleId);
			articleContent.setBrief(brief);
			articleContent.setContent(content);
			sysArticleContentMapper.update(articleContent);
		}
	}

	@Override
	@Transactional
	public void deleteArticle(DataMap paramMap) {
		
		SysArticle sysArticle =sysArticleMapper.select(paramMap);
		if(null!=sysArticle){
			sysArticleContentMapper.delete(paramMap);
			sysArticleMapper.delete(paramMap);
		}
	}

	@Override
	public void updateArticleState(DataMap paramMap) {
		sysArticleMapper.updateState(paramMap);
	}

	@Override
	public List<TemplateCatalog> queryCatalogList(DataMap paramMap) {
		if(!"1".equals(paramMap.getString("isSystem")) && StringHelper.isEmpty(paramMap.getString("authDataStr"))){
			return null;
		}
		return templateCatalogMapper.queryValid(paramMap);
	}

	@Override
	public void publishCatalog(DataMap paramMap) {
		//发布队列
		ZkClient zkClient = ZKHelper.getInstance();
		String path = CacheConstants.ARTICLE_PUBLISH_QUEUE_NODE_PATH+"/CA:"+paramMap.getString("catalogId");
		if(!ZKHelper.exists(path)){
			zkClient.createPersistent(path,true);
		}
	}

	@Override
	public void publishCatalogRecursion(DataMap paramMap) {
		//发布队列
		ZkClient zkClient = ZKHelper.getInstance();
		String path = CacheConstants.ARTICLE_PUBLISH_QUEUE_NODE_PATH+"/CAR:"+paramMap.getString("catalogId");
		if(!ZKHelper.exists(path)){
			zkClient.createPersistent(path,true);
		}
	}

}
