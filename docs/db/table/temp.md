# 栏目表结构


## 系统站点表 - template_site


| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | site_id | 站点id | bigint | 20 | 主键自增 |
| 2 | site_no | 站点编号 | varchar | 50 | 唯一 |
| 3 | site_name | 站点名称 | varchar | 100 |  |
| 4 | state | 状态 | char | 1 | 1.有效,0.无效 |
| 5 | description | 描述 | varchar | 255 |  |
| 6 | created_by | 创建人 | bigint | 20 |  |
| 7 | created_time | 创建时间 | datetime |  |  |
| 8 | modified_by | 修改人 | bigint | 20 |  |
| 9 | modified_time | 修改时间 | datetime |  |  | |


## 系统栏目表 - template_catalog


| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | catalog_id | 栏目id | bigint | 20 | 主键自增 |
| 2 | parent_id | 父栏目id | bigint | 20 |  |
| 3 | site_id | 站点id | bigint | 20 | 关联表`template_site` |
| 4 | catalog_no | 栏目编号 | varchar | 50 | 唯一 |
| 5 | catalog_name | 栏目名称 | varchar | 100 |  |
| 6 | state | 状态 | char | 1 | 1.有效,0.无效 |
| 7 | orderline | 排序值 | bigint | 20 |  |
| 8 | description | 描述 | varchar | 500 |  |
| 9 | link_url | 目录链接 | varchar |  255 | 链接时，指向的页面，若没有设定，则栏目页面链接到发布的页面 |
| 10 | file_type | 发布生成的文件类型 | varchar | 10 | 记录其所有的上级ID号，用竖杠分开　若是根，则为空 |
| 11 | route | 路由 | varchar |  255 |  |
| 12 | small_image | 栏目图片(小) | varchar |  255 |  |
| 13 | large_image | 栏目图片(大) | varchar |  255 |  |
| 14 | seo_title | seo标题 | varchar |  255 |  |
| 15 | seo_keywords | seo关键字 | varchar |  255 |  |
| 16 | seo_description | seo描述 | varchar |  500 |  |
| 17 | created_by | 创建人 | bigint | 20 |  |
| 18 | created_time | 创建时间 | datetime |  |  |
| 19 | modified_by | 修改人 | bigint | 20 |  |
| 20 | modified_time | 修改时间 | datetime |  |  | |

## 模板变量表 - template_var


| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | id | id | bigint | 20 | 主键自增 |
| 2 | site_id | 站点id | bigint | 20 | 关联表`template_site` |
| 3 | item_key | 变量key | varchar | 50 | 同一站点下，变量key唯一 |
| 4 | item_value | 变量value | longtext |  |  |
| 5 | state | 状态 | char | 1 | 1.有效,0.无效 |
| 6 | description | 描述 | varchar | 255 |  |
| 7 | created_by | 创建人 | bigint | 20 |  |
| 8 | created_time | 创建时间 | datetime |  |  |
| 9 | modified_by | 修改人 | bigint | 20 |  |
| 10 | modified_time | 修改时间 | datetime |  |  | |


## 模板表 - template


| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | id | id | bigint | 20 | 主键自增 |
| 2 | name | 模板名称 | varchar | 255 |  |
| 3 | catalog_id | 栏目id | bigint | 20 | 关联表`template_catalog` |
| 4 | site_id | 站点id | bigint | 20 | 关联表`template_site` |
| 5 | state | 状态 | char | 1 | 1.有效,0.无效 |
| 6 | file_path | 发布文件路径 | varchar | 100 | 其它模板使用 |
| 7 | type | 模板类型 | char | 1 | 1:首页模板 2:信息列表 3:信息细览 4:其它模板 |
| 8 | encoding | 字符编码 | varchar | 20 |  |
| 9 | content | 模板内容 | longtext |  |  |
| 10 | created_by | 创建人 | bigint | 20 |  |
| 11 | created_time | 创建时间 | datetime |  |  |
| 12 | modified_by | 修改人 | bigint | 20 |  |
| 13 | modified_time | 修改时间 | datetime |  |  | |


## 模板发布计划表 - template_publish_plan


| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | id | id | bigint | 20 | 主键自增 |
| 2 | catalog_id | 栏目id | bigint | 20 | 关联表`template_catalog` |
| 3 | site_id | 站点id | bigint | 20 | 关联表`template_site` |
| 4 | cron | cron表达式 | varchar | 255 |  |
| 5 | recursion | 是否发布子栏目 | char | 1 | 0:否 1:是 |
| 6 | created_by | 创建人 | bigint | 20 |  |
| 7 | created_time | 创建时间 | datetime |  |  |
| 8 | modified_by | 修改人 | bigint | 20 |  |
| 9 | modified_time | 修改时间 | datetime |  |  | |


## 文章表 - sys_article


| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | article_id | 文章id | bigint | 20 | 主键自增 |
| 2 | title | 文章标题 | varchar | 255 |  |
| 3 | url | 文章url地址 | varchar | 255 | 发布时，生成的文章的URL链接 |
| 4 | author | 文章作者 | varchar | 50 |  |
| 5 | source | 文章来源 | bigint | 20 |  |
| 6 | is_hot | 是否推荐文章  | char | 1 | 1:是，0：不是(缺省) |
| 7 | is_head | 是否头条  | char | 1 | 1：是，0：不是 |
| 8 | publish_date | 发布时间  | datetime |   |   |
| 9 | catalog_id | 栏目id  | bigint | 20 | 关联表`template_catalog` |
| 10 | site_id | 站点id | bigint | 20 | 关联表`template_site` |
| 11 | state | 状态  | char | 1 | 0：未发布，1：已发布 |
| 12 | hits | 文章点击次数  | bigint | 20 |  |
| 13 | created_by | 创建人 | bigint | 20 |  |
| 14 | created_time | 创建时间 | datetime |  |  |
| 15 | modified_by | 修改人 | bigint | 20 |  |
| 16 | modified_time | 修改时间 | datetime |  |  | |


## 文章内容表 - sys_article_content


| 序号 | 名称 | 描述 | 类型 | 长度 | 备注 |
| ------ | ------ | ------ | ----| -----|
| 1 | article_id | 文章id | bigint | 20 | 关联表`sys_article` |
| 2 | brief | 文章摘要 | varchar | 500 |  |
| 3 | content | 文章内容 | longtext |  |  | |
