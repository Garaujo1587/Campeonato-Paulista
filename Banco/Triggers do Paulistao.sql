USE paulistao

-- trigger que impede realizar as operacoes insert, delete e update na tabela times
CREATE TRIGGER t_times ON times
FOR DELETE, UPDATE, INSERT
AS
BEGIN
		ROLLBACK TRANSACTION
		RAISERROR('A tabela já está montada', 16, 1)
	
END

DISABLE TRIGGER t_times ON times

-- trigger que impede realizar as operacoes insert, delete na tabela grupos
CREATE  TRIGGER t_jogos ON jogos
FOR INSERT, DELETE
AS
BEGIN
		ROLLBACK TRANSACTION
		RAISERROR('A tabela já esta montada', 16, 1)

END

DISABLE TRIGGER t_jogos ON jogos