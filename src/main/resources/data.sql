insert into community values (1,'BENCH');
ALTER SEQUENCE community_community_id_seq RESTART WITH 2;

insert into users (email, first_name, last_name, password, phone_number, sex, user_type) values ('admin@softvision.ro', 'admin', 'admin', 'masterPassword', '1234567890', 'M', 'Admin');

insert into users (email, first_name, last_name, password, phone_number, sex, user_type) values ('defaultmanger@softvision.ro', 'default', 'manager', 'parolaStiutaDeToti', '1234567890', 'M', 'Manager');

insert into role values (1,'ADMIN');
insert into role values (2,'MANAGER');
insert into role values (3,'EMPLOYEE');
