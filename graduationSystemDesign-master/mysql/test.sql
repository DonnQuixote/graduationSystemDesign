/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 30/04/2021 00:38:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `pages` int(0) NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '解忧杂货店', '东野圭吾', 102, 27.50);
INSERT INTO `book` VALUES (2, '人间失格', '太宰治', 150, 30.00);
INSERT INTO `book` VALUES (3, '白夜行', '东野圭吾', 300, 40.90);
INSERT INTO `book` VALUES (4, '托尔斯泰', 'test2', 300, 30.23);
INSERT INTO `book` VALUES (5, 'test', '冻豆腐规划', 265, 333.33);
INSERT INTO `book` VALUES (6, '计算机网络', '张三', 685, 32.65);
INSERT INTO `book` VALUES (8, '更改法外狂徒', NULL, NULL, NULL);
INSERT INTO `book` VALUES (9, '奥术大师多', '时代大厦', NULL, NULL);
INSERT INTO `book` VALUES (10, 'dfg', 'dfsg', NULL, NULL);
INSERT INTO `book` VALUES (11, 'dgdg', 'dgdfg', NULL, NULL);
INSERT INTO `book` VALUES (12, 'asdas', 'asd', NULL, NULL);
INSERT INTO `book` VALUES (13, 'asdas', 'asd', NULL, NULL);
INSERT INTO `book` VALUES (14, 'asdas', 'asd', NULL, NULL);
INSERT INTO `book` VALUES (15, 'asdas', 'asd', NULL, NULL);
INSERT INTO `book` VALUES (16, 'asdas', 'asd', NULL, NULL);
INSERT INTO `book` VALUES (17, 'asdas', 'asd', NULL, NULL);
INSERT INTO `book` VALUES (18, 'asdas', 'asd', NULL, NULL);
INSERT INTO `book` VALUES (19, 'asdas', 'asd', NULL, NULL);
INSERT INTO `book` VALUES (20, 'asdas', 'asd', NULL, NULL);
INSERT INTO `book` VALUES (22, 'asdas', 'asd', NULL, NULL);
INSERT INTO `book` VALUES (23, 'asdas', 'asd', NULL, NULL);
INSERT INTO `book` VALUES (24, 'sdfsd', 'dfgd', NULL, NULL);
INSERT INTO `book` VALUES (25, 'dgdfg', 'dfg', NULL, NULL);
INSERT INTO `book` VALUES (26, 'test', 'aaa', NULL, NULL);
INSERT INTO `book` VALUES (27, 'asds', 'sad', NULL, NULL);
INSERT INTO `book` VALUES (29, 'asd11', '111', NULL, NULL);
INSERT INTO `book` VALUES (34, 'dff', 'ddd', NULL, NULL);
INSERT INTO `book` VALUES (35, 'fff', 'fff', NULL, NULL);
INSERT INTO `book` VALUES (37, 'ASDSA', 'ASDAS', NULL, NULL);
INSERT INTO `book` VALUES (38, 'testa', 'sdfs', NULL, NULL);

-- ----------------------------
-- Table structure for demand
-- ----------------------------
DROP TABLE IF EXISTS `demand`;
CREATE TABLE `demand`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '药品名称',
  `count` int(0) NULL DEFAULT NULL COMMENT '需求次数',
  `provider` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '药品厂家',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '大致价钱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demand
-- ----------------------------
INSERT INTO `demand` VALUES (5, 'test', 233, 'sad', 11.00);
INSERT INTO `demand` VALUES (6, '4月28日需求药品测试', 23, '自己', 23.60);
INSERT INTO `demand` VALUES (7, '测试需求', 223, '供应商', 23.30);

