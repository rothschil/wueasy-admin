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
package com.wueasy.admin.bus.constants;

/**
 * 系统变量
 * @author: fallsea
 * @version 1.0
 */
public class WueasyConstants
{
    
    /**
     * 用户状态：0 未激活
     */
    public static final String USER_STATE_0 = "0";
    
    /**
     * 用户状态：1 正常
     */
    public static final String USER_STATE_1 = "1";
    
    /**
     * 用户状态：2 锁定
     */
    public static final String USER_STATE_2 = "2";
    
    /**
     * 用户状态：3 注销
     */
    public static final String USER_STATE_3 = "3";
    
    
    /**用户登陆状态：正常**/
    public static final String USER_LOGIN_STATE_1 = "1";
    
    /**用户登陆状态：用户名不存在**/
    public static final String USER_LOGIN_STATE_2 = "2";
    
    /**用户登陆状态：密码不正确**/
    public static final String USER_LOGIN_STATE_3 = "3";
    
    /**用户登陆状态：用户已经锁定**/
    public static final String USER_LOGIN_STATE_4 = "4";
    
    /**用户登陆状态：用户已经注销**/
    public static final String USER_LOGIN_STATE_5 = "5";
    
    /**用户登陆状态：用户状态不正常**/
    public static final String USER_LOGIN_STATE_6 = "6";
    
    /**用户登陆状态：用户未激活**/
    public static final String USER_LOGIN_STATE_7 = "7";
    
    
    /**用户密码类型：密码重置**/
    public static final String USER_PWD_TYPE_1 ="1";
    
    /**用户密码类型：密码修改**/
    public static final String USER_PWD_TYPE_2 ="2";
    
    /**用户约束，首次登录修改密码**/
    public static final String USER_RESTRICT_TYPE_1 = "1";
    
    /**用户约束，密码过期修改密码**/
    public static final String USER_RESTRICT_TYPE_2 = "2";
    
    /**用户约束，密码过期前提醒**/
    public static final String USER_RESTRICT_TYPE_3 = "3";
    
    /**登录类型：用户名**/
    public static final String LOGIN_TYPE_NO = "1";
    
    /**登录类型：手机号码**/
    public static final String LOGIN_TYPE_PHONE = "2";
    
    /**登录类型：邮箱**/
    public static final String LOGIN_TYPE_EMAIL = "3";
    
}
