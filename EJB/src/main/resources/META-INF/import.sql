insert into cf_role(rol_id,rol_name) values(nextval('rol_id_seq'), 'USER')
insert into cf_role(rol_id,rol_name) values(nextval('rol_id_seq'), 'ADMIN')

insert into cf_category(cat_id, cat_name) values (nextval('cat_id_seq'), 'JAVA')
insert into cf_category(cat_id, cat_name) values (nextval('cat_id_seq'), 'SQL')
insert into cf_category(cat_id, cat_name) values (nextval('cat_id_seq'), 'Elektryka')
insert into cf_category(cat_id, cat_name) values (nextval('cat_id_seq'), 'Uslugi')
insert into cf_category(cat_id, cat_name) values (nextval('cat_id_seq'), 'Finanse')
insert into cf_category(cat_id, cat_name) values (nextval('cat_id_seq'), 'Budownictwo')

INSERT INTO cf_user(use_id, use_firstname, use_lastname) VALUES (nextval('use_id_seq'), 'Michal', 'Sala')
INSERT INTO cf_company(com_id, com_description, com_name) VALUES (nextval('com_id_seq'), 'Test company description', 'Test company')

INSERT INTO cf_account(acc_id, acc_create_date, acc_login, acc_password, acc_personal,acc_com_id, acc_use_id) VALUES (nextval('acc_id_seq'), now(), 'test', 'test', TRUE, null , 1)
INSERT INTO cf_account(acc_id, acc_create_date, acc_login, acc_password, acc_personal,acc_com_id, acc_use_id) VALUES (nextval('acc_id_seq'), now(), 'test2', 'test2', FALSE, 1 , null)

INSERT INTO cf_account_roles(aro_acc_id, aro_rol_id) VALUES (1, 1)
INSERT INTO cf_account_roles(aro_acc_id, aro_rol_id) VALUES (2, 1)

INSERT INTO cf_contract(con_id, con_name, con_description, con_max_price, con_publish_end, con_publish_start,con_acc_id) VALUES (nextval('con_id_seq'), 'Testowe zlecenie', 'Opis testowego zlecenia', 1000, now() + INTERVAL '2 hours' , now(), 1)
INSERT INTO cf_contract(con_id, con_name, con_description, con_max_price, con_publish_end, con_publish_start,con_acc_id) VALUES (nextval('con_id_seq'), 'Testowe zlecenie2', 'Opis testowego zlecenia2', 20000, now(), now(), 2)

INSERT INTO cf_contract_category(cc_con_id,cc_cat_id) VALUES (1, 1)
INSERT INTO cf_contract_category(cc_con_id,cc_cat_id) VALUES (1, 2)
INSERT INTO cf_contract_category(cc_con_id,cc_cat_id) VALUES (2, 4)


INSERT INTO cf_offer(off_id, off_comment, off_years, off_months, off_days, off_hours, off_offer_price, off_acc_id, off_con_id) VALUES (nextval('off_id_seq'), 'Nic ciekawego', 1, 6, 0, 0, 900, 2, 1)
INSERT INTO cf_offer(off_id, off_comment, off_years, off_months, off_days, off_hours, off_offer_price, off_acc_id, off_con_id) VALUES (nextval('off_id_seq'), 'Nic ciekawego2', 0, 11, 0, 0, 19000, 1, 2)