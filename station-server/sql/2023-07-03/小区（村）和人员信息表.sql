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

 Date: 03/07/2023 09:21:18
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
-- Table structure for sq_important_family
-- ----------------------------
DROP TABLE IF EXISTS `sq_important_family`;
CREATE TABLE `sq_important_family`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `person_id` int NOT NULL,
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭成员姓名',
  `relationship` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '与重点关注人员关系',
  `phone` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `cert_no` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `work` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作',
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `person_id`(`person_id`) USING BTREE,
  CONSTRAINT `sq_important_family_ibfk_1` FOREIGN KEY (`person_id`) REFERENCES `sq_person_fcous` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '重点关注人员的家庭成员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_important_family
-- ----------------------------

-- ----------------------------
-- Table structure for sq_important_record
-- ----------------------------
DROP TABLE IF EXISTS `sq_important_record`;
CREATE TABLE `sq_important_record`  (
  `id` int NOT NULL,
  `person_id` int NOT NULL,
  `record_time` datetime NULL DEFAULT NULL COMMENT '记录时间',
  `record_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录标题',
  `record_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录描述',
  `file_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件识别编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '重点人员核查和登记或报备记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_important_record
-- ----------------------------

-- ----------------------------
-- Table structure for sq_person_fcous
-- ----------------------------
DROP TABLE IF EXISTS `sq_person_fcous`;
CREATE TABLE `sq_person_fcous`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `xq_id` int NULL DEFAULT NULL,
  `person_type` tinyint NULL DEFAULT NULL COMMENT '人员类型 1 常驻人口 2 流动人员',
  `focus_reason` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关注原因-字典',
  `level` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '重点关注级别',
  `reason_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关注原因-补充描述',
  `address` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '居住地址',
  `meta_addr_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址编码',
  `native_palce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `living_date` datetime NULL DEFAULT NULL COMMENT '居住日期',
  `nation` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '人员姓名',
  `cert_no` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号',
  `phone` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系方式',
  `face_img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '人脸图片地址（这个或许会放到人脸库）',
  `features` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '人脸特征值 （这个或许会放到人脸库）',
  `create_user` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后操作时间',
  `source_platform` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据来源平台（群众自主、警员录入、网页、三方）',
  `update_user` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后更新用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '重点关注人口' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_person_fcous
-- ----------------------------

-- ----------------------------
-- Table structure for sq_person_flow
-- ----------------------------
DROP TABLE IF EXISTS `sq_person_flow`;
CREATE TABLE `sq_person_flow`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `xq_id` int NOT NULL COMMENT '所属小区',
  `bm` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `cert_no` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号',
  `phone` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `meta_addr_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址编码',
  `native_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `nation` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `living_date` datetime NOT NULL COMMENT '入住当前地址的日期',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来此的原因或目的',
  `face_img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '人脸图片',
  `features` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '特征值',
  `company` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司',
  `career` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
  `create_user` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `last_update_time` datetime NULL DEFAULT NULL COMMENT '最后操作日期',
  `update_user` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后更新用户',
  `is_important` tinyint NULL DEFAULT NULL COMMENT '是否是重点人员',
  `attention_level` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关注的级别 0绿 1黄 2红',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sq_person_flow_ibfk_1`(`xq_id`) USING BTREE,
  INDEX `sq_person_flow_ibfk_2`(`meta_addr_id`) USING BTREE,
  CONSTRAINT `sq_person_flow_ibfk_1` FOREIGN KEY (`xq_id`) REFERENCES `sq_community` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sq_person_flow_ibfk_2` FOREIGN KEY (`meta_addr_id`) REFERENCES `sq_community_structure` (`meta_addr_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流动人口' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_person_flow
-- ----------------------------

-- ----------------------------
-- Table structure for sq_person_house
-- ----------------------------
DROP TABLE IF EXISTS `sq_person_house`;
CREATE TABLE `sq_person_house`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `address_id` int NOT NULL COMMENT '地址id',
  `community_id` int NOT NULL COMMENT '社区id',
  `person_id` int NOT NULL COMMENT '人员id',
  `person_type` int NOT NULL COMMENT '人员类型 0 其他 1 常驻 2 流动 3 重点',
  `relationship_with_owner` int(10) UNSIGNED ZEROFILL NOT NULL COMMENT '人员与房主的关系 本人、家庭成员、租户、暂住',
  `start_time` datetime NULL DEFAULT NULL COMMENT '入住时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '离开时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `x_id`(`person_id`, `person_type`) USING BTREE,
  INDEX `address_id`(`address_id`) USING BTREE,
  INDEX `community_id`(`community_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_person_house
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
  `create_user` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后操作时间',
  `is_important` tinyint NULL DEFAULT NULL COMMENT '是否是重点人员',
  `attention_level` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关注的级别 0绿 1黄 2红',
  `source_platform` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据来源平台（群众自主、警员录入、网页、三方）',
  `update_user` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后更新用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `xq_id`(`xq_id`) USING BTREE,
  INDEX `meta_addr_id`(`meta_addr_id`) USING BTREE,
  CONSTRAINT `sq_person_resident_ibfk_1` FOREIGN KEY (`xq_id`) REFERENCES `sq_community` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sq_person_resident_ibfk_2` FOREIGN KEY (`meta_addr_id`) REFERENCES `sq_community_structure` (`meta_addr_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '社区常驻人口表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sq_person_resident
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
