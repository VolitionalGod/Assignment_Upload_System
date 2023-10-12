--liquibase formatted sql

--changeset tubel.john:user_role_cs_1
CREATE TABLE IF NOT EXISTS `user_role` (
`ROLE_ID` varchar(100) NOT NULL,
`USER_ID` int  NOT NULL,
FOREIGN KEY (ROLE_ID) REFERENCES role(ROLE_ID),
FOREIGN KEY (USER_ID) REFERENCES user_login_details(USER_ID));

--changeset tubel.john:user_role_cs_2
insert into `user_role` (`ROLE_ID`,`USER_ID`)
values ('ROLE_ADMIN','1');
