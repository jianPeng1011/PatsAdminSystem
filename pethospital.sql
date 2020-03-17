/*
Navicat MySQL Data Transfer

Source Server         : linkDB
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : pethospital

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2020-03-17 11:26:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_drug
-- ----------------------------
DROP TABLE IF EXISTS `t_drug`;
CREATE TABLE `t_drug` (
  `drugId` int(16) NOT NULL,
  `drugName` varchar(255) DEFAULT NULL,
  `unit` varchar(16) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) unsigned zerofill DEFAULT NULL,
  `sl` decimal(10,0) unsigned zerofill DEFAULT NULL,
  `money` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`drugId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_drug
-- ----------------------------
INSERT INTO `t_drug` VALUES ('1', '狗狗感冒药', '瓶', '狗狗感冒药，即用即好', '00000090.00', '0000000099', '8910.00');

-- ----------------------------
-- Table structure for t_fostercare
-- ----------------------------
DROP TABLE IF EXISTS `t_fostercare`;
CREATE TABLE `t_fostercare` (
  `fosterId` varchar(255) NOT NULL,
  `userId` int(16) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `petName` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `startTime` date NOT NULL,
  `endTime` date NOT NULL,
  `createTime` date DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `evaluation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fosterId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_fostercare
-- ----------------------------

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logId` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `logType` varchar(255) DEFAULT NULL,
  `requestIp` varchar(255) DEFAULT NULL,
  `exceptionCode` varchar(255) DEFAULT NULL,
  `exceptionDetail` varchar(255) DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3111 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_pet
-- ----------------------------
DROP TABLE IF EXISTS `t_pet`;
CREATE TABLE `t_pet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `breed` varchar(255) DEFAULT NULL COMMENT '品种',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pet
-- ----------------------------
INSERT INTO `t_pet` VALUES ('1', '斗牛犬', '狗', '中型犬', '其面部有皱褶，成年犬大约有23公斤，高30-36厘米，属于中型犬', null);
INSERT INTO `t_pet` VALUES ('2', '中华田园犬', '狗', '中型犬', '中华田园犬是中国本土犬种之一，肩高 约40-55厘米，体重约 20-25公斤。肉食性不强，饮食偏杂食。与狼的外形非常相似，嘴短，额平。地域分布很广，主要分布于长城以南，青藏高原以东，以中原为中心的低海拔的汉族集聚地，是中国汉族几千年农耕社会背景下的产物', null);
INSERT INTO `t_pet` VALUES ('3', '萨摩耶', '狗', '中型犬', '是狐狸犬家族的一员，原是西伯利亚的原住民萨摩耶族培育出的犬种。特征是萨摩耶的笑容，看起来永远在笑的样子喜欢亲近人。除了温和的个性也以忍耐力跟健壮著称，原是用来拉雪橇跟看守驯鹿，也曾被西方探险家用来从事极地探险的任务', '测试备注');
INSERT INTO `t_pet` VALUES ('4', '英短', '猫', '家猫', '英国短毛猫，体形圆胖，四肢粗短发达，被毛短而密，头大脸圆，温柔平静，对人友善，极易饲养。在英国本地很早就获得认可', null);

-- ----------------------------
-- Table structure for t_seedoctor
-- ----------------------------
DROP TABLE IF EXISTS `t_seedoctor`;
CREATE TABLE `t_seedoctor` (
  `seeDoctorId` varchar(255) NOT NULL,
  `doctorId` int(16) NOT NULL,
  `doctorName` varchar(255) NOT NULL,
  `userId` int(16) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `petName` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `doctorTime` date NOT NULL,
  `createTime` date DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `evaluation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`seeDoctorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seedoctor
-- ----------------------------
INSERT INTO `t_seedoctor` VALUES ('JZ202003162189', '49', '刘医生', '50', '顾客王', '哈士奇', '18722229999', '2020-03-16', '2020-03-16', '狗子拉肚子', '已完成', null);
INSERT INTO `t_seedoctor` VALUES ('JZ202003164257', '47', '刘医生', '48', '顾客张', '哈士奇', '18733338888', '2020-03-16', '2020-03-16', '狗子拉肚子', '已完成', '刘医生很专业');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(16) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL DEFAULT '111111',
  `type` int(2) NOT NULL DEFAULT '0',
  `money` decimal(10,2) unsigned zerofill DEFAULT '00000000.00',
  `phone` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `workYears` int(10) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `imgUrl` varchar(255) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('0000000000000001', '管理员', '111111', '0', '00000000.00', '18737661333', null, null, null, null, null);
INSERT INTO `t_user` VALUES ('0000000000000048', '顾客张', '111111', '2', '00000700.00', '18733338888', null, null, null, null, null);
INSERT INTO `t_user` VALUES ('0000000000000049', '刘医生', '111111', '1', '00000000.00', '18733338887', '金牌医生', '10', '女', '/20200316/97b5edd39bee4a9f875f3b478a777dd1.jpeg', '我是刘医生');
INSERT INTO `t_user` VALUES ('0000000000000050', '顾客王', '111111', '2', '00009700.00', '18722229999', null, null, null, null, null);
