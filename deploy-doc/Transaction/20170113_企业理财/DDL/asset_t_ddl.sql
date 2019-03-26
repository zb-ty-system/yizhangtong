/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.76
Source Server Version : 50619
Source Host           : 192.168.0.76:3306
Source Database       : asset_t

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2016-12-28 14:21:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for patp_account_info
-- ----------------------------
DROP TABLE IF EXISTS `patp_account_info`;
CREATE TABLE `patp_account_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_type` varchar(10) DEFAULT NULL COMMENT '用户类型（个人1机构2）',
  `thirdparty_account_id` varchar(64) DEFAULT NULL COMMENT '交易系统账户id',
  `member_id` varchar(64) DEFAULT NULL COMMENT '会员系统会员id',
  `total_share` decimal(16,4) DEFAULT NULL COMMENT '总份额',
  `available_share` decimal(16,4) DEFAULT NULL COMMENT '可用份额',
  `frozen_share` decimal(16,4) DEFAULT NULL COMMENT '冻结份额',
  `share_unit` varchar(8) DEFAULT NULL COMMENT '份额单位',
  `status` varchar(10) DEFAULT NULL COMMENT '状态 1申购请求中2申购确认3申购失败4兑付申请中5兑付确认6兑付失败',
  `version` int(11) DEFAULT NULL COMMENT '版本',
  `memo` varchar(1024) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用户账户信息表';

-- ----------------------------
-- Table structure for patp_confirm_batch
-- ----------------------------
DROP TABLE IF EXISTS `patp_confirm_batch`;
CREATE TABLE `patp_confirm_batch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `confirm_batch_no` varchar(64) DEFAULT NULL COMMENT '确认批次号',
  `confirm_file_path` varchar(256) DEFAULT NULL COMMENT '确认文件路径',
  `confirm_file_name` varchar(64) DEFAULT NULL COMMENT '确认文件名',
  `trade_biz_code` varchar(8) DEFAULT NULL COMMENT '交易业务代码',
  `confirm_amount` decimal(16,4) DEFAULT NULL COMMENT '确认金额',
  `confirm_record_count` int(11) DEFAULT NULL COMMENT '确认记录数',
  `status` varchar(8) DEFAULT NULL COMMENT '状态 1处理中2已处理3处理失败',
  `memo` varchar(1024) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for patp_income
-- ----------------------------
DROP TABLE IF EXISTS `patp_income`;
CREATE TABLE `patp_income` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_account_id` varchar(64) DEFAULT NULL COMMENT '交易账户号',
  `thirdparty_account_id` varchar(64) DEFAULT NULL COMMENT '三方账户',
  `product_id` varchar(64) DEFAULT NULL COMMENT '产品id',
  `earning` decimal(16,4) DEFAULT NULL COMMENT '收益',
  `amount` decimal(16,4) DEFAULT NULL COMMENT '金额',
  `trade_biz_code` varchar(8) DEFAULT NULL COMMENT '交易业务号022-申购申请 122-申购确认 024-赎回 025-赎回预约 124-赎回确认',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `memo` varchar(1024) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for patp_invest_confirm
