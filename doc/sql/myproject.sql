/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : flowable

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-03-21 15:34:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for b_branch
-- ----------------------------
DROP TABLE IF EXISTS `b_branch`;
CREATE TABLE `b_branch` (
  `ID` bigint(20) NOT NULL,
  `BRANCH_CODE` varchar(255) DEFAULT NULL COMMENT '分公司代码',
  `BRANCH_NAME` varchar(255) DEFAULT NULL COMMENT '分公司名称',
  `OPERATE_VERSION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `CREATED_BY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `UPDATED_BY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `UPDATED_ON` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_ON` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of b_branch
-- ----------------------------
INSERT INTO `b_branch` VALUES ('1108629292557881345', '3010100', '上海分公司', '1', 'system', null, null, '2017-04-17 15:00:12');

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` bigint(36) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_pwd` varchar(255) DEFAULT NULL,
  `OPERATE_VERSION` varchar(255) DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATED_BY` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_ON` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_ON` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1107629460720467969', 'huahifeng', 'hhf', '1', '胡海丰', null, null, '2019-03-18 21:06:54.653');
INSERT INTO `person` VALUES ('1107629494006464514', 'huahifeng', 'hhf', '1', '胡海丰', null, null, '2019-03-18 21:07:03.17');
INSERT INTO `person` VALUES ('1107636460489560066', 'huahifeng', 'hhf', '1', '胡海丰', null, null, '2019-03-18 21:34:45.433');
INSERT INTO `person` VALUES ('1107647187417935874', 'huahifeng', 'hhf', '1', '胡海丰', null, null, '2019-03-18 22:17:22.916');
INSERT INTO `person` VALUES ('1107647511302049794', 'huahifeng', 'hhf', '1', '胡海丰', null, null, '2019-03-18 22:18:40.138');
INSERT INTO `person` VALUES ('1107836470103678977', 'huahifeng', 'hhf', '1', '胡海丰', null, null, '2019-03-19 10:49:31.462');

-- ----------------------------
-- Table structure for t_rbac_user
-- ----------------------------
DROP TABLE IF EXISTS `t_rbac_user`;
CREATE TABLE `t_rbac_user` (
  `ID` bigint(36) NOT NULL,
  `USER_CODE` varchar(255) DEFAULT NULL COMMENT '用户代码',
  `USER_LOGIN_CODE` varchar(255) DEFAULT NULL COMMENT '用户登录代码',
  `USER_NAME` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `PASSWORD` varchar(255) DEFAULT NULL COMMENT '密码',
  `MOBILE` varchar(255) DEFAULT NULL COMMENT '电话',
  `EMAIL` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `BRANCH_CODE` varchar(255) DEFAULT NULL COMMENT '分公司代码',
  `BRANCH_NAME` varchar(255) DEFAULT NULL COMMENT '分公司名称',
  `USER_STATUS` varchar(255) DEFAULT NULL COMMENT '用户状态   1:有效 0:无效',
  `OPERATE_VERSION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `CREATED_BY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `UPDATED_BY` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_ON` datetime DEFAULT NULL,
  `CREATED_ON` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_rbac_user
-- ----------------------------
INSERT INTO `t_rbac_user` VALUES ('1107910857515352066', 'huhaifeng', 'huhaifeng', '胡海丰', '123456', '18158939927', '1311843248@qq.com', '9010100', '总公司', '1', '1', 'system', null, null, '2017-04-17 15:00:12.0');
INSERT INTO `t_rbac_user` VALUES ('1107914154217324546', 'zhangsan', 'zhangsan', '张三', '123456', '18158939927', '1311843248@qq.com', '9010100', '总公司', '1', '1', 'system', null, null, '2017-04-17 15:00:12.0');

-- ----------------------------
-- Table structure for t_user_region
-- ----------------------------
DROP TABLE IF EXISTS `t_user_region`;
CREATE TABLE `t_user_region` (
  `ID` bigint(20) NOT NULL,
  `USER_LOGIN_CODE` varchar(255) DEFAULT NULL COMMENT '用户登录代码',
  `REGION_CODE` varchar(255) DEFAULT NULL COMMENT '审核机构代码',
  `REGION_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '审核机构名称',
  `OPERATE_VERSION` varchar(255) DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  `LEVEL` varchar(255) DEFAULT NULL COMMENT '核保等级',
  `UPDATED_ON` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_ON` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_region
-- ----------------------------
INSERT INTO `t_user_region` VALUES ('234543245', 'huhaifeng', '3010100', '上海分公司', '1', 'system', null, '6', '2019-03-20 16:07:54', '2019-03-20 16:07:54');
INSERT INTO `t_user_region` VALUES ('1108211598385766402', 'zhangsan', '3010100', '上海分公司', '1', 'system', null, '5', '2019-03-20 16:07:54', '2019-03-20 16:07:54');

-- ----------------------------
-- Table structure for uw_task
-- ----------------------------
DROP TABLE IF EXISTS `uw_task`;
CREATE TABLE `uw_task` (
  `ID` bigint(20) NOT NULL,
  `APP_NO` varchar(200) NOT NULL COMMENT '申请号',
  `BRANCH_CODE` varchar(255) DEFAULT NULL COMMENT '分公司代码',
  `TASK_LEVEL` varchar(255) DEFAULT NULL COMMENT '任务等级',
  `CURRENT_LEVEL` varchar(255) DEFAULT NULL COMMENT '已核等级',
  `FINAL_LEVEL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '终审等级',
  `TASK_STATUS` varchar(255) DEFAULT NULL COMMENT '任务状态  Y:已终审  N:未终审',
  `AUDIT_CODE` varchar(255) DEFAULT NULL COMMENT '核保代码	1：通过 2：拒保 3：退回',
  `AUDIT_REASON` varchar(255) DEFAULT NULL COMMENT '核保原因',
  `HANDLER_NO` varchar(255) DEFAULT NULL COMMENT '当前处理人',
  `HANDLER_LEVEL` varchar(255) DEFAULT NULL COMMENT '当前处理人等级',
  `HANDLER_BRANCH_CODE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '当前处理人分公司',
  `HANDLER_NAME` varchar(255) DEFAULT NULL COMMENT '当前处理人名称',
  `INSURED_AMOUNT` varchar(255) DEFAULT NULL COMMENT '保额',
  `OPERATE_VERSION` varchar(255) DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATED_BY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `UPDATED_ON` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_ON` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of uw_task
-- ----------------------------

-- ----------------------------
-- Table structure for uw_task_detail
-- ----------------------------
DROP TABLE IF EXISTS `uw_task_detail`;
CREATE TABLE `uw_task_detail` (
  `ID` bigint(36) NOT NULL,
  `PROCESS_NODE_ID` varchar(200) DEFAULT NULL COMMENT '流程任务节点id',
  `PROCESS_NODE_NAME` varchar(255) DEFAULT NULL COMMENT '流程任务节点名称',
  `PROCESS_TASK_ID` varchar(255) DEFAULT NULL COMMENT '工作流任务id',
  `BUSINESS_NO` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Uw_TASK表主键id',
  `APP_NO` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '申请号',
  `BRANCH_CODE` varchar(255) DEFAULT NULL COMMENT '分公司代码',
  `TASK_LEVEL` varchar(255) DEFAULT NULL COMMENT '任务等级',
  `CURRENT_LEVEL` varchar(255) DEFAULT NULL COMMENT '已核等级',
  `FINAL_LEVEL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '终审等级',
  `TASK_STATUS` varchar(255) DEFAULT NULL COMMENT '任务状态  Y:已终审  N:未终审',
  `AUDIT_CODE` varchar(255) DEFAULT NULL COMMENT '核保代码	1：通过 2：拒保 3：退回',
  `AUDIT_REASON` varchar(255) DEFAULT NULL COMMENT '核保原因',
  `HANDLER_NO` varchar(255) DEFAULT NULL COMMENT '当前处理人',
  `HANDLER_LEVEL` varchar(255) DEFAULT NULL COMMENT '当前处理人等级',
  `HANDLER_BRANCH_CODE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '当前处理人分公司',
  `HANDLER_NAME` varchar(255) DEFAULT NULL COMMENT '当前处理人名称',
  `INSURED_AMOUNT` varchar(255) DEFAULT NULL COMMENT '保额',
  `OPERATE_VERSION` varchar(255) DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATED_BY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `UPDATED_ON` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_ON` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of uw_task_detail
-- ----------------------------
INSERT INTO `uw_task_detail` VALUES ('1108625755656450050', 'usertask', '用户审核', '45013', '426033707967447040', '7607YK707orET5', '3010100', '6', '0', '', 'N', null, null, 'zhangsan', '5', '9010100', '张三', '681', '2', '胡海丰', '胡海丰', '2019-03-21 15:05:52', '2019-03-21 15:05:52');

-- ----------------------------
-- Table structure for uw_task_detail_his
-- ----------------------------
DROP TABLE IF EXISTS `uw_task_detail_his`;
CREATE TABLE `uw_task_detail_his` (
  `ID` bigint(36) NOT NULL,
  `APP_NO` varchar(200) NOT NULL COMMENT '申请号',
  `BRANCH_CODE` varchar(255) DEFAULT NULL COMMENT '分公司代码',
  `TASK_LEVEL` varchar(255) DEFAULT NULL COMMENT '任务等级',
  `CURRENT_LEVEL` varchar(255) DEFAULT NULL COMMENT '已核等级',
  `FINAL_LEVEL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '终审等级',
  `TASK_STATUS` varchar(255) DEFAULT NULL COMMENT '任务状态  Y:已终审  N:未终审',
  `AUDIT_CODE` varchar(255) DEFAULT NULL COMMENT '核保代码	1：通过 2：拒保 3：退回',
  `AUDIT_REASON` varchar(255) DEFAULT NULL COMMENT '核保原因',
  `HANDLER_NO` varchar(255) DEFAULT NULL COMMENT '当前处理人',
  `HANDLER_LEVEL` varchar(255) DEFAULT NULL COMMENT '当前处理人等级',
  `HANDLER_BRANCH_CODE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '当前处理人分公司',
  `HANDLER_NAME` varchar(255) DEFAULT NULL COMMENT '当前处理人名称',
  `INSURED_AMOUNT` varchar(255) DEFAULT NULL COMMENT '保额',
  `OPERATE_VERSION` varchar(255) DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATED_BY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `UPDATED_ON` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `PROCESS_NODE_ID` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '流程任务节点id',
  `PROCESS_NODE_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '流程任务节点名称',
  `PROCESS_TASK_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '工作流任务id',
  `BUSINESS_NO` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Uw_TASK表主键id',
  `CREATED_ON` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of uw_task_detail_his
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
