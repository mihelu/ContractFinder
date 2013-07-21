insert into cf_role(rol_id,rol_name) values(nextval('rol_id_seq'), 'USER')
insert into cf_role(rol_id,rol_name) values(nextval('rol_id_seq'), 'ADMIN')

INSERT INTO cf_user(use_id, use_firstname, use_lastname) VALUES (nextval('use_id_seq'), 'Michał', 'Sala')
INSERT INTO cf_company(com_id, com_description, com_name) VALUES (nextval('com_id_seq'), 'Test company description', 'Test company')

INSERT INTO cf_account(acc_id, acc_create_date, acc_login, acc_password, acc_personal,acc_com_id, acc_use_id) VALUES (nextval('acc_id_seq'), now(), 'test', 'test', TRUE, null , 1)
INSERT INTO cf_account(acc_id, acc_create_date, acc_login, acc_password, acc_personal,acc_com_id, acc_use_id) VALUES (nextval('acc_id_seq'), now(), 'test2', 'test2', FALSE, 1 , null)

INSERT INTO cf_account_roles(aro_acc_id, aro_rol_id) VALUES (1, 1)
INSERT INTO cf_account_roles(aro_acc_id, aro_rol_id) VALUES (2, 1)

INSERT INTO cf_contract(con_id, con_description, con_name, con_publish_end, con_publish_start,con_acc_id) VALUES (nextval('con_id_seq'), 'Testowe zlecenie', 'Opis testowego zlecenia', now(), now(), 1)

INSERT INTO cf_offer(off_id, off_comment, off_days, off_offer_price, off_acc_id, off_con_id) VALUES (nextval('off_id_seq'), 'Nic ciekawego', 7, 1200, 2, 1)