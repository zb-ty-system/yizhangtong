
-- ----------------------------
-- Table structure for op_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `op_dictionary`;
CREATE TABLE `op_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_by` varchar(64) NOT NULL COMMENT '修改人',
  `type` varchar(32) DEFAULT NULL COMMENT '类型 角色：role  权限：auth',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `code` varchar(32) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT '上级id',
  `display_order` int(11) DEFAULT NULL COMMENT '显示顺序',
  `memo` varchar(256) DEFAULT NULL COMMENT '描述',
  `is_valid` tinyint(4) DEFAULT NULL COMMENT '是否有效 1 有效 2 无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for op_menu
-- ----------------------------
DROP TABLE IF EXISTS `op_menu`;
CREATE TABLE `op_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_by` varchar(64) NOT NULL COMMENT '修改人',
  `name` varchar(128) DEFAULT NULL COMMENT '菜单名称',
  `code` varchar(32) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT '上级菜单ID',
  `display_order` int(11) DEFAULT NULL COMMENT '显示顺序',
  `refer_url` varchar(256) DEFAULT NULL COMMENT '菜单链接',
  `memo` varchar(256) DEFAULT NULL COMMENT '备注',
  `is_valid` tinyint(4) DEFAULT NULL COMMENT '是否有效 1 有效 2 无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for op_menu_power
-- ----------------------------
DROP TABLE IF EXISTS `op_menu_power`;
CREATE TABLE `op_menu_power` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_by` varchar(64) NOT NULL COMMENT '修改人',
  `menu_id` int(11) NOT NULL COMMENT '菜单表ID',
  `power_id` int(11) NOT NULL COMMENT '权限ID',
  `is_valid` char(10) NOT NULL COMMENT '是否有效 1 有效 2 无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for op_operation_history
-- ----------------------------
DROP TABLE IF EXISTS `op_operation_history`;
CREATE TABLE `op_operation_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_by` varchar(64) NOT NULL COMMENT '修改人',
  `user_id` varchar(128) NOT NULL COMMENT '用户名',
  `operation_type` varchar(128) NOT NULL COMMENT '操作类型',
  `content` varchar(64) NOT NULL COMMENT '操作描述',
  `refer_id` varchar(10) DEFAULT NULL COMMENT '业务ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for op_role_power
-- ----------------------------
DROP TABLE IF EXISTS `op_role_power`;
CREATE TABLE `op_role_power` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_by` varchar(64) NOT NULL COMMENT '修改人',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `power_id` int(11) NOT NULL COMMENT '权限ID',
  `is_valid` tinyint(4) NOT NULL COMMENT '是否有效 1 有效 2无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for op_user
-- ----------------------------
DROP TABLE IF EXISTS `op_user`;
CREATE TABLE `op_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_by` varchar(64) NOT NULL COMMENT '修改人',
  `name` varchar(128) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '登录密码',
  `email` varchar(64) NOT NULL COMMENT '公司邮箱',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for op_user_role
-- ----------------------------
DROP TABLE IF EXISTS `op_user_role`;
CREATE TABLE `op_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_by` varchar(64) NOT NULL COMMENT '修改人',
  `user_id` int(11) NOT NULL COMMENT '用户表的ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `is_valid` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
