package com.wueasy.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wueasy.base.entity.DataMap;
import com.wueasy.demo.entity.DemoFunctionParam;

public interface DemoFunctionParamMapper {
	
	/**
	 * @Description: 查询列表
	 * @author: fallsea
	 * @date: 2017年10月30日 下午4:10:03
	 * @param paramMap
	 * @return
	 */
	List<DemoFunctionParam> queryList(DataMap paramMap);
	
    int delete(DataMap paramMap);
    
    int deleteAll(String funcId);

    int insert(DataMap paramMap);

    DemoFunctionParam selectById(DataMap paramMap);
    
    
    DemoFunctionParam selectById(Long id);
    
    /**
     * @Description: 查询功能号属性信息
     * @author: fallsea
     * @date: 2017年10月30日 下午4:20:33
     * @param funcId 功能号id
     * @param attribute 属性
     * @return
     */
    DemoFunctionParam selectByFuncAttribute(@Param("funcId")Long funcId,@Param("attribute")String attribute);

    int update(DataMap paramMap);
}