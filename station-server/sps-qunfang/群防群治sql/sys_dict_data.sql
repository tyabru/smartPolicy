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

 Date: 18/07/2023 16:54:59
*/



INSERT INTO `sys_dict_data` VALUES (, 0, '未确认', '0', 'event_status', NULL, 'default', 'N', '0', 'admin', '2023-06-15 15:20:57', 'admin', '2023-06-15 15:22:11', '未确认');
INSERT INTO `sys_dict_data` VALUES (, 0, '处理中', '1', 'event_status', NULL, 'default', 'N', '0', 'admin', '2023-06-15 15:23:04', 'admin', '2023-06-15 15:23:18', NULL);
INSERT INTO `sys_dict_data` VALUES (, 0, '已完成', '2', 'event_status', NULL, 'default', 'N', '0', 'admin', '2023-06-15 15:23:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (, 0, '未确认', '0', 'threat_allocate_status', NULL, 'default', 'N', '0', 'admin', '2023-06-22 17:04:03', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (, 1, '处理中', '1', 'threat_allocate_status', NULL, 'default', 'N', '0', 'admin', '2023-06-22 17:04:42', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (, 2, '已取消', '2', 'threat_allocate_status', NULL, 'default', 'N', '0', 'admin', '2023-06-22 17:05:00', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (, 3, '已完成', '3', 'threat_allocate_status', NULL, 'default', 'N', '0', 'admin', '2023-06-22 17:05:08', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (, 0, '普通用户', '0', 'common_user_type', NULL, 'default', 'N', '0', 'admin', '2023-07-06 09:54:28', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (, 1, '网格员', '1', 'common_user_type', NULL, 'default', 'N', '0', 'admin', '2023-07-06 09:54:57', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (, 2, '网格员长', '2', 'common_user_type', NULL, 'default', 'N', '0', 'admin', '2023-07-06 09:55:25', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (, 0, '违法犯罪线索', '0', 'event_type', NULL, 'default', 'N', '0', 'admin', '2023-07-07 21:28:00', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (, 1, '公共安全隐患', '1', 'event_type', NULL, 'default', 'N', '0', 'admin', '2023-07-07 21:28:23', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (, 2, '意见建议', '2', 'event_type', NULL, 'default', 'N', '0', 'admin', '2023-07-07 21:28:54', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (, 3, '其它', '3', 'event_type', NULL, 'default', 'N', '0', 'admin', '2023-07-07 21:29:11', '', NULL, NULL);