-- ----------------------------
DROP TABLE IF EXISTS `patp_invest_confirm`;
CREATE TABLE `patp_invest_confirm` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_account_id` varchar(64) DEFAULT NULL COMMENT '交易账户号',
  `request_id` varchar(64) DEFAULT NULL COMMENT '申购请求id',
  `thirdparty_account_id` varchar(64) DEFAULT NULL COMMENT '三方账户号',
  `confirm_amount` decimal(16,4) DEFAULT NULL COMMENT '确认金额',
  `confirm_price` decimal(16,4) DEFAULT NULL COMMENT '确认价格',
  `confirm_batch_no` varchar(64) DEFAULT NULL COMMENT '确认批次号',
  `confirm_share` decimal(16,4) DEFAULT NULL COMMENT '确认份额',
  `confirm_date` datetime DEFAULT NULL COMMENT '交易确认日期',
  `capital_share` decimal(16,4) DEFAULT NULL COMMENT '资产份额',
  `maturity_date` datetime DEFAULT NULL COMMENT '到期日',
  `expected_income` decimal(16,4) DEFAULT NULL COMMENT '预计收益',
  `share_unit` varchar(4) DEFAULT NULL COMMENT '份额单位',
  `app_sheet_no` varchar(64) DEFAULT NULL COMMENT '资产系统流水号',
  `status` varchar(8) DEFAULT NULL COMMENT '状态1待确认2已确认3失败',
  `version` int(11) DEFAULT NULL COMMENT '版本',
  `memo` varchar(1024) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_request_id` (`request_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for patp_invest_request
-- ----------------------------
DROP TABLE IF EXISTS `patp_invest_request`;
CREATE TABLE `patp_invest_request` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_account_id` varchar(64) DEFAULT NULL COMMENT '资产系统账户号',
  `thirdparty_account_id` varchar(64) NOT NULL COMMENT '交易系统账户id',
  `member_id` varchar(64) DEFAULT NULL COMMENT '会员id',
  `sale_channel` varchar(8) DEFAULT NULL COMMENT '销售机构代码(唐小僧11|企业理财12|摇旺21',
  `product_id` varchar(64) DEFAULT NULL COMMENT '产品id',
  `app_sheet_no` varchar(64) DEFAULT NULL,
  `invest_amount` decimal(16,4) DEFAULT NULL COMMENT '申购金额',
  `status` varchar(8) DEFAULT NULL COMMENT '状态1新申请2请求中3已确认4失败',
  `trade_serial_no` varchar(64) DEFAULT NULL COMMENT '交易流水号',
  `trade_date` datetime DEFAULT NULL COMMENT '交易日期',
  `trade_biz_code` int(11) DEFAULT NULL COMMENT '交易业务代码022-申购申请 122-申购确认 024-赎回 025-赎回预约 124-赎回确认',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `memo` varchar(1024) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_trade_serial_no` (`trade_serial_no`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8 COMMENT='申购请求表';

-- ----------------------------
-- Table structure for patp_redeem_confirm
-- ----------------------------
DROP TABLE IF EXISTS `patp_redeem_confirm`;
CREATE TABLE `patp_redeem_confirm` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_account_id` varchar(64) DEFAULT NULL COMMENT '资产系统账户id',
  `request_id` varchar(64) DEFAULT NULL COMMENT '赎回请求id',
  `confirm_batch_no` varchar(64) DEFAULT NULL COMMENT '确认批次号',
  `thirdparty_account_id` varchar(64) DEFAULT NULL COMMENT '交易系统账户id',
  `confirm_amount` decimal(16,4) DEFAULT NULL COMMENT '确认金额',
  `confirm_price` decimal(16,5) DEFAULT NULL COMMENT '确认价格',
  `confirm_share` decimal(16,4) DEFAULT NULL COMMENT '确认份额',
  `confirm_date` datetime DEFAULT NULL,
  `capital_share` decimal(16,4) DEFAULT NULL COMMENT '资产份额',
  `income` decimal(16,4) DEFAULT NULL COMMENT '收益',
  `maturity_date` datetime DEFAULT NULL COMMENT '到期日',
  `share_unit` varchar(2) DEFAULT NULL COMMENT '单位',
  `app_sheet_no` varchar(64) DEFAULT NULL,
  `status` varchar(8) DEFAULT NULL COMMENT '状态1待确认2已确认3失败',
  `version` int(11) DEFAULT NULL COMMENT '版本',
  `memo` varchar(1024) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_request_id` (`request_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for patp_redeem_request
-- ----------------------------
DROP TABLE IF EXISTS `patp_redeem_request`;
CREATE TABLE `patp_redeem_request` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_account_id` varchar(64) DEFAULT NULL,
  `thirdparty_account_id` varchar(64) DEFAULT NULL,
  `member_id` varchar(64) DEFAULT NULL,
  `origin_app_sheet_no` varchar(64) DEFAULT NULL COMMENT '对应申购请求appsheetno',
  `sale_channel` varchar(64) DEFAULT NULL,
  `product_id` varchar(64) DEFAULT NULL COMMENT '产品id',
  `app_sheet_no` varchar(64) DEFAULT NULL,
  `redeem_amount` decimal(16,4) DEFAULT NULL COMMENT '赎回金额',
  `trade_serial_no` varchar(64) DEFAULT NULL COMMENT '交易流水号',
  `trade_date` datetime DEFAULT NULL COMMENT '交易日期',
  `status` varchar(8) DEFAULT NULL COMMENT '状态1新申请2请求中3已确认4失败',
  `version` int(11) DEFAULT NULL COMMENT '版本',
  `trade_biz_code` varchar(8) DEFAULT NULL COMMENT '交易业务代码',
  `memo` varchar(1024) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_trade_serial_no` (`trade_serial_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

