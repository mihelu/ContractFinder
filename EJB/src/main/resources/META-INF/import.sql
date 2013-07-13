insert into cf_role(rol_id,rol_name) values(nextval('rol_id_seq'), 'USER')
insert into cf_role(rol_id,rol_name) values(nextval('rol_id_seq'), 'ADMIN')

INSERT INTO cf_user(use_id, use_firstname, use_lastname) VALUES (nextval('use_id_seq'), 'Michał', 'Sala');

INSERT INTO cf_account(acc_id, acc_create_date, acc_login, acc_password, acc_personal,acc_com_id, acc_use_id) VALUES (nextval('acc_id_seq'), now(), 'test', 'test', TRUE, null , 1);

INSERT INTO cf_account_roles(aro_acc_id, aro_rol_id) VALUES (1, 1);
