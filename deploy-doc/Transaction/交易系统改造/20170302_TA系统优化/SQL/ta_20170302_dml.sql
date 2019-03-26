ALTER TABLE ta_batch MODIFY COLUMN status VARCHAR (32)
COMMENT '批次处理状态：INIT:初始化,PROCESSING:处理中,COMPLETE:已处理,PROCESS_FAIL:处理异常,CALCULATE_INCOME:计算收益中,UPLOAD_COMPLETE:文件上传完成,CALCULATE_ACCOUNT:修改账户金额中;';

ALTER TABLE ta_batch  MODIFY COLUMN operation_batch_result varchar(32) COMMENT '运营操作结果：INIT:未处理,CONFIRM_SUCCESS:确认成功,CONFIRM_FAIL:确认失败;';

ALTER TABLE ta_invest_record  MODIFY COLUMN status varchar(32) COMMENT '投资状态：INIT:初始化,CONFIRM_SUCCESS:投资成功,CONFIRM_FAIL:投资失败;';

ALTER TABLE ta_invest_record  MODIFY COLUMN asset_match_status varchar(32) COMMENT '资产匹配状态：UNMATCHED:未匹配,CONFIRMED:匹配成功,FAILED:匹配失败;';

ALTER TABLE ta_user_asset_cash  MODIFY COLUMN cash_type varchar(32) COMMENT '兑付类型 AUTO_CASH:到期自动兑付 ADVANCE_CASH:提前兑付 DELAY_CASH:延迟兑付 USER_CASH:用户提前兑付';

ALTER TABLE ta_user_asset_cash  MODIFY COLUMN status varchar(32) COMMENT '兑付状态：INIT:初始化,CASH_SUCCESS:兑付成功,CASH_FAIL:兑付失败;';

alter table ta_batch alter column version set default 0;

alter table ta_invest_record alter column version set default 0;

alter table ta_user_asset_cash alter column version set default 0;

alter table ta_user_asset_cash modify column app_sheet_no varchar(64);