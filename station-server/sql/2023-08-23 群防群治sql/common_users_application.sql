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

 Date: 22/08/2023 17:33:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for common_users_application
-- ----------------------------
DROP TABLE IF EXISTS `common_users_application`;
CREATE TABLE `common_users_application`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主id',
  `user_id` bigint(20) NOT NULL COMMENT '普通用户id',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
  `application_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '申请类型',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理状态，0未处理，1已接受，2已拒绝',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of common_users_application
-- ----------------------------
INSERT INTO `common_users_application` VALUES (1, 102, 'SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS', '2', '1');
INSERT INTO `common_users_application` VALUES (2, 104, '你好1111', '2', '1');

SET FOREIGN_KEY_CHECKS = 1;