-- ----------------------------
-- Table structure for drug_entered
-- ----------------------------
DROP TABLE IF EXISTS `drug_entered`;
CREATE TABLE `drug_entered`  (
  `eid` int(0) NOT NULL AUTO_INCREMENT COMMENT '进药父id',
  `ordertime` datetime(0) NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of drug_entered
-- ----------------------------
INSERT INTO `drug_entered` VALUES (1, '2021-04-15 13:10:46', 23.00);
INSERT INTO `drug_entered` VALUES (9, '2021-04-15 15:16:59', 853.73);
INSERT INTO `drug_entered` VALUES (11, '2021-04-15 15:41:39', 1016.33);
INSERT INTO `drug_entered` VALUES (12, '2021-04-15 15:50:27', 225.00);
INSERT INTO `drug_entered` VALUES (14, '2021-04-27 15:01:30', 467.30);
INSERT INTO `drug_entered` VALUES (15, '2021-04-27 15:04:09', 1400.96);
INSERT INTO `drug_entered` VALUES (17, '2021-04-29 23:28:22', 40.60);
INSERT INTO `drug_entered` VALUES (18, '2021-04-29 23:53:20', 81.20);
INSERT INTO `drug_entered` VALUES (19, '2021-04-29 23:54:44', 2489.31);

-- ----------------------------
-- Table structure for drug_entered_details
-- ----------------------------
DROP TABLE IF EXISTS `drug_entered_details`;
CREATE TABLE `drug_entered_details`  (
  `edid` int(0) NOT NULL AUTO_INCREMENT COMMENT '进药子订单id',
  `eid` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `count` int(0) NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `provider` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`edid`) USING BTREE,
  INDEX `eid`(`eid`) USING BTREE,
  CONSTRAINT `drug_entered_details_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `drug_entered` (`eid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of drug_entered_details
-- ----------------------------
INSERT INTO `drug_entered_details` VALUES (5, 1, '草药', 1, 23.00, '云南白药');
INSERT INTO `drug_entered_details` VALUES (7, 9, '测试雪球', 2, 365.20, '测试需求');
INSERT INTO `drug_entered_details` VALUES (8, 9, '测试新雪芽哟', 1, 123.33, '测绘师');
INSERT INTO `drug_entered_details` VALUES (9, 11, '麝香保心丸子', 4, 223.00, '上海和黄药业有限公司是测试');
INSERT INTO `drug_entered_details` VALUES (10, 11, '测试新雪芽哟', 1, 123.33, '测绘师');
INSERT INTO `drug_entered_details` VALUES (11, 11, '你好', 1, 1.00, '1');
INSERT INTO `drug_entered_details` VALUES (12, 12, '测试雪球', 7, 365.20, '测试需求');
INSERT INTO `drug_entered_details` VALUES (13, 12, '麝香保心丸子', 1, 223.00, '上海和黄药业有限公司是测试');
INSERT INTO `drug_entered_details` VALUES (14, 12, '几次来回', 1, 1234.00, '测试类型');
INSERT INTO `drug_entered_details` VALUES (15, 12, '麝香保心丸子', 1, 223.00, '上海和黄药业有限公司是测试');
INSERT INTO `drug_entered_details` VALUES (18, 14, '麝香保心丸子', 2, 223.00, '上海和黄药业有限公司是测试');
INSERT INTO `drug_entered_details` VALUES (19, 14, '测试用药', 1, 20.30, '测试单位');
INSERT INTO `drug_entered_details` VALUES (20, 14, '阿萨德', 1, 1.00, '234');
INSERT INTO `drug_entered_details` VALUES (21, 15, '阿萨德', 5, 1.00, '234');
INSERT INTO `drug_entered_details` VALUES (23, 17, '麝香保心丸子', 8, 22.00, '上海和黄药业有限公司是测试');
INSERT INTO `drug_entered_details` VALUES (24, 17, '测试用药', 1, 20.30, '测试单位');
INSERT INTO `drug_entered_details` VALUES (25, 17, '测试用药', 2, 20.30, '测试单位');
INSERT INTO `drug_entered_details` VALUES (26, 18, '测试用药', 4, 20.30, '测试单位');
INSERT INTO `drug_entered_details` VALUES (27, 19, '几次来回', 2, 1234.00, '测试类型');
INSERT INTO `drug_entered_details` VALUES (28, 19, '麝香水', 1, 1.00, '1');
INSERT INTO `drug_entered_details` VALUES (29, 19, '测试用药', 1, 20.31, '测试单位');

-- ----------------------------
-- Table structure for drug_loss_report
-- ----------------------------
DROP TABLE IF EXISTS `drug_loss_report`;
CREATE TABLE `drug_loss_report`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `storagedate` date NULL DEFAULT NULL,
  `enddate` date NULL DEFAULT NULL,
  `count` int(0) NULL DEFAULT NULL,
  `provider` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL COMMENT '报废类型：0为过期，1为人为删除',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '报废原因',
  `unitprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `purchaseprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '进价',
  `expirydates` int(0) NULL DEFAULT NULL COMMENT '期限，以月份为单位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of drug_loss_report
-- ----------------------------
INSERT INTO `drug_loss_report` VALUES (1, '麝香保心丸子', '2018-12-01', '2021-03-01', 1, '上海和黄药业有限公司是测试', 1, NULL, NULL, NULL, NULL);
INSERT INTO `drug_loss_report` VALUES (19, '测试', '2021-04-14', '2021-04-30', 1, '阿萨德', 1, '最新删除', 1.00, 1.00, NULL);
INSERT INTO `drug_loss_report` VALUES (20, '测试从头检测一遍', '2021-04-06', '2021-04-21', 12, '删掉', 1, '测试删除', 12.00, 12.00, NULL);
INSERT INTO `drug_loss_report` VALUES (21, '4月12号数据', '2021-04-12', '2021-04-22', 1, '1', 1, '4月12号删除', 1.00, 11.00, NULL);
INSERT INTO `drug_loss_report` VALUES (22, '云南白药', '2021-01-15', '2021-03-01', 23, 'ceshi', 2, '药品过期', 1.00, 1.00, NULL);
INSERT INTO `drug_loss_report` VALUES (23, '再来啊', '2021-04-05', '2021-04-13', 1, '1', 2, '药品过期', 1.00, 1.00, NULL);
INSERT INTO `drug_loss_report` VALUES (24, '你好', '2021-04-04', '2021-04-05', 1, '1', 2, '药品过期', 1.00, 1.00, NULL);
INSERT INTO `drug_loss_report` VALUES (25, '可以开始啦', '2021-04-14', '2021-04-24', -6, '1', 2, '药品过期', 1.00, 1.00, NULL);
INSERT INTO `drug_loss_report` VALUES (26, 'test', '2021-04-12', '2021-04-30', 1, '1', 1, 'test', 1.00, 1.00, NULL);
INSERT INTO `drug_loss_report` VALUES (27, '测试删除组件', '2021-04-29', '2021-04-30', 1, '1', 1, '测试删除更改状态', 1.00, 1.00, NULL);
INSERT INTO `drug_loss_report` VALUES (28, '测试删除组件', '2021-04-29', '2021-04-30', 1, '1', 1, '测试', 1.00, 1.00, NULL);
INSERT INTO `drug_loss_report` VALUES (29, '测试删除组件', '2021-04-29', '2021-04-30', 1, '1', 1, 'test', 1.00, 1.00, NULL);
INSERT INTO `drug_loss_report` VALUES (30, 'zhangsanasd', '2021-01-05', '2021-05-21', 23, 'test', 1, '测试', 23.56, 23.50, NULL);
INSERT INTO `drug_loss_report` VALUES (31, 'test', '2021-04-12', '2021-04-30', 1, '1', 1, '测试人', 1.00, 1.00, NULL);
INSERT INTO `drug_loss_report` VALUES (32, 'test', '2021-04-12', '2021-04-30', 1, '1', 1, '测试', 1.00, 1.00, NULL);

-- ----------------------------
-- Table structure for drug_shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `drug_shopping_cart`;
CREATE TABLE `drug_shopping_cart`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '进药购物车',
  `eid` int(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `count` int(0) NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `provider` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of drug_shopping_cart
-- ----------------------------

-- ----------------------------
-- Table structure for medicine_basicmessage
-- ----------------------------
DROP TABLE IF EXISTS `medicine_basicmessage`;
CREATE TABLE `medicine_basicmessage`  (
  `medicineid` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `storagedate` date NULL DEFAULT NULL COMMENT '生产日期',
  `count` int(0) NULL DEFAULT NULL COMMENT '数量',
  `expirydates` int(0) NULL DEFAULT NULL COMMENT '以月份为单位',
  `purchaseprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '进价',
  `unitprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `provider` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '供应商',
  `enddate` date NULL DEFAULT NULL COMMENT '最后有效日期',
  `state` int(0) NULL DEFAULT NULL COMMENT '0代表可用，1代表不可用',
  PRIMARY KEY (`medicineid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medicine_basicmessage
-- ----------------------------
INSERT INTO `medicine_basicmessage` VALUES (1, '麝香保心丸子', '2020-01-14', 510, 72, 22.00, 32.00, '上海和黄药业有限公司是测试', '2021-10-01', 0);
INSERT INTO `medicine_basicmessage` VALUES (3, '测试用药', '2021-04-01', 14, 36, 20.30, 33.90, '测试单位', '2022-12-01', 0);
INSERT INTO `medicine_basicmessage` VALUES (4, '测试用药', '2021-03-30', 26, 36, 20.31, 33.90, '测试单位', '2021-12-01', 0);
INSERT INTO `medicine_basicmessage` VALUES (13, '阿萨德', '2021-04-13', 1, 1, 1.00, 1.00, '234', '2021-11-01', 0);
INSERT INTO `medicine_basicmessage` VALUES (20, '几次来回', '2021-03-16', 1234, NULL, 1234.00, 1234.00, '测试类型', '2021-05-11', 0);
INSERT INTO `medicine_basicmessage` VALUES (24, '麝香水', '2021-03-26', 111, NULL, 1.00, 1.00, '1', '2021-07-08', 0);
INSERT INTO `medicine_basicmessage` VALUES (25, '藿香', '2021-04-06', 1, NULL, 1.00, 1.00, '1', '2021-05-07', 0);
INSERT INTO `medicine_basicmessage` VALUES (26, '仙丹', '2021-05-06', 1, NULL, 1.00, 1.00, '1', '2021-05-28', 0);
INSERT INTO `medicine_basicmessage` VALUES (29, 'test', '2021-04-12', 1, NULL, 1.00, 1.00, '1', '2021-04-30', 0);
INSERT INTO `medicine_basicmessage` VALUES (30, 'test', '2021-04-12', 1, NULL, 1.00, 1.00, '1', '2021-04-30', 0);
INSERT INTO `medicine_basicmessage` VALUES (31, 'test', '2021-04-12', 1, NULL, 1.00, 1.00, '1', '2021-04-30', 0);
INSERT INTO `medicine_basicmessage` VALUES (32, 'test', '2021-04-12', 1, NULL, 1.00, 1.00, '1', '2021-04-30', 1);
INSERT INTO `medicine_basicmessage` VALUES (33, 'test', '2021-04-12', 1, NULL, 1.00, 1.00, '1', '2021-04-30', 0);
INSERT INTO `medicine_basicmessage` VALUES (34, 'test', '2021-04-12', 1, NULL, 1.00, 1.00, '1', '2021-04-30', 1);
INSERT INTO `medicine_basicmessage` VALUES (35, 'zhangsanasd', '2021-01-05', 23, NULL, 23.50, 23.56, 'test', '2021-05-21', 1);
INSERT INTO `medicine_basicmessage` VALUES (36, '测试删除组件', '2021-04-29', 1, NULL, 1.00, 1.00, '1', '2021-04-30', 1);

-- ----------------------------
-- Table structure for medicine_sales_record
-- ----------------------------
DROP TABLE IF EXISTS `medicine_sales_record`;
CREATE TABLE `medicine_sales_record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '数据库自增id',
  `medicineid` int(0) NULL DEFAULT NULL COMMENT '药品id',
  `medicinecount` int(0) NULL DEFAULT NULL COMMENT '卖出药品数量',
  `provider` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medicine_sales_record
-- ----------------------------
INSERT INTO `medicine_sales_record` VALUES (2, 1, 51, '上海和黄药业有限公司是测试', '麝香保心丸子');
INSERT INTO `medicine_sales_record` VALUES (3, 2, 3, 'svddsfq', 'dsfds');
INSERT INTO `medicine_sales_record` VALUES (4, 3, 3, '测试单位', '测试用药');

-- ----------------------------
-- Table structure for order_front
-- ----------------------------
DROP TABLE IF EXISTS `order_front`;
CREATE TABLE `order_front`  (
  `oid` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ordertime` datetime(0) NULL DEFAULT NULL,
  `orderstate` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单状态，包括：”已出药“，”未出药“',
  `totalprice` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`oid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_front
-- ----------------------------
INSERT INTO `order_front` VALUES (9, '2021-04-08 17:58:10', NULL, NULL);
INSERT INTO `order_front` VALUES (10, '2021-04-12 13:50:25', '出库成功', 35.90);
INSERT INTO `order_front` VALUES (27, '2021-04-13 15:36:57', '出库成功', 34.90);
INSERT INTO `order_front` VALUES (28, '2021-04-13 15:39:52', '出库成功', 747.70);
INSERT INTO `order_front` VALUES (29, '2021-04-13 15:54:17', '出库成功', 679.90);
INSERT INTO `order_front` VALUES (34, '2021-04-27 17:25:23', '出库成功', 679.90);
INSERT INTO `order_front` VALUES (35, '2021-04-27 17:34:59', '出库成功', 646.00);
INSERT INTO `order_front` VALUES (36, '2021-04-27 17:39:03', '出库成功', 679.90);
INSERT INTO `order_front` VALUES (37, '2021-04-27 17:44:41', '出库成功', 969.00);
INSERT INTO `order_front` VALUES (38, '2021-04-27 17:45:57', '出库成功', 67.80);
INSERT INTO `order_front` VALUES (39, '2021-04-27 18:58:04', '出库成功', 969.00);
INSERT INTO `order_front` VALUES (40, '2021-04-27 18:59:55', '出库成功', 646.00);
INSERT INTO `order_front` VALUES (41, '2021-04-27 19:01:40', '出库成功', 646.00);
INSERT INTO `order_front` VALUES (42, '2021-04-27 19:03:38', '出库成功', 646.00);
INSERT INTO `order_front` VALUES (44, '2021-04-28 11:31:24', '出库成功', 679.90);
INSERT INTO `order_front` VALUES (59, '2021-04-29 23:12:17', '出库成功', 7429.00);

-- ----------------------------
-- Table structure for order_front_details
-- ----------------------------
DROP TABLE IF EXISTS `order_front_details`;
CREATE TABLE `order_front_details`  (
  `odid` int(0) NOT NULL AUTO_INCREMENT,
  `oid` int(0) UNSIGNED NOT NULL COMMENT '所属订单编号，与order_front表中id对照',
  `medicineid` int(0) UNSIGNED NOT NULL COMMENT '药品编号,与medicine中药品id对照',
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `medicinecount` int(0) NULL DEFAULT NULL,
  `provider` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`odid`) USING BTREE,
  INDEX `oid`(`oid`) USING BTREE,
  INDEX `order_front_details_ibfk_2`(`medicineid`) USING BTREE,
  CONSTRAINT `order_front_details_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `order_front` (`oid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_front_details_ibfk_2` FOREIGN KEY (`medicineid`) REFERENCES `medicine_basicmessage` (`medicineid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_front_details
-- ----------------------------
INSERT INTO `order_front_details` VALUES (13, 10, 26, 1.00, 3, '1', '仙丹');
INSERT INTO `order_front_details` VALUES (14, 10, 1, 323.00, 2, '上海和黄药业有限公司是测试', '麝香保心丸子');
INSERT INTO `order_front_details` VALUES (15, 10, 20, 1234.00, 31, '测试类型', '几次来回');
INSERT INTO `order_front_details` VALUES (16, 10, 24, 1.00, 1, '1', '麝香水');
INSERT INTO `order_front_details` VALUES (18, 10, 25, 1.00, 3, '1', '藿香');
INSERT INTO `order_front_details` VALUES (19, 10, 3, 33.90, 1, '测试单位', '测试用药');
INSERT INTO `order_front_details` VALUES (22, 27, 3, 33.90, 1, '测试单位', '测试用药');
INSERT INTO `order_front_details` VALUES (24, 28, 1, 323.00, 2, '上海和黄药业有限公司是测试', '麝香保心丸子');
INSERT INTO `order_front_details` VALUES (25, 28, 4, 33.90, 1, '测试单位', '测试用药');
INSERT INTO `order_front_details` VALUES (26, 28, 3, 33.90, 2, '测试单位', '测试用药');
INSERT INTO `order_front_details` VALUES (27, 29, 1, 323.00, 2, '上海和黄药业有限公司是测试', '麝香保心丸子');
INSERT INTO `order_front_details` VALUES (28, 29, 4, 33.90, 1, '测试单位', '测试用药');
INSERT INTO `order_front_details` VALUES (34, 34, 1, 323.00, 2, '上海和黄药业有限公司是测试', '麝香保心丸子');
INSERT INTO `order_front_details` VALUES (35, 34, 3, 33.90, 1, '测试单位', '测试用药');
INSERT INTO `order_front_details` VALUES (36, 35, 1, 323.00, 2, '上海和黄药业有限公司是测试', '麝香保心丸子');
INSERT INTO `order_front_details` VALUES (37, 36, 1, 323.00, 2, '上海和黄药业有限公司是测试', '麝香保心丸子');
INSERT INTO `order_front_details` VALUES (38, 36, 4, 33.90, 1, '测试单位', '测试用药');
INSERT INTO `order_front_details` VALUES (39, 37, 1, 323.00, 3, '上海和黄药业有限公司是测试', '麝香保心丸子');
INSERT INTO `order_front_details` VALUES (40, 38, 3, 33.90, 2, '测试单位', '测试用药');
INSERT INTO `order_front_details` VALUES (41, 39, 1, 323.00, 3, '上海和黄药业有限公司是测试', '麝香保心丸子');
INSERT INTO `order_front_details` VALUES (42, 40, 1, 323.00, 2, '上海和黄药业有限公司是测试', '麝香保心丸子');
INSERT INTO `order_front_details` VALUES (43, 41, 1, 323.00, 2, '上海和黄药业有限公司是测试', '麝香保心丸子');
INSERT INTO `order_front_details` VALUES (44, 42, 1, 323.00, 2, '上海和黄药业有限公司是测试', '麝香保心丸子');
INSERT INTO `order_front_details` VALUES (45, 44, 1, 323.00, 1, '上海和黄药业有限公司是测试', '麝香保心丸子');
INSERT INTO `order_front_details` VALUES (46, 44, 3, 33.90, 3, '测试单位', '测试用药');
INSERT INTO `order_front_details` VALUES (48, 59, 1, 323.00, 23, '上海和黄药业有限公司是测试', '麝香保心丸子');

-- ----------------------------
-- Table structure for overdue_reminder
-- ----------------------------
DROP TABLE IF EXISTS `overdue_reminder`;
CREATE TABLE `overdue_reminder`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `storagedate` date NULL DEFAULT NULL,
  `enddate` date NULL DEFAULT NULL,
  `count` int(0) NULL DEFAULT NULL,
  `provider` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of overdue_reminder
-- ----------------------------
INSERT INTO `overdue_reminder` VALUES (1, 'test', '2021-03-30', '2021-09-10', 10, 'Tom');

-- ----------------------------
-- Table structure for provider_message
-- ----------------------------
DROP TABLE IF EXISTS `provider_message`;
CREATE TABLE `provider_message`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `provider` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '供应商单位名称',
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电话',
  `director` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '负责人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of provider_message
-- ----------------------------
INSERT INTO `provider_message` VALUES (1, '上海和黄药业有限公司', '12345667897', '张三');
INSERT INTO `provider_message` VALUES (2, '山海', '12345678910', '战术萨');
INSERT INTO `provider_message` VALUES (3, '测试', '12323', '13221');
INSERT INTO `provider_message` VALUES (4, '测试测试', '123', '123123123');
INSERT INTO `provider_message` VALUES (5, 'asd', '111', 'asd');
INSERT INTO `provider_message` VALUES (6, 'dsf', '111', 'sdf');
INSERT INTO `provider_message` VALUES (7, 'xvxc', '2345', 'sxf');
INSERT INTO `provider_message` VALUES (8, 'asd', '`111111', 'asdd');
INSERT INTO `provider_message` VALUES (9, 'dfg', '111', 'dfgdfg');
INSERT INTO `provider_message` VALUES (10, 'gfdcg', 'dfgdfg', 'dfgdfg');
INSERT INTO `provider_message` VALUES (11, 'asd', 'asd', 'asd');
INSERT INTO `provider_message` VALUES (12, '测试', NULL, NULL);
INSERT INTO `provider_message` VALUES (13, '测试', NULL, NULL);
INSERT INTO `provider_message` VALUES (14, '测试', NULL, NULL);
INSERT INTO `provider_message` VALUES (15, '测试', NULL, NULL);
INSERT INTO `provider_message` VALUES (16, '测试', NULL, NULL);
INSERT INTO `provider_message` VALUES (17, '测试', '110', '马朋辉');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '123456');
INSERT INTO `role` VALUES (2, 'clerk', '123456');
INSERT INTO `role` VALUES (3, 'test', '123456');

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `oid` int(0) NOT NULL COMMENT '所属订单编号，与order_front表中id对照',
  `medicineid` int(0) NOT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `medicinecount` int(0) NULL DEFAULT NULL,
  `provider` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
