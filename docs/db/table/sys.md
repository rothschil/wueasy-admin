# 系统基础表结构



## 系统用户表 - sys_user

> 保存用户基础信息

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | user_id | 用户id | bigint | 20 | 主键自增 |
| 2 | login_no | 登录名 | varchar | 50 | 唯一 |
| 3 | username | 用户姓名/昵称 | varchar | 50 |  |
| 4 | password | 用户密码 | varchar | 128 | 加密后的密码 |
| 5 | state | 用户状态 | char | 1 | 0.未激活,1.正常,2.锁定,3.注销 |
| 6 | is_system | 是否超级管理员 | char | 1 | 1.是,0.否 |
| 7 | salt | 加密密码的盐 | varchar | 10 |  |
| 8 | login_count | 登录次数 | bigint | 20 |  |
| 9 | last_time | 最后登录时间 | datetime |   |  |
| 10 | last_ip | 最后登录ip | varchar | 50 |  |
| 11 | created_by | 创建人 | bigint | 20 |  |
| 12 | created_time | 创建时间 | datetime |  |  |
| 13 | modified_by | 修改人 | bigint | 20 |  |
| 14 | modified_time | 修改时间 | datetime |  |  |
| 15 | phone | 手机号码 | varchar | 11 | 唯一 |
| 16 | email | 电子邮件 | varchar | 50 | 唯一 |
| 17 | head_image | 头像 | varchar | 255 |  |  |


## 用户登录日志表 - sys_user_login_log

> 保存用户登录成功/登陆失败流水表

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | id | id | bigint | 20 | 主键自增 |
| 2 | login_no | 登录名 | varchar | 50 |  |
| 3 | ip | 登录ip | varchar | 50 |  |
| 4 | type | 类型 | char | 1 | 1:成功,2:用户名不存在,3:密码不正确,4:用户已经锁定,5:用户已经注销,6.用户状态不正常 |
| 5 | login_type | 登录类型 | char | 1 | 1:用户名,2:手机号码,3:邮箱 |
| 6 | created_time | 登录时间 | datetime |  |  | |

## 密码修改记录表 - sys_user_pwd_log

> 记录用户修改密码历史。

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | id | id | bigint | 20 | 主键自增 |
| 2 | user_id | 用户id | bigint | 20 | 关联表`sys_user`  |
| 3 | password | 用户密码 | varchar | 128 | 加密后的密码 |
| 4 | salt | 加密密码的盐 | varchar | 10 |  |
| 5 | type | 类型 | char | 1 | 1:密码重置,2:密码修改 |
| 6 | created_time | 修改时间 | datetime |  |  | |


## 系统分组表 - sys_group

> 用于区分子系统。

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | group_id | 分组id | bigint | 20 | 主键自增 |
| 2 | group_name | 分组名称 | varchar | 50 |   |
| 3 | font_icon | 图标信息 | varchar | 255 |  | |

## 系统参数类型表 - sys_parameter_type

> 记录系统参数类型/分类

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | id | id | bigint | 20 | 主键自增 |
| 2 | param_no | 英文编号 | varchar | 50 | 唯一 |
| 3 | param_name | 中文名称 | varchar | 100 |   |
| 4 | parent_id | 父id | bigint | 20 |  |
| 5 | is_system | 是否系统参数 | char | 1 | 1 是，0 否 |
| 6 | description | 描述 | varchar | 255 |  |
| 7 | created_by | 创建人 | bigint | 20 |  |
| 8 | created_time | 创建时间 | datetime |  |  |
| 9 | modified_by | 修改人 | bigint | 20 |  |
| 10 | modified_time | 修改时间 | datetime |  |  | |


## 系统参数值表 - sys_parameter_value

> 记录系统参数值信息。

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | id | id | bigint | 20 | 主键自增 |
| 2 | param_type_id | 类型id | bigint | 20 | 关联表`sys_parameter_type` |
| 3 | item_key | 枚举key | varchar | 50 |   |
| 4 | item_value | 枚举value | varchar | 100 |  |
| 5 | description | 描述 | varchar | 255 |  |
| 6 | state | 状态 | char | 1 | 1.有效,0.无效 |
| 7 | encrypt | 是否加密 | char | 1 | 1:加密 |
| 8 | orderline | 排序值 | bigint | 20 |  |
| 9 | created_by | 创建人 | bigint | 20 |  |
| 10 | created_time | 创建时间 | datetime |  |  |
| 11 | modified_by | 修改人 | bigint | 20 |  |
| 12 | modified_time | 修改时间 | datetime |  |  | |


