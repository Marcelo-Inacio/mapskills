INSERT INTO MAPSKILLS.INSTITUTION (ID, CODE, CNPJ, COMPANY, CITY, LEVEL, ID_GAME_THEME) VALUES (1, '146', 65090609000144, 'Jessen Vidal', 'São José dos Campos - SP', 1, null);
INSERT INTO MAPSKILLS.INSTITUTION (ID, CODE, CNPJ, COMPANY, CITY, LEVEL, ID_GAME_THEME) VALUES (2, '282', 65058955000145, 'Etec - Guara', 'Guaratinguetá - SP', 0, null);

INSERT INTO MAPSKILLS.LOGIN (ID, USERNAME, PASSWORD) VALUES (1, 'rafael.alves@fatec.sp.gov.br', '$2a$05$rxtH5C1Ffra4RgC11DcCK');
INSERT INTO MAPSKILLS.MENTOR (ID, NAME, ID_LOGIN, ID_INSTITUTION) VALUES (1, 'Rafael Henry Alves', 1, 1);

INSERT INTO MAPSKILLS.LOGIN (ID, USERNAME, PASSWORD) VALUES (2, 'isabel.cardoso@etec.sp.gov.br', '$2a$05$rxtH5C1Ffra4RgC11DcCK');
INSERT INTO MAPSKILLS.MENTOR (ID, NAME, ID_LOGIN, ID_INSTITUTION) VALUES (2, 'Isabel Lorena Cardoso', 2, 2);