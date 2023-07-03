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

 Date: 03/07/2023 10:43:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sq_company
-- ----------------------------
DROP TABLE IF EXISTS `sq_company`;
CREATE TABLE `sq_company`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `dept_id` int NOT NULL COMMENT '社区id',
  `company_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位编码',
  `company_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位名称',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位类型',
  `phone` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册地址',
  `
registration_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `trade_code` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照号',
  `organization_code` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织机构代码',
  `credit_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `head_master` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人姓名',
  `contact_phone` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人联系方式',
  `identity_code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人证件号码',
  `identity_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件号码类型',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dept_id`(`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '单位' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_company
-- ----------------------------

-- ----------------------------
-- Table structure for sq_company_desc
-- ----------------------------
DROP TABLE IF EXISTS `sq_company_desc`;
CREATE TABLE `sq_company_desc`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '单位详情附件-描述社单位营业执照、单位平面图、安防消防设施安放位置图等相关信息，可通过group分组管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_company_desc
-- ----------------------------
INSERT INTO `sq_company_desc` VALUES (1, 1, 'default', '小区平面图', 'https://www.jingyuinfo.com/fjsaofjosg.jpg', 'jpg', '默认的附件分组', '2023-06-14 15:32:54', '456');
INSERT INTO `sq_company_desc` VALUES (2, 1, 'default', '门头照片', 'https://www.jingyuinfo.com/fjsaofjosg.jpg', 'jpg', '默认的附件分组', '2023-06-14 15:34:04', '456');
INSERT INTO `sq_company_desc` VALUES (3, 1, '社区安防', '位置分布图', 'https://www.jingyuinfo.com/fjsaofjosg.jpg', 'jpg', '小区整体分布图', '2023-06-14 15:34:57', '456');
INSERT INTO `sq_company_desc` VALUES (4, 1, '消防演练及培训', '消防演练及培训相关文件', 'https://www.jingyuinfo.com/fjsaofjosg.jpg', 'jpg', '消防演练及培训', '2023-06-14 15:36:54', '456');
INSERT INTO `sq_company_desc` VALUES (5, 1, '社区安防', '视频监控分布图', 'https://www.jingyuinfo.com/fjsaofjosg.jpg', 'jpg', '社区安防', '2023-06-14 15:39:16', '456');

-- ----------------------------
-- Table structure for sq_company_employee
-- ----------------------------
DROP TABLE IF EXISTS `sq_company_employee`;
CREATE TABLE `sq_company_employee`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_id` int NOT NULL,
  `name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `identity_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `identity_code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `living_addres` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `native_palce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `position_type` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位类型 治安人员/普通员工/负责人等',
  `joined_date` date NULL DEFAULT NULL COMMENT '入职日期',
  `create_time` datetime NULL DEFAULT NULL,
  `del_flag` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_oper_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后操作时间',
  `oper_user` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '单位员工' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_company_employee
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
