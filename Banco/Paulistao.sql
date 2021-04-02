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

CREATE TABLE jogos (
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

-- PROCEDURE QUE GERA OS GRUPOS ALEATORIAMENTE E COM AS REGRAS DO CAMPEONATO

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

	IF NOT EXISTS (SELECT * FROM grupos WHERE codigoTime = @time)
	BEGIN
		IF (@time = 3 OR @time = 10 OR @time = 13 OR @time = 16)
		BEGIN
			IF ((SELECT COUNT (grupo) FROM grupos WHERE grupo = 'A') = 1)
			BEGIN
				SET @grupo = 'B'
			END
			IF ((SELECT COUNT (grupo) FROM grupos WHERE grupo = 'B') = 1)
			BEGIN
				SET @grupo = 'C'
			END
			IF ((SELECT COUNT (grupo) FROM grupos WHERE grupo = 'C') = 1)
			BEGIN
				SET @grupo = 'D'
			END
			INSERT INTO grupos VALUES
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

	IF NOT EXISTS (SELECT * FROM grupos WHERE codigoTime = @time)
	BEGIN
		IF ((SELECT COUNT (grupo) FROM grupos WHERE grupo = 'A') = 4)
		BEGIN
			SET @grupo = 'B'
		END
		IF ((SELECT COUNT (grupo) FROM grupos WHERE grupo = 'B') = 4)
		BEGIN
			SET @grupo = 'C'
		END
		IF ((SELECT COUNT (grupo) FROM grupos WHERE grupo = 'C') = 4)
		BEGIN
			SET @grupo = 'D'
		END
	INSERT INTO grupos VALUES
	(@grupo, @time)
	SET @cta = @cta + 1
	END
END
SET @saida = 'Todos os grupos estão completos'

-- mostrando os grupos formados
SELECT g.grupo, g.codigoTime, t.nomeTime FROM grupos g, times t WHERE t.codigoTime = g.codigoTime
ORDER BY grupo

-- chamando a procedure para formar os grupos
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

-- PROCEDURE QUE GERA OS JOGOS E AS RODADAS - INCOMPLETA

CREATE PROCEDURE sp_criando_rodadas (@saida VARCHAR(MAX) OUTPUT)
AS

DECLARE @ctarodada AS INT,
		@ctajogo AS INT,
		@timeA AS INT,
		@timeB AS INT,
		@dtjogo AS DATE

SET @ctarodada = 0
SET @dtjogo = '2019-01-20'

WHILE (@ctarodada < 12)
BEGIN
SET @ctajogo = 0
	IF (@ctarodada <> 0 AND @ctarodada % 2 <> 0)
	BEGIN 
		SET @dtjogo = (DATEADD(DAY, 3, @dtjogo))
	END
	IF (@ctarodada <> 0 AND @ctarodada % 2 = 0)
	BEGIN
		SET @dtjogo = (DATEADD(DAY, 4, @dtjogo))
	END
	WHILE (@ctajogo < 8)
	BEGIN
		SET @timeA = (SELECT TOP 1 t.codigoTime FROM times t ORDER BY NEWID())
		SET @timeB = (SELECT TOP 1 t.codigoTime FROM times t ORDER BY NEWID())
		
		IF (@timeA <> @timeB 
		AND (SELECT grupo FROM grupos WHERE codigoTime = @timeA) <> (SELECT grupo FROM grupos WHERE codigoTime = @timeB)
		AND NOT EXISTS(SELECT * FROM jogos WHERE codigoTimeA = @timeA AND codigoTimeB = @timeB)
		AND NOT EXISTS(SELECT t.codigoTime, j.data FROM jogos j, times t 
		WHERE j.codigoTimeA = t.codigoTime AND data = @dtjogo AND j.codigoTimeA = @timeA AND t.codigoTime = @timeA
		OR j.codigoTimeB = t.codigoTime AND data = @dtjogo AND j.codigoTimeB = @timeB AND t.codigoTime = @timeB)
		AND NOT EXISTS (SELECT COUNT(*), t.codigoTime, j.data 
		FROM jogos j INNER JOIN times t
		ON t.codigoTime = j.codigoTimeA AND j.codigoTimeA = @timeA AND data = @dtjogo
		OR t.codigoTime = j.codigoTimeB AND j.codigoTimeB = @timeB AND data = @dtjogo
		WHERE data = @dtjogo
		GROUP BY t.codigoTime, j.data
		HAVING COUNT(*)>1)) 
		BEGIN
			INSERT INTO jogos VALUES 
			(@timeA, @timeB, NULL, NULL, @dtjogo)
			SET @ctajogo = @ctajogo + 1	  
		END
	END
	SET @ctarodada = @ctarodada + 1
END
SET @saida = 'Rodadas geradas'

-- mostrando os jogos
SELECT * FROM jogos j 
ORDER BY data

-- chamando a procedure para gerar os jogos e as rodadas
DECLARE @out VARCHAR(MAX)
EXEC sp_criando_rodadas @out OUTPUT
PRINT @out

-- select que mostra o problema: um time não pode jogar duas vezes na mesma rodada(data)
SELECT COUNT(*), t.codigoTime, j.data 
FROM jogos j INNER JOIN times t
ON t.codigoTime = j.codigoTimeA OR t.codigoTime = j.codigoTimeB 
WHERE data = '2019-01-20' 
GROUP BY t.codigoTime, j.data
HAVING COUNT(*)>1