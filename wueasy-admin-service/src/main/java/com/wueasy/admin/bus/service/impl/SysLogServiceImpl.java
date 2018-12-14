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

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wueasy.admin.entity.SysLog;
import com.wueasy.admin.mapper.SysLogMapper;
import com.wueasy.base.bus.server.service.SysLogService;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.entity.Page;
import com.wueasy.base.util.DateHelper;
import com.wueasy.base.util.PageHelper;
import com.wueasy.base.util.StringHelper;

/**
 * 日志服务类
 * @author: fallsea
 * @version 1.0
 */
@Service("SysLogService")
public class SysLogServiceImpl implements SysLogService {
	
	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	public void add(String funcNo, String funcName, String version,String requestId, Long datetime, String ip,
			Long userId, String inParam, String outParam, int errorNo, String errorInfo,String systemInfo) {
		SysLog sysLog = new SysLog();
		sysLog.setFuncNo(funcNo);
		sysLog.setFuncName(funcName);
		sysLog.setVersion(version);
		sysLog.setRequestId(requestId);
		sysLog.setDatetime(datetime);
		sysLog.setIp(ip);
		sysLog.setCreatedBy(userId);
		sysLog.setInParam(inParam);
		sysLog.setOutParam(outParam);
		sysLog.setErrorNo(Long.valueOf(errorNo));
		sysLog.setErrorInfo(errorInfo);
		sysLog.setCreatedTime(new Date());
		sysLog.setSystemInfo(systemInfo);
		sysLogMapper.insert(sysLog);
	}

	@Override
	public Page queryPage(DataMap paramMap) {
		
		int pageNum = PageHelper.getPageNum(paramMap);
        int pageSize = PageHelper.getPageSize(paramMap);
        
        String lastDate = paramMap.getString("createDate");//创建时间
        if(StringHelper.isNotBlank(lastDate) && !" - ".equals(lastDate))
        {
            String lastDates[] = lastDate.split(" - ");
            paramMap.put("startDate", DateHelper.parseString(lastDates[0], DateHelper.PATTERN_DATE_TIME));
            paramMap.put("endDate", DateHelper.parseString(lastDates[1], DateHelper.PATTERN_DATE_TIME));
        }
        
        PageHelper.startPage(pageNum, pageSize);
        List<SysLog> list = sysLogMapper.query(paramMap);
        
        return PageHelper.getPage(list);
	}

}
