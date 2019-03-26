

CREATE TABLE `object_maxsn` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '表名',
  `column_name` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '列明',
  `max_serial_no` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '最大流水号',
  `no_format` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '流水号格式类型',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_by` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_table_column_name` (`table_name`,`column_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='记录最大流水号的表';



CREATE TABLE `product` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `third_party_id` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '第三方产品ID',
  `product_line_id` int(11) DEFAULT NULL COMMENT '产品所属产品线ID(关联product_line.id)',
  `asset_pool_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品关联的资产池ID',
  `code` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '产品编号',
  `full_name` varchar(128) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '产品名称全称',
  `short_name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品名称缩写',
  `total_amount` decimal(16,4) DEFAULT NULL COMMENT '产品总规模',
  `invested_amount` decimal(16,4) DEFAULT NULL COMMENT '已预约规模(付款前)',
  `unconfirmed_purchase_amount` decimal(16,4) DEFAULT NULL COMMENT '买入待确认规模(已付款的总规模)',
  `actual_invest_amount` decimal(16,4) DEFAULT NULL COMMENT '当前规模(产品成立时所有购买成功的订单总额)',
  `risk_level` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '风险等级(1-5)',
  `investor_requirement` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '投资人风险承受能力条件',
  `status` int(3) DEFAULT NULL COMMENT '产品的状态(100:未排期，101:已排期，102:上架，103:下架，105:待成立，106：募集失败，107：存续期，108：流标，109：到期，110：流标终止，111：到期完成)',
  `pattern_code` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品的类型代码(01:现金管理, 02:定期类, 03:净值型)',
  `unit` tinyint(3) DEFAULT NULL COMMENT '计量单位(1:份额, 2:人民币)',
  `min_invest_amount` decimal(16,4) DEFAULT NULL COMMENT '起投金额',
  `max_invest_amount` decimal(16,4) DEFAULT NULL COMMENT '投资限额',
  `increase_invest_amount` decimal(16,4) DEFAULT NULL COMMENT '递增金额(步长)',
  `basic_interests_period` int(11) DEFAULT NULL COMMENT '年基础计息周期(360, 365, 366三种枚举值)',
  `on_sale_time` datetime DEFAULT NULL COMMENT '上架时间',
  `is_open_hmt` tinyint(3) DEFAULT NULL COMMENT '是否对港澳台客户开放',
  `calendar_mode` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '日历模式 1：自然日 2：交易日',
  `introduction` longtext COLLATE utf8_unicode_ci COMMENT '产品介绍',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `modify_by` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='产品信息表';



CREATE TABLE `product_line` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `third_party_id` varchar(64) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '外部产品线ID',
  `line_name` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品线名称',
  `pattern_code` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品线的形态代码(01:现金管理, 02:定期类, 03:净值型)',
  `line_no` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '产品线的编号',
  `status` tinyint(3) DEFAULT NULL COMMENT '产品线的状态(1:新注册, 2:已审核, 3:已注销)',
  `risk_level` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '风险等级',
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品线的描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `modify_by` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='产品线信息表';



CREATE TABLE `product_periodic_regular` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL COMMENT '产品ID(关联product.id)',
  `sale_start_date` datetime DEFAULT NULL COMMENT '募集起始日期',
  `sale_end_date` datetime DEFAULT NULL COMMENT '募集截止日期',
  `establish_date` datetime DEFAULT NULL COMMENT '成立日期',
  `value_date` datetime DEFAULT NULL COMMENT '起息日期',
  `expire_date` datetime DEFAULT NULL COMMENT '到期日期',
  `receive_payment_date` datetime DEFAULT NULL COMMENT '回款日期',
  `lock_period_unit` tinyint(3) DEFAULT NULL COMMENT '锁定期单位(1:天 2：周 3：月 4:年)',
  `lock_period` int(11) DEFAULT NULL COMMENT '锁定期',
  `min_yield_rate` decimal(16,4) DEFAULT NULL COMMENT '最低预期收益率',
  `max_yield_rate` decimal(16,4) DEFAULT NULL COMMENT '最高预期收益率',
  `income_type` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收益方式',
  `added_yield_rate` decimal(16,4) DEFAULT NULL COMMENT '加息',
  `establish_condition` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '成立条件',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `modify_by` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='定期产品扩展信息表';
