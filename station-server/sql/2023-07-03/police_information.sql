/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3308
 Source Schema         : sq_database

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 03/07/2023 09:22:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for police_information
-- ----------------------------
DROP TABLE IF EXISTS `police_information`;
CREATE TABLE `police_information`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '系统自动生成编码',
  `police_number` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '警号',
  `police_name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
  `gender` int NOT NULL DEFAULT 1 COMMENT '性别0女1男',
  `police_age` int NULL DEFAULT NULL COMMENT '年龄',
  `nation` char(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '民族',
  `birthday` varchar(12) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '出生日期',
  `id_card` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '身份证号码',
  `phone_number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '警员邮箱',
  `identity_type` int NOT NULL DEFAULT 0 COMMENT '政治面貌0党员1团员2群众',
  `education` int NULL DEFAULT NULL COMMENT '学历',
  `graduation_school` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '毕业院校',
  `police_post` int NOT NULL DEFAULT 0 COMMENT '警察类型',
  `police_type` int NULL DEFAULT NULL COMMENT '所属岗位',
  `speciality` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '技能专长',
  `dept_id` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属派出所ID',
  `police_photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '警员照片',
  `community_code` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '小区编码',
  `address_code` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '居住地址编码',
  `entry_time` date NULL DEFAULT NULL COMMENT '入职时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime NULL DEFAULT NULL COMMENT '删除时间',
  `operate_time` datetime NOT NULL COMMENT '操作时间',
  `operate_type` int NOT NULL COMMENT '操作类型0新增1修改2删除',
  `operate_name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '操作人姓名',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `INDEX_POLICE_ID_NUMBER`(`police_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '警员基本信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of police_information
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
