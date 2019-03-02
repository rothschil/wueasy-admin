package com.wueasy.demo.mapper;

import java.util.List;

import com.wueasy.base.entity.DataMap;
import com.wueasy.demo.entity.DemoArea;

public interface DemoAreaMapper {

    List<DemoArea> selectList(DataMap paramMap);
    
}