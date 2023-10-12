--liquibase formatted sql

--changeset tubel.john:business_function_cs_1
CREATE TABLE IF NOT EXISTS `business_function` (
  `BUSINESS_FUNCTION_ID` varchar(30) NOT NULL,
  `BUSINESS_FUNCTION_DESC` varchar(100) NOT NULL,
  PRIMARY KEY (`BUSINESS_FUNCTION_ID`)
);
