/*
 Navicat Premium Data Transfer

 Source Server         : demo
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 03/07/2022 00:49:33
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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fastcode
-- ----------------------------
INSERT INTO `fastcode` VALUES (00000000001, 'b2c7db8916ff28d5e0fd3d13bc504cd1', '123asd', 2, 'facility', '22@aa.com', '123a5a6', '3a st', 'testDavid', 'done');
INSERT INTO `fastcode` VALUES (00000000002, 'f350d524103f8639d0df4e9c7ba45a14', 'sadadv2121a', 2, 'facility', '804404826@qq.com', '1212', '12112', 'testNike', 'done');
INSERT INTO `fastcode` VALUES (00000000004, '9d6f3590a9bc81820594eb88ba973ebb', '123456', 2, 'facility', '12@q.com', '1231', '12313', 'testLee', 'wait for user apply');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hobby
-- ----------------------------
INSERT INTO `hobby` VALUES (0000001, 'Sport', 1, NULL, 'http://localhost:9090/file/download/d198c37c365047e4ba016e1cb1a893a5.jpg');
INSERT INTO `hobby` VALUES (0000002, 'Basketball', 2, 'Sport', 'http://localhost:9090/file/download/6e7a436a72704cd49658e9533335a640.jpg');
INSERT INTO `hobby` VALUES (0000003, 'Swim', 2, 'Sport', 'http://localhost:9090/file/download/53a19f7a2cb54b379bcdbcd121eae44f.jpg');
INSERT INTO `hobby` VALUES (0000004, 'Video Game', 1, NULL, 'http://localhost:9090/file/download/608b9683cb7e4c7fa0ac89ebb2f9efe3.jpg');
INSERT INTO `hobby` VALUES (0000005, 'Apex Legend', 2, 'Video Game', 'http://localhost:9090/file/download/0fe9eed241a74610bfe117250a2e2dfb.jpg');
INSERT INTO `hobby` VALUES (0000006, 'NBA 2K22', 2, 'Video Game', 'http://localhost:9090/file/download/ca12267033af4fbeb881f6e11c16ee61.jpg');
INSERT INTO `hobby` VALUES (0000007, 'Baseball', 2, 'Sport', NULL);
INSERT INTO `hobby` VALUES (0000008, 'Golf', 2, 'Sport', NULL);
INSERT INTO `hobby` VALUES (0000009, 'Footy', 2, 'Sport', NULL);
INSERT INTO `hobby` VALUES (0000010, 'Table Tennis', 2, 'Sport', NULL);
INSERT INTO `hobby` VALUES (0000011, 'Badminton', 2, 'Sport', NULL);
INSERT INTO `hobby` VALUES (0000012, 'Music', 1, NULL, NULL);
INSERT INTO `hobby` VALUES (0000013, 'Violin', 2, 'Music', NULL);
INSERT INTO `hobby` VALUES (0000014, 'Piano', 2, 'Music', NULL);
INSERT INTO `hobby` VALUES (0000015, 'Guitar', 2, 'Music', NULL);
INSERT INTO `hobby` VALUES (0000016, 'Singing', 2, 'Music', NULL);
INSERT INTO `hobby` VALUES (0000017, 'Film', 1, NULL, NULL);
INSERT INTO `hobby` VALUES (0000018, 'Romance', 2, 'Film', NULL);
INSERT INTO `hobby` VALUES (0000019, 'Comedy', 2, 'Film', NULL);
INSERT INTO `hobby` VALUES (0000020, 'Action', 2, 'Film', NULL);
INSERT INTO `hobby` VALUES (0000021, 'Science Fiction', 2, 'Film', NULL);
INSERT INTO `hobby` VALUES (0000022, 'Crime', 2, 'Film', NULL);
INSERT INTO `hobby` VALUES (0000023, 'History', 2, 'Film', NULL);
INSERT INTO `hobby` VALUES (0000024, 'Horror', 2, 'Film', NULL);
INSERT INTO `hobby` VALUES (0000025, 'War', 2, 'Film', NULL);
INSERT INTO `hobby` VALUES (0000026, 'Baseball', 2, 'Sport', NULL);
INSERT INTO `hobby` VALUES (0000027, 'Swimming', 2, 'Sport', NULL);
INSERT INTO `hobby` VALUES (0000028, 'Jogging', 2, 'Sport', NULL);
INSERT INTO `hobby` VALUES (0000029, 'Surfing', 2, 'Sport', NULL);
INSERT INTO `hobby` VALUES (0000030, 'Arts', 1, NULL, NULL);
INSERT INTO `hobby` VALUES (0000031, 'Painting', 2, 'Arts', NULL);
INSERT INTO `hobby` VALUES (0000032, 'Pottery', 2, 'Arts', NULL);
INSERT INTO `hobby` VALUES (0000033, 'Photography', 2, 'Arts', NULL);
INSERT INTO `hobby` VALUES (0000034, 'Sculpture', 2, 'Arts', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of profile
-- ----------------------------
INSERT INTO `profile` VALUES (00000001, 2, '123', NULL, 0, NULL, NULL, 12, 'male', 'http://localhost:9090/file/download/8a8e2c028a79435f8a697b4d530e4036.jpg', 0, 0, NULL);
INSERT INTO `profile` VALUES (00000002, 3, 'testEric', NULL, 1, NULL, NULL, 31, 'male', 'http://localhost:9090/file/download/c9b95e67af8549c684cf9bb1674f069b.png', 0, 0, NULL);
INSERT INTO `profile` VALUES (00000003, 10, 'testMike', NULL, 0, NULL, NULL, 18, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000004, 5, 'hobbyFacility', NULL, 0, NULL, NULL, 23, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000005, 4, 'xixi', NULL, 1, NULL, NULL, 22, 'male', 'http://localhost:9090/file/download/c9b95e67af8549c684cf9bb1674f069b.png', 0, 0, NULL);
INSERT INTO `profile` VALUES (00000010, 4, 'jbfb', NULL, 0, NULL, NULL, 58, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000011, 4, 'wund', NULL, 1, NULL, NULL, 66, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000012, 0, 'krxp', NULL, 0, NULL, NULL, 70, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000013, 3, 'aeyo', NULL, 1, NULL, NULL, 79, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000014, 2, 'olqx', NULL, 1, NULL, NULL, 76, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000015, 5, 'eozm', NULL, 0, NULL, NULL, 85, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000016, 5, 'lxyy', NULL, 0, NULL, NULL, 94, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000017, 9, 'jahn', NULL, 0, NULL, NULL, 98, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000018, 2, 'gnyl', NULL, 1, NULL, NULL, 69, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000019, 3, 'pvvj', NULL, 0, NULL, NULL, 75, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000020, 7, 'vukl', NULL, 1, NULL, NULL, 79, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000021, 9, 'xmeq', NULL, 0, NULL, NULL, 96, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000022, 0, 'jtqa', NULL, 0, NULL, NULL, 84, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000023, 0, 'qmda', NULL, 0, NULL, NULL, 69, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000024, 1, 'gdgi', NULL, 1, NULL, NULL, 61, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000025, 0, 'hnek', NULL, 1, NULL, NULL, 99, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000026, 8, 'oxmf', NULL, 0, NULL, NULL, 60, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000027, 5, 'hkhx', NULL, 1, NULL, NULL, 60, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000028, 6, 'fmgw', NULL, 0, NULL, NULL, 59, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000029, 7, 'oazp', NULL, 0, NULL, NULL, 74, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000030, 5, 'edhs', NULL, 0, NULL, NULL, 79, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000031, 3, 'dakw', NULL, 1, NULL, NULL, 83, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000032, 4, 'ajxg', NULL, 0, NULL, NULL, 88, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000033, 7, 'lzyz', NULL, 0, NULL, NULL, 99, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000034, 5, 'uwsz', NULL, 1, NULL, NULL, 87, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000035, 2, 'tqta', NULL, 1, NULL, NULL, 99, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000036, 6, 'feyp', NULL, 1, NULL, NULL, 56, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000037, 3, 'bhjl', NULL, 1, NULL, NULL, 72, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000038, 3, 'gbaz', NULL, 0, NULL, NULL, 52, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000039, 3, 'raoy', NULL, 1, NULL, NULL, 50, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000040, 9, 'fiob', NULL, 0, NULL, NULL, 66, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000041, 3, 'qygt', NULL, 0, NULL, NULL, 81, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000042, 9, 'gvba', NULL, 1, NULL, NULL, 93, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000043, 0, 'pxmj', NULL, 1, NULL, NULL, 70, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000044, 8, 'trlk', NULL, 1, NULL, NULL, 66, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000045, 5, 'dous', NULL, 1, NULL, NULL, 96, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000046, 1, 'kgqs', NULL, 0, NULL, NULL, 81, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000047, 5, 'scci', NULL, 1, NULL, NULL, 94, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000048, 1, 'gxld', NULL, 1, NULL, NULL, 87, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000049, 5, 'lvpd', NULL, 0, NULL, NULL, 62, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000050, 0, 'wddk', NULL, 0, NULL, NULL, 98, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000051, 7, 'lqbd', NULL, 0, NULL, NULL, 75, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000052, 9, 'wadi', NULL, 0, NULL, NULL, 67, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000053, 3, 'nnrn', NULL, 1, NULL, NULL, 88, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000054, 9, 'kwka', NULL, 1, NULL, NULL, 97, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000055, 5, 'sjek', NULL, 0, NULL, NULL, 62, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000056, 6, 'oclu', NULL, 0, NULL, NULL, 62, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000057, 0, 'derr', NULL, 0, NULL, NULL, 57, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000058, 8, 'ledm', NULL, 0, NULL, NULL, 68, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000059, 1, 'wjwt', NULL, 1, NULL, NULL, 98, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000060, 7, 'vggg', NULL, 0, NULL, NULL, 64, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000061, 7, 'bwvz', NULL, 1, NULL, NULL, 59, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000062, 1, 'hpji', NULL, 0, NULL, NULL, 62, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000063, 9, 'zrgk', NULL, 0, NULL, NULL, 94, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000064, 0, 'uczl', NULL, 0, NULL, NULL, 55, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000065, 4, 'hcqz', NULL, 1, NULL, NULL, 92, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000066, 8, 'saqk', NULL, 1, NULL, NULL, 93, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000067, 9, 'wdfy', NULL, 1, NULL, NULL, 95, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000068, 2, 'xgju', NULL, 1, NULL, NULL, 94, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000069, 4, 'grci', NULL, 1, NULL, NULL, 72, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000070, 6, 'nciw', NULL, 0, NULL, NULL, 89, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000071, 8, 'taci', NULL, 0, NULL, NULL, 58, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000072, 6, 'xlve', NULL, 0, NULL, NULL, 77, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000073, 5, 'wfyb', NULL, 0, NULL, NULL, 89, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000074, 7, 'kwcu', NULL, 0, NULL, NULL, 58, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000075, 0, 'lthu', NULL, 1, NULL, NULL, 67, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000076, 0, 'qomv', NULL, 1, NULL, NULL, 84, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000077, 2, 'qfnw', NULL, 1, NULL, NULL, 63, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000078, 9, 'vqrb', NULL, 1, NULL, NULL, 61, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000079, 3, 'dmvf', NULL, 0, NULL, NULL, 70, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000080, 6, 'swae', NULL, 1, NULL, NULL, 90, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000081, 1, 'mrbt', NULL, 1, NULL, NULL, 56, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000082, 4, 'soiv', NULL, 1, NULL, NULL, 98, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000083, 0, 'lqua', NULL, 1, NULL, NULL, 66, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000084, 2, 'vcmj', NULL, 0, NULL, NULL, 91, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000085, 4, 'tnza', NULL, 0, NULL, NULL, 58, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000086, 9, 'ebjf', NULL, 1, NULL, NULL, 50, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000087, 6, 'oyox', NULL, 1, NULL, NULL, 50, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000088, 7, 'dmzd', NULL, 0, NULL, NULL, 91, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000089, 6, 'cuvl', NULL, 1, NULL, NULL, 92, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000090, 2, 'ycgr', NULL, 0, NULL, NULL, 72, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000091, 8, 'vhyk', NULL, 0, NULL, NULL, 55, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000092, 7, 'jejh', NULL, 0, NULL, NULL, 66, 'female', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000093, 4, 'gbqy', NULL, 0, NULL, NULL, 57, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000094, 5, 'xxch', NULL, 0, NULL, NULL, 51, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000095, 8, 'owas', NULL, 0, NULL, NULL, 70, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000096, 3, 'msno', NULL, 0, NULL, NULL, 57, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000097, 9, 'ndpx', NULL, 0, NULL, NULL, 72, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000098, 4, 'cpum', NULL, 0, NULL, NULL, 89, 'male', NULL, 0, 0, NULL);
INSERT INTO `profile` VALUES (00000099, 9, 'kmcc', NULL, 0, NULL, NULL, 68, 'female', NULL, 0, 0, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (00000002, 'testEric', '123', 'qian980730@gmail.com', '05/05/2022 17:53:04', NULL, NULL, NULL, 'manager', NULL);
INSERT INTO `user` VALUES (00000003, 'testMike', '123', '122@q.com', '05/05/2022 17:59:56', NULL, 'asa', NULL, 'nurse', NULL);
INSERT INTO `user` VALUES (00000004, 'hobbyFacility', '123', '3122@qa.com', '05/05/2022 18:01:50', NULL, '3a st kg', 'asda2ba', 'facility', NULL);
INSERT INTO `user` VALUES (00000005, 'testNike', '132', '804404826@qq.com', '07/05/2022 23:42:16', NULL, '12112', '1212', 'facility', NULL);
INSERT INTO `user` VALUES (00000006, 'testDavid', '123', '22@aa.com', '07/05/2022 23:44:44', NULL, '3a st', '123a5a6', 'facility', NULL);
INSERT INTO `user` VALUES (00000010, 'jbfb', '422', 'gc28@qq.com', '21/02/2021 14:34:17', NULL, '597Autq', '431841', 'facility', NULL);
INSERT INTO `user` VALUES (00000011, 'wund', '934', 'tg74@qq.com', '09/05/2021 14:35:35', NULL, '063Uezm', '516445', 'resident', NULL);
INSERT INTO `user` VALUES (00000012, 'krxp', '726', 'zo07@qq.com', '13/02/2022 02:05:08', NULL, '752Bimt', '116844', 'resident', NULL);
INSERT INTO `user` VALUES (00000013, 'aeyo', '082', 'nc42@qq.com', '10/04/2021 09:59:20', NULL, '915Yulv', '387268', 'resident', NULL);
INSERT INTO `user` VALUES (00000014, 'olqx', '580', 'lm27@qq.com', '15/08/2021 08:27:36', NULL, '034Djkw', '905155', 'nurse', NULL);
INSERT INTO `user` VALUES (00000015, 'eozm', '399', 'ms73@qq.com', '10/05/2021 01:36:42', NULL, '132Vczp', '459598', 'facility', NULL);
INSERT INTO `user` VALUES (00000016, 'lxyy', '617', 'qd75@qq.com', '30/04/2021 18:36:41', NULL, '798Vomd', '135113', 'nurse', NULL);
INSERT INTO `user` VALUES (00000017, 'jahn', '985', 'zp00@qq.com', '28/03/2022 19:46:55', NULL, '455Znaw', '098535', 'resident', NULL);
INSERT INTO `user` VALUES (00000018, 'gnyl', '136', 'po82@qq.com', '15/05/2022 17:24:53', NULL, '593Wpov', '307705', 'resident', NULL);
INSERT INTO `user` VALUES (00000019, 'pvvj', '176', 'lz80@qq.com', '10/05/2021 16:12:50', NULL, '504Brhc', '880450', 'resident', NULL);
INSERT INTO `user` VALUES (00000020, 'vukl', '271', 'gc57@qq.com', '05/02/2021 20:25:35', NULL, '837Qzws', '702997', 'resident', NULL);
INSERT INTO `user` VALUES (00000021, 'xmeq', '816', 'ol15@qq.com', '22/02/2021 15:25:57', NULL, '392Sdub', '804581', 'facility', NULL);
INSERT INTO `user` VALUES (00000022, 'jtqa', '421', 'pl95@qq.com', '12/05/2021 22:21:44', NULL, '487Alwk', '167986', 'facility', NULL);
INSERT INTO `user` VALUES (00000023, 'qmda', '863', 'zn28@qq.com', '21/06/2021 07:22:05', NULL, '236Qluq', '278735', 'nurse', NULL);
INSERT INTO `user` VALUES (00000024, 'gdgi', '759', 'lu76@qq.com', '03/04/2022 01:39:01', NULL, '223Mrja', '859417', 'nurse', NULL);
INSERT INTO `user` VALUES (00000025, 'hnek', '053', 'su58@qq.com', '10/01/2021 02:38:44', NULL, '110Yyhg', '183055', 'nurse', NULL);
INSERT INTO `user` VALUES (00000026, 'oxmf', '006', 'op79@qq.com', '09/09/2021 18:25:34', NULL, '216Nlog', '340964', 'nurse', NULL);
INSERT INTO `user` VALUES (00000027, 'hkhx', '812', 'uf75@qq.com', '05/06/2021 17:18:05', NULL, '156Imbo', '336872', 'resident', NULL);
INSERT INTO `user` VALUES (00000028, 'fmgw', '035', 'wp11@qq.com', '12/05/2021 10:22:31', NULL, '934Xdjb', '866957', 'nurse', NULL);
INSERT INTO `user` VALUES (00000029, 'oazp', '216', 'dp13@qq.com', '17/04/2022 05:09:45', NULL, '864Hzcd', '617311', 'nurse', NULL);
INSERT INTO `user` VALUES (00000030, 'edhs', '053', 'pu37@qq.com', '09/01/2021 16:31:11', NULL, '634Mbzj', '517829', 'resident', NULL);
INSERT INTO `user` VALUES (00000031, 'dakw', '646', 'cs42@qq.com', '20/04/2022 01:39:58', NULL, '788Eldc', '683873', 'resident', NULL);
INSERT INTO `user` VALUES (00000032, 'ajxg', '170', 'ce40@qq.com', '29/01/2021 04:54:34', NULL, '840Hbfa', '525318', 'facility', NULL);
INSERT INTO `user` VALUES (00000033, 'lzyz', '910', 'pe11@qq.com', '13/03/2021 21:35:37', NULL, '655Upqr', '584235', 'resident', NULL);
INSERT INTO `user` VALUES (00000034, 'uwsz', '957', 'jf10@qq.com', '22/10/2021 16:49:13', NULL, '328Wlcl', '745786', 'nurse', NULL);
INSERT INTO `user` VALUES (00000035, 'tqta', '439', 'nl69@qq.com', '10/05/2022 11:53:30', NULL, '341Rjku', '656360', 'resident', NULL);
INSERT INTO `user` VALUES (00000036, 'feyp', '950', 'us23@qq.com', '17/12/2021 01:50:38', NULL, '299Iyji', '018180', 'facility', NULL);
INSERT INTO `user` VALUES (00000037, 'bhjl', '743', 'ez87@qq.com', '27/01/2021 07:35:18', NULL, '969Lspq', '481269', 'resident', NULL);
INSERT INTO `user` VALUES (00000038, 'gbaz', '311', 'lu97@qq.com', '03/05/2021 18:53:41', NULL, '829Mbmu', '980389', 'facility', NULL);
INSERT INTO `user` VALUES (00000039, 'raoy', '173', 'fh27@qq.com', '16/03/2022 19:08:13', NULL, '127Mqni', '721668', 'resident', NULL);
INSERT INTO `user` VALUES (00000040, 'fiob', '896', 'rv20@qq.com', '23/01/2021 07:35:31', NULL, '832Cusa', '272316', 'nurse', NULL);
INSERT INTO `user` VALUES (00000041, 'qygt', '815', 'xq69@qq.com', '29/03/2021 05:43:16', NULL, '235Lyrf', '166589', 'nurse', NULL);
INSERT INTO `user` VALUES (00000042, 'gvba', '982', 'bf90@qq.com', '09/10/2021 08:48:16', NULL, '265Ktdx', '673281', 'resident', NULL);
INSERT INTO `user` VALUES (00000043, 'pxmj', '478', 'ni80@qq.com', '22/01/2022 12:46:07', NULL, '149Heox', '380967', 'resident', NULL);
INSERT INTO `user` VALUES (00000044, 'trlk', '716', 'rd91@qq.com', '24/03/2022 09:24:30', NULL, '713Ojmw', '073456', 'nurse', NULL);
INSERT INTO `user` VALUES (00000045, 'dous', '281', 'mb34@qq.com', '20/04/2021 05:21:35', NULL, '017Vsee', '198368', 'resident', NULL);
INSERT INTO `user` VALUES (00000046, 'kgqs', '041', 'by89@qq.com', '12/08/2021 07:52:21', NULL, '913Njdb', '683021', 'resident', NULL);
INSERT INTO `user` VALUES (00000047, 'scci', '914', 'st41@qq.com', '31/01/2021 00:35:44', NULL, '800Tdwo', '410284', 'nurse', NULL);
INSERT INTO `user` VALUES (00000048, 'gxld', '251', 'gh29@qq.com', '15/01/2021 00:38:24', NULL, '245Cqub', '738281', 'resident', NULL);
INSERT INTO `user` VALUES (00000049, 'lvpd', '172', 'oz53@qq.com', '01/10/2021 11:43:22', NULL, '247Lair', '251505', 'nurse', NULL);
INSERT INTO `user` VALUES (00000050, 'wddk', '714', 'ip11@qq.com', '16/05/2022 03:08:25', NULL, '008Hzni', '051469', 'resident', NULL);
INSERT INTO `user` VALUES (00000051, 'lqbd', '967', 'hp97@qq.com', '29/09/2021 07:51:36', NULL, '853Bpqg', '147300', 'resident', NULL);
INSERT INTO `user` VALUES (00000052, 'wadi', '813', 'xu38@qq.com', '08/04/2021 06:57:08', NULL, '290Zuij', '676326', 'facility', NULL);
INSERT INTO `user` VALUES (00000053, 'nnrn', '718', 'zg87@qq.com', '29/06/2021 16:05:35', NULL, '807Zuue', '330247', 'resident', NULL);
INSERT INTO `user` VALUES (00000054, 'kwka', '372', 'xw39@qq.com', '07/03/2022 09:36:48', NULL, '542Afsz', '505021', 'facility', NULL);
INSERT INTO `user` VALUES (00000055, 'sjek', '865', 'pm38@qq.com', '16/04/2022 04:26:50', NULL, '616Wqdn', '929975', 'nurse', NULL);
INSERT INTO `user` VALUES (00000056, 'oclu', '555', 'mb64@qq.com', '20/09/2021 10:32:17', NULL, '034Vzwp', '373172', 'resident', NULL);
INSERT INTO `user` VALUES (00000057, 'derr', '061', 'ad10@qq.com', '12/12/2021 11:03:11', NULL, '461Udui', '039917', 'facility', NULL);
INSERT INTO `user` VALUES (00000058, 'ledm', '392', 'zu35@qq.com', '07/04/2022 17:22:01', NULL, '017Plsl', '306969', 'resident', NULL);
INSERT INTO `user` VALUES (00000059, 'wjwt', '423', 'ir54@qq.com', '10/04/2022 03:30:02', NULL, '105Pqzi', '829397', 'nurse', NULL);
INSERT INTO `user` VALUES (00000060, 'vggg', '540', 'ev95@qq.com', '13/02/2021 00:50:53', NULL, '302Ijng', '748569', 'resident', NULL);
INSERT INTO `user` VALUES (00000061, 'bwvz', '766', 'lp78@qq.com', '16/03/2021 09:04:02', NULL, '803Dqgu', '345046', 'resident', NULL);
INSERT INTO `user` VALUES (00000062, 'hpji', '238', 'vd64@qq.com', '27/12/2021 17:07:51', NULL, '332Pssw', '681399', 'resident', NULL);
INSERT INTO `user` VALUES (00000063, 'zrgk', '528', 'hd46@qq.com', '29/12/2021 15:59:06', NULL, '903Souk', '179116', 'resident', NULL);
INSERT INTO `user` VALUES (00000064, 'uczl', '787', 'ed02@qq.com', '14/02/2021 14:51:12', NULL, '587Tuzp', '616291', 'facility', NULL);
INSERT INTO `user` VALUES (00000065, 'hcqz', '196', 'zk41@qq.com', '25/03/2021 05:26:49', NULL, '447Kyvf', '287415', 'facility', NULL);
INSERT INTO `user` VALUES (00000066, 'saqk', '850', 'vi87@qq.com', '23/11/2021 15:13:09', NULL, '918Hqie', '960345', 'resident', NULL);
INSERT INTO `user` VALUES (00000067, 'wdfy', '637', 'nf71@qq.com', '26/01/2021 13:44:02', NULL, '998Elmc', '220599', 'resident', NULL);
INSERT INTO `user` VALUES (00000068, 'xgju', '889', 'ol84@qq.com', '09/11/2021 12:20:05', NULL, '965Whnn', '808929', 'resident', NULL);
INSERT INTO `user` VALUES (00000069, 'grci', '258', 'ph24@qq.com', '29/03/2021 07:01:22', NULL, '630Rfeg', '084760', 'nurse', NULL);
INSERT INTO `user` VALUES (00000070, 'nciw', '686', 'nb42@qq.com', '15/11/2021 03:08:47', NULL, '209Xthl', '471636', 'facility', NULL);
INSERT INTO `user` VALUES (00000071, 'taci', '330', 'hy91@qq.com', '05/01/2021 20:16:49', NULL, '662Rint', '073161', 'resident', NULL);
INSERT INTO `user` VALUES (00000072, 'xlve', '961', 'fr44@qq.com', '30/06/2021 21:24:05', NULL, '019Azdn', '445752', 'resident', NULL);
INSERT INTO `user` VALUES (00000073, 'wfyb', '441', 'mf86@qq.com', '24/08/2021 12:56:04', NULL, '519Nkcc', '735797', 'resident', NULL);
INSERT INTO `user` VALUES (00000074, 'kwcu', '365', 'ar92@qq.com', '30/04/2022 06:14:00', NULL, '990Biox', '280108', 'facility', NULL);
INSERT INTO `user` VALUES (00000075, 'lthu', '283', 'he34@qq.com', '24/08/2021 10:26:27', NULL, '158Pyvc', '820938', 'facility', NULL);
INSERT INTO `user` VALUES (00000076, 'qomv', '327', 'yq21@qq.com', '03/05/2022 05:58:48', NULL, '899Cbfb', '400104', 'resident', NULL);
INSERT INTO `user` VALUES (00000077, 'qfnw', '632', 'mu64@qq.com', '11/04/2021 11:00:19', NULL, '155Yavu', '653528', 'nurse', NULL);
INSERT INTO `user` VALUES (00000078, 'vqrb', '953', 'pw20@qq.com', '13/01/2021 15:43:39', NULL, '452Qlck', '300250', 'nurse', NULL);
INSERT INTO `user` VALUES (00000079, 'dmvf', '722', 'ei04@qq.com', '24/02/2021 03:51:48', NULL, '696Knuj', '620332', 'resident', NULL);
INSERT INTO `user` VALUES (00000080, 'swae', '941', 'gs80@qq.com', '26/04/2022 20:03:03', NULL, '915Jsqk', '401139', 'nurse', NULL);
INSERT INTO `user` VALUES (00000081, 'mrbt', '216', 'gb65@qq.com', '21/07/2021 21:19:11', NULL, '144Apfj', '671981', 'resident', NULL);
INSERT INTO `user` VALUES (00000082, 'soiv', '590', 'vn69@qq.com', '28/05/2021 11:40:58', NULL, '269Tcip', '605405', 'nurse', NULL);
INSERT INTO `user` VALUES (00000083, 'lqua', '239', 'oe60@qq.com', '06/07/2021 16:39:44', NULL, '087Ztmf', '531486', 'resident', NULL);
INSERT INTO `user` VALUES (00000084, 'vcmj', '539', 'dq24@qq.com', '07/03/2021 18:15:40', NULL, '791Nnjj', '991553', 'resident', NULL);
INSERT INTO `user` VALUES (00000085, 'tnza', '993', 'um98@qq.com', '24/10/2021 11:29:06', NULL, '684Iniq', '165575', 'resident', NULL);
INSERT INTO `user` VALUES (00000086, 'ebjf', '263', 'np98@qq.com', '08/06/2021 15:16:03', NULL, '964Xncf', '275979', 'nurse', NULL);
INSERT INTO `user` VALUES (00000087, 'oyox', '491', 'nr89@qq.com', '21/04/2022 06:25:55', NULL, '636Krfz', '649270', 'resident', NULL);
INSERT INTO `user` VALUES (00000088, 'dmzd', '890', 'co68@qq.com', '01/03/2022 13:08:02', NULL, '072Olev', '764258', 'resident', NULL);
INSERT INTO `user` VALUES (00000089, 'cuvl', '716', 'dv03@qq.com', '02/01/2022 18:32:10', NULL, '031Ysqz', '748645', 'resident', NULL);
INSERT INTO `user` VALUES (00000090, 'ycgr', '363', 'th16@qq.com', '07/03/2022 10:21:59', NULL, '422Kuof', '061729', 'resident', NULL);
INSERT INTO `user` VALUES (00000091, 'vhyk', '153', 'jd00@qq.com', '11/10/2021 07:27:36', NULL, '395Tgle', '347969', 'resident', NULL);
INSERT INTO `user` VALUES (00000092, 'jejh', '501', 'hz31@qq.com', '02/01/2022 22:38:24', NULL, '097Axxy', '872038', 'facility', NULL);
INSERT INTO `user` VALUES (00000093, 'gbqy', '138', 'pi03@qq.com', '09/01/2022 14:50:17', NULL, '419Naxv', '348512', 'resident', NULL);
INSERT INTO `user` VALUES (00000094, 'xxch', '616', 'iq40@qq.com', '24/11/2021 08:48:11', NULL, '469Xgnu', '293774', 'resident', NULL);
INSERT INTO `user` VALUES (00000095, 'owas', '103', 'kd23@qq.com', '09/03/2022 14:24:19', NULL, '351Xjot', '298953', 'resident', NULL);
INSERT INTO `user` VALUES (00000096, 'msno', '600', 'pt11@qq.com', '06/06/2021 15:02:20', NULL, '706Spkm', '269844', 'resident', NULL);
INSERT INTO `user` VALUES (00000097, 'ndpx', '000', 'ru48@qq.com', '05/03/2021 23:15:55', NULL, '468Frxz', '321641', 'resident', NULL);
INSERT INTO `user` VALUES (00000098, 'cpum', '929', 'zf97@qq.com', '07/10/2021 23:36:31', NULL, '267Rvvg', '993257', 'resident', NULL);
INSERT INTO `user` VALUES (00000099, 'kmcc', '873', 'ma02@qq.com', '15/03/2022 20:43:11', NULL, '906Cpeb', '721210', 'facility', NULL);

SET FOREIGN_KEY_CHECKS = 1;
