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

 Date: 18/07/2023 16:56:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for event_info
-- ----------------------------
DROP TABLE IF EXISTS `event_info`;
CREATE TABLE `event_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '事件id',
  `upload_user_id` bigint(20) NULL DEFAULT NULL COMMENT '提交用户id',
  `event_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '事件类别',
  `contact_person_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人姓名',
  `contact_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发生地址',
  `address_data` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发生地址经纬度信息',
  `event_summarize` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '事件概述',
  `event_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '事件描述',
  `occur_time` datetime NULL DEFAULT NULL COMMENT '发生时间',
  `photo_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '照片地址',
  `video_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频地址',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理状态 0未确认，1已确认，2已完成',
  `reply` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '完成时提供反馈信息',
  `upload_time` datetime NULL DEFAULT NULL COMMENT '事件上传时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of event_info
-- ----------------------------
INSERT INTO `event_info` VALUES (29272059906, 1, '1', '123123', '1231254', NULL, NULL, 'BBB', NULL, NULL, '/profile/photo/a.jpg,/profile/photo/b.jpg,/profile/photo/c.jpg', '/profile/video/VID20230709113934.mp4', '1', NULL, '2023-07-04 11:41:49', NULL);
INSERT INTO `event_info` VALUES (29272059907, 1, '2', '7857', '5875', '25425', NULL, 'ccc', NULL, NULL, NULL, NULL, '0', NULL, '2023-07-04 11:45:14', NULL);
INSERT INTO `event_info` VALUES (29272059908, 1, '3', 'sass', 'sas', 'sas1', NULL, 'ddd', NULL, NULL, NULL, NULL, '0', NULL, '2023-07-04 11:48:15', NULL);
INSERT INTO `event_info` VALUES (80421836800, 1, '0', 'dsad', 'sd', 'dsa', 'sd', 'AAA', NULL, NULL, NULL, NULL, '0', NULL, '2023-07-04 11:51:16', NULL);

SET FOREIGN_KEY_CHECKS = 1;
