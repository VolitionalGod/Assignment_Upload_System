--liquibase formatted sql

--changeset tubel.john:student_acadmic_details_cs_1
CREATE TABLE IF NOT EXISTS `student_acadmic_details` (
  `STUDENT_ACADMIC_DETAILS_ID`  int  NOT NULL AUTO_INCREMENT,
  `USER_ID` int  NOT NULL,
  `ENROLLMENT_ID` varchar(100),
  `YEAR_ID` int  NOT NULL,
  `COURSE_ID` int NOT NULL,
  PRIMARY KEY (`STUDENT_ACADMIC_DETAILS_ID`)
);