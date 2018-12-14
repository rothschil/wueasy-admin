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
package com.wueasy.admin.template;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author: fallsea
 * @version 1.0
 */
public class Context {

	/**
     * 当前目录的ID
     */
    private Long catalogId = 0L;


    /**
     * 当前发布的文章ID，只有在发布文章时才会传入此参数
     */
    private Long articleId = 0L;
    
    /**
     * 站点id
     */
    private Long siteId = 0L;
    
    private String templateUrl;

    /**
     * 属性数据，可以附加
     */
    private Map<String,Object> attributes = new HashMap<String,Object>();


    /**
     * 变量数据
     */
    private Map<String,String> variables = new HashMap<String,String>();

    /**
     * 返回当前栏目ID
     *
     * @return
     */
    public Long getCatalogId()
    {
        return catalogId;
    }

    /**
     * 设置当前栏目ID
     *
     * @param catalogId
     */
    public void setCatalogId(Long catalogId)
    {
        this.catalogId = catalogId;
    }

    /**
     * 返回当前文章ID
     *
     * @return
     */
    public Long getArticleId()
    {
        return articleId;
    }

    /**
     * 设置当前文章ID
     *
     * @param articleId
     */
    public void setArticleId(Long articleId)
    {
        this.articleId = articleId;
    }

    /**
     * 返回当前文章ID
     *
     * @return
     */
    public String getTemplateUrl()
    {
        return templateUrl;
    }

    /**
     * 设置当前文章ID
     *
     * @param articleId
     */
    public void setTemplateUrl(String templateUrl)
    {
        this.templateUrl = templateUrl;
    }
    
    /**
     * 设置属性对象
     *
     * @param name
     * @param object
     */
    public void setAttribute(String name, Object object)
    {
        attributes.put(name, object);
    }

    /**
     * 获得属性对象
     *
     * @param name
     * @return
     */
    public Object getAttribute(String name)
    {
        return attributes.get(name);
    }

    /**
     * 添加一个变量
     *
     * @param name
     * @param value
     */
    public void addVariable(String name, String value)
    {
        variables.put(name, value);
    }

    /**
     * 获得一个变量
     *
     * @param name
     * @return
     */
    public String getVariable(String name)
    {
        String value = variables.get(name);
        return (value == null) ? "" : value;
    }

    /**
     * 获取所有变量
     *
     * @return
     */
    public Map<String,String> getAllVariable()
    {
        return variables;
    }

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

}
