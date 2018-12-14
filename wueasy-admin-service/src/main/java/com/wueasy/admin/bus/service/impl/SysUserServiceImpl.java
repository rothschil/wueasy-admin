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

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.wueasy.admin.bus.constants.WueasyConstants;
import com.wueasy.admin.bus.service.SysUserService;
import com.wueasy.admin.entity.SysAuthRoleData;
import com.wueasy.admin.entity.SysMenu;
import com.wueasy.admin.entity.SysRolePermission;
import com.wueasy.admin.entity.SysUser;
import com.wueasy.admin.entity.SysUserLoginLog;
import com.wueasy.admin.entity.SysUserPwdLog;
import com.wueasy.admin.mapper.SysAuthRoleDataMapper;
import com.wueasy.admin.mapper.SysGroupMapper;
import com.wueasy.admin.mapper.SysMenuFunctionMapper;
import com.wueasy.admin.mapper.SysMenuMapper;
import com.wueasy.admin.mapper.SysRolePermissionMapper;
import com.wueasy.admin.mapper.SysUserLoginLogMapper;
import com.wueasy.admin.mapper.SysUserMapper;
import com.wueasy.admin.mapper.SysUserPwdLogMapper;
import com.wueasy.base.entity.DataMap;
import com.wueasy.base.entity.Page;
import com.wueasy.base.entity.Result;
import com.wueasy.base.exception.InvokeException;
import com.wueasy.base.util.DateHelper;
import com.wueasy.base.util.PageHelper;
import com.wueasy.base.util.RandomHelper;
import com.wueasy.base.util.SecurityHelper;
import com.wueasy.base.util.StringHelper;
import com.wueasy.cache.util.ParameterHelper;

/**
 * 系统用户服务类
 * @author: fallsea
 * @version 1.0
 */
