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

 Date: 22/08/2023 17:32:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for allocate_rectify_content
-- ----------------------------
DROP TABLE IF EXISTS `allocate_rectify_content`;
CREATE TABLE `allocate_rectify_content`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `allocate_id` bigint(50) NOT NULL COMMENT '分配事件id',
  `send_time` datetime NULL DEFAULT NULL COMMENT '下发时间',
  `rectify_opinion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改意见',
  `confirmed` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否被确认，0为否，1为是',
  `send_user_id` bigint(20) NOT NULL COMMENT '下发用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of allocate_rectify_content
-- ----------------------------
INSERT INTO `allocate_rectify_content` VALUES (13, 3623270400, '2023-07-27 09:43:07', '修改一下', '0', 1);
INSERT INTO `allocate_rectify_content` VALUES (15, 88273678336, '2023-08-01 09:08:04', '做好一点', '0', 1);
INSERT INTO `allocate_rectify_content` VALUES (17, 88273678336, '2023-08-01 09:16:22', '66', '0', 1);
INSERT INTO `allocate_rectify_content` VALUES (18, 88273678336, '2023-08-01 10:32:52', '666', '0', 1);
INSERT INTO `allocate_rectify_content` VALUES (19, 88273678336, '2023-08-01 15:18:42', '26', '0', 1);
INSERT INTO `allocate_rectify_content` VALUES (20, 66819284992, '2023-08-22 16:34:29', '4444', '0', 102);

SET FOREIGN_KEY_CHECKS = 1;
