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

 Date: 03/07/2023 09:20:07
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
  `
preventive_measures` tinyint NULL DEFAULT NULL COMMENT '防范措施 人防 物防 技防',
  `is_safe_area` tinyint NULL DEFAULT NULL COMMENT '是否是安全小区',
  `naming_organ` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '命名机关',
  `naming_time` datetime NULL DEFAULT NULL COMMENT '命名时间',
  `create_time` datetime NULL DEFAULT NULL,
  `create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_update_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '小区（村）基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_community
-- ----------------------------
INSERT INTO `sq_community` VALUES (1, '610113530123456789123', '通盛未来城', NULL, '陕西省西安市莲湖区环城西路2515号', '610102120000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sq_community_desc
-- ----------------------------
DROP TABLE IF EXISTS `sq_community_desc`;
CREATE TABLE `sq_community_desc`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `community_id` int NOT NULL COMMENT '小区id',
  `group_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'default' COMMENT '附件分组标题 默认default',
  `file_desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件描述,此文件的作用',
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件URL',
  `file_format` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件后缀/文件格式',
  `group_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分组信息描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `xq_id`(`community_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_community_desc
-- ----------------------------
INSERT INTO `sq_community_desc` VALUES (1, 1, 'default', '小区平面图', 'https://www.jingyuinfo.com/fjsaofjosg.jpg', 'jpg', '默认的附件分组', '2023-06-14 15:32:54', '456');
INSERT INTO `sq_community_desc` VALUES (2, 1, 'default', '门头照片', 'https://www.jingyuinfo.com/fjsaofjosg.jpg', 'jpg', '默认的附件分组', '2023-06-14 15:34:04', '456');
INSERT INTO `sq_community_desc` VALUES (3, 1, '社区安防', '位置分布图', 'https://www.jingyuinfo.com/fjsaofjosg.jpg', 'jpg', '小区整体分布图', '2023-06-14 15:34:57', '456');
INSERT INTO `sq_community_desc` VALUES (4, 1, '消防演练及培训', '消防演练及培训相关文件', 'https://www.jingyuinfo.com/fjsaofjosg.jpg', 'jpg', '消防演练及培训', '2023-06-14 15:36:54', '456');
INSERT INTO `sq_community_desc` VALUES (5, 1, '社区安防', '视频监控分布图', 'https://www.jingyuinfo.com/fjsaofjosg.jpg', 'jpg', '社区安防', '2023-06-14 15:39:16', '456');

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
  `center_point` point NULL COMMENT '小区中心坐标 【经度，维度】',
  `xq_region` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '小区别界坐标集合 用于地图画区',
  `building_count` int NULL DEFAULT NULL COMMENT '小区包含楼栋数',
  `person` int NULL DEFAULT NULL COMMENT '总人口数',
  `resident` int NULL DEFAULT NULL COMMENT '常驻人口数',
  `important` int NULL DEFAULT NULL COMMENT '重点关注人口数',
  `in_charge` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `charge_phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人联系电话',
  `police` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责民警',
  `police_phone` varchar(21) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民警联系电话',
  `police_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责民警姓名',
  `create_time` datetime NULL DEFAULT NULL,
  `last_update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_update_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `community_id`(`community_id`) USING BTREE,
  INDEX `dept_id`(`village_id`) USING BTREE,
  CONSTRAINT `sq_community_detail_ibfk_1` FOREIGN KEY (`community_id`) REFERENCES `sq_community` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '小区/村基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_community_detail
-- ----------------------------

-- ----------------------------
-- Table structure for sq_community_wuye
-- ----------------------------
DROP TABLE IF EXISTS `sq_community_wuye`;
CREATE TABLE `sq_community_wuye`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `community_id` int NOT NULL COMMENT '所属小区',
  `company` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作单位',
  `work_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作类型',
  `name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '人员姓名',
  `cert_no` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号',
  `phone` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '现居地址',
  `native_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始工作时间',
  `is_temp` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否是临时工',
  `end_time` datetime NULL DEFAULT NULL COMMENT '工作结束时间/离职时间',
  `is_leaving` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否离职',
  `create_time` datetime NULL DEFAULT NULL,
  `last_update_time` datetime NULL DEFAULT NULL,
  `last_update_user` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sq_community_wuye_ibfk_1`(`community_id`) USING BTREE,
  CONSTRAINT `sq_community_wuye_ibfk_1` FOREIGN KEY (`community_id`) REFERENCES `sq_community_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_community_wuye
-- ----------------------------

-- ----------------------------
-- Table structure for sqc_area_structure
-- ----------------------------
DROP TABLE IF EXISTS `sqc_area_structure`;
CREATE TABLE `sqc_area_structure`  (
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
  CONSTRAINT `sqc_area_structure_ibfk_1` FOREIGN KEY (`community_id`) REFERENCES `sq_community_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '小区房屋结构和地址信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sqc_area_structure
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
