-- db.changelog-1.3.0.13-update-student-question-context.sql 07/10/2017
-- Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
-- Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.

ALTER TABLE MAPSKILLS.STUDENT_QUESTION_CONTEXT ADD ID_STUDENT BIGINT;

UPDATE MAPSKILLS.STUDENT_QUESTION_CONTEXT SQC SET ID_STUDENT = (SELECT S.ID FROM MAPSKILLS.STUDENT S WHERE S.ID_USER = SQC.ID_USER);

ALTER TABLE MAPSKILLS.STUDENT_QUESTION_CONTEXT DROP COLUMN ID_USER;
-- rollback ALTER TABLE MAPSKILLS.STUDENT_QUESTION_CONTEXT ADD ID_USER BIGINT;
-- rollback ALTER TABLE MAPSKILLS.STUDENT_QUESTION_CONTEXT DROP COLUMN ID_STUDENT;