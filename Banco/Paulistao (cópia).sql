CREATE DATABASE paulistao2
GO
USE paulistao2

CREATE TABLE times (
codigoTime INT NOT NULL,  
nomeTime VARCHAR(100),
cidade VARCHAR(100),
estadio VARCHAR(100),
grupo CHAR(1),
PRIMARY KEY (codigoTime)
)


CREATE TABLE jogos (
codigoTimeA INT NOT NULL,  
codigoTimeB INT NOT NULL,
golsTimeA INT, 
golsTimeB INT, 
data DATE NOT NULL
FOREIGN KEY(codigoTimeA) REFERENCES times (codigoTime),
FOREIGN KEY(codigoTimeB) REFERENCES times (codigoTime)
) 

-- PROCEDURE CRIA OS GRUPOS

CREATE PROCEDURE sp_criando_grupos (@saida VARCHAR(MAX) OUTPUT) 
AS

	DECLARE @COD_TIME AS INT,
			@COD_SORTEIO AS INT,
			@COD_GRUPO AS INT,
			@I AS INT

	SET @COD_GRUPO = 65
	SET @COD_SORTEIO = 1

-- DELETA VALERES NA TABELA TIMES
	DELETE FROM times

-- CRIA TABELAS PROVISORIAS 

	CREATE TABLE #COD_SORTEIO(
		COD INT
	)

	CREATE TABLE #time (
	codigoTime INT,  
	nomeTime VARCHAR(100),
	cidade VARCHAR(100),
	estadio VARCHAR(100)
	)

-- INSERE OS CODIGOS DOS CABEÇAS DE CHAVES
	INSERT INTO #COD_SORTEIO VALUES
	(1),
	(5),
	(9),
	(13)

