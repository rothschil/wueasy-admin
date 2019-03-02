# wueasy

> wueasy是一个java后端的分布式快速开发平台（框架），主要用于提供后端接口的研发，提供统一标准的规范。

使用自动生成代码工具，可以更便捷的开发。

## 案例

* https://admin.wueasy.com/login

	账号：wueasy
	密码：wueasy

## 主要使用技术

* Spring Boot
* Motan
* ZooKeeper
* MyBatis

##  环境要求

* `JDK 1.8`
* `MAVEN 3.x`
* `ZooKeeper 3.4.13`


## 特性

* 分布式架构
* 统一验证处理
* 统一接口配置
* 调用日志
* 多种方式调用
* 自动生成代码工具


## 版本

当前最新版本为`1.1.4`，版本已经发布[maven中心仓库](https://mvnrepository.com/search?q=com.wueasy)，可以直接引用使用。


* 核心基础包
```xml
<dependency>
	<groupId>com.wueasy</groupId>
	<artifactId>wueasy-base</artifactId>
	<version>RELEASE</version>
</dependency>
```
* 代码自动生成工具
```xml
<dependency>
	<groupId>com.wueasy</groupId>
	<artifactId>wueasy-auto</artifactId>
	<version>RELEASE</version>
</dependency>
```
* bus服务端工具
```xml
<dependency>
	<groupId>com.wueasy</groupId>
	<artifactId>wueasy-bus-server</artifactId>
	<version>RELEASE</version>
</dependency>
```
* bus客户端工具
```xml
<dependency>
	<groupId>com.wueasy</groupId>
	<artifactId>wueasy-bus-client</artifactId>
	<version>RELEASE</version>
</dependency>
```

* 基本web包(统一拦截器、统一controller接口)
```xml
<dependency>
	<groupId>com.wueasy</groupId>
	<artifactId>wueasy-base-web</artifactId>
	<version>RELEASE</version>
</dependency>
```

## 授权

* 社区版：使用 AGPLv3 开源，如果你选择使用社区版，则必须完全遵守 AGPLv3 的相关条款
* 商业版：请联系 QQ `535412000` 或邮箱 `server@wueasy.com` 进行细节咨询

```java
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
```

## 社区

[问题反馈或讨论](https://github.com/wueasy/wueasy/issues)


qq交流群：**201820396** [点击链接加入群](https://jq.qq.com/?_wv=1027&k=53IJSvz)


## 网络拓扑图

![](./images/wueasy拓扑图.jpeg)
