package com.wueasy.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wueasy.base.entity.DataMap;
import com.wueasy.demo.entity.DemoFunction;

public interface DemoFunctionMapper {
	
	/**
	 * @Description: 查询分页信息
	 * @author: fallsea
	 * @date: 2017年10月29日 上午10:18:50
	 * @param paramMap
	 * @return
	 */
	List<DemoFunction> queryPage(DataMap paramMap);
	
	/**
	 * @Description: 删除功能号
	 * @author: fallsea
	 * @date: 2017年10月29日 下午12:26:23
	 * @param paramMap
	 * @return
	 */
    int delete(DataMap paramMap);

    /**
     * @Description: 新增功能号
     * @author: fallsea
     * @date: 2017年10月29日 上午11:40:54
     * @param paramMap
     * @return
     */
    int insert(DataMap paramMap);


    /**
     * @Description: 查询单条记录
     * @author: fallsea
     * @date: 2017年10月29日 下午12:25:37
     * @param paramMap
     * @return
     */
    DemoFunction selectByFuncNo(DataMap paramMap);
    
    /**
     * @Description: 查询单条记录
     * @author: fallsea
     * @date: 2017年10月29日 下午1:48:39
     * @param funcNo 功能号
     * @param funcNo 版本号
     * @return
     */
    DemoFunction selectByFuncNo(@Param("funcNo")String funcNo,@Param("version")String version);
    
    /**
     * @Description: 查询功能号信息
     * @author: fallsea
     * @date: 2017年10月30日 下午2:29:32
     * @param id
     * @return
     */
    DemoFunction selectById(Long id);
    
    /**
     * @Description: 查询功能号信息
     * @author: fallsea
     * @date: 2017年10月30日 下午3:06:09
     * @param paramMap
     * @return
     */
    DemoFunction selectById(DataMap paramMap);

    /**
     * @Description: 修改功能号
     * @author: fallsea
     * @date: 2017年10月29日 上午11:42:56
     * @param paramMap
     * @return
     */
    int update(DataMap paramMap);
}