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
 Date: 18/07/2023 16:56:21
=======
 Date: 22/08/2023 17:33:19
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for event_user_allocated
-- ----------------------------
DROP TABLE IF EXISTS `event_user_allocated`;
CREATE TABLE `event_user_allocated`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主id',
  `user_id` bigint(20) NOT NULL COMMENT '分配userid',
<<<<<<< HEAD
=======
  `user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理这件事用户的类型',
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590
  `event_id` bigint(20) NOT NULL COMMENT '事件id',
  `inspect_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '检查情况',
  `rectify_opinion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '整改意见',
  `rectify_result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '整改结果',
  `inspect_location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '检查位置',
  `inspect_datetime` datetime NULL DEFAULT NULL COMMENT '检查日期时间',
  `exist_problem` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '存在问题',
  `photo_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '照片地址',
  `video_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频地址',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '事件处理状态，0未确认，1已确认，2已取消，3已完成',
<<<<<<< HEAD
  `allocated_user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分配任务',
  `allocate_user_id` bigint(20) NOT NULL COMMENT '分配任务用户的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88479174657 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;
=======
  `allocated_user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分配任务用户类型',
  `allocate_user_id` bigint(20) NOT NULL COMMENT '分配任务用户的id',
  `allocate_time` datetime NULL DEFAULT NULL COMMENT '下发时间',
  `deal_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '处置标志0代表网格员处理。\"1\"代表管理员处置',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 96965699607 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590

