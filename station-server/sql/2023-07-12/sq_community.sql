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

 Date: 12/07/2023 10:05:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sq_community
-- ----------------------------
DROP TABLE IF EXISTS `sq_community`;
CREATE TABLE `sq_community`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(21) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '小区编码',
  `name` varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '小区名称',
  `type` tinyint NULL DEFAULT NULL COMMENT '0 小区 1 村',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小区详细地址',
  `community` varchar(21) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属社区',
  `remark` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `preventive_measures` tinyint NULL DEFAULT NULL COMMENT '防范措施 人防 物防 技防',
  `is_safe_area` tinyint NULL DEFAULT NULL COMMENT '是否是安全小区',
  `naming_organ` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '命名机关',
  `naming_time` datetime NULL DEFAULT NULL COMMENT '命名时间',
  `create_time` datetime NULL DEFAULT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '小区（村）基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_community
-- ----------------------------
INSERT INTO `sq_community` VALUES (8, '610113530123456789143', '东新城花园', 0, '发生发撒佛奥利弗骄傲', '117', NULL, 1, 0, '是发发发', '2023-07-09 00:00:00', '2023-07-07 11:14:44', 'admin', '2023-07-10 09:28:01', 'admin');
INSERT INTO `sq_community` VALUES (9, '610113530123456789146', '东城新村', 1, '发撒发撒发撒法', '117', NULL, 1, 0, '飞洒发发', '2023-07-06 00:00:00', '2023-07-07 15:08:00', 'admin', NULL, NULL);

-- ----------------------------
-- Table structure for sq_community_desc
-- ----------------------------
DROP TABLE IF EXISTS `sq_community_desc`;
CREATE TABLE `sq_community_desc`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `community_id` int NOT NULL DEFAULT -1 COMMENT '小区id',
  `group_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'default' COMMENT '附件分组标题 默认default',
  `file_desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件描述,此文件的作用',
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件URL',
  `file_format` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件后缀/文件格式',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `xq_id`(`community_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_community_desc
-- ----------------------------
INSERT INTO `sq_community_desc` VALUES (29, 8, '小区平面图', '微信图片_20230607144957.jpg', '/profile//2023/07/10/微信图片_20230607144957_20230710092800A002.jpg', 'image/jpeg', '2023-07-10 09:28:01', 'admin');

