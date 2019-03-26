
CREATE DATABASE  IF NOT EXISTS `asset_t` ;

USE `asset_t`;

/*Table structure for table `ta_account_info` */

DROP TABLE IF EXISTS `ta_account_info`;

CREATE TABLE `ta_account_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `platform_member_id` varchar(32) DEFAULT NULL COMMENT '会员ID',
  `cert_type` varchar(32) DEFAULT NULL COMMENT '证件类型',
  `cert_no` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `channel_no` varchar(32) DEFAULT NULL COMMENT '渠道 TXS-唐小僧 QYLC-企业理财 YW-摇旺',
  `ta_account_no` varchar(32) DEFAULT NULL COMMENT 'ta账号',
  `status` varchar(32) DEFAULT NULL COMMENT '账户状态 NORMAL:正常,FROZEN:冻结,CLOSED:销户',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='用户交易账户表';

/*Table structure for table `ta_account_transaction` */

DROP TABLE IF EXISTS `ta_account_transaction`;

CREATE TABLE `ta_account_transaction` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `ta_account_no` varchar(32) DEFAULT NULL COMMENT 'ta账户',
  `trade_account_no` varchar(32) DEFAULT NULL COMMENT '交易账户',
  `trade_amount` decimal(16,4) DEFAULT NULL COMMENT '交易金额',
  `total_amount` decimal(16,4) DEFAULT NULL COMMENT '变动后账户总额',
  `frozen_amount` decimal(16,4) DEFAULT NULL COMMENT '变动后冻结金额',
  `cash_amount` decimal(16,4) DEFAULT NULL COMMENT '变动后可兑付金额',
  `table_id` int(16) DEFAULT NULL COMMENT '变动表ID',
  `type` varchar(32) DEFAULT NULL COMMENT 'OPEN:产品账户开户, CONFIRMED:投资确认-金额增加,INTEREST:利息发放-金额增加,CASHED:产品兑付-金额减小,FROZEN:产品账户冻结,CLOSED:产品账户销户;',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `ta_batch` */

DROP TABLE IF EXISTS `ta_batch`;

CREATE TABLE `ta_batch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trade_biz_code` varchar(8) DEFAULT NULL COMMENT '022-投资申请 122-投资确认 124-兑付确认',
  `file_name` varchar(256) DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(512) DEFAULT NULL COMMENT '文件路径',
  `status` varchar(32) DEFAULT NULL COMMENT '状态：0初始化 1处理中 2已处理 3 处理异常',
  `thirdparty_batch_no` varchar(32) DEFAULT NULL COMMENT '交易系统文件批次号',
  `batch_no` char(32) DEFAULT NULL COMMENT '批次号',
  `total_amount` decimal(16,4) DEFAULT NULL COMMENT '总金额',
  `total_count` int(11) DEFAULT NULL COMMENT '总记录数',
  `current_line_no` int(11) DEFAULT NULL COMMENT '文件读取行数',
  `parent_batch_no` varchar(32) DEFAULT NULL COMMENT '关联的批次号',
  `retry_times` tinyint(4) DEFAULT NULL COMMENT '重试次数',
  `memo` varchar(512) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `product_code` varchar(64) DEFAULT NULL COMMENT '产品编号',
  `sub_status` varchar(4) DEFAULT NULL COMMENT '处理子状态',
  `operation_batch_result` varchar(32) DEFAULT NULL COMMENT '运营操作',
  `operation_batch_time` datetime DEFAULT NULL COMMENT '运营操作时间',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Table structure for table `ta_invest_asset_match` */

DROP TABLE IF EXISTS `ta_invest_asset_match`;

CREATE TABLE `ta_invest_asset_match` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `trade_no` varchar(64) DEFAULT NULL COMMENT '交易流水号',
  `app_sheet_no` varchar(64) DEFAULT NULL COMMENT 'TA流水号',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单流水号',
  `trade_amount` decimal(16,4) DEFAULT NULL COMMENT '交易金额',
  `product_code` varchar(64) DEFAULT NULL COMMENT '产品code',
  `asset_code` varchar(64) DEFAULT NULL COMMENT '资产code',
  `matched_amount` decimal(16,4) DEFAULT NULL COMMENT '匹配金额',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8;

/*Table structure for table `ta_invest_record` */

DROP TABLE IF EXISTS `ta_invest_record`;

CREATE TABLE `ta_invest_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trade_serial_no` varchar(64) NOT NULL COMMENT '交易系统流水号',
  `ta_account_no` varchar(64) DEFAULT NULL COMMENT 'TA账户',
  `trade_account_no` varchar(64) DEFAULT NULL COMMENT '交易账号',
  `thirdparty_account_no` varchar(64) DEFAULT NULL COMMENT '交易系统的交易账号',
  `platform_user_id` varchar(64) DEFAULT NULL COMMENT '平台用户ID',
  `sale_channel` varchar(8) DEFAULT NULL COMMENT '销售机构代码 唐小僧11|企业理财12|摇旺21',
  `invest_amount` decimal(16,4) DEFAULT NULL COMMENT '投资金额',
  `invest_time` datetime DEFAULT NULL COMMENT '投资时间',
  `product_code` varchar(64) DEFAULT NULL COMMENT '产品编号',
  `pay_serial_no` varchar(64) DEFAULT NULL COMMENT '支付的银行流水号',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `confirm_amount` decimal(16,4) DEFAULT NULL COMMENT '确认金额',
  `confirm_time` datetime DEFAULT NULL COMMENT '确认时间',
  `status` varchar(32) DEFAULT NULL COMMENT '状态 0投资失败 1待确认 2投资成功',
  `pattern_code` varchar(8) DEFAULT NULL COMMENT '产品类型 01:现金管理, 02:定期类, 03:净值型',
  `create_by` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `app_sheet_no` varchar(64) DEFAULT NULL COMMENT '资产系统流水号',
  `file_batch_no` varchar(64) DEFAULT NULL COMMENT '文件批次号',
  `asset_match_status` varchar(32) DEFAULT NULL COMMENT '资产匹配状态 0 匹配失败 1 待匹配 2匹配成功',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8 COMMENT='用户投资记录表';

/*Table structure for table `ta_product_asset_register` */

DROP TABLE IF EXISTS `ta_product_asset_register`;

CREATE TABLE `ta_product_asset_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `product_code` varchar(64) DEFAULT NULL COMMENT '产品代码',
  `asset_code` varchar(64) DEFAULT NULL COMMENT '资产代码',
  `asset_assign_amount` decimal(16,4) DEFAULT '0.0000' COMMENT '资产分配金额',
  `asset_matched_amount` decimal(16,4) DEFAULT '0.0000' COMMENT '已匹配金额',
  `match_status` varchar(32) DEFAULT NULL COMMENT '匹配状态 UNMATCHED 未完成,MATCHED 已完成',
  `asset_name` varchar(64) DEFAULT NULL COMMENT '资产名称',
  `priority` int(4) DEFAULT NULL COMMENT '匹配优先级',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='产品-资产登记表';

/*Table structure for table `ta_product_register` */

DROP TABLE IF EXISTS `ta_product_register`;

CREATE TABLE `ta_product_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `product_code` varchar(64) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(128) DEFAULT NULL COMMENT '产品名称',
  `product_line_code` varchar(64) DEFAULT NULL COMMENT '产品线编号',
  `product_status` varchar(32) DEFAULT NULL COMMENT '  REGISTERED:已登记,UNCONFIRMED:待确认, CONFIRMED:已确认,COLLECTED募集成功,MATCHED:匹配完成, NOTICED_AMS:已通知资管, NOTICED_FAILED:通知失败, GENERATED_REPLY_PLAN:已生成还款计划;',
  `invest_effect_time` datetime DEFAULT NULL COMMENT '投资生效时间',
  `invest_cash_time` datetime DEFAULT NULL COMMENT '投资兑付时间',
  `actual_cash_time` datetime DEFAULT NULL COMMENT '实际兑付时间',
  `duration` int(8) DEFAULT NULL COMMENT '存续期',
  `actual_duration` int(8) DEFAULT NULL COMMENT '实际存续期',
  `duration_unit` varchar(8) DEFAULT NULL COMMENT '存续期单位',
  `product_amount` decimal(16,4) DEFAULT NULL COMMENT '产品规模',
  `sale_amount` decimal(16,4) DEFAULT NULL COMMENT '实际销售规模',
  `calendar_mode` varchar(32) DEFAULT NULL COMMENT '日历模式 1：自然日 2：交易日',
  `yield` decimal(4,4) DEFAULT NULL COMMENT '年化收益率',
  `added_yield` decimal(4,4) DEFAULT NULL COMMENT '年化加息',
  `memo` varchar(1024) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='产品登记表';

/*Table structure for table `ta_sequence` */

DROP TABLE IF EXISTS `ta_sequence`;

CREATE TABLE `ta_sequence` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `sequence_name` varchar(32) NOT NULL COMMENT '序列号名称',
  `current_val` int(11) NOT NULL DEFAULT '0' COMMENT '当前值',
  `sequence_step` int(11) NOT NULL DEFAULT '1' COMMENT '步长',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_id` (`id`) USING BTREE,
  UNIQUE KEY `idx_sequence_name` (`sequence_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='TA系统序列表';

/*Table structure for table `ta_trade_account_info` */

DROP TABLE IF EXISTS `ta_trade_account_info`;

CREATE TABLE `ta_trade_account_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `ta_account_no` varchar(32) DEFAULT NULL COMMENT 'ta账户',
  `trade_account_no` varchar(32) DEFAULT NULL COMMENT '交易账户',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品代码',
  `total_amount` decimal(16,4) DEFAULT NULL COMMENT '账户总额',
  `frozen_amount` decimal(16,4) DEFAULT NULL COMMENT '冻结金额',
  `cash_amount` decimal(16,4) DEFAULT NULL COMMENT '可兑付金额',
  `status` varchar(32) DEFAULT NULL COMMENT '账户状态 NORMAL:正常,FROZEN:冻结,CLOSED:已销户',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Table structure for table `ta_user_asset_cash` */

DROP TABLE IF EXISTS `ta_user_asset_cash`;

CREATE TABLE `ta_user_asset_cash` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trade_serial_no` varchar(64) NOT NULL COMMENT '交易系统流水号',
  `ta_account_no` varchar(64) DEFAULT NULL COMMENT 'TA账户',
  `trade_account_no` varchar(64) DEFAULT NULL COMMENT '交易账户',
  `thirdparty_account_no` varchar(64) DEFAULT NULL COMMENT '交易系统的交易账号',
  `platform_user_id` varchar(64) DEFAULT NULL COMMENT '平台用户ID',
  `app_sheet_no` int(11) DEFAULT NULL COMMENT '资管系统流水号',
  `sale_channel` varchar(8) DEFAULT NULL COMMENT '销售机构代码 唐小僧11|企业理财12|摇旺21',
  `product_code` varchar(64) DEFAULT NULL COMMENT '产品编号',
  `confirm_amount` decimal(16,4) DEFAULT NULL COMMENT '确认金额',
  `confirm_time` datetime DEFAULT NULL COMMENT '确认时间',
  `principal_balance` decimal(16,4) DEFAULT NULL COMMENT '本金',
  `expected_date` date DEFAULT NULL COMMENT '预计到期日',
  `expected_acomut` char(10) DEFAULT NULL COMMENT '预计兑付金额',
  `expected_income` decimal(16,4) DEFAULT NULL COMMENT '预计收益',
  `cash_amount` decimal(16,4) DEFAULT NULL COMMENT '实际兑付金额',
  `cash_income` decimal(16,4) DEFAULT NULL COMMENT '实际兑付收益',
  `cash_time` datetime DEFAULT NULL COMMENT '实际兑付时间',
  `actual_duration` int(11) DEFAULT NULL COMMENT '实际存续期',
  `status` varchar(11) DEFAULT NULL COMMENT '状态 1待兑付 2兑付成功 0兑付失败',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `cash_type` int(11) DEFAULT NULL COMMENT '类型 1 到期自动兑付 2 提前兑付 3 延迟兑付 4用户提前兑付',
  `file_batch_no` varchar(64) DEFAULT NULL COMMENT '文件批次号',
  `create_by` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='用户资产兑付计划表';