## 数据字典分类表 - sys_enum_type

> 记录数据字典分类列表

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | id | id | bigint | 20 | 主键自增 |
| 2 | enum_no | 英文编号 | varchar | 50 | 唯一 |
| 3 | enum_name | 中文名称 | varchar | 100 |   |
| 4 | parent_id | 父id | bigint | 20 |  |
| 5 | is_system | 是否系统参数 | char | 1 | 1 是，0 否 |
| 6 | description | 描述 | varchar | 255 |  |
| 7 | created_by | 创建人 | bigint | 20 |  |
| 8 | created_time | 创建时间 | datetime |  |  |
| 9 | modified_by | 修改人 | bigint | 20 |  |
| 10 | modified_time | 修改时间 | datetime |  |  | |


## 数据字典枚举值表 - sys_enum_value

> 记录数据字典枚举列表信息。

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | id | id | bigint | 20 | 主键自增 |
| 2 | enum_type_id | 类型id | bigint | 20 | 关联表`sys_enum_type` |
| 3 | item_key | 枚举key | varchar | 50 |   |
| 4 | item_value | 枚举value | varchar | 100 |  |
| 5 | description | 描述 | varchar | 255 |  |
| 6 | state | 状态 | char | 1 | 1.有效,0.无效 |
| 7 | orderline | 排序值 | bigint | 20 |  |
| 8 | created_by | 创建人 | bigint | 20 |  |
| 9 | created_time | 创建时间 | datetime |  |  |
| 10 | modified_by | 修改人 | bigint | 20 |  |
| 11 | modified_time | 修改时间 | datetime |  |  | |



## 角色表 - sys_role

> 保存系统的角色信息。

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | role_id | 角色id | bigint | 20 | 主键自增 |
| 2 | role_name | 角色名称 | varchar | 50 |  |
| 3 | parent_Id | 父角色id | bigint | 20 |  |
| 4 | description | 角色描述 | varchar | 255 |  |
| 5 | route | 路由值 | varchar | 255 | 记录其所有的上级ID号，用竖杠分开　若是根，则为空 |
| 6 | orderline | 排序值 | bigint | 20 |  |
| 7 | created_by | 创建人 | bigint | 20 |  |
| 8 | created_time | 创建时间 | datetime |  |  |
| 9 | modified_by | 修改人 | bigint | 20 |  |
| 10 | modified_time | 修改时间 | datetime |  |  | |


## 角色用户关联表 - sys_role_user

> 保存角色和用户之间的关联，为用户分配角色权限。同一用户可以有多个角色。

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | id | id | bigint | 20 | 主键自增 |
| 2 | user_id | 用户id | bigint | 20 | 关联表`sys_user` |
| 3 | role_id | 角色id | bigint | 20 | 关联表`sys_role` |
| 4 | created_by | 创建人 | bigint | 20 |  |
| 5 | created_time | 创建时间 | datetime |  |  | |


## 角色权限表 - sys_role_permission

> 保存角色和菜单的关联。

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | id | id | bigint | 20 | 主键自增 |
| 2 | role_id | 角色id | bigint | 20 | 关联表`sys_role` |
| 3 | menu_ids | 菜单id集合 | text |  | 多个逗号分隔  |
| 4 | menu_function_ids | 菜单功能id集合 | text |  | 多个逗号分隔 |
| 5 | created_by | 创建人 | bigint | 20 |  |
| 6 | created_time | 创建时间 | datetime |  |  |
| 7 | modified_by | 修改人 | bigint | 20 |  |
| 8 | modified_time | 修改时间 | datetime |  |  | |


## 数据权限模型表 - sys_auth_data_model

> 记录数据权限模型配置

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | model_id | id | bigint | 20 | 主键自增 |
| 2 | model_no | 模型编号 | varchar | 50 | 唯一 |
| 3 | model_name | 模型名称 | varchar | 100 |   |
| 4 | service_name | service名称 | varchar | 50 |  |
| 5 | func_nos | 功能号集合 | text |  | 那些功能号需要验证数据权限，多个逗号分隔 |
| 6 | field | 验证字段 | varchar | 255 |  | |


## 角色数据权限关联表 - sys_auth_role_data


| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | id | id | bigint | 20 | 主键自增 |
| 2 | model_id | 模型id | bigint | 20 | 关联表`sys_auth_data_model` |
| 3 | role_id | 角色id | bigint | 20 | 关联表`sys_role` |
| 4 | auth_data_ids | 数据权限集合 | text |  |  多个逗号分隔 |
| 5 | created_by | 创建人 | bigint | 20 |  |
| 6 | created_time | 创建时间 | datetime |  |  |
| 7 | modified_by | 修改人 | bigint | 20 |  |
| 8 | modified_time | 修改时间 | datetime |  |  | |


## 系统菜单表 - sys_menu

> 记录系统菜单信息。

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | menu_id | 菜单id | bigint | 20 | 主键自增 |
| 2 | menu_name | 菜单名称 | varchar | 255 |  |
| 3 | description | 描述 | varchar | 255 |   |
| 4 | state | 状态 | char | 1 | 1.有效,0.无效 |
| 5 | parent_id | 父菜单id | bigint | 20 |  |
| 6 | link_url | 菜单url地址 | varchar | 255 |  |
| 7 | font_icon | 图标信息 | varchar | 255 |  |
| 8 | route | 路由值 | varchar | 255 | 记录其所有的上级ID号，用竖杠分开　若是根，则为空 |
| 9 | orderline | 排序值 | bigint | 20 |  |
| 10 | created_by | 创建人 | bigint | 20 |  |
| 11 | created_time | 创建时间 | datetime |  |  |
| 12 | modified_by | 修改人 | bigint | 20 |  |
| 13 | modified_time | 修改时间 | datetime |  |  |
| 14 | group_id | 分组id | bigint | 20 | 关联表`sys_group` | |


## 菜单功能表 - sys_menu_function

> 记录系统菜单中的按钮信息，用于处理按钮权限。

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | id | id | bigint | 20 | 主键自增 |
| 2 | menu_id | 菜单id | bigint | 20 | 关联表`sys_menu` |
| 3 | name | 功能名称 | varchar | 50 | 按钮名称  |
| 4 | link_url | 访问url | varchar | 255 |  |
| 5 | funcs | 功能号权限集合 | varchar | 255 | 多个逗号分割 |
| 6 | orderline | 排序值 | bigint | 20 |  |
| 7 | created_by | 创建人 | bigint | 20 |  |
| 8 | created_time | 创建时间 | datetime |  |  |
| 9 | modified_by | 修改人 | bigint | 20 |  |
| 10 | modified_time | 修改时间 | datetime |  |  | |


## 组织机构表 - sys_org

> 记录组织机构信息

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | id | id | bigint | 20 | 主键自增 |
| 2 | org_no | 机构编号 | varchar | 30 |  |
| 3 | org_name | 机构名称 | varchar | 100 |  |
| 4 | parent_id | 父机构id | bigint | 20 |  |
| 5 | description | 描述 | varchar | 255 |   |
| 6 | created_by | 创建人 | bigint | 20 |  |
| 7 | created_time | 创建时间 | datetime |  |  |
| 8 | modified_by | 修改人 | bigint | 20 |  |
| 9 | modified_time | 修改时间 | datetime |  |  | |

## 组织机构用户表 - sys_org_user

> 组织机构用户关联表

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | id | id | bigint | 20 | 主键自增 |
| 2 | org_id | 机构id | bigint | 20 | 关联表`sys_org` |
| 3 | user_id | 用户id | bigint | 20 | 关联表`sys_user` |
| 4 | created_by | 创建人 | bigint | 20 |  |
| 5 | created_time | 创建时间 | datetime |  |  | |


## 系统日志记录表 - sys_log

> 记录访问日志流水。

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | id | id | bigint | 20 | 主键自增 |
| 2 | func_no | 功能号 | varchar | 20 |  |
| 3 | version | 功能号版本 | varchar | 20 |   |
| 4 | func_name | 功能号名称 | varchar | 100 |  |
| 5 | datetime | 执行时间 | bigint | 20 |  |
| 6 | request_id | 请求id | varchar | 50 |  |
| 7 | ip | ip | varchar | 50 |  |
| 8 | error_no | 错误码 | bigint | 20 |  |
| 9 | error_info | 错误消息 | varchar | 255 |  |
| 10 | created_by | 创建人 | bigint | 20 |  |
| 11 | created_time | 创建时间 | datetime |  |  | |


## 系统日志内容表 - sys_log_content

> 记录访问日志的详细内容，大字段信息。

| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | id | 日志id | bigint | 20 | 关联表`sys_log` |
| 2 | in_param | 入参 | longtext |  |  |
| 3 | out_param | 出参 | longtext |  |   |
| 4 | system_info | 系统信息 | text |  |  | |
