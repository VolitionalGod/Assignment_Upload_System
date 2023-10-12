--liquibase formatted sql

--changeset tubel.john:role_business_function_cs_1
CREATE TABLE IF NOT EXISTS `role_business_function` (
`ROLE_ID` varchar(100) NOT NULL,
`BUSINESS_FUNCTION_ID` varchar(100) NOT NULL,
FOREIGN KEY (ROLE_ID) REFERENCES role(ROLE_ID),
FOREIGN KEY (BUSINESS_FUNCTION_ID) REFERENCES business_function(BUSINESS_FUNCTION_ID));