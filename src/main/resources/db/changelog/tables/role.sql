--liquibase formatted sql

--changeset tubel.john:role_cs_1
CREATE TABLE IF NOT EXISTS `role` (
  `ROLE_ID` varchar(100) NOT NULL,
  `ROLE_DESC` varchar(100) NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
);

--changeset tubel.john:role_cs_2
insert into `role` (`ROLE_ID`,`ROLE_DESC`)
values ('ROLE_ADMIN','Role for the application admin'),
('ROLE_MODERATOR','Role for the application moderator'),
('ROLE_STUDENT','Role for the student to upload assignment');


