/*
 Navicat Premium Data Transfer

 Source Server         : easyadmin
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : easy_data

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 07/01/2019 21:24:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for job_execution_log
-- ----------------------------
DROP TABLE IF EXISTS `job_execution_log`;
CREATE TABLE `job_execution_log`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `task_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `hostname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sharding_item` int(11) NOT NULL,
  `execution_source` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `failure_cause` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_success` int(11) NOT NULL,
  `start_time` timestamp(0) NULL DEFAULT NULL,
  `complete_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for job_status_trace_log
-- ----------------------------
DROP TABLE IF EXISTS `job_status_trace_log`;
CREATE TABLE `job_status_trace_log`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `original_task_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `task_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `slave_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `source` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `execution_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sharding_item` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `message` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creation_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `TASK_ID_STATE_INDEX`(`task_id`, `state`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_auth_data_model
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_data_model`;
CREATE TABLE `sys_auth_data_model`  (
  `model_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `model_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型编号',
  `model_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型名称',
  `service_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'service名称',
  `func_nos` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '那些功能号需要验证数据权限',
  `field` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验证字段',
  PRIMARY KEY (`model_id`) USING BTREE,
  UNIQUE INDEX `AK_Key_2`(`model_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据权限模型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_auth_role_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_role_data`;
CREATE TABLE `sys_auth_role_data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `model_id` bigint(20) NULL DEFAULT NULL COMMENT '模型id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `auth_data_ids` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '数据权限集合',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `AK_Key_2`(`model_id`, `role_id`) USING BTREE,
  INDEX `FK_Reference_22`(`role_id`) USING BTREE,
  CONSTRAINT `FK_Reference_21` FOREIGN KEY (`model_id`) REFERENCES `sys_auth_data_model` (`model_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_22` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色数据权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_enum_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_enum_type`;
CREATE TABLE `sys_enum_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enum_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典类型英文编号',
  `enum_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型中文名称',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父id',
  `is_system` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否系统参数1：是',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `AK_sys_enum_type_no`(`enum_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_enum_value
-- ----------------------------
DROP TABLE IF EXISTS `sys_enum_value`;
CREATE TABLE `sys_enum_value`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enum_type_id` bigint(20) NOT NULL COMMENT '字典类型id',
  `item_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '枚举值',
  `item_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '枚举名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `state` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态,1.有效,0.无效',
  `orderline` bigint(20) NULL DEFAULT NULL COMMENT '排序值',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `AK_Key_2`(`enum_type_id`, `item_key`) USING BTREE,
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`enum_type_id`) REFERENCES `sys_enum_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典枚举值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group`  (
  `group_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分组名称',
  `font_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标信息',
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统分组(站点)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `func_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能号',
  `version` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能号版本',
  `func_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能号名称',
  `datetime` bigint(20) NULL DEFAULT NULL COMMENT '执行时间',
  `request_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `error_no` bigint(20) NULL DEFAULT NULL COMMENT '错误码',
  `error_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '错误消息',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log_content
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_content`;
CREATE TABLE `sys_log_content`  (
  `id` bigint(20) NOT NULL,
  `in_param` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '入参',
  `out_param` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '出参',
  `system_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '系统信息',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单中文名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `state` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态,1.有效,0.无效',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父栏目id',
  `link_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '后台请求url',
  `font_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标信息',
  `route` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由值，记录其所有的上级ID号，用|分开　若是根，则为空',
  `orderline` bigint(20) NULL DEFAULT NULL COMMENT '排序值',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `group_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE,
  INDEX `FK_Reference_9`(`group_id`) USING BTREE,
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`group_id`) REFERENCES `sys_group` (`group_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_function`;
CREATE TABLE `sys_menu_function`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能名称',
  `link_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问url',
  `funcs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能号权限集合,',
  `orderline` bigint(20) NULL DEFAULT NULL COMMENT '排序值',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_8`(`menu_id`) USING BTREE,
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单功能表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `org_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构编号',
  `org_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父id',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_sys_org`(`org_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织机构管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_org_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_user`;
CREATE TABLE `sys_org_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '机构id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `AK_Key_4`(`org_id`, `user_id`) USING BTREE,
  INDEX `FK_Reference_11`(`user_id`) USING BTREE,
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`org_id`) REFERENCES `sys_org` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织机构用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_parameter_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameter_type`;
CREATE TABLE `sys_parameter_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文编号',
  `param_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文名称',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父id',
  `is_system` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否系统参数1：是',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_sys_parameter_type`(`param_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统参数类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_parameter_value
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameter_value`;
CREATE TABLE `sys_parameter_value`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_type_id` bigint(20) NOT NULL COMMENT '类型id',
  `item_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '枚举key',
  `item_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '枚举value',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `state` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态,1.有效,0.无效',
  `encrypt` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否加密,1:加密',
  `orderline` bigint(20) NULL DEFAULT NULL COMMENT '排序值',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `AK_Key_2`(`param_type_id`, `item_key`) USING BTREE,
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`param_type_id`) REFERENCES `sys_parameter_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统参数值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `parent_Id` bigint(20) NULL DEFAULT NULL COMMENT '父角色id',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `route` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由值，记录其所有的上级ID号，用|分开　若是根，则为空',
  `orderline` bigint(20) NULL DEFAULT NULL COMMENT '排序值',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `menu_ids` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '菜单集合',
  `menu_function_ids` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '菜单功能集合',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_7`(`role_id`) USING BTREE,
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_5`(`user_id`) USING BTREE,
  INDEX `FK_Reference_6`(`role_id`) USING BTREE,
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `login_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `state` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户状态(0.未激活,1.正常,2.锁定,3.注销)',
  `is_system` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否超级管理员(1.是)',
  `salt` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密密码的盐',
  `login_count` bigint(20) NULL DEFAULT NULL COMMENT '登录次数',
  `last_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `last_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登录ip',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `head_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `AK_login_no`(`login_no`) USING BTREE,
  UNIQUE INDEX `AK_phone`(`phone`) USING BTREE,
  UNIQUE INDEX `AK_email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_login_log`;
CREATE TABLE `sys_user_login_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录ip',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型(1:成功,2:用户名不存在,3:密码不正确,4:用户已经锁定,5:用户已经注销,6.用户状态不正常)',
  `login_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录类型(1:用户名,2:手机号码,3:邮箱)',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_pwd_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_pwd_log`;
CREATE TABLE `sys_user_pwd_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `salt` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密密码的盐',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型（1:密码重置,2:密码修改）',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_10`(`user_id`) USING BTREE,
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户密码历史表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
