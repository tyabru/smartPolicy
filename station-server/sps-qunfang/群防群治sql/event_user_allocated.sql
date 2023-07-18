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

 Date: 18/07/2023 16:56:21
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
  `allocated_user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分配任务',
  `allocate_user_id` bigint(20) NOT NULL COMMENT '分配任务用户的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88479174657 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of event_user_allocated
-- ----------------------------
INSERT INTO `event_user_allocated` VALUES (25130344448, 103, 29272059906, 'ok', 'ok', 'ok', '123', '2023-07-09 21:29:46', 'ok', NULL, NULL, '2', '管理员', 1);
INSERT INTO `event_user_allocated` VALUES (25130344449, 104, 29272059906, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '管理员', 1);
INSERT INTO `event_user_allocated` VALUES (30461476864, 102, 29272059906, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', '管理员', 1);

SET FOREIGN_KEY_CHECKS = 1;