-- ----------------------------
-- Records of event_user_allocated
-- ----------------------------
<<<<<<< HEAD
INSERT INTO `event_user_allocated` VALUES (25130344448, 103, 29272059906, 'ok', 'ok', 'ok', '123', '2023-07-09 21:29:46', 'ok', NULL, NULL, '2', '管理员', 1);
INSERT INTO `event_user_allocated` VALUES (25130344449, 104, 29272059906, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1);
INSERT INTO `event_user_allocated` VALUES (30461476864, 102, 29272059906, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', '管理员', 1);
=======
INSERT INTO `event_user_allocated` VALUES (3623270400, 102, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, '2023-07-25 17:24:10', '0');
INSERT INTO `event_user_allocated` VALUES (13529245696, 102, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (13529245697, 103, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (13529245698, 104, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (13533440000, 105, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (13997670400, 102, NULL, 80421836800, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, '2023-08-22 17:16:25', '0');
INSERT INTO `event_user_allocated` VALUES (15580975104, 103, NULL, 20110227456, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 102, '2023-08-22 15:42:39', '0');
INSERT INTO `event_user_allocated` VALUES (25130344448, 103, NULL, 29272059906, 'ok', 'ok', 'ok', '123', '2023-07-09 21:29:46', 'ok', NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (25130344449, 104, NULL, 29272059906, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '3', '网格员长', 103, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (30461476864, 105, NULL, 29272059906, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '3', '网格员长', 103, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (37626165248, 102, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (37626165249, 103, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (37626165250, 104, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (37626165251, 105, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (40066256896, 102, NULL, 29272059906, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '3', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (49429811200, 103, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, '2023-07-26 10:45:03', '0');
INSERT INTO `event_user_allocated` VALUES (49535668224, 102, NULL, 29272059907, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (49535668225, 103, NULL, 29272059907, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '3', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (49535668226, 105, NULL, 29272059907, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '3', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (55981942784, 102, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (55981942785, 103, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (55981942786, 104, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (55981942787, 105, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (62475010048, 102, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (62479204352, 103, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (62479204353, 104, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (62479204354, 105, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (66819284992, 103, NULL, 17267777536, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', '管理员', 102, '2023-08-22 16:33:43', '0');
INSERT INTO `event_user_allocated` VALUES (67828627456, 102, NULL, 29272059906, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '3', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (67828627457, 103, NULL, 29272059906, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (67828627458, 104, NULL, 29272059906, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (77077145600, 102, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (77077145601, 103, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (77077145602, 104, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (77077145603, 105, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (81425920000, 102, NULL, 80421836800, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, '2023-08-22 17:14:18', '0');
INSERT INTO `event_user_allocated` VALUES (81425920001, 103, NULL, 80421836800, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, '2023-08-22 17:14:18', '0');
INSERT INTO `event_user_allocated` VALUES (81425920002, 104, NULL, 80421836800, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, '2023-08-22 17:14:18', '0');
INSERT INTO `event_user_allocated` VALUES (81425920003, 105, NULL, 80421836800, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, '2023-08-22 17:14:18', '0');
INSERT INTO `event_user_allocated` VALUES (81425920004, 108, NULL, 80421836800, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, '2023-08-22 17:14:18', '0');
INSERT INTO `event_user_allocated` VALUES (82594117632, 102, NULL, 29272059906, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (82594117633, 103, NULL, 29272059907, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (82594117634, 104, NULL, 29272059907, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '3', '网格员长', 103, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (88273678336, 104, NULL, 37147518976, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, '2023-08-01 09:04:34', '0');
INSERT INTO `event_user_allocated` VALUES (88273678337, 108, NULL, 37147518976, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, '2023-08-01 09:04:34', '0');
INSERT INTO `event_user_allocated` VALUES (88479174658, 103, NULL, 29272059906, '2', '4', '5', '1', '2023-07-21 00:00:00', '3', '/profile/upload/2023/07/19/c_20230719175610A001.jpg,/profile/upload/2023/07/19/b_20230719175612A002.jpg', '/profile/upload/2023/07/19/VID20230709113934_20230719175627A003.mp4', '3', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (88479174659, 1, NULL, 465504256, '5425', '425', '452', '4532', '2023-07-13 00:00:00', '452', '/profile/upload/2023/07/24/寝室1_20230724160914A001.jpg', NULL, '2', '管理员', 1, NULL, '1');
INSERT INTO `event_user_allocated` VALUES (88479174660, 1, NULL, 465504256, '2', '4', '5', '1', '2023-07-12 00:00:00', '3', '/profile/upload/2023/07/24/工位1_20230724161251A001.jpg', NULL, '3', '管理员', 1, NULL, '1');
INSERT INTO `event_user_allocated` VALUES (96163074048, 105, NULL, 37147518976, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, '2023-08-01 09:15:43', '0');
INSERT INTO `event_user_allocated` VALUES (96961505280, 102, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (96965699584, 103, NULL, 465504256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1, NULL, '0');
INSERT INTO `event_user_allocated` VALUES (96965699585, 1, NULL, 465504256, '1452', '254', '2542', '4524', '2023-07-30 23:38:08', '452', '/profile/upload/2023/07/30/吴京_20230730233845A002.png,/profile/upload/2023/07/30/无标题_20230730233845A001.png', NULL, '3', '管理员', 1, NULL, '1');
INSERT INTO `event_user_allocated` VALUES (96965699586, 1, NULL, 37147518976, '123', '123', '123', '123', '2023-08-09 00:00:00', '123', '/profile/upload/2023/08/01/OIP-C_20230801092145A007.jpg', '/profile/upload/2023/08/01/VID20221130141435_20230801092149A008.mp4', '2', '管理员', 1, NULL, '1');
INSERT INTO `event_user_allocated` VALUES (96965699587, 1, NULL, 37147518976, '123', '22', '22', '123', '2023-08-08 00:00:00', '222', '/profile/upload/2023/08/01/OIP-C_20230801092351A009.jpg', NULL, '2', '管理员', 1, NULL, '1');
INSERT INTO `event_user_allocated` VALUES (96965699588, 1, NULL, 37147518976, '2', '4', '5', '1', '2023-08-09 00:00:00', '3', '/profile/upload/2023/08/01/无标题_20230801150028A001.png,/profile/upload/2023/08/01/吴京_20230801150028A002.png', '/profile/upload/2023/08/01/VID20221130141435_20230801150031A003.mp4', '2', '管理员', 1, NULL, '1');
INSERT INTO `event_user_allocated` VALUES (96965699589, 1, NULL, 37147518976, '1', '411', '1', '4', '2023-08-03 00:00:00', '1', '/profile/upload/2023/08/01/OIP-C_20230801150423A001.jpg', '/profile/upload/2023/08/01/VID20221130141435_20230801150427A002.mp4', '2', '管理员', 1, NULL, '1');
INSERT INTO `event_user_allocated` VALUES (96965699590, 1, NULL, 37147518976, '1', '411', '1', '4', '2023-08-03 00:00:00', '1', '/profile/upload/2023/08/01/OIP-C_20230801150423A001.jpg', '/profile/upload/2023/08/01/VID20221130141435_20230801150427A002.mp4', '3', '管理员', 1, '2023-08-01 15:07:40', '1');
INSERT INTO `event_user_allocated` VALUES (96965699591, 1, NULL, 37147518976, '45', NULL, '578', '1', '2023-08-05 00:00:00', '57', '/profile/upload/2023/08/01/吴京_20230801151720A001.png,/profile/upload/2023/08/01/OIP-C_20230801151720A002.jpg', '/profile/upload/2023/08/01/VID20221130141435_20230801151724A003.mp4', '3', '管理员', 1, '2023-08-01 15:17:26', '1');
INSERT INTO `event_user_allocated` VALUES (96965699592, 102, NULL, 20110227456, '12.1', '12.', '12.', '2.', '2023-08-08 00:00:00', '2.12.', '/profile/upload/2023/08/22/OIP-C_20230822154252A002.jpg', NULL, '3', '管理员', 102, '2023-08-22 15:42:54', '1');
INSERT INTO `event_user_allocated` VALUES (96965699593, 102, NULL, 17267777536, '456', '645', '6456', '45', '2023-08-17 00:00:00', '45', '/profile/upload/2023/08/22/OIP-C_20230822163355A002.jpg', NULL, '3', '管理员', 102, '2023-08-22 16:33:58', '1');
INSERT INTO `event_user_allocated` VALUES (96965699594, 1, NULL, 80421836800, '123', '123', '123', '123', '2023-08-09 00:00:00', '213', NULL, NULL, '2', '管理员', 1, '2023-08-22 16:45:23', '1');
INSERT INTO `event_user_allocated` VALUES (96965699595, 1, NULL, 80421836800, 'asd', 'asd', 'asd', 'asd', '2023-08-02 00:00:00', 'dsa', NULL, NULL, '2', '管理员', 1, '2023-08-22 16:46:12', '1');
INSERT INTO `event_user_allocated` VALUES (96965699596, 1, NULL, 80421836800, '4345', '4534', '343', '53', '2023-08-09 00:00:00', '3453', NULL, NULL, '2', '管理员', 1, '2023-08-22 16:47:53', '1');
INSERT INTO `event_user_allocated` VALUES (96965699597, 1, NULL, 80421836800, 'as', 'sa', 'as', 'as', '2023-08-09 00:00:00', 'sa', NULL, NULL, '2', '管理员', 1, '2023-08-22 16:51:34', '1');
INSERT INTO `event_user_allocated` VALUES (96965699598, 1, NULL, 29272059908, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '3', '管理员', 1, '2023-08-22 17:01:20', '1');
INSERT INTO `event_user_allocated` VALUES (96965699599, 1, NULL, 29272059908, '4', '4', NULL, '', '2023-08-16 00:00:00', NULL, NULL, NULL, '3', '管理员', 1, '2023-08-22 17:01:52', '1');
INSERT INTO `event_user_allocated` VALUES (96965699600, 1, NULL, 29272059908, '14', NULL, NULL, '14', NULL, NULL, NULL, NULL, '3', '管理员', 1, '2023-08-22 17:02:01', '1');
INSERT INTO `event_user_allocated` VALUES (96965699601, 1, NULL, 29272059908, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '3', '管理员', 1, '2023-08-22 17:02:10', '1');
INSERT INTO `event_user_allocated` VALUES (96965699602, 1, NULL, 29272059908, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, '3', '管理员', 1, '2023-08-22 17:03:10', '1');
INSERT INTO `event_user_allocated` VALUES (96965699603, 1, NULL, 29272059908, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '3', '管理员', 1, '2023-08-22 17:05:52', '1');
INSERT INTO `event_user_allocated` VALUES (96965699604, 1, NULL, 29272059908, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '3', '管理员', 1, '2023-08-22 17:07:17', '1');
INSERT INTO `event_user_allocated` VALUES (96965699605, 1, NULL, 80421836800, '1', '1', '1', '1', '2023-08-22 17:12:08', '1', '/profile/upload/2023/08/22/OIP-C_20230822171204A009.jpg', NULL, '2', '管理员', 1, '2023-08-22 17:12:12', '1');
INSERT INTO `event_user_allocated` VALUES (96965699606, 1, NULL, 80421836800, '123', '123', '123', '123', '2023-08-16 00:00:00', '123', '/profile/upload/2023/08/22/OIP-C_20230822171515A010.jpg', NULL, '2', '管理员', 1, '2023-08-22 17:15:25', '1');
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590

SET FOREIGN_KEY_CHECKS = 1;
