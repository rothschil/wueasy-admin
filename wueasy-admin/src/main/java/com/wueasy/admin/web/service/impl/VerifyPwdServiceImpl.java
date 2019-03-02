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
package com.wueasy.admin.web.service.impl;

import org.springframework.stereotype.Service;

import com.wueasy.base.bus.client.Client;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.entity.Result;
import com.wueasy.base.web.security.service.VerifyPwdService;

/**
 * 验证二次密码
 * @author: fallsea
 * @version 1.0
 */
@Service
public class VerifyPwdServiceImpl implements VerifyPwdService {

	@Override
	public Result verify(String verifyPwd) {
		Client client = new Client();
    	DataMap paramMap = new DataMap();
    	paramMap.put("verifyPwd", verifyPwd);
		return client.invoke("S1034", paramMap);
	}

}
