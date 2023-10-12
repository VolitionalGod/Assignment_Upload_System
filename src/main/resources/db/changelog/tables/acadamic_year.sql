--liquibase formatted sql

--changeset tubel.john:acadamic_year-CS-1
CREATE TABLE IF NOT EXISTS `acadamic_year` (
  `YEAR_ID` int  NOT NULL AUTO_INCREMENT,
  `YEAR_NAME` varchar(50) NOT NULL,
  `YEAR_START_DATE` date NOT NULL,
  `YEAR_END_DATE` date NOT NULL,
  `ACTIVE` TINYINT NOT NULL DEFAULT 1,
  `LAST_MODIFIED_DATE` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`YEAR_ID`)
);


