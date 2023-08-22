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

 Date: 22/08/2023 17:32:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for common_users
-- ----------------------------
DROP TABLE IF EXISTS `common_users`;
CREATE TABLE `common_users`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NOT NULL COMMENT '所属辖区id',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `real_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户真实姓名',
  `user_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '00' COMMENT '用户类型（0普通，1，网格员，2网格员长）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `id_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `home_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`, `user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '普通用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of common_users
-- ----------------------------
INSERT INTO `common_users` VALUES (102, 219, '152121212121', 'tatata', '1', '12123123', NULL, '15823698458', '', '0', '/profile/upload/2023/07/13/OIP-C_20230713092734A001.jpg', '$2a$10$kKmYxRWlvKaYsTlkw9fviuW9ONc7nwX4MNrpx7xxn370/o9fHdljG', '0', '0', '', NULL, '', '2023-07-02 16:26:14', 'admin', '2023-07-22 14:33:00', NULL);
INSERT INTO `common_users` VALUES (103, 218, 'wanggyuanzhang1', '45', '2', '524254', '', '', NULL, '1', '', '', '0', '0', '', NULL, '', '2023-07-04 21:02:30', '', '2023-08-22 15:41:04', NULL);
INSERT INTO `common_users` VALUES (104, 220, '752785270', '4545', '2', '453435', NULL, '', NULL, '0', '', '', '0', '0', '', NULL, '', '2023-07-04 21:06:01', '', '2023-07-22 14:52:40', NULL);
INSERT INTO `common_users` VALUES (105, 219, '25424', '马云', '1', '', '245', '25424', NULL, '0', '', '', '0', '0', '', NULL, '', '2023-07-13 17:32:05', '', '2023-07-22 14:24:22', NULL);
INSERT INTO `common_users` VALUES (106, 0, '群众2', '群众2', '0', '', '452', '15212', '1521', '0', '/profile/upload/2023/07/13/吴京_20230713173344A001.png', '', '0', '0', '', NULL, '', '2023-07-13 17:34:02', '', '2023-07-22 15:05:28', NULL);
INSERT INTO `common_users` VALUES (107, 0, '群众1', '群众1', '0', '', '24', '425', '14544', '0', '', '', '0', '0', '', NULL, '', '2023-07-13 17:39:53', '', '2023-07-22 15:05:12', NULL);
INSERT INTO `common_users` VALUES (108, 220, '340407135', '小明', '1', '', '500181199908188888', '15320250549', NULL, '0', '/profile/upload/2023/08/01/吴京_20230801085737A003.png', '', '0', '0', '', NULL, '', '2023-08-01 08:51:57', '', '2023-08-01 08:57:39', NULL);

SET FOREIGN_KEY_CHECKS = 1;
