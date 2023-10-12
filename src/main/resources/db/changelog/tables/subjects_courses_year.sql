--liquibase formatted sql

--changeset tubel.john:programmes_courses_year_cs_1
CREATE TABLE IF NOT EXISTS `programmes_courses_year` (
	`YEAR_ID` int  NOT NULL,
	`COURSE_ID` int NOT NULL,
	`SUBJECT_ID` int NOT NULL,
	FOREIGN KEY (`YEAR_ID`) REFERENCES `acadamic_year`(`YEAR_ID`),
	FOREIGN KEY (`COURSE_ID`) REFERENCES `courses`(`COURSE_ID`),
	FOREIGN KEY (`SUBJECT_ID`) REFERENCES `subjects`(`SUBJECT_ID`)
);