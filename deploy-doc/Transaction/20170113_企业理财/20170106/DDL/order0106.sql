--1�� trd_order �ֶ����Ƹ���


alter table trd_order change product_id product_code varchar(64);

--2�� trd_bank_serial �ֶγ����޸�


alter table trd_bank_serial modify column file_no varchar(256);