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
package com.wueasy.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wueasy.admin.entity.Template;
import com.wueasy.base.entity.DataMap;

public interface TemplateMapper {

    Template select(DataMap paramMap);
    
    Template select(@Param("id")Long id);
    
    List<Template> query(DataMap paramMap);
    
    int delete(DataMap paramMap);
    
    int deleteMultiple(DataMap paramMap);
    
    int insert(DataMap paramMap);
    
    int update(DataMap paramMap);
    
    /**
     * 查询栏目，类型 有效数量
     * @author: fallsea
     * @param catalogId
     * @param type
     * @return
     */
    int queryCatalogTypeValidCount(@Param("catalogId")Long catalogId,@Param("type")String type);
    
    /**
     * 查询有效的模板信息
     * @author: fallsea
     * @param catalogId
     * @param type
     * @return
     */
    Template selectValidByCatalogType(@Param("catalogId")Long catalogId,@Param("type")String type);
    
    /**
     * 通过栏目id和栏目类型查询模板
     * @author: fallsea
     * @param catalogId
     * @param type
     * @return
     */
    Template queryTemplateByCatalogIdAndType(@Param("catalogId")Long catalogId,@Param("type")String type);
    
    /**
     * 查询list
     * @author: fallsea
     * @param catalogId
     * @param type
     * @return
     */
    List<Template> queryTemplateListByCatalogIdAndType(@Param("catalogId")Long catalogId,@Param("type")String type);

}