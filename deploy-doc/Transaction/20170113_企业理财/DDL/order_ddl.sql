/*
Navicat MySQL Data Transfer

Source Server         : dev
Source Server Version : 50633
Source Host           : localhost:3306
Source Database       : order

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2016-12-28 16:12:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pay_result_file
-- ----------------------------
DROP TABLE IF EXISTS `pay_result_file`;
CREATE TABLE `pay_result_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial_no` varchar(64) DEFAULT '' COMMENT '文件批次号',
  `user_id` varchar(64) DEFAULT '' COMMENT '操作用户',
  `path` varchar(500) DEFAULT '' COMMENT '文件地址',
  `status` int(3) DEFAULT NULL COMMENT '状态(10:待处理，20:正在读取，30:已处理，40:不可操作，50处理失败)',
  `file_original_name` varchar(128) DEFAULT '' COMMENT '上传文件名称',
  `operate_count` int(11) DEFAULT NULL COMMENT '操作次数',
  `read_line` int(11) DEFAULT NULL COMMENT '读取行数',
  `invest_type` int(3) DEFAULT NULL COMMENT '资金类型：10-付款，20-退款，30-兑付',
  `result` varchar(1000) DEFAULT '' COMMENT '处理结果',
  `version` int(11) DEFAULT NULL COMMENT '乐观锁版本号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `modify_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='流水文件';

-- ----------------------------
-- Table structure for trd_bank_serial
-- ----------------------------
DROP TABLE IF EXISTS `trd_bank_serial`;
CREATE TABLE `trd_bank_serial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trade_day` varchar(32) DEFAULT '' COMMENT '交易日',
  `trade_time` varchar(32) DEFAULT '' COMMENT '交易时间',
  `interest_day` varchar(32) DEFAULT '' COMMENT '起息日',
  `trade_type` varchar(32) DEFAULT '' COMMENT '交易类型',
  `loan_amount` decimal(16,4) DEFAULT NULL COMMENT '借方金额',
  `pay_amount` decimal(16,4) DEFAULT NULL COMMENT '贷方金额',
  `amount` decimal(16,4) DEFAULT NULL COMMENT '余额',
  `remark` varchar(32) DEFAULT '' COMMENT '摘要',
  `serial_no` varchar(32) DEFAULT '' COMMENT '流水号',
  `procedure_no` varchar(32) DEFAULT '' COMMENT '流程实例号',
  `business_name` varchar(32) DEFAULT '' COMMENT '业务名称',
  `use_type` varchar(32) DEFAULT '' COMMENT '用途',
  `business_refer_no` varchar(32) DEFAULT '' COMMENT '业务参考号',
  `business_remark` varchar(32) DEFAULT '' COMMENT '业务摘要',
  `other_remark` varchar(32) DEFAULT '' COMMENT '其他摘要',
  `bank_name` varchar(32) DEFAULT '' COMMENT '收/付方分行名',
  `pay_receive_name` varchar(32) DEFAULT '' COMMENT '收/付方名称',
  `pay_receive_account` varchar(32) DEFAULT '' COMMENT '收/付方账号',
  `pay_receive_open_bank_no` varchar(32) DEFAULT '' COMMENT '收/付方开户行行号',
  `pay_receive_open_bank_name` varchar(32) DEFAULT '' COMMENT '收/付方开户行名称',
  `pay_receive_open_bank_address` varchar(32) DEFAULT '' COMMENT '收/付方开户行地址',
  `company_bank_name` varchar(128) DEFAULT '' COMMENT '母/子公司帐号分行名',
  `company_bank_account` varchar(32) DEFAULT '' COMMENT '母/子公司帐号',
  `company_name` varchar(32) DEFAULT '' COMMENT '母/子公司名称',
  `info_flag` varchar(32) DEFAULT '' COMMENT '信息标志',
  `file_flag` varchar(32) DEFAULT '' COMMENT '有否附件信息',
  `account_flag` varchar(32) DEFAULT '' COMMENT '冲账标志',
  `extend_remark` varchar(32) DEFAULT '' COMMENT '扩展摘要',
  `trade_analyse_code` varchar(32) DEFAULT '' COMMENT '交易分析码',
  `bill_no` varchar(32) DEFAULT '' COMMENT '票据号',
  `business_order_no` varchar(32) DEFAULT '' COMMENT '上午支付订单号',
  `inner_no` varchar(32) DEFAULT '' COMMENT '内部编号',
  `pay_res_code` varchar(16) DEFAULT '' COMMENT '预约码',
  `file_no` varchar(32) DEFAULT '' COMMENT '文件批次号(pay_result_file.path)',
  `invest_type` int(3) DEFAULT NULL COMMENT '资金类型：10-付款，20-退款，30-兑付',
  `status` int(3) DEFAULT NULL COMMENT '流水处理状态(100：未处理，200已处理)',
  `version` int(11) DEFAULT NULL COMMENT '乐观锁版本号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `modify_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for trd_order
-- ----------------------------
DROP TABLE IF EXISTS `trd_order`;
CREATE TABLE `trd_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(64) NOT NULL COMMENT '订单编号',
  `member_id` varchar(64) DEFAULT NULL COMMENT '用户ID',
  `product_id` varchar(64) DEFAULT NULL COMMENT '产品编号(production.id)',
  `amount` decimal(16,4) DEFAULT NULL COMMENT '交易额',
  `trx_date` datetime DEFAULT NULL COMMENT '交易日期',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `status` int(3) DEFAULT NULL COMMENT '订单状态(100-交易待确认、200-交易成功、300-交易失败、400-产品流标、500-申购申请、600-申购成功、700-申购失败)',
  `res_pay_code` varchar(64) DEFAULT NULL COMMENT '预约码',
  `pay_status` int(3) DEFAULT NULL COMMENT '支付状态(10:等待支付，20:支付成功，30:支付失败;40:等待兑付，50:兑付成功，60:兑付失败)',
  `order_type` int(3) DEFAULT NULL COMMENT '订单状态(10买入、20卖出、30收益发放、40到期兑付)',
  `memo` varchar(500) DEFAULT NULL COMMENT '描述',
  `accounts_manager_tel` varchar(12) DEFAULT '' COMMENT '客户经理手机号',
  `channel` int(3) DEFAULT NULL COMMENT '渠道(11：唐小僧，12：企业理财，21摇旺)',
  `trade_serial_no` varchar(64) DEFAULT '' COMMENT '交易流水号',
  `product_name` varchar(256) DEFAULT '' COMMENT '产品名称',
  `version` int(11) DEFAULT NULL COMMENT '乐观锁版本号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `modify_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='订单信息';

-- ----------------------------
-- Table structure for trd_order_contract
-- ----------------------------
DROP TABLE IF EXISTS `trd_order_contract`;
CREATE TABLE `trd_order_contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contract_code` varchar(64) DEFAULT '' COMMENT '合同编号',
  `order_id` varchar(64) DEFAULT NULL COMMENT '订单编号(trd_order.id)',
  `content` text COMMENT '合同内容',
  `contract_type` int(3) DEFAULT NULL COMMENT '合同类型(100买入、200卖出、300兑付)',
  `contract_file_name` varchar(128) DEFAULT '' COMMENT '合同文件名',
  `contract_path` varchar(256) DEFAULT '' COMMENT '文件路径',
  `show_name` varchar(128) DEFAULT '' COMMENT '文件显示名',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单合同';

-- ----------------------------
-- Table structure for trd_order_history
-- ----------------------------
DROP TABLE IF EXISTS `trd_order_history`;
CREATE TABLE `trd_order_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(64) NOT NULL COMMENT '订单编号',
  `member_id` varchar(64) DEFAULT NULL COMMENT '用户ID',
  `product_id` varchar(64) DEFAULT NULL COMMENT '产品编号(production.id)',
  `amount` decimal(16,4) DEFAULT NULL COMMENT '交易额',
  `trx_date` datetime DEFAULT NULL COMMENT '交易日期',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `status` int(3) DEFAULT NULL COMMENT '订单状态(100-交易待确认、200-交易成功、300-交易失败、400-产品流标)',
  `res_pay_code` varchar(64) DEFAULT NULL COMMENT '预约码',
  `pay_status` int(3) DEFAULT NULL COMMENT '支付状态(10:等待支付，20:支付成功，30:支付失败)',
  `order_type` int(3) DEFAULT NULL COMMENT '订单状态(10买入、20卖出、30收益发放、40到期兑付)',
  `memo` varchar(500) DEFAULT NULL COMMENT '描述',
  `accounts_manager_tel` varchar(12) DEFAULT '' COMMENT '客户经理手机号',
  `channel` int(3) DEFAULT NULL COMMENT '渠道(11：唐小僧，12：企业理财，21摇旺)',
  `trade_serial_no` varchar(64) DEFAULT '' COMMENT '交易流水号',
  `product_name` varchar(256) DEFAULT '' COMMENT '产品名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `modify_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='订单历史记录';

-- ----------------------------
-- Table structure for trd_pay_result
-- ----------------------------
DROP TABLE IF EXISTS `trd_pay_result`;
CREATE TABLE `trd_pay_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_no` varchar(64) DEFAULT NULL COMMENT '支付编号',
  `res_pay_code` varchar(16) DEFAULT NULL COMMENT '预约码',
  `user_bank` varchar(128) DEFAULT '' COMMENT '用户银行',
  `user_bank_name` varchar(128) DEFAULT NULL COMMENT '用户开户行',
  `user_bank_account` varchar(64) DEFAULT '' COMMENT '用户银行账号',
  `pay_amount` decimal(14,4) DEFAULT NULL COMMENT '支付金额',
  `receive_bank` varchar(128) DEFAULT '' COMMENT '收款银行',
  `receive_bank_name` varchar(128) DEFAULT NULL COMMENT '收款方开户行',
  `receive_bank_account` varchar(64) DEFAULT '' COMMENT '收款方银行账号',
  `receive__amount` decimal(14,4) DEFAULT NULL COMMENT '实际到账金额',
  `status` int(3) DEFAULT NULL COMMENT '状态(100:支付成功、200:多单、300:订单金额不一致、400:没有对应订单)',
  `pay_biz_code` int(3) DEFAULT NULL COMMENT '支付类型：10:支付 20;兑付 30:申购失败退款',
  `pay_serial_no` varchar(64) DEFAULT NULL COMMENT '银行流水号',
  `file_serial_no` varchar(255) DEFAULT '' COMMENT '文件批次号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `modify_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='支付结果';

-- ----------------------------
-- Table structure for trd_res_code
-- ----------------------------
DROP TABLE IF EXISTS `trd_res_code`;
CREATE TABLE `trd_res_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '预约码ID',
  `order_id` varchar(64) DEFAULT '' COMMENT '订单ID',
  `pay_date` varchar(16) DEFAULT '' COMMENT '预约码-字母',
  `pay_code` int(11) DEFAULT NULL COMMENT '预约码-数字',
  `version` int(11) DEFAULT NULL COMMENT '乐观锁版本号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `modify_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