@Service("SysUserService")
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
    private SysUserMapper sysUserMapper;
	    
    @Autowired
    private SysUserLoginLogMapper sysUserLoginLogMapper;
	    
    @Autowired
    private SysMenuMapper sysMenuMapper;
    
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;
    
    @Autowired
    private SysMenuFunctionMapper sysMenuFunctionMapper;
    
    @Autowired
    private SysUserPwdLogMapper sysUserPwdLogMapper;
    
    @Autowired
    private SysGroupMapper sysGroupMapper;
    
    @Autowired
    private SysAuthRoleDataMapper sysAuthRoleDataMapper;
    
	@Override
	public SysUser login(DataMap paramMap) {
		
		String loginNo = paramMap.getString("loginNo");
		
		String ip  = paramMap.getString("ip");
		
		String password = paramMap.getString("password");
		
		String loginType = WueasyConstants.LOGIN_TYPE_NO;
		SysUser sysUser = null;
		if(StringHelper.isMobile(loginNo)){//手机号码
			loginType = WueasyConstants.LOGIN_TYPE_PHONE;
			sysUser = sysUserMapper.findByPhone(loginNo);
		}else if(StringHelper.isEmail(loginNo)){//邮箱
			loginType = WueasyConstants.LOGIN_TYPE_EMAIL;
			sysUser = sysUserMapper.findByEmail(loginNo);
		}else{
			sysUser = sysUserMapper.findByLoginNo(loginNo);
		}
        Date createdTime = new Date();
        
        //登录日志对象
        SysUserLoginLog sysUserLoginLog = new SysUserLoginLog();
        sysUserLoginLog.setIp(ip);
        sysUserLoginLog.setLoginNo(loginNo);
        sysUserLoginLog.setCreatedTime(createdTime);
        sysUserLoginLog.setLoginType(loginType);
        if( null == sysUser )
        {
            sysUserLoginLog.setType(WueasyConstants.USER_LOGIN_STATE_2);
            sysUserLoginLogMapper.insert(sysUserLoginLog);
            throw new InvokeException(-100101, "用户名或密码不正确!");
        }
        
        //判断密码是否相等
        if( !sysUser.getPassword().equals(SecurityHelper.getSHA512Str(sysUser.getSalt()+password)) )
        {
            sysUserLoginLog.setType(WueasyConstants.USER_LOGIN_STATE_3);
            sysUserLoginLogMapper.insert(sysUserLoginLog);
            userLock(sysUser);
            
            throw new InvokeException(-100102, "用户名或密码不正确!");
        }
        
        //判断用户状态是否正确
        String state = sysUser.getState();
        if(WueasyConstants.USER_STATE_1.equals(state))
        {
            //登录成功后,修改用户最后登录时间,更新登录次数
            sysUserMapper.updateByLoginCountAndLastTime(sysUser.getUserId(),createdTime,ip);
            
            userPwdRestrict(sysUser);
            
            //清空密码
            sysUser.setPassword(null);
            sysUser.setSalt(null);
            sysUserLoginLog.setType(WueasyConstants.USER_LOGIN_STATE_1);
            sysUserLoginLogMapper.insert(sysUserLoginLog);
            return sysUser;
        }
        else if(WueasyConstants.USER_STATE_2.equals(state))
        {
            sysUserLoginLog.setType(WueasyConstants.USER_LOGIN_STATE_4);
            sysUserLoginLogMapper.insert(sysUserLoginLog);
            throw new InvokeException(-100103, "用户已经锁定,请联系管理员!");
        }
        else if(WueasyConstants.USER_STATE_3.equals(state))
        {
            sysUserLoginLog.setType(WueasyConstants.USER_LOGIN_STATE_5);
            sysUserLoginLogMapper.insert(sysUserLoginLog);
            throw new InvokeException(-100104, "用户已经注销,请联系管理员!");
        }
        else if(WueasyConstants.USER_STATE_1.equals(state))
        {
            sysUserLoginLog.setType(WueasyConstants.USER_LOGIN_STATE_7);
            sysUserLoginLogMapper.insert(sysUserLoginLog);
            throw new InvokeException(-100106, "用户未激活,请联系管理员!");
        }
        else
        {
            sysUserLoginLog.setType(WueasyConstants.USER_LOGIN_STATE_6);
            sysUserLoginLogMapper.insert(sysUserLoginLog);
            throw new InvokeException(-100105, "用户状态不正常,请联系管理员!");
        }
	}
	
	
	/**
	 * 用户密码过期提醒
	 * @author: fallsea
	 * @param sysUser
	 */
	private void userPwdRestrict(SysUser sysUser){
		//查询用户是否首次登陆，查询密码是否过期
        //查询最新记录是否是首次登录
        int isFirstLoginUpdatePwd = ParameterHelper.getInt("userLogin.isFirstLoginUpdatePwd",0);
        int userPwdRegularUpdate = ParameterHelper.getInt("userLogin.userPwdRegularUpdate",0);
        if(isFirstLoginUpdatePwd ==1 || userPwdRegularUpdate>0){
        	
        	SysUserPwdLog sysUserPwdLog = sysUserPwdLogMapper.selectNewByUserId(sysUser.getUserId());
        	
        	//判断是否首次修改密码
        	if(isFirstLoginUpdatePwd ==1){
        		
        		if(null == sysUserPwdLog || WueasyConstants.USER_PWD_TYPE_1.equals(sysUserPwdLog.getType())){
        			//首次登录，需要修改密码（密码重置也相当于首次登录）
        			sysUser.setType(WueasyConstants.USER_RESTRICT_TYPE_1);
        		}
        	}
        	
        	if(userPwdRegularUpdate > 0 && StringHelper.isBlank(sysUser.getType())){
        		
        		//如果不存在，需要修改密码
        		if(null == sysUserPwdLog){
        			sysUser.setType(WueasyConstants.USER_RESTRICT_TYPE_2);
        		}else{
        			
        			int userPwdRegularUpdateRemindTime = ParameterHelper.getInt("userLogin.userPwdRegularUpdateRemindTime",0);
        			
        			//如果今天到最后一次修改的时间，大于等于 配置的天数，那么密码已过期
        			int day = DateHelper.getDateDiff(new Date(), sysUserPwdLog.getCreatedTime());
        			
        			
        			if (day >= userPwdRegularUpdate){
        				//密码过期
        				sysUser.setType(WueasyConstants.USER_RESTRICT_TYPE_2);
        				
        			}else if(userPwdRegularUpdateRemindTime > 0 ){
        				
        				int gqDay = userPwdRegularUpdate - day; //还剩下几天过期
        				
        				if(userPwdRegularUpdateRemindTime > gqDay){
        					//提示密码过期
        					sysUser.setType(WueasyConstants.USER_RESTRICT_TYPE_3);
        					sysUser.setDay(gqDay);
        				}
        			}
        		}
        		
        	}
        	
        	
        }
	}

	/**
	 * 用户密码账号锁定控制
	 * @author: fallsea
	 * @param sysUser
	 */
	private void userLock(SysUser sysUser){
		//查询用户密码连续输错次数，超出设置的阀值，则锁定用户
        int failedMaxNum = ParameterHelper.getInt("userLogin.failedMaxNum",0);
        if(failedMaxNum>0){
        	
        	String failedType = ParameterHelper.getString("userLogin.failedType", "day");
        	
        	Date dateTime = null;
        	Calendar calendar = Calendar.getInstance();
        	
        	int failedTime = ParameterHelper.getInt("userLogin.failedTime");
        	
        	if(failedTime<1){
        		failedTime = 1;
        	}
        	
        	if("hour".equals(failedType)){
        		calendar.add(Calendar.HOUR, -failedTime);//当前系统时间的  前一小时
        		dateTime = calendar.getTime();
        	}else if("minute".equals(failedType)){
        		calendar.add(Calendar.MINUTE, -failedTime);//当前系统时间的  前分钟
        		dateTime = calendar.getTime();
        	}else{
        		calendar.set(Calendar.HOUR_OF_DAY, 0);
        		calendar.set(Calendar.MINUTE, 0);
        		calendar.set(Calendar.SECOND, 0);
        		dateTime = calendar.getTime();
        	}
        	
        	//登录失败次数
        	int loginCount = sysUserLoginLogMapper.queryUserLogCount(sysUser.getLoginNo(), dateTime);
        	if(loginCount >= failedMaxNum){
        		sysUserMapper.updateState(sysUser.getUserId(), WueasyConstants.USER_STATE_2);
        		throw new InvokeException(-100102, "用户名或密码不正确,用户已被锁定,请联系管理员!");
        	}
        	if((failedMaxNum-loginCount) <=3){
        		throw new InvokeException(-100102, "用户名或密码不正确,您还有"+(failedMaxNum-loginCount)+"次重试机会!");
        	}else{
        		throw new InvokeException(-100102, "用户名或密码不正确!");
        	}
        }
	}
	
	

	@Override
	public void insertUser(DataMap paramMap) {
		SysUser sysUser = sysUserMapper.findByLoginNo(paramMap.getString("loginNo"));
        if(null!=sysUser)
        {
            throw new InvokeException(-100401, "用户名已存在!");
        }
        
        //验证手机号码是否存在
        String phone = paramMap.getString("phone");
        if(StringHelper.isNotEmpty(phone)){
        	sysUser = sysUserMapper.findByPhone(phone);
            if(null!=sysUser)
            {
                throw new InvokeException(-100402, "手机号码已存在!");
            }
        }
        
        //验证邮箱是否存在
        String email = paramMap.getString("email");
        if(StringHelper.isNotEmpty(email)){
        	sysUser = sysUserMapper.findByEmail(email);
            if(null!=sysUser)
            {
                throw new InvokeException(-100403, "邮箱已存在!");
            }
        }
        
        String salt = RandomHelper.randomAlphabetic(10);
        paramMap.put("salt", salt);
        paramMap.put("password", SecurityHelper.getSHA512Str(salt+paramMap.getString("password")));
        //新增
        sysUserMapper.insert(paramMap);
	}


	@Override
	public void updateUser(DataMap paramMap) {
		
		Long userId = paramMap.getLong("userId");
		
		SysUser sysUser = null;
		//验证手机号码是否存在
        String phone = paramMap.getString("phone");
        if(StringHelper.isNotEmpty(phone)){
        	sysUser = sysUserMapper.findByPhone(phone);
            if(null!=sysUser && !userId.equals(sysUser.getUserId()))
            {
                throw new InvokeException(-100501, "手机号码已存在!");
            }
        }
        
        //验证邮箱是否存在
        String email = paramMap.getString("email");
        if(StringHelper.isNotEmpty(email)){
        	sysUser = sysUserMapper.findByEmail(email);
        	if(null!=sysUser && !userId.equals(sysUser.getUserId()))
            {
                throw new InvokeException(-100502, "邮箱已存在!");
            }
        }
        sysUserMapper.update(paramMap);
	}
	
	@Override
	public void updatePwd(DataMap paramMap) {
		//1.先验证旧密码
		String loginNo = paramMap.getString("loginNo");//当前登陆的用户id
		String oldPassword = paramMap.getString("oldPassword");//旧密码
		
		SysUser sysUser = sysUserMapper.findByLoginNo(loginNo);
		if(null==sysUser)
        {
            throw new InvokeException(-100201, "用户信息不存在!");
        }
		//判断密码是否相等
        if( !sysUser.getPassword().equals(SecurityHelper.getSHA512Str(sysUser.getSalt()+oldPassword)) )
        {
            throw new InvokeException(-100202, "密码验证失败!");
        }
        
        //判断用户状态是否正确
        String state = sysUser.getState();
        if(!WueasyConstants.USER_STATE_1.equals(state))
        {
        	 throw new InvokeException(-102203, "用户状态不正常!");
        }
        
		String salt = RandomHelper.randomAlphabetic(10);
		String password = SecurityHelper.getSHA512Str(salt+paramMap.getString("newPassword"));
		//3.修改密码
		sysUserMapper.updatePassword(paramMap.getLong("userId"), password, salt);
		
		//新增密码记录
		SysUserPwdLog pwdLog = new SysUserPwdLog();
		pwdLog.setCreatedTime(new Date());
		pwdLog.setUserId(paramMap.getLong("userId"));
		pwdLog.setSalt(salt);
		pwdLog.setPassword(password);
		pwdLog.setType(WueasyConstants.USER_PWD_TYPE_2);
		sysUserPwdLogMapper.insert(pwdLog);
	}


	@Override
	public Page queryUserPage(DataMap paramMap) {
		
		int pageNum = PageHelper.getPageNum(paramMap);
        int pageSize = PageHelper.getPageSize(paramMap);
        
        String lastDate = paramMap.getString("lastDate");//最后登录时间
        if(StringHelper.isNotBlank(lastDate) && !" - ".equals(lastDate))
        {
            String lastDates[] = lastDate.split(" - ");
            paramMap.put("startDate", DateHelper.parseString(lastDates[0] + " 00:00:00", DateHelper.PATTERN_DATE_TIME));
            paramMap.put("endDate", DateHelper.parseString(lastDates[1] + " 23:59:59", DateHelper.PATTERN_DATE_TIME));
        }
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> list = sysUserMapper.queryPage(paramMap);
        return PageHelper.getPage(list);
	}


	@Override
	public void updateUserState(DataMap paramMap) {
		//超级管理员不能修改状态
		Long userId = paramMap.getLong("userId");
		SysUser sysUser = sysUserMapper.findByUserId(userId);
		if(null==sysUser)
        {
            throw new InvokeException(-100701, "用户信息不存在!");
        }
		if("1".equals(sysUser.getIsSystem()))
		{
			throw new InvokeException(-100702, "[超级管理员]用户不允许被修改状态!");
		}
		sysUserMapper.updateState(userId, paramMap.getString("state"));
	}


	@Override
	public void resetPwd(DataMap paramMap) {
		//1.先验证操作员信息，账号和密码是否匹配
		String sessionLoginNo = paramMap.getString("sessionLoginNo");//当前登陆的用户id
		
		SysUser sysUser = sysUserMapper.findByLoginNo(sessionLoginNo);
		if(null==sysUser)
        {
            throw new InvokeException(-101001, "用户信息不存在!");
        }
        
        //判断用户状态是否正确
        String state = sysUser.getState();
        if(!WueasyConstants.USER_STATE_1.equals(state))
        {
        	 throw new InvokeException(-101002, "用户状态不正常!");
        }
        
		//2.验证被重置的账号是否是超级管理员
        Long userId = paramMap.getLong("userId");
        sysUser = sysUserMapper.findByUserId(userId);
		if(null==sysUser)
        {
            throw new InvokeException(-101002, "用户信息不存在!");
        }
		if("1".equals(sysUser.getIsSystem()))
		{
			throw new InvokeException(-101004, "[超级管理员]用户不允许被重置密码!");
		}
		
		String salt = RandomHelper.randomAlphabetic(10);
		String password = SecurityHelper.getSHA512Str(salt+paramMap.getString("newPassword"));
		//3.修改密码
		sysUserMapper.updatePassword(userId, password, salt);
		
		//新增密码记录
		SysUserPwdLog pwdLog = new SysUserPwdLog();
		pwdLog.setCreatedTime(new Date());
		pwdLog.setUserId(userId);
		pwdLog.setSalt(salt);
		pwdLog.setPassword(password);
		pwdLog.setType(WueasyConstants.USER_PWD_TYPE_1);
		sysUserPwdLogMapper.insert(pwdLog);
	}


	@Override
	public Page queryUserLoginLog(DataMap paramMap) {
		int pageNum = PageHelper.getPageNum(paramMap);
        int pageSize = PageHelper.getPageSize(paramMap);
        
        String createDate = paramMap.getString("createDate");//创建时间
        if(StringHelper.isNotBlank(createDate) && !" - ".equals(createDate))
        {
            String createDates[] = createDate.split(" - ");
            paramMap.put("startDate", DateHelper.parseString(createDates[0] + " 00:00:00", DateHelper.PATTERN_DATE_TIME));
            paramMap.put("endDate", DateHelper.parseString(createDates[1] + " 23:59:59", DateHelper.PATTERN_DATE_TIME));
        }
        PageHelper.startPage(pageNum, pageSize);
        List<SysUserLoginLog> list = sysUserLoginLogMapper.queryPage(paramMap);
        return PageHelper.getPage(list);
	}


	@Override
	public Result queryUserAuth(DataMap paramMap) {
		
		Result result = new Result();
		//判断是否超级管理员，超级管理员有所有权限
		Long userId = paramMap.getLong("userId");
		SysUser sysUser = sysUserMapper.findByUserId(userId);
		Set<String> menuIdSet = null;
		Set<String> menuFunctionIdSet = null;
		
		
		if(!"1".equals(sysUser.getIsSystem())){
			
			/************************查询功能权限 start****************************/
			
			//查询用户所在角色的权限
			List<SysRolePermission>  sysRolePermissionList= sysRolePermissionMapper.queryUserRole(userId);
			if(null!=sysRolePermissionList && !sysRolePermissionList.isEmpty()){
				
				menuIdSet = new HashSet<String>();//菜单id集合
				menuFunctionIdSet = new HashSet<String>();//按钮id集合
				for (SysRolePermission sysRolePermission : sysRolePermissionList) {
					if(StringHelper.isNotBlank(sysRolePermission.getMenuIds())){
						
						menuIdSet.addAll(Splitter.on(",").trimResults().omitEmptyStrings().splitToList(sysRolePermission.getMenuIds()));
					}
					if(StringHelper.isNotBlank(sysRolePermission.getMenuFunctionIds())){
						menuFunctionIdSet.addAll(Splitter.on(",").trimResults().omitEmptyStrings().splitToList(sysRolePermission.getMenuFunctionIds()));
					}
				}
				
				//查询有权限的菜单列表
				if(null!=menuIdSet && !menuIdSet.isEmpty()){
					List<SysMenu> list = sysMenuMapper.queryValidList(menuIdSet);
					if(null!=list && !list.isEmpty()){
						
						Set<Long> groupIdSet = new HashSet<Long>();
						for (SysMenu sysMenu : list) {
							groupIdSet.add(sysMenu.getGroupId());
						}
						
						result.setResult("groupList",sysGroupMapper.query(groupIdSet));//分组集合
						
					}
					result.setResult(list);
				}
				
				//查询有权限的按钮列表
				if(null!=menuFunctionIdSet && !menuFunctionIdSet.isEmpty()){
					result.setResult("menuFunction",sysMenuFunctionMapper.queryUserAuthMenuFunction(menuFunctionIdSet));
				}
				
			}
			
			/************************查询功能权限 end****************************/
			
			
			/************************查询数据权限 start****************************/
			List<SysAuthRoleData> sysAuthRoleDataList = sysAuthRoleDataMapper.queryUserAuthRoleData(userId);
			if(null!=sysAuthRoleDataList && !sysAuthRoleDataList.isEmpty()){
				
				Map<Long,Set<String>> map = new HashMap<Long, Set<String>>();
				
				Long modelId = null ;
				Set<String> authDataIdSet = null; 
				for (SysAuthRoleData sysAuthRoleData : sysAuthRoleDataList) {
					if(null==modelId){
						modelId = sysAuthRoleData.getModelId();
						authDataIdSet = new HashSet<String>();
					}
					if(!modelId.equals(sysAuthRoleData.getModelId())){
						map.put(sysAuthRoleData.getModelId(), authDataIdSet);
						modelId = sysAuthRoleData.getModelId();
						authDataIdSet = new HashSet<String>();
					}
					String authDataIds = sysAuthRoleData.getAuthDataIds();
					if(StringHelper.isNotEmpty(authDataIds)){
						authDataIdSet.addAll(Splitter.on(",").trimResults().omitEmptyStrings().splitToList(authDataIds));
					}
				}
				if(null!=modelId){
					map.put(modelId, authDataIdSet);
				}
				result.setResult("authRoleData",map);
			}
			
			/************************查询数据权限 end****************************/
			
		}else{
			result.setResult(sysMenuMapper.queryValidList(null));
			result.setResult("groupList",sysGroupMapper.query(null));//分组集合
		}
		return result;
	}


	@Override
	public Result verifyPwd(DataMap paramMap) {
		
		Long userId = paramMap.getLong("userId");
		String verifyPwd = paramMap.getString("verifyPwd");
		
		SysUser sysUser = sysUserMapper.findDetailByUserId(userId);
		if(null==sysUser)
        {
            throw new InvokeException(-103401, "用户信息不存在!");
        }
		//判断密码是否相等
	    if( !sysUser.getPassword().equals(SecurityHelper.getSHA512Str(sysUser.getSalt()+verifyPwd)) )
	    {
	        throw new InvokeException(-103402, "密码验证失败!");
	    }
		return new Result();
	}

}
