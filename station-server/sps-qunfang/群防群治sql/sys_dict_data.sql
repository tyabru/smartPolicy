/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : sq_database

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 22/08/2023 17:34:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(11) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 114 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '其他操作');
INSERT INTO `sys_dict_data` VALUES (19, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (20, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (21, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (22, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (23, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (24, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (25, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (26, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (27, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (28, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (29, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2023-06-20 15:41:47', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (100, 0, '未确认', '0', 'event_status', NULL, 'default', 'N', '0', 'admin', '2023-06-15 15:20:57', 'admin', '2023-06-15 15:22:11', '未确认');
INSERT INTO `sys_dict_data` VALUES (101, 0, '处理中', '1', 'event_status', NULL, 'default', 'N', '0', 'admin', '2023-06-15 15:23:04', 'admin', '2023-06-15 15:23:18', NULL);
INSERT INTO `sys_dict_data` VALUES (102, 0, '已完成', '2', 'event_status', NULL, 'default', 'N', '0', 'admin', '2023-06-15 15:23:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (103, 0, '已下发待提交', '0', 'threat_allocate_status', NULL, 'default', 'N', '0', 'admin', '2023-06-22 17:04:03', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (104, 1, '已提交待确认', '1', 'threat_allocate_status', NULL, 'default', 'N', '0', 'admin', '2023-06-22 17:04:42', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (105, 2, '已取消', '2', 'threat_allocate_status', NULL, 'default', 'N', '0', 'admin', '2023-06-22 17:05:00', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (106, 3, '已完成', '3', 'threat_allocate_status', NULL, 'default', 'N', '0', 'admin', '2023-06-22 17:05:08', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (107, 0, '普通用户', '0', 'common_user_type', NULL, 'default', 'N', '0', 'admin', '2023-07-06 09:54:28', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (108, 1, '网格员', '1', 'common_user_type', NULL, 'default', 'N', '0', 'admin', '2023-07-06 09:54:57', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (109, 2, '网格员长', '2', 'common_user_type', NULL, 'default', 'N', '0', 'admin', '2023-07-06 09:55:25', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (110, 0, '违法犯罪线索', '0', 'event_type', NULL, 'default', 'N', '0', 'admin', '2023-07-07 21:28:00', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (111, 1, '公共安全隐患', '1', 'event_type', NULL, 'default', 'N', '0', 'admin', '2023-07-07 21:28:23', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (112, 2, '意见建议', '2', 'event_type', NULL, 'default', 'N', '0', 'admin', '2023-07-07 21:28:54', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (113, 3, '其它', '3', 'event_type', NULL, 'default', 'N', '0', 'admin', '2023-07-07 21:29:11', '', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
