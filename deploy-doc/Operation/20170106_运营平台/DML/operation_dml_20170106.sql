INSERT INTO `op_dictionary` VALUES ('1', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', 'role', '管理员', '', '0', '1', '管理员角色', '1');
INSERT INTO `op_dictionary` VALUES ('2', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', 'auth', '管理员', '', '0', '1', '管理员权限', '1');


INSERT INTO `op_menu` VALUES ('1', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '客户管理', '', '0', '1', '', '', '1');
INSERT INTO `op_menu` VALUES ('2', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '资金运营', '', '0', '2', '', '', '1');
INSERT INTO `op_menu` VALUES ('3', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '资产管理', '', '0', '3', '', '', '1');
INSERT INTO `op_menu` VALUES ('4', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '产品管理', '', '0', '4', '', '', '1');
INSERT INTO `op_menu` VALUES ('5', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '投后管理', '', '0', '5', '', '', '1');
INSERT INTO `op_menu` VALUES ('6', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '业务单查询', '', '2', '6', 'order', null, '1');
INSERT INTO `op_menu` VALUES ('7', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '批次对账', '', '2', '7', 'reconcil', null, '1');
INSERT INTO `op_menu` VALUES ('8', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '补单', '', '2', '8', 'supplement', null, '1');
INSERT INTO `op_menu` VALUES ('9', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '产品注册', '', '4', '11', 'product/add', null, '1');
INSERT INTO `op_menu` VALUES ('10', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '产品线管理', '', '4', '9', 'product/line', null, '1');
INSERT INTO `op_menu` VALUES ('11', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '产品管理', '', '4', '10', 'product', null, '1');
INSERT INTO `op_menu` VALUES ('12', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '定期到期兑付', '', '5', '12', 'investafter/termexpcash', null, '1');
INSERT INTO `op_menu` VALUES ('13', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '客户信息查询', '', '1', '13', 'customer', null, '1');
INSERT INTO `op_menu` VALUES ('14', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '企业证照认证', '', '1', '14', 'license', null, '1');
INSERT INTO `op_menu` VALUES ('15', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '法人资料认证', '', '1', '15', 'legal', null, '1');
INSERT INTO `op_menu` VALUES ('16', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '资产仓库', '', '3', '16', 'asset/warehouse', null, '1');
INSERT INTO `op_menu` VALUES ('17', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '资产入库', '', '3', '17', 'asset/put', null, '1');
INSERT INTO `op_menu` VALUES ('18', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '资产池', '', '3', '18', 'asset/pool', null, '1');
INSERT INTO `op_menu` VALUES ('19', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '交易确认', '', '3', '19', 'asset/confirmdeal', null, '1');


INSERT INTO `op_menu_power` VALUES ('1', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '1', '2', '1');
INSERT INTO `op_menu_power` VALUES ('2', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '2', '2', '1');
INSERT INTO `op_menu_power` VALUES ('3', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '3', '2', '1');
INSERT INTO `op_menu_power` VALUES ('4', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '4', '2', '1');
INSERT INTO `op_menu_power` VALUES ('5', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '5', '2', '1');
INSERT INTO `op_menu_power` VALUES ('6', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '6', '2', '1');
INSERT INTO `op_menu_power` VALUES ('7', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '7', '2', '1');
INSERT INTO `op_menu_power` VALUES ('8', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '8', '2', '1');
INSERT INTO `op_menu_power` VALUES ('9', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '9', '2', '1');
INSERT INTO `op_menu_power` VALUES ('10', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '10', '2', '1');
INSERT INTO `op_menu_power` VALUES ('11', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '11', '2', '1');
INSERT INTO `op_menu_power` VALUES ('12', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '12', '2', '1');
INSERT INTO `op_menu_power` VALUES ('13', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '13', '2', '1');
INSERT INTO `op_menu_power` VALUES ('14', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '14', '2', '1');
INSERT INTO `op_menu_power` VALUES ('15', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '15', '2', '1');
INSERT INTO `op_menu_power` VALUES ('16', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '16', '2', '1');
INSERT INTO `op_menu_power` VALUES ('17', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '17', '2', '1');
INSERT INTO `op_menu_power` VALUES ('18', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '18', '2', '1');
INSERT INTO `op_menu_power` VALUES ('19', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '19', '2', '1');

INSERT INTO `op_role_power` VALUES ('1', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '1', '2', '1');

INSERT INTO `op_user` VALUES ('1', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', 'admin', 'vCk%2FvvN3vKEyC7DDm%2BnQEQ%3D%3D', 'admin@zillionfortune.com', '13112345678');

INSERT INTO `op_user_role` VALUES ('1', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system', '1', '1', '1');
