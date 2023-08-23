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

<<<<<<< HEAD
 Date: 18/07/2023 16:56:01
=======
 Date: 22/08/2023 17:33:12
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590
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
<<<<<<< HEAD
=======
  `upload_user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '管理员' COMMENT '提交用户类型',
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590
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
<<<<<<< HEAD
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;
=======
  `finish_time` datetime NULL DEFAULT NULL COMMENT '归档时间',
  `finish_user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '归档用户名',
  `finish_user_id` bigint(50) NULL DEFAULT NULL COMMENT '归档用户id',
  `dept_id` bigint(50) NULL DEFAULT NULL COMMENT '归属部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1669602029272059909 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590

-- ----------------------------
-- Records of event_info
-- ----------------------------
<<<<<<< HEAD
INSERT INTO `event_info` VALUES (29272059906, 1, '1', '123123', '1231254', NULL, NULL, 'BBB', NULL, NULL, '/profile/photo/a.jpg,/profile/photo/b.jpg,/profile/photo/c.jpg', '/profile/video/VID20230709113934.mp4', '1', NULL, '2023-07-04 11:41:49', NULL);
INSERT INTO `event_info` VALUES (29272059907, 1, '2', '7857', '5875', '25425', NULL, 'ccc', NULL, NULL, NULL, NULL, '0', NULL, '2023-07-04 11:45:14', NULL);
INSERT INTO `event_info` VALUES (29272059908, 1, '3', 'sass', 'sas', 'sas1', NULL, 'ddd', NULL, NULL, NULL, NULL, '0', NULL, '2023-07-04 11:48:15', NULL);
INSERT INTO `event_info` VALUES (80421836800, 1, '0', 'dsad', 'sd', 'dsa', 'sd', 'AAA', NULL, NULL, NULL, NULL, '0', NULL, '2023-07-04 11:51:16', NULL);
=======
INSERT INTO `event_info` VALUES (465504256, 106, '普通用户', '0', '456', '15320250549', '786', NULL, NULL, '666', '2023-07-31 00:15:18', '/profile/upload/2023/07/31/吴京_20230731001447A001.png', NULL, '2', 'ok', '2023-07-21 11:03:17', NULL, '2023-07-23 20:39:36', NULL, NULL, 218);
INSERT INTO `event_info` VALUES (17267777536, 102, '管理员', '1', '2', '15320250549', '重庆市', NULL, NULL, '1213', '2023-08-15 00:00:00', '/profile/upload/2023/08/22/OIP-C_20230822163324A001.jpg', NULL, '1', NULL, '2023-08-22 16:33:31', NULL, NULL, NULL, NULL, 217);
INSERT INTO `event_info` VALUES (29272059906, 107, '普通用户', '1', '123123', '15320250549', NULL, NULL, 'BBB', '发生什么事了！', NULL, '/profile/photo/a.jpg,/profile/photo/c.jpg', '/profile/video/VID20230709113934.mp4', '2', '123', '2023-07-04 11:41:49', NULL, '2023-07-23 20:39:40', NULL, NULL, 201);
INSERT INTO `event_info` VALUES (29272059907, 106, '普通用户', '2', '7857', '5875', '25425', NULL, 'ccc', NULL, NULL, NULL, NULL, '2', '1', '2023-07-04 11:45:14', NULL, '2023-07-12 20:39:43', NULL, NULL, 201);
INSERT INTO `event_info` VALUES (29272059908, 106, '普通用户', '3', 'sass', 'sas', 'sas1', NULL, 'ddd', NULL, NULL, NULL, NULL, '1', NULL, '2023-07-04 11:48:15', NULL, '2023-07-19 20:39:46', NULL, NULL, 201);
INSERT INTO `event_info` VALUES (37147518976, 1, '管理员', '1', '小王', '15320250549', '重庆市', NULL, '15', '123123', '2023-08-01 09:00:16', '/profile/upload/2023/08/01/吴京_20230801090026A004.png,/profile/upload/2023/08/01/OIP-C_20230801090026A005.jpg', '/profile/upload/2023/08/01/VID20221130141435_20230801090300A006.mp4', '2', '1', '2023-08-01 09:03:10', NULL, NULL, NULL, NULL, 220);
INSERT INTO `event_info` VALUES (80421836800, 106, '普通用户', '0', '53354', '15320250549', '重庆市', 'sd', 'AAA', '1', '2023-08-22 16:41:15', '/profile/upload/2023/08/22/OIP-C_20230822172943A001.jpg', NULL, '1', NULL, '2023-07-04 11:51:16', NULL, '2023-07-04 20:39:48', NULL, NULL, 202);
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590

SET FOREIGN_KEY_CHECKS = 1;
