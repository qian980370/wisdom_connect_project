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

 Date: 07/05/2022 02:34:40
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
  `id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'file name',
  `type` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'file type',
  `size` bigint(20) NULL DEFAULT NULL COMMENT 'file size (kb)',
  `md5` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'file md5',
  `url` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'file url',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT 'logic delete',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT 'enable or disable',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_md5`(`md5`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES (00000000007, 'p5.jpg', 'jpg', 6, 'f49ead1ab0dd8f6d138552d18e17c75c', 'http://localhost:9090/file/download/01ac525dcfb64cd08d7ab7187f10590c.jpg', 0, 1);
INSERT INTO `file` VALUES (00000000008, 'p1.jpg', 'jpg', 3, '624574540d6bcef534fa77482bfa7bf0', 'http://localhost:9090/file/download/8a8e2c028a79435f8a697b4d530e4036.jpg', 0, 1);
INSERT INTO `file` VALUES (00000000018, 'p4.jpg', 'jpg', 3, '0ed0fe81c5140c80fee674621e9def18', 'http://localhost:9090/file/download/8d8b9e7c7e7741388de128a839b4188c.jpg', 0, 1);
INSERT INTO `file` VALUES (00000000019, 'p3.jpg', 'jpg', 3, 'aed72629f87beca90e8b18754c87dad4', 'http://localhost:9090/file/download/6f733129a4cb4971ac44d1477f6c92c5.jpg', 0, 1);
INSERT INTO `file` VALUES (00000000020, 'sport.jpg', 'jpg', 8, 'df5e08af5bc8e13d3eff0bb0f6234fc3', 'http://localhost:9090/file/download/d198c37c365047e4ba016e1cb1a893a5.jpg', 0, 1);
INSERT INTO `file` VALUES (00000000021, 'basketball.jpg', 'jpg', 6, 'a4d370c73d7b75075490e48a88472be0', 'http://localhost:9090/file/download/6e7a436a72704cd49658e9533335a640.jpg', 0, 1);
INSERT INTO `file` VALUES (00000000022, 'swim.jpg', 'jpg', 7, '15cca6984a8edec9d1435998aad3f867', 'http://localhost:9090/file/download/53a19f7a2cb54b379bcdbcd121eae44f.jpg', 0, 1);
INSERT INTO `file` VALUES (00000000023, 'video_game.jpg', 'jpg', 11, '5ef1055c7869938f52ffd8812b579b08', 'http://localhost:9090/file/download/608b9683cb7e4c7fa0ac89ebb2f9efe3.jpg', 0, 1);
INSERT INTO `file` VALUES (00000000024, 'apex.jpg', 'jpg', 11, '6813e818d2a7a1f60d25f2372f231d4a', 'http://localhost:9090/file/download/0fe9eed241a74610bfe117250a2e2dfb.jpg', 0, 1);
INSERT INTO `file` VALUES (00000000025, 'NBA 2K22.jpg', 'jpg', 7, '89fd2af17ded1a6f6cf2c9e8ce4ced0b', 'http://localhost:9090/file/download/ca12267033af4fbeb881f6e11c16ee61.jpg', 0, 1);
INSERT INTO `file` VALUES (00000000026, 'unavailable', 'jpg', 9, 'special', 'http://localhost:9090/file/download/unavailable.jpg', 0, 1);
INSERT INTO `file` VALUES (00000000027, 'p2.png', 'png', 3, '8945f2acb4719791db8cbe4754f2f90d', 'http://localhost:9090/file/download/c9b95e67af8549c684cf9bb1674f069b.png', 0, 1);

-- ----------------------------
-- Table structure for hobby
-- ----------------------------
DROP TABLE IF EXISTS `hobby`;
CREATE TABLE `hobby`  (
  `id` int(7) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `level` int(3) NOT NULL,
  `tophobbyname` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hobby
-- ----------------------------
INSERT INTO `hobby` VALUES (0000001, 'Sport', 1, NULL, 'http://localhost:9090/file/download/d198c37c365047e4ba016e1cb1a893a5.jpg');
INSERT INTO `hobby` VALUES (0000002, 'Basketball', 2, 'Sport', 'http://localhost:9090/file/download/6e7a436a72704cd49658e9533335a640.jpg');
INSERT INTO `hobby` VALUES (0000003, 'Swim', 2, 'Sport', 'http://localhost:9090/file/download/53a19f7a2cb54b379bcdbcd121eae44f.jpg');
INSERT INTO `hobby` VALUES (0000004, 'Video Game', 1, NULL, 'http://localhost:9090/file/download/608b9683cb7e4c7fa0ac89ebb2f9efe3.jpg');
INSERT INTO `hobby` VALUES (0000005, 'Apex Legend', 2, 'Video Game', 'http://localhost:9090/file/download/0fe9eed241a74610bfe117250a2e2dfb.jpg');
INSERT INTO `hobby` VALUES (0000006, 'NBA 2K22', 2, 'Video Game', 'http://localhost:9090/file/download/ca12267033af4fbeb881f6e11c16ee61.jpg');

-- ----------------------------
-- Table structure for profile
-- ----------------------------
DROP TABLE IF EXISTS `profile`;
CREATE TABLE `profile`  (
  `id` int(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
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
  `last_login` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of profile
-- ----------------------------
INSERT INTO `profile` VALUES (00000001, 2, '123', NULL, 0, NULL, NULL, 12, 'male', 'http://localhost:9090/file/download/8a8e2c028a79435f8a697b4d530e4036.jpg', 0, 0, NULL);
INSERT INTO `profile` VALUES (00000002, 3, 'haha', NULL, 1, NULL, NULL, 31, 'male', 'http://localhost:9090/file/download/c9b95e67af8549c684cf9bb1674f069b.png', 0, 0, NULL);
INSERT INTO `profile` VALUES (00000005, 4, 'xixi', NULL, 1, NULL, NULL, 22, 'male', 'http://localhost:9090/file/download/c9b95e67af8549c684cf9bb1674f069b.png', 0, 0, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `register_time` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `last_login` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `abn` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (00000002, 'testEric', '123', '25@g.com', '05/05/2022 17:53:04', NULL, NULL, NULL, 'manager');
INSERT INTO `user` VALUES (00000003, 'testMike', '123', '122@q.com', '05/05/2022 17:59:56', NULL, 'asa', NULL, 'nurse');
INSERT INTO `user` VALUES (00000004, 'hobbyFacility', '123', '3122@qa.com', '05/05/2022 18:01:50', NULL, '3a st kg', 'asda2ba', 'facility');

SET FOREIGN_KEY_CHECKS = 1;
