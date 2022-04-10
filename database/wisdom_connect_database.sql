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

 Date: 11/04/2022 01:42:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hobby
-- ----------------------------
DROP TABLE IF EXISTS `hobby`;
CREATE TABLE `hobby`  (
  `id` int(7) NOT NULL,
  `name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hobby
-- ----------------------------

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of profile
-- ----------------------------
INSERT INTO `profile` VALUES (-1842679807, 1982541826, 'july', NULL, 1, NULL, NULL, 55, 'Male', NULL);
INSERT INTO `profile` VALUES (-1305808894, 1982541826, 'july', NULL, 1, NULL, NULL, 55, 'Male', NULL);
INSERT INTO `profile` VALUES (-555028479, 1982541826, 'july', NULL, 1, NULL, NULL, 55, 'Male', NULL);
INSERT INTO `profile` VALUES (288075777, 1982541826, 'zack', NULL, 1, NULL, NULL, 44, 'Male', NULL);
INSERT INTO `profile` VALUES (455798785, 1982541826, 'july', NULL, 1, NULL, NULL, 55, 'Male', NULL);
INSERT INTO `profile` VALUES (543928322, 1982541826, 'july', NULL, 1, NULL, NULL, 55, 'Male', NULL);
INSERT INTO `profile` VALUES (560615426, 1982541826, 'jack', NULL, 1, NULL, NULL, 34, 'Male', NULL);
INSERT INTO `profile` VALUES (573288449, 1982541826, 'zzq', NULL, 0, NULL, NULL, 12, 'Male', NULL);
INSERT INTO `profile` VALUES (606752770, 1982541826, 'asa', NULL, 0, NULL, NULL, 12, 'Male', NULL);
INSERT INTO `profile` VALUES (619376642, 1982541826, 'july', NULL, 1, NULL, NULL, 55, 'Male', NULL);
INSERT INTO `profile` VALUES (636203010, 1982541826, 'zack', NULL, 1, NULL, NULL, 44, 'Male', NULL);
INSERT INTO `profile` VALUES (850112513, 1982541826, 'zack', NULL, 1, NULL, NULL, 44, 'Male', NULL);
INSERT INTO `profile` VALUES (992669698, 1982541826, 'july', NULL, 1, NULL, NULL, 55, 'Male', NULL);
INSERT INTO `profile` VALUES (1072410626, 1982541826, 'zzq', NULL, 0, NULL, NULL, 12, 'Male', NULL);
INSERT INTO `profile` VALUES (1290514434, 1982541826, 'july', NULL, 1, NULL, NULL, 55, 'Male', NULL);
INSERT INTO `profile` VALUES (1303097346, 1982541826, 'eric', NULL, 0, NULL, NULL, 3, 'Male', NULL);
INSERT INTO `profile` VALUES (1416294402, 1982541826, 'july', NULL, 1, NULL, NULL, 55, 'Male', NULL);
INSERT INTO `profile` VALUES (1487646721, 1982541826, 'zzq', NULL, 0, NULL, NULL, 12, 'Male', NULL);
INSERT INTO `profile` VALUES (1709944833, 1982541826, 'zzq', NULL, 0, NULL, NULL, 12, 'Male', NULL);
INSERT INTO `profile` VALUES (1936437250, 1982541826, 'zzq', NULL, 0, NULL, NULL, 12, 'Male', NULL);
INSERT INTO `profile` VALUES (1999351810, 1982541826, 'july', NULL, 1, NULL, NULL, 55, 'Male', NULL);

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
INSERT INTO `user` VALUES (1982541826, 'eric', '12321', 'asd@q.com', '11/04/2022 00:37:54');

SET FOREIGN_KEY_CHECKS = 1;
