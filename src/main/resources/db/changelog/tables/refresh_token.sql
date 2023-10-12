--liquibase formatted sql

--changeset tubel.john:refresh_token_cs_1
CREATE TABLE IF NOT EXISTS `refresh_token` (
  `ID` int NOT NULL,
  `USER_ID` int  NOT NULL UNIQUE,
  `TOKEN` varchar(200) NOT null,
  `EXPIRY_DATE` timestamp NOT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`USER_ID`) REFERENCES `user_login_details` (`USER_ID`));