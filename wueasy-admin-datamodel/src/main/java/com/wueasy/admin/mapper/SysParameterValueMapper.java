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

import com.wueasy.admin.entity.SysParameterValue;
import com.wueasy.base.entity.DataMap;

public interface SysParameterValueMapper {
	/**
	 * 删除
	 * @author: fallsea
	 * @param paramMap
	 * @return
	 */
    int delete(DataMap paramMap);

    /**
     * 新增
     * @author: fallsea
     * @param paramMap
     * @return
     */
    int insert(DataMap paramMap);

    /**
     * 查询单个数据
     * @author: fallsea
     * @param paramMap
     * @return
     */
    SysParameterValue select(DataMap paramMap);
    
    /**
     * 查询单个数据
     * @author: fallsea
     * @param id
     * @return
     */
    SysParameterValue select(Long id);

    /**
     * 查询列表
     * @author: fallsea
     * @param paramMap
     * @return
     */
    List<SysParameterValue> query(DataMap paramMap);

    /**
     * 查询枚举指是否存在
     * @author: fallsea
     * @param paramTypeId
     * @param itemValue
     * @return
     */
    SysParameterValue selectByParamTypeIdAndItemValue(@Param("paramTypeId")Long paramTypeId,@Param("itemKey")String itemKey);
    
    /**
     * 查询枚举类型下数量
     * @author: fallsea
     * @param paramTypeId
     * @return
     */
    int selectByParamTypeId(@Param("paramTypeId")Long paramTypeId);
    
    /**
     * 修改
     * @author: fallsea
     * @param paramMap
     * @return
     */
    int update(DataMap paramMap);
    
    /**
     * 查询有效list
     * @author: fallsea
     * @return
     */
    List<DataMap> queryValidList();
}