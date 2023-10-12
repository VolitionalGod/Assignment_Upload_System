--liquibase formatted sql

--changeset tubel.john:email_validation_status_cs-1
CREATE TABLE IF NOT EXISTS `email_validation_status` (
  `EMAIL_VALIDATION_STATUS_ID` int NOT NULL AUTO_INCREMENT,
  `EMAIL_VALIDATION_STATUS` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(100),
  PRIMARY KEY (`EMAIL_VALIDATION_STATUS_ID`)
);

--changeset tubel.john:email_validation_status_cs-2
INSERT INTO email_validation_status values(1,'Pending','Email validation is pending');