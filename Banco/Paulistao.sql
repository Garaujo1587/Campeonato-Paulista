CREATE DATABASE paulistao
GO
USE paulistao

CREATE TABLE times (
codigoTime INT NOT NULL,  
nomeTime VARCHAR(100),
cidade VARCHAR(100),
estadio VARCHAR(100)
PRIMARY KEY (codigoTime)
)

CREATE TABLE grupos (
grupo CHAR(1) NOT NULL,  
codigoTime INT NOT NULL
PRIMARY KEY (codigoTime)
FOREIGN KEY (codigoTime) REFERENCES times (codigoTime)
)

CREATE TABLE Jogos (
codigoTimeA INT NOT NULL,  
codigoTimeB INT NOT NULL,
golsTimeA INT, 
golsTimeB INT, 
data DATE NOT NULL
PRIMARY KEY(codigoTimeA, codigoTimeB)
FOREIGN KEY(codigoTimeA) REFERENCES times (codigoTime),
FOREIGN KEY(codigoTimeB) REFERENCES times (codigoTime)
) 

INSERT INTO times VALUES 
(1, 'Botafogo-SP', 'Ribeirão Preto', 'Santa Cruz'),
(2, 'Bragantino', 'Bragantino Paulista', 'Nabi Abi Chedid'),
(3, 'Corinthians', 'São Paulo',	'Arena Corinthians'),
(4, 'Ferroviária', 'Araraquara', 'Fonte Luminosa'),
(5, 'Guarani', 'Campinas', 'Brinco de Ouro da Princesa'),
(6, 'Ituano', 'Itu', 'Novelli Júnior'),
(7, 'Mirassol',	'Mirassol', 'José Maria de Campos Maia'),
(8, 'Novorizontino', 'Novo Horizonte', 'Jorge Ismael de Biasi'),
(9, 'Oeste', 'Barueri',	'Arena Barueri'),
(10, 'Palmeiras', 'São Paulo', 'Allianz Parque'),
(11, 'Ponte Preta', 'Campinas',	'Moisés Lucarelli'),
(12, 'Red Bull Brasil',	'Campinas',	'Moisés Lucarelli'),
(13, 'Santos', 'Santos', 'Vila Belmiro'),
(14, 'São Bento', 'Sorocaba', 'Walter Ribeiro'),
(15, 'São Caetano',	'São Caetano do Sul', 'Anacletto Campanella'),
(16, 'São Paulo', 'São Paulo', 'Morumbi')


-- tabela teste que representa os grupos
CREATE TABLE teste (
grupo CHAR(1) NOT NULL,  
codigoTime INT NOT NULL
PRIMARY KEY (codigoTime)
FOREIGN KEY (codigoTime) REFERENCES times (codigoTime)
)


CREATE PROCEDURE sp_criando_grupos (@saida VARCHAR(MAX) OUTPUT) 
AS
-- Inserindo os times grandes nos grupos de forma aleatoria
DECLARE @cta INT,
		@time INT,
		@grupo CHAR(1)

	SET @cta = 0
	SET @grupo = 'A'

WHILE (@cta < 4)
BEGIN
	SET @time = (SELECT TOP 1 t.codigoTime FROM times t ORDER BY NEWID())

	IF EXISTS (SELECT * FROM teste WHERE codigoTime = @time)
	BEGIN
		PRINT 'Já foi inserido'
	END
	ELSE 
	BEGIN
	IF (@time = 3 OR @time = 10 OR @time = 13 OR @time = 16)
	BEGIN
		IF ((SELECT COUNT (grupo) FROM teste WHERE grupo = 'A') = 1)
		BEGIN
			PRINT 'Grupo A com time grande'
			SET @grupo = 'B'
		END
		IF ((SELECT COUNT (grupo) FROM teste WHERE grupo = 'B') = 1)
		BEGIN
			PRINT 'Grupo B com time grande'
			SET @grupo = 'C'
		END
		IF ((SELECT COUNT (grupo) FROM teste WHERE grupo = 'C') = 1)
		BEGIN
			PRINT 'Grupo C com time grande'
			SET @grupo = 'D'
		END
		IF ((SELECT COUNT (grupo) FROM teste WHERE grupo = 'D') = 1)
		BEGIN
			PRINT 'Grupo D com time grande'
		END
		INSERT INTO teste VALUES
		(@grupo, @time)
		SET @cta = @cta + 1
	END
	END
END

-- Inserindo os outros times	
	SET @grupo = 'A'

WHILE (@cta < 16)
BEGIN

	SET @time = (SELECT TOP 1 t.codigoTime FROM times t ORDER BY NEWID())

IF EXISTS (SELECT * FROM teste WHERE codigoTime = @time)
BEGIN
	PRINT 'Já foi inserido'
END
ELSE 
BEGIN
	IF ((SELECT COUNT (grupo) FROM teste WHERE grupo = 'A') = 4)
	BEGIN
		PRINT 'Grupo A formado'
		SET @grupo = 'B'
	END
	IF ((SELECT COUNT (grupo) FROM teste WHERE grupo = 'B') = 4)
	BEGIN
		PRINT 'Grupo B formado'
		SET @grupo = 'C'
	END
	IF ((SELECT COUNT (grupo) FROM teste WHERE grupo = 'C') = 4)
	BEGIN
		PRINT 'Grupo C formado'
		SET @grupo = 'D'
	END
	IF ((SELECT COUNT (grupo) FROM teste WHERE grupo = 'D') = 4)
	BEGIN
		PRINT 'Grupo D formado'
		PRINT 'Todos os grupos estão completos'
	END
INSERT INTO teste VALUES
(@grupo, @time)
SET @cta = @cta + 1
END
END
SET @saida = 'Todos os grupos estão completos'

-- mostrando os grupos formados
SELECT te.grupo, te.codigoTime, t.nomeTime FROM teste te, times t WHERE t.codigoTime = te.codigoTime
ORDER BY grupo

-- chamando ã procedure para formar os grupos
DECLARE @out VARCHAR(MAX)
EXEC sp_criando_grupos @out OUTPUT
PRINT @out

/*
Formas de gerar números aleatórios

SELECT TOP 4 t.codigoTime FROM times t ORDER BY NEWID()

Select cast(RAND(checksum(newid()))*17 as int )

SELECT ABS(CHECKSUM(NewId())) % 17

SELECT CAST(RAND(CHECKSUM(NEWID())) * 16 AS INT) + 1

*/