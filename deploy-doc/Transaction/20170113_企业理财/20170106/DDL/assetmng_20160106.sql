/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.6.19-67.0-log : Database - assetmng
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`assetmng` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `assetmng`;

/*Table structure for table `ams_asset_info` */

DROP TABLE IF EXISTS `ams_asset_info`;

CREATE TABLE `ams_asset_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `finance_project` varchar(32) DEFAULT NULL COMMENT '融资项目',
  `asset_code` varchar(64) DEFAULT NULL COMMENT '资产代码：多支持25位，一般填写统一社会信用代码（18位），数字与英文',
  `asset_name` varchar(32) NOT NULL COMMENT '资产名称',
  `asset_desc` varchar(1024) DEFAULT NULL COMMENT '资产描述：项目背景，时间地点，资金用途',
  `asset_amount` decimal(16,4) DEFAULT NULL COMMENT '融资金额：最多支持99亿',
  `asset_type` tinyint(2) unsigned DEFAULT NULL COMMENT '发行类型：1.小贷收益权；2.应收账款收益权；3.定向融资工具；',
  `is_source_package` int(2) NOT NULL DEFAULT '2' COMMENT '1,是原始资产2,拆分过的资产包，默认是2',
  `success_amount` decimal(16,4) DEFAULT NULL COMMENT '成立条件：最少募集多少金额',
  `collect_start_time` date DEFAULT NULL COMMENT '募集开始日期',
  `collect_end_time` date DEFAULT NULL COMMENT '募集结束日期',
  `collect_time` int(4) DEFAULT NULL COMMENT '募集期：天',
  `limit_amount` decimal(16,4) DEFAULT NULL COMMENT '起投金额',
  `increse_amount` decimal(16,4) DEFAULT NULL COMMENT '递增金额',
  `issuer_id` varchar(30) DEFAULT NULL COMMENT '发行机构编码',
  `issuer_name` varchar(32) DEFAULT NULL COMMENT '发行机构名称',
  `issuer_reg_asset` int(64) DEFAULT NULL COMMENT '注册资本',
  `legal_person` varchar(64) DEFAULT NULL COMMENT '法人代表',
  `legal_person_id` varchar(32) DEFAULT NULL COMMENT '法人代表身份证，可以包含字母X',
  `business_scope` varchar(64) DEFAULT NULL COMMENT '经营范围，可为空',
  `issuer_desc` varchar(64) DEFAULT NULL COMMENT '发行方简介，可为空',
  `start_date` datetime DEFAULT NULL COMMENT '起息日',
  `end_date` datetime DEFAULT NULL COMMENT '到期日',
  `repayment_date` datetime DEFAULT NULL COMMENT '还款日',
  `closed_period` int(4) DEFAULT NULL COMMENT '封闭期 封闭期过后可以转让，当封闭期=期限时，即该产品不可转让',
  `limit_period` int(4) DEFAULT NULL COMMENT '期限',
  `repayment_type` tinyint(2) DEFAULT NULL COMMENT '还款方式：1.每月付息，到期还本；2.等额本息；3：到期还本付息；4：利息自动拨付，本金复投；5：等额本金',
  `repayment_mode` varchar(128) DEFAULT NULL COMMENT '还款保障：如抵押、保险、保证、保险措施',
  `credit_measure` varchar(128) DEFAULT NULL COMMENT '增信措施：手动填写',
  `exchane_id` varchar(30) DEFAULT NULL COMMENT '交易所ID',
  `exchange_name` varchar(64) DEFAULT NULL COMMENT '交易所名称',
  `exchange_province` varchar(20) DEFAULT NULL COMMENT '交易所所在省份',
  `exchange_reg_amounts` varchar(10) DEFAULT NULL COMMENT '交易所注册资金：手动填写，单位为万元',
  `consignee` varchar(32) DEFAULT NULL COMMENT '承销人',
  `record_name` varchar(32) DEFAULT NULL COMMENT '备案名称',
  `sale_amount` decimal(16,4) DEFAULT NULL COMMENT '已销售金额',
  `frozen_amount` decimal(16,4) DEFAULT NULL COMMENT '冻结金额',
  `sale_status` tinyint(2) DEFAULT NULL COMMENT '销售状态 1:销售中，2：已售完，3：已失效',
  `sale_priority` int(3) DEFAULT NULL COMMENT '销售优先级',
  `comments` varchar(1024) DEFAULT NULL COMMENT '备注',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  `modify_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=502 DEFAULT CHARSET=utf8 COMMENT='资产表';

/*Table structure for table `ams_asset_pool` */

DROP TABLE IF EXISTS `ams_asset_pool`;

CREATE TABLE `ams_asset_pool` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pool_id` int(11) NOT NULL COMMENT '资产池ID',
  `asset_id` int(11) NOT NULL COMMENT '资产ID',
  `asset_amount` decimal(16,4) NOT NULL COMMENT '分配金额',
  `used_amount` decimal(16,4) DEFAULT '0.0000' COMMENT '已使用金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='资产资产池关联表';

/*Table structure for table `ams_asset_sale_match` */

DROP TABLE IF EXISTS `ams_asset_sale_match`;

CREATE TABLE `ams_asset_sale_match` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `trade_no` varchar(30) NOT NULL COMMENT '订单流水号',
  `asset_id` int(11) DEFAULT NULL COMMENT '资产ID',
  `asset_amount` decimal(16,4) DEFAULT NULL COMMENT '资产匹配金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='资产销售匹配表';

/*Table structure for table `ams_pool_info` */

DROP TABLE IF EXISTS `ams_pool_info`;

CREATE TABLE `ams_pool_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pool_code` varchar(30) DEFAULT NULL COMMENT '资产包编码',
  `pool_name` varchar(32) NOT NULL COMMENT '资产池名称',
  `asset_package_type` varchar(64) DEFAULT NULL COMMENT '1,用于打包资产池2，用于部署发布资产池.默认用于部署发布资产池',
  `business_mode` tinyint(2) DEFAULT NULL COMMENT '1:定向委托的收益权转让,2:定向委托的交易所挂牌承销',
  `total_amount` decimal(16,4) DEFAULT NULL COMMENT '总金额',
  `frozen_amount` decimal(16,4) DEFAULT NULL COMMENT '冻结金额',
  `sale_amount` decimal(16,4) DEFAULT NULL COMMENT '已销售金额',
  `stock` decimal(16,4) DEFAULT NULL COMMENT '库存',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态 1.可分配，2.分配完成',
  `issuer_code` varchar(30) DEFAULT NULL COMMENT '发行机构编号(可能与原始资产不同)',
  `issuer_name` varchar(32) DEFAULT NULL COMMENT '发行机构名称(可能与原始资产不同)',
  `comments` varchar(1024) DEFAULT NULL COMMENT '备注',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  `modify_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=501 DEFAULT CHARSET=utf8 COMMENT='资产池信息表';

/*Table structure for table `ams_pool_product` */

DROP TABLE IF EXISTS `ams_pool_product`;

CREATE TABLE `ams_pool_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pool_id` int(11) NOT NULL COMMENT '资产池ID',
  `product_code` varchar(30) NOT NULL COMMENT '产品代码',
  `amount` decimal(16,4) NOT NULL COMMENT '产品总金额',
  `status` tinyint(2) DEFAULT NULL COMMENT '产品状态 1:募集中，2：成立，3：盖标4：流标',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='资产池分配产品表';

/*Table structure for table `ams_product_order` */

DROP TABLE IF EXISTS `ams_product_order`;

CREATE TABLE `ams_product_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `trade_no` varchar(30) NOT NULL COMMENT '交易流水号',
  `product_code` varchar(30) DEFAULT NULL COMMENT '产品编码',
  `trade_amount` decimal(16,4) DEFAULT NULL COMMENT '交易金额',
  `trade_time` datetime DEFAULT NULL COMMENT '交易时间',
  `status` int(2) DEFAULT NULL COMMENT '交易状态 1:成功；2：失败',
  `member_id` int(11) DEFAULT NULL COMMENT '会员ID',
  `member_name` varchar(32) DEFAULT NULL COMMENT '会员姓名',
  `bank_no` varchar(20) DEFAULT NULL COMMENT '银行卡号',
  `bank_name` varchar(10) DEFAULT NULL COMMENT '银行名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='资产销售订单表';