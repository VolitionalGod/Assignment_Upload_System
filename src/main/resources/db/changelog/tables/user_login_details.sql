--liquibase formatted sql

--changeset tubel.john:user_login_details_cs_1
CREATE TABLE IF NOT EXISTS `user_login_details`(
  `USER_ID` int  NOT NULL AUTO_INCREMENT,
  `LOGIN_NAME` varchar(100) NOT NULL,
  `PASSCODE` varchar(150) NOT NULL,
  `FIRST_NAME` varchar(100) NOT NULL,
  `LAST_NAME` varchar(100) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `ENABLED` TINYINT NOT NULL DEFAULT 1,
  `NON_LOCKED` TINYINT NOT NULL DEFAULT 1,
  `NON_EXPIRED` TINYINT NOT NULL DEFAULT 1,
  `EXPIRY_DATE` timestamp NOT NULL,
  `LAST_LOGIN_DATE` timestamp,
  PRIMARY KEY (`USER_ID`)
);

--changeset tubel.john:user_login_details_cs_2
INSERT into user_login_details(`LOGIN_NAME`,`PASSCODE`,`FIRST_NAME`,`LAST_NAME`,`EMAIL`,`ENABLED`,`NON_LOCKED`,`NON_EXPIRED`,`EXPIRY_DATE`,`LAST_LOGIN_DATE`)
values ('Tubel','$2a$10$00AYEg6qVGibC35Qb9IoWugdew97nuQsO/6kG04WkIFSLVAjl2HLq','Tubel','John','tubel.m.john@gmail.com',1,1,1,now(),now());