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

 Date: 25/06/2022 20:22:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blockrelationship
-- ----------------------------
DROP TABLE IF EXISTS `blockrelationship`;
CREATE TABLE `blockrelationship`  (
  `id` int(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `profileid` int(8) UNSIGNED ZEROFILL NOT NULL,
  `blockid` int(8) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`id`, `profileid`, `blockid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blockrelationship
-- ----------------------------
INSERT INTO `blockrelationship` VALUES (00000002, 00000001, 00000008);

-- ----------------------------
-- Table structure for call
-- ----------------------------
DROP TABLE IF EXISTS `call`;
CREATE TABLE `call`  (
  `id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `starter` int(8) UNSIGNED ZEROFILL NOT NULL,
  `receiver` int(8) UNSIGNED ZEROFILL NOT NULL,
  `link` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `finished` int(2) NOT NULL DEFAULT 0 COMMENT '0 is means call is not done',
  PRIMARY KEY (`id`, `starter`, `receiver`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of call
-- ----------------------------

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
-- Table structure for fastcode
-- ----------------------------
DROP TABLE IF EXISTS `fastcode`;
CREATE TABLE `fastcode`  (
  `id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `md5` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `manager` int(11) NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `abn` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `address` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `state` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'wait for code',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fastcode
-- ----------------------------
INSERT INTO `fastcode` VALUES (00000000001, 'b2c7db8916ff28d5e0fd3d13bc504cd1', '123asd', 2, 'facility', '22@aa.com', '123a5a6', '3a st', 'testDavid', 'done');
INSERT INTO `fastcode` VALUES (00000000004, '9d6f3590a9bc81820594eb88ba973ebb', '123456', 2, 'facility', '12@q.com', '1231', '12313', 'testLee', 'wait for user apply');
INSERT INTO `fastcode` VALUES (00000000006, '44a5d03f0b067e1b76649f857efb3645', '123456', 2, 'facility', 'u7272166@anu.edu.au', '12313', '12313', 'testYIfei', 'wait for user apply');
INSERT INTO `fastcode` VALUES (00000000007, 'aa94f2c611e593d4c22af66fbd51e7bc', 'abc', 2, 'facility', '804404826@qq.com', '123', '123', 'teskNike', 'done');
INSERT INTO `fastcode` VALUES (00000000008, '4366bf1779a72e3c756f5a6c2091c6d2', '926', 2, 'facility', 'anniesun9873@gmail.com', '802934795066', '37 Sunflower Street', 'Sunflower', 'done');
INSERT INTO `fastcode` VALUES (00000000009, 'c160a12e422c024695dac71d9519a376', NULL, NULL, 'facility', '123132', '12313', '121121', '1231', 'wait for code');

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
-- Table structure for friendrelationship
-- ----------------------------
DROP TABLE IF EXISTS `friendrelationship`;
CREATE TABLE `friendrelationship`  (
  `id` int(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `profileid1` int(8) UNSIGNED ZEROFILL NOT NULL,
  `profileid2` int(8) UNSIGNED ZEROFILL NOT NULL,
  `state` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0 is waiting for response',
  PRIMARY KEY (`id`, `profileid1`, `profileid2`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friendrelationship
-- ----------------------------
INSERT INTO `friendrelationship` VALUES (0000000002, 00000009, 00000001, 1);

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
-- Table structure for hobbyrelationship
-- ----------------------------
DROP TABLE IF EXISTS `hobbyrelationship`;
CREATE TABLE `hobbyrelationship`  (
  `id` int(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `profileid` int(8) UNSIGNED ZEROFILL NOT NULL,
  `hobbyid` int(8) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`id`, `profileid`, `hobbyid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hobbyrelationship
-- ----------------------------
INSERT INTO `hobbyrelationship` VALUES (0000000001, 00000001, 00000001);
INSERT INTO `hobbyrelationship` VALUES (0000000003, 00000001, 00000004);

-- ----------------------------
-- Table structure for profile
-- ----------------------------
DROP TABLE IF EXISTS `profile`;
CREATE TABLE `profile`  (
  `id` int(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `owner` int(8) NOT NULL,
  `username` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `privacy` int(255) NOT NULL DEFAULT 0 COMMENT '0 is everyone could find',
  `age` int(100) NOT NULL,
  `gender` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `icon` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `online` int(2) NOT NULL DEFAULT 0,
  `oncall` int(2) NOT NULL DEFAULT 0,
  `last_login` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of profile
-- ----------------------------
INSERT INTO `profile` VALUES (00000001, 2, '123', 0, 12, 'male', 'http://localhost:9090/file/download/8a8e2c028a79435f8a697b4d530e4036.jpg', 0, 0, NULL);
INSERT INTO `profile` VALUES (00000002, 3, 'haha', 0, 31, 'male', 'http://localhost:9090/file/download/c9b95e67af8549c684cf9bb1674f069b.png', 0, 0, NULL);
INSERT INTO `profile` VALUES (00000005, 4, 'xixi', 0, 22, 'male', 'http://localhost:9090/file/download/c9b95e67af8549c684cf9bb1674f069b.png', 0, 0, NULL);
INSERT INTO `profile` VALUES (00000006, 2, '321', 0, 22, 'female', 'http://localhost:9090/file/download/8a8e2c028a79435f8a697b4d530e4036.jpg', 0, 0, NULL);
INSERT INTO `profile` VALUES (00000007, 3, 'aa', 1, 22, 'male', 'http://localhost:9090/file/download/c9b95e67af8549c684cf9bb1674f069b.png', 0, 0, NULL);
INSERT INTO `profile` VALUES (00000008, 3, 'bb', 0, 22, 'male', 'http://localhost:9090/file/download/c9b95e67af8549c684cf9bb1674f069b.png', 0, 0, NULL);
INSERT INTO `profile` VALUES (00000009, 4, 'cc', 0, 33, 'female', 'http://localhost:9090/file/download/c9b95e67af8549c684cf9bb1674f069b.png', 0, 0, NULL);

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
  `code` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (00000002, 'testEric', '123', 'qian980730@gmail.com', '05/05/2022 17:53:04', NULL, NULL, NULL, 'manager', NULL);
INSERT INTO `user` VALUES (00000003, 'testMike', '123', '122@q.com', '05/05/2022 17:59:56', NULL, 'asa', NULL, 'nurse', NULL);
INSERT INTO `user` VALUES (00000004, 'hobbyFacility', '123', '3122@qa.com', '05/05/2022 18:01:50', NULL, '3a st kg', 'asda2ba', 'resident', NULL);
INSERT INTO `user` VALUES (00000006, 'testDavid', '123', '22@aa.com', '07/05/2022 23:44:44', NULL, '3a st', '123a5a6', 'facility', NULL);
INSERT INTO `user` VALUES (00000007, 'teskNike', '123', '804404826@qq.com', '10/05/2022 21:27:56', NULL, '123', '123', 'facility', NULL);
INSERT INTO `user` VALUES (00000008, 'Sunflower', '123', 'anniesun9873@gmail.com', '11/05/2022 13:44:24', NULL, '37 Sunflower Street', '802934795066', 'facility', NULL);

SET FOREIGN_KEY_CHECKS = 1;