-- ----------------------------
-- Table structure for sq_community_detail
-- ----------------------------
DROP TABLE IF EXISTS `sq_community_detail`;
CREATE TABLE `sq_community_detail`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `community_id` int NOT NULL COMMENT '小区编码',
  `village_id` int NULL DEFAULT NULL COMMENT '关联小区id',
  `village_code` varchar(21) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联小区编码',
  `full_area` decimal(10, 2) NULL DEFAULT NULL COMMENT '总面积',
  `center_point` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小区中心坐标 【经度，维度】',
  `xq_region` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '小区别界坐标集合 用于地图画区',
  `building_count` int NULL DEFAULT NULL COMMENT '小区包含楼栋数',
  `person` int NULL DEFAULT 0 COMMENT '总人口数',
  `resident` int NULL DEFAULT 0 COMMENT '常驻人口数',
  `important` int NULL DEFAULT 0 COMMENT '重点关注人口数',
  `head_master` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `master_phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人联系电话',
  `police` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责民警',
  `police_phone` varchar(21) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民警联系电话',
  `police_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责民警姓名',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `community_id`(`community_id`) USING BTREE,
  INDEX `dept_id`(`village_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '小区/村基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_community_detail
-- ----------------------------
INSERT INTO `sq_community_detail` VALUES (6, 117, 8, '610113530123456789143', 123213.00, '123,123', NULL, 12, 0, 0, 0, 'AADad', '15634158871', '101', '15634125864', '张三丰', '2023-07-07 11:14:44', '2023-07-10 09:30:21', 'admin', 'admin');
INSERT INTO `sq_community_detail` VALUES (7, 117, 9, '610113530123456789146', 213.00, '121,80', NULL, 12, 0, 0, 0, '张三', '15634158871', '101', '15634125864', '张三丰', '2023-07-07 15:08:00', '2023-07-07 16:12:00', 'admin', NULL);

-- ----------------------------
-- Table structure for sq_community_structure
-- ----------------------------
DROP TABLE IF EXISTS `sq_community_structure`;
CREATE TABLE `sq_community_structure`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `community_id` int NOT NULL COMMENT '小区外键',
  `community_code` varchar(21) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '小区编码',
  `community_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小区名称',
  `meta_addr_id` varchar(21) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小区结构元数据编码',
  `full_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址全称',
  `meta_level` int NULL DEFAULT NULL COMMENT '地址级别 10 小区 11楼栋 12 单元 13 层 14 房屋',
  `parent_id` int NULL DEFAULT NULL COMMENT '上级编码',
  `dept_id` varchar(21) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门',
  `short_name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址简称',
  `region` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属区域',
  `region_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `meta_addr_id`(`meta_addr_id`) USING BTREE,
  INDEX `area_id`(`community_id`) USING BTREE,
  CONSTRAINT `sq_community_structure_ibfk_1` FOREIGN KEY (`community_id`) REFERENCES `sq_community_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '小区房屋结构和地址信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_community_structure
-- ----------------------------

-- ----------------------------
-- Table structure for sq_person_resident
-- ----------------------------
DROP TABLE IF EXISTS `sq_person_resident`;
CREATE TABLE `sq_person_resident`  (
  `id` int NOT NULL,
  `bm` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '人员编码',
  `xq_id` int NOT NULL COMMENT '社区主键id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '人员姓名',
  `cert_no` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号',
  `phone` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系方式',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '居住地址',
  `meta_addr_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址编码',
  `identity` int NOT NULL DEFAULT -1 COMMENT '与房东关系（本人、家人等）',
  `native_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `living_date` datetime NOT NULL COMMENT '入住日期',
  `face_img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '人脸图片地址（这个或许会放到人脸库）',
  `features` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '人脸特征值 （这个或许会放到人脸库）',
  `company` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司',
  `career` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
  `create_by` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后操作时间',
  `update_by` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后更新用户',
  `is_important` tinyint NULL DEFAULT NULL COMMENT '是否是重点人员',
  `attention_level` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关注的级别 0绿 1黄 2红',
  `source_platform` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据来源平台（群众自主、警员录入、网页、三方）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `xq_id`(`xq_id`) USING BTREE,
  INDEX `meta_addr_id`(`meta_addr_id`) USING BTREE,
  CONSTRAINT `sq_person_resident_ibfk_1` FOREIGN KEY (`xq_id`) REFERENCES `sq_community` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sq_person_resident_ibfk_2` FOREIGN KEY (`meta_addr_id`) REFERENCES `sq_community_structure` (`meta_addr_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '社区常驻人口表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_person_resident
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_code` varchar(21) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编码/应对公安内部的标准编码',
  `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `dept_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门类型',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE,
  INDEX `dept_type`(`dept_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 118 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '610524000000', '合阳县公安局', '1', 0, '合阳县公安局', '15888888888', 'hyxga@email.com', '0', '0', 'admin', '2023-06-20 15:41:46', 'admin', '2023-06-26 17:07:56');
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '610524201000', '金水派出所', '101', 15, NULL, NULL, NULL, '0', '0', '', NULL, 'admin', '2020-11-04 17:17:10');
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '610524202000', '九龙派出所', '101', 13, NULL, NULL, NULL, '0', '0', '', NULL, 'admin', '2020-11-04 17:16:29');
INSERT INTO `sys_dept` VALUES (103, 100, '0,100', '610524204000', '南蔡派出所', '101', 12, NULL, NULL, NULL, '0', '0', '', NULL, 'admin', '2020-11-04 17:16:07');
INSERT INTO `sys_dept` VALUES (104, 100, '0,100', '610524206000', '坊镇派出所', '101', 14, NULL, NULL, NULL, '0', '0', '', NULL, 'admin', '2020-11-04 17:16:44');
INSERT INTO `sys_dept` VALUES (105, 100, '0,100', '610524208000', '洽川派出所', '101', 7, NULL, NULL, NULL, '0', '0', '', NULL, 'admin', '2020-11-04 17:14:50');
INSERT INTO `sys_dept` VALUES (106, 100, '0,100', '610524209000', '路井派出所', '101', 3, NULL, NULL, NULL, '0', '0', '', NULL, 'admin', '2020-11-04 17:13:25');
INSERT INTO `sys_dept` VALUES (107, 100, '0,100', '610524210000', '和家庄派出所', '101', 4, NULL, NULL, NULL, '0', '0', '', NULL, 'admin', '2020-11-04 17:14:06');
INSERT INTO `sys_dept` VALUES (108, 100, '0,100', '610524212000', '黑池派出所', '101', 2, NULL, NULL, NULL, '0', '0', '', NULL, 'admin', '2020-11-04 17:13:01');
INSERT INTO `sys_dept` VALUES (109, 100, '0,100', '610524213000', '新池派出所', '101', 1, NULL, NULL, NULL, '0', '0', '', NULL, 'admin', '2020-11-04 17:12:45');
INSERT INTO `sys_dept` VALUES (110, 100, '0,100', '610524215000', '甘井派出所', '101', 11, NULL, NULL, NULL, '0', '0', '', NULL, 'admin', '2020-11-04 17:15:54');
INSERT INTO `sys_dept` VALUES (111, 100, '0,100', '610524216000', '皇甫庄派出所', '101', 10, NULL, NULL, NULL, '0', '0', '', NULL, 'admin', '2020-11-04 17:15:38');
INSERT INTO `sys_dept` VALUES (112, 100, '0,100', '610524217000', '金峪派出所', '101', 9, NULL, NULL, NULL, '0', '0', '', NULL, 'admin', '2020-11-04 17:15:21');
INSERT INTO `sys_dept` VALUES (113, 100, '0,100', '610524218000', '同家庄派出所', '101', 5, NULL, NULL, NULL, '0', '0', '', NULL, 'admin', '2020-11-04 17:14:22');
INSERT INTO `sys_dept` VALUES (114, 100, '0,100', '610524220000', '百良派出所', '101', 6, NULL, NULL, NULL, '0', '0', '', NULL, 'admin', '2020-11-04 17:14:36');
INSERT INTO `sys_dept` VALUES (115, 100, '0,100', '610524221000', '王家洼派出所', '101', 8, NULL, NULL, NULL, '0', '0', '', NULL, 'admin', '2020-11-04 17:15:06');
INSERT INTO `sys_dept` VALUES (116, 109, '0,100,109', NULL, '社区警务队', '10102', 0, NULL, NULL, NULL, '0', '0', 'admin', '2023-07-05 11:26:03', 'admin', '2023-07-05 14:51:11');
INSERT INTO `sys_dept` VALUES (117, 116, '0,100,109,116', NULL, '凤凰社区', '1010201', 0, NULL, NULL, NULL, '0', '0', 'admin', '2023-07-05 11:26:46', '', NULL);

SET FOREIGN_KEY_CHECKS = 1;
