--1、 trd_order 字段名称更改


alter table trd_order change product_id product_code varchar(64);

--2、 trd_bank_serial 字段长度修改


alter table trd_bank_serial modify column file_no varchar(256);