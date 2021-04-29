-- trigger que impede realizar as operacoes insert, delete e update na tabela times
CREATE TRIGGER t_times ON times
FOR INSERT, DELETE
AS
BEGIN
	IF ((SELECT COUNT(*) FROM times) > 16)
	BEGIN 
		ROLLBACK TRANSACTION
		RAISERROR('A tabela times já está montada', 16, 1)
	END
END

CREATE TRIGGER t_upd ON times
FOR UPDATE
AS
BEGIN
	ROLLBACK TRANSACTION
	RAISERROR('A tabela times não pode ser atualizada', 16, 1)
END


-- Testando as triggers do time
INSERT INTO times VALUES (17, 'time', 'cidade', 'estadio', 'E')
DELETE FROM times
UPDATE times
SET nomeTime = 'Santos FC'
where codigoTime = 9



-- trigger que impede realizar as operacoes insert, delete na tabela grupos
CREATE TRIGGER t_jogos ON jogos
FOR INSERT, DELETE
AS
BEGIN
	IF ((SELECT COUNT(*) FROM jogos) > 96)
	BEGIN 
		ROLLBACK TRANSACTION
		RAISERROR('A tabela jogos já está montada', 16, 1)
	END
END

-- testando a trigger jogos
INSERT INTO jogos VALUES (1, 16, 1, 1, 2, '2020-01-20')
DELETE FROM jogos