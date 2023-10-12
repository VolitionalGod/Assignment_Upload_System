--liquibase formatted sql

--changeset tubel.john:assignment_cs_1
CREATE TABLE IF NOT EXISTS `assignment` (
  `ASSIGNMENT_ID` int  NOT NULL AUTO_INCREMENT,
  `STUDENT_ACADMIC_DETAILS_ID` int  NOT NULL,
  `SUBJECT_ID` int  NOT NULL,
  `FILEPATH` varchar(200) NOT NULL,
  PRIMARY KEY (`ASSIGNMENT_ID`),
  FOREIGN KEY (`SUBJECT_ID`) REFERENCES `subjects` (`SUBJECT_ID`),
  FOREIGN KEY (`STUDENT_ACADMIC_DETAILS_ID`) REFERENCES `student_acadmic_details` (`STUDENT_ACADMIC_DETAILS_ID`)
);
