# Host: localhost  (Version 5.6.33)
# Date: 2016-11-09 15:24:03
# Generator: MySQL-Front 5.4  (Build 1.36)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "business_parameter_config"
#

DROP TABLE IF EXISTS `business_parameter_config`;
CREATE TABLE `business_parameter_config` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT 'ID',
  `parameter_name` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '参数名称',
  `parameter_value` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '参数值',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'remark',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  `modify_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "business_parameter_config"
#

INSERT INTO `business_parameter_config` VALUES (1,'credit_card_recharge_limit_switc','1','信用卡充值限额开关','2016-07-29 11:44:00',NULL,NULL),(2,'web_recharge_frozen_amount_limit','900','web端充值冻结金额下限','2016-08-02 09:06:55',NULL,NULL),(3,'client_recharge_frozen_amount_li','2','手机端充值冻结金额下限','2016-08-02 09:40:00',NULL,NULL),(4,'web_recharge_frozen_days','5','web端充值冻结天数','2016-08-10 17:27:00',NULL,NULL),(5,'client_recharge_frozen_days','20','手机端充值冻结天数','2016-08-10 17:28:00',NULL,NULL),(6,'recharge_overtime_days','1','充值流水超时天数','2016-08-18 00:00:00',NULL,NULL);

#
# Structure for table "customer"
#

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '主键ID',
  `customer_id` varchar(22) NOT NULL DEFAULT '' COMMENT '客户ID',
  `customer_no` varchar(22) NOT NULL DEFAULT '' COMMENT '客户号',
  `customer_name` varchar(20) NOT NULL DEFAULT '' COMMENT '客户名称',
  `certificate_type` tinyint(1) DEFAULT NULL COMMENT '证件类型',
  `certificate_no` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_customer_id` (`customer_id`),
  UNIQUE KEY `inx_customer_no` (`customer_no`),
  KEY `idx_certificate_type` (`certificate_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户信息表';

#
# Data for table "customer"
#


#
# Structure for table "enterprise"
#

DROP TABLE IF EXISTS `enterprise`;
CREATE TABLE `enterprise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` varchar(22) NOT NULL DEFAULT '' COMMENT '客户ID',
  `business_registration_no` varchar(32) NOT NULL DEFAULT '0' COMMENT '工商注册号',
  `legal_person_name` varchar(128) DEFAULT NULL COMMENT '法人姓名',
  `legal_person_certificate_type` tinyint(1) DEFAULT NULL COMMENT '法人证件类型',
  `legal_person_certificate_no` varchar(32) DEFAULT NULL COMMENT '法人证件号码',
  `enterprise_name` varchar(256) DEFAULT NULL COMMENT '企业名称',
  `enterprise_register_addr` varchar(256) DEFAULT NULL COMMENT '企业注册地址',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_customer_id` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业信息扩展表';

#
# Data for table "enterprise"
#


#
# Structure for table "individual"
#

DROP TABLE IF EXISTS `individual`;
CREATE TABLE `individual` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` varchar(22) NOT NULL DEFAULT '' COMMENT '客户Id',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `gender` varchar(20) DEFAULT NULL COMMENT '性别',
  `work` varchar(32) DEFAULT NULL COMMENT '工作',
  `work_years` int(2) DEFAULT NULL COMMENT '工作年限',
  `common_address` varchar(256) DEFAULT NULL COMMENT '常用地址',
  `marriage_status` tinyint(1) DEFAULT NULL COMMENT '婚姻状态',
  `annual_salary` decimal(10,2) DEFAULT NULL COMMENT '年收入',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_customer_id` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人信息扩展表';

#
# Data for table "individual"
#


#
# Structure for table "sequence"
#

DROP TABLE IF EXISTS `sequence`;
CREATE TABLE `sequence` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT 'sequence名称',
  PRIMARY KEY (`id`),
  KEY `Index_2` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=10000545 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "sequence"
#


#
# Structure for table "system_parameter_config"
#

DROP TABLE IF EXISTS `system_parameter_config`;
CREATE TABLE `system_parameter_config` (
  `id` int(11) NOT NULL,
  `parameter_name` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `parameter_value` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `remark` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "system_parameter_config"
#

INSERT INTO `system_parameter_config` VALUES (1,'recharge_repair_time_range_end','30','充值补单时间范围，从当前时间开始30分钟以内','2016-08-18 00:00:00',0,NULL,NULL),(2,'recharge_repair_time_range_start','1','充值补单时间范围，从当前时间前1分钟开始','2016-08-18 00:00:00',0,NULL,NULL);

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '主键ID',
  `member_id` varchar(22) NOT NULL DEFAULT '' COMMENT '用户ID',
  `app_id` tinyint(1) NOT NULL DEFAULT '0' COMMENT '应用ID',
  `customer_id` varchar(20) NOT NULL DEFAULT '' COMMENT '客户ID',
  `mobile` varchar(18) DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) DEFAULT '' COMMENT '登录密码',
  `gesture_pwd` varchar(32) DEFAULT NULL COMMENT '手势密码',
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `status` tinyint(1) DEFAULT NULL COMMENT '用户状态',
  `head_img_url` varchar(128) DEFAULT NULL COMMENT '头像地址',
  `register_source` tinyint(1) DEFAULT NULL COMMENT '注册来源',
  `real_name_time` datetime DEFAULT NULL COMMENT '实名时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后成功登录时间',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '记录创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '记录最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ind_member_id` (`member_id`),
  KEY `idx_customer_id` (`customer_id`),
  KEY `idx_mobile` (`mobile`),
  KEY `idx_register_source` (`register_source`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Data for table "user"
#


#
# Structure for table "user_grade"
#

DROP TABLE IF EXISTS `user_grade`;
CREATE TABLE `user_grade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` varchar(22) NOT NULL DEFAULT '' COMMENT '用户ID',
  `grade_score` int(11) DEFAULT '0' COMMENT '等级分数',
  `grade_ category_name` varchar(32) DEFAULT NULL COMMENT '等级类别名称',
  `grade_name` varchar(32) DEFAULT NULL COMMENT '等级名称',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员等级表';

#
# Data for table "user_grade"
#


#
# Function "currval"
#

DROP FUNCTION IF EXISTS `currval`;
CREATE FUNCTION `currval`(seq_name VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN 
DECLARE VALUE INTEGER;
SET VALUE = 0;
SELECT 
    id
INTO VALUE FROM
    sequence
WHERE
    NAME = seq_name; RETURN VALUE;
END;

#
# Function "nextval"
#

DROP FUNCTION IF EXISTS `nextval`;
CREATE FUNCTION `nextval`(seq_name VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN DELETE FROM sequence WHERE NAME=seq_name; INSERT INTO sequence(NAME) VALUES (seq_name); RETURN currval(seq_name);
END;