-- INSERE TIMES CABEÇAS DE CHAVES 
	INSERT INTO #time VALUES 
	(1, 'Corinthians', 'São Paulo',	'Arena Corinthians'),
	(2, 'Palmeiras', 'São Paulo', 'Allianz Parque'),
	(3, 'São Paulo', 'São Paulo', 'Morumbi'),
	(4, 'Santos', 'Santos', 'Vila Belmiro')

	-- SORTEIA OS CABEÇAS DE CHAVES
	WHILE(@COD_SORTEIO < 14)
	BEGIN
	

	-- SORTEIA O TIME
		SET @COD_TIME = (SELECT TOP 1 T.codigoTime FROM  #time T ORDER BY NEWID())


	-- INSERE TIME NA TABELA DOS TIMES
		INSERT INTO times VALUES(
		(@COD_SORTEIO),
		(SELECT T.nomeTime FROM #time T WHERE T.codigoTime = @COD_TIME),
		(SELECT T.cidade FROM #time T WHERE T.codigoTime = @COD_TIME),
		(SELECT T.estadio FROM #time T WHERE T.codigoTime = @COD_TIME),
		(NCHAR(@COD_GRUPO))
		)

	-- DELETA VALORES JÁ SORTEADOS 
		DELETE FROM #COD_SORTEIO WHERE #COD_SORTEIO.COD = @COD_SORTEIO
		DELETE FROM #time WHERE #time.codigoTime = @COD_TIME

	-- SET NOVO VALOR DO GRUPO E CODIGO DO TIME
		SET @COD_GRUPO = @COD_GRUPO + 1
		SET @COD_SORTEIO =  @COD_SORTEIO + 4

	END

	-- LIMPAS TABELAS

	DELETE FROM #COD_SORTEIO
	DELETE FROM #time

	-- INSERE VALORES NAS TABELAS PROVISORIAS 
	SET @COD_SORTEIO = 2
	WHILE(@COD_SORTEIO < 13)
	BEGIN
		IF(@COD_SORTEIO <> 5 AND @COD_SORTEIO <> 9 AND @COD_SORTEIO <> 13)
		BEGIN
			INSERT INTO #COD_SORTEIO VALUES
			(@COD_SORTEIO)
		END
		SET @COD_SORTEIO = @COD_SORTEIO + 1
	END

	INSERT INTO #time VALUES
	(1, 'Botafogo-SP', 'Ribeirão Preto', 'Santa Cruz'),
	(2, 'Bragantino', 'Bragantino Paulista', 'Nabi Abi Chedid'),
	(3, 'Ferroviária', 'Araraquara', 'Fonte Luminosa'),
	(4, 'Guarani', 'Campinas', 'Brinco de Ouro da Princesa'),
	(5, 'Ituano', 'Itu', 'Novelli Júnior'),
	(6, 'Mirassol',	'Mirassol', 'José Maria de Campos Maia'),
	(7, 'Novorizontino', 'Novo Horizonte', 'Jorge Ismael de Biasi'),
	(8, 'Oeste', 'Barueri',	'Arena Barueri'),
	(9, 'Ponte Preta', 'Campinas',	'Moisés Lucarelli'),
	(10, 'Red Bull Brasil',	'Campinas',	'Moisés Lucarelli'),
	(11, 'São Bento', 'Sorocaba', 'Walter Ribeiro'),
	(12, 'São Caetano',	'São Caetano do Sul', 'Anacletto Campanella')

	SET @COD_GRUPO = 65
	SET @COD_SORTEIO = 2
	WHILE(@COD_GRUPO < 69)
	BEGIN
	
	SET @I = 0
		
		WHILE(@I < 3)
		BEGIN
			
			SET @COD_TIME = (SELECT TOP 1 T.codigoTime FROM  #time T ORDER BY NEWID())

			-- INSERE TIME NA TABELA DOS TIMES
			INSERT INTO times VALUES(
			(@COD_SORTEIO),
			(SELECT T.nomeTime FROM #time T WHERE T.codigoTime = @COD_TIME),
			(SELECT T.cidade FROM #time T WHERE T.codigoTime = @COD_TIME),
			(SELECT T.estadio FROM #time T WHERE T.codigoTime = @COD_TIME),
			(NCHAR(@COD_GRUPO))
			)

			-- DELETA VALORES JÁ SORTEADOS 
			DELETE FROM #COD_SORTEIO WHERE #COD_SORTEIO.COD = @COD_SORTEIO
			DELETE FROM #time WHERE #time.codigoTime = @COD_TIME

			SET @I = @I + 1
			SET @COD_SORTEIO = @COD_SORTEIO + 1

		END 
		SET @COD_SORTEIO = @COD_SORTEIO + 1
		SET @COD_GRUPO = @COD_GRUPO + 1
	END



SET @saida = 'Todos os grupos estão completos'


-- chamando a procedure para formar os grupos
DECLARE @out VARCHAR(MAX)
EXEC sp_criando_grupos @out OUTPUT
PRINT @out

GO

SELECT * FROM times

GO


-- PROCEDURE QUE GERA OS JOGOS E AS RODADAS

CREATE PROCEDURE sp_criando_rodadas (@saida VARCHAR(MAX) OUTPUT)
AS
	DELETE FROM jogos

	DECLARE @RODADAS AS INT,
			@DTJOGO AS DATE, 
			@OUTRO_TIME AS INT,
			@I AS INT,
			@OUTRO_TIME2 AS INT,
			@I2 AS INT

	SET @RODADAS = 0
	SET @OUTRO_TIME = 8
	SET @DTJOGO = '2019-01-20'



	WHILE(@RODADAS < 12)
	BEGIN

	-- CALCULA A DATA
	IF (@RODADAS <> 0 AND @RODADAS % 2 <> 0)
	BEGIN 
		SET @dtjogo = (DATEADD(DAY, 3, @dtjogo))
	END
	IF (@RODADAS <> 0 AND @RODADAS % 2 = 0)
	BEGIN
		SET @dtjogo = (DATEADD(DAY, 4, @dtjogo))
	END
		
		SET @I = 1
		WHILE(@I < 5)
		BEGIN

			SET @OUTRO_TIME = @OUTRO_TIME + 1
			IF(@OUTRO_TIME > 16)
			BEGIN
				SET @OUTRO_TIME = 5 
			END

			IF(@RODADAS % 2 = 0)
			BEGIN
				INSERT INTO jogos VALUES
				(@OUTRO_TIME,@I, NULL, NULL,@DTJOGO)
			END
			ELSE
			BEGIN
				INSERT INTO jogos VALUES
				(@I,@OUTRO_TIME, NULL, NULL,@DTJOGO)
			END

			SET @I = @I + 1
			
		END

	IF(@OUTRO_TIME = 6 OR @OUTRO_TIME = 5 AND @I = 5)
	BEGIN
		SET @OUTRO_TIME = @OUTRO_TIME + 9
	END
	ELSE
	BEGIN
		SET @OUTRO_TIME = @OUTRO_TIME -3 
	END
		
	-- SEGUNDA PARTE TABELA

		SET @I = 1
		SET @I2 = 5 + @RODADAS
		IF(@I2 < 9)
		BEGIN
			SET @OUTRO_TIME2 = @I2 + 8
		END
		ELSE
		BEGIN
			SET @OUTRO_TIME2 = @I2 -4
		END
		
		WHILE(@I < 5)
		BEGIN
		
			-- INSERE VALOR
			INSERT INTO jogos VALUES
			(@I2,@OUTRO_TIME2, NULL, NULL,@DTJOGO)

			SET @OUTRO_TIME2 = @OUTRO_TIME2 + 1
			IF(@OUTRO_TIME2 > 16)
			BEGIN
				SET @OUTRO_TIME2 = 5
			END
			
			SET @I2 = @I2 + 1
			IF(@I2 > 16)
			BEGIN
				SET @I2 = 5
			END

		SET @I = @I + 1
		END

		SET @RODADAS = @RODADAS + 1
	END

SET @saida = 'Rodadas geradas'



-- chamando a procedure para gerar os jogos e as rodadas
DECLARE @out VARCHAR(MAX)
EXEC sp_criando_rodadas @out OUTPUT
PRINT @out

-- mostrando os jogos
SELECT * FROM jogos j 
ORDER BY data

SELECT COUNT(*), t.codigoTime, j.data 
FROM jogos j INNER JOIN times t
ON t.codigoTime = j.codigoTimeA OR t.codigoTime = j.codigoTimeB 
WHERE data = '2019-01-20' 
GROUP BY t.codigoTime, j.data
HAVING COUNT(*)>1

-- select que mostra como deve ficar, um time joga apenas uma rodada(data)
SELECT t.codigoTime, j.data FROM times t, jogos j
WHERE t.codigoTime = j.codigoTimeA AND data = '2019-01-20' 
OR t.codigoTime = j.codigoTimeB AND data = '2019-01-20'