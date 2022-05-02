/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : wisdom_connect_database

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 02/05/2022 11:14:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat`  (
  `id` int(8) NOT NULL,
  `holderone` int(8) NOT NULL,
  `holdertwo` int(8) NOT NULL,
  `message` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `calling` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES (1982541823, 1982541826, 1982541825, NULL, 0);

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` int(11) NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'file name',
  `type` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'file type',
  `size` bigint(10) NULL DEFAULT NULL COMMENT 'file size',
  `url` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'file url',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT 'logic delete',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT 'enable or disable',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for hobby
-- ----------------------------
DROP TABLE IF EXISTS `hobby`;
CREATE TABLE `hobby`  (
  `id` int(7) NOT NULL,
  `name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `level` int(3) NOT NULL,
  `tophobbyid` int(7) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hobby
-- ----------------------------
INSERT INTO `hobby` VALUES (1234567, 'swim', 2, 2345678);
INSERT INTO `hobby` VALUES (2345678, 'sport', 1, NULL);
INSERT INTO `hobby` VALUES (3456789, 'basketball', 2, 2345678);

-- ----------------------------
-- Table structure for profile
-- ----------------------------
DROP TABLE IF EXISTS `profile`;
CREATE TABLE `profile`  (
  `id` int(8) NOT NULL,
  `owner` int(8) NOT NULL,
  `username` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `friend` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `privacy` int(255) NOT NULL DEFAULT 0,
  `block` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `hobby` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `age` int(100) NOT NULL,
  `gender` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `icon` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `online` int(2) NOT NULL DEFAULT 0,
  `oncall` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of profile
-- ----------------------------
INSERT INTO `profile` VALUES (74162177, 1982541825, 'jackprofile1', NULL, 1, NULL, NULL, 32, 'Male', NULL, 0, 0);
INSERT INTO `profile` VALUES (288075777, 1982541826, 'zack', NULL, 1, NULL, NULL, 44, 'Male', NULL, 0, 0);
INSERT INTO `profile` VALUES (455798785, 1982541826, 'july', NULL, 1, NULL, NULL, 55, 'Male', NULL, 0, 0);
INSERT INTO `profile` VALUES (573288449, 1982541826, 'zzq', NULL, 0, NULL, NULL, 12, 'Male', NULL, 0, 0);
INSERT INTO `profile` VALUES (732643329, 1982541826, 'ZZ', NULL, 1, NULL, NULL, 12, 'Male', NULL, 0, 0);
INSERT INTO `profile` VALUES (850112513, 1982541826, 'zack', NULL, 1, NULL, NULL, 44, 'Male', NULL, 0, 0);
INSERT INTO `profile` VALUES (1487646721, 1982541826, 'zzq', NULL, 0, NULL, NULL, 12, 'Male', NULL, 0, 0);
INSERT INTO `profile` VALUES (1709944833, 1982541826, 'zzq', NULL, 0, NULL, NULL, 12, 'Male', NULL, 0, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(8) NOT NULL,
  `username` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `register_time` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (-1758740479, 'jack2', '12331', '231321', '01/05/2022 21:04:24');
INSERT INTO `user` VALUES (-596918270, 'mike', '321321', '32132@qq.com', '01/05/2022 21:06:47');
INSERT INTO `user` VALUES (611033090, 'jack3', '1234', '6546@qq.com', '02/05/2022 00:08:20');
INSERT INTO `user` VALUES (1206611969, 'adada', 'asdadsada', 'asdadsa', '01/05/2022 16:35:55');
INSERT INTO `user` VALUES (1982541825, 'jack', '123456', '7894@g.com', '12/04/2022 00:50:00');
INSERT INTO `user` VALUES (1982541826, 'eric', '12321', 'asd@q.com', '11/04/2022 00:37:54');

SET FOREIGN_KEY_CHECKS = 1;
