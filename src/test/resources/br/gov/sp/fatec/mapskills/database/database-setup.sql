CREATE SCHEMA MAPSKILLS;

CREATE TABLE IF NOT EXISTS MAPSKILLS.LOGIN (ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY, USERNAME VARCHAR(100) UNIQUE, PASSWORD VARCHAR(255));

CREATE TABLE IF NOT EXISTS MAPSKILLS.INSTITUTION (ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY, CODE VARCHAR(5) UNIQUE, CNPJ BIGINT UNIQUE, COMPANY VARCHAR(250) UNIQUE, CITY VARCHAR(250), LEVEL INTEGER, ID_GAME_THEME INTEGER);

CREATE TABLE IF NOT EXISTS MAPSKILLS.MENTOR (ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY, NAME VARCHAR(250), ID_LOGIN INTEGER, ID_INSTITUTION INTEGER, FOREIGN KEY (ID_LOGIN) REFERENCES MAPSKILLS.LOGIN(ID), FOREIGN KEY (ID_INSTITUTION) REFERENCES MAPSKILLS.INSTITUTION(ID));

CREATE TABLE IF NOT EXISTS MAPSKILLS.COURSE (ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY, CODE VARCHAR(5) UNIQUE, NAME VARCHAR(250), "_PERIOD" INTEGER, ID_INSTITUTION INTEGER, FOREIGN KEY (ID_INSTITUTION) REFERENCES MAPSKILLS.INSTITUTION(ID));

CREATE TABLE IF NOT EXISTS MAPSKILLS.STUDENT (ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY, NAME VARCHAR(250), RA VARCHAR(15) UNIQUE, PHONE VARCHAR(15), COMPLETED BOOLEAN, INSTITUTION_CODE VARCHAR(5), COURSE_CODE VARCHAR(5), START_YEAR INTEGER, START_SEMESTER INTEGER, CODE VARCHAR(6), ID_LOGIN INTEGER, ID_COURSE INTEGER, FOREIGN KEY (ID_LOGIN) REFERENCES MAPSKILLS.LOGIN(ID), FOREIGN KEY (ID_COURSE) REFERENCES MAPSKILLS.COURSE(ID));

CREATE TABLE IF NOT EXISTS MAPSKILLS.ADMINISTRATOR (ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY, NAME VARCHAR(250), ID_LOGIN INTEGER, FOREIGN KEY (ID_LOGIN) REFERENCES MAPSKILLS.LOGIN(ID));

CREATE TABLE IF NOT EXISTS MAPSKILLS.SKILL (ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY, NAME VARCHAR(250), DESCRIPTION VARCHAR(250));

CREATE TABLE IF NOT EXISTS MAPSKILLS.GAME_THEME (ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY, NAME VARCHAR(250), ACTIVE BOOLEAN);

CREATE TABLE IF NOT EXISTS MAPSKILLS.QUESTION (ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY, ID_SKILL INTEGER NOT NULL, FOREIGN KEY (ID_SKILL) REFERENCES MAPSKILLS.SKILL(ID));

CREATE TABLE IF NOT EXISTS MAPSKILLS.ALTERNATIVE (ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY, DESCRIPTION VARCHAR(250), SKILL_VALUE INTEGER, ID_QUESTION INTEGER, FOREIGN KEY (ID_QUESTION) REFERENCES MAPSKILLS.QUESTION(ID));

CREATE TABLE IF NOT EXISTS MAPSKILLS.SCENE (ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY, "_INDEX" INTEGER, TEXT VARCHAR(250), IMAGE_NAME VARCHAR(250), ID_GAME_THEME INTEGER, ID_QUESTION INTEGER, FOREIGN KEY (ID_GAME_THEME) REFERENCES MAPSKILLS.GAME_THEME(ID), FOREIGN KEY (ID_QUESTION) REFERENCES MAPSKILLS.QUESTION(ID));

CREATE TABLE IF NOT EXISTS MAPSKILLS.STUDENT_QUESTION_CONTEXT (ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY, "DATE" DATE, SKILL_VALUE INTEGER, ID_STUDENT INTEGER, ID_SKILL INTEGER, ID_SCENE INTEGER, SCENE_INDEX INTEGER, FOREIGN KEY (ID_STUDENT) REFERENCES MAPSKILLS.STUDENT(ID), FOREIGN KEY (ID_SCENE) REFERENCES MAPSKILLS.SCENE(ID), FOREIGN KEY (ID_SKILL) REFERENCES MAPSKILLS.SKILL(ID));