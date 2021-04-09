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
PRIMARY KEY(codigoTimeA, codigoTimeB)
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
	DELETE FROM jogos
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



SET @saida = 'Grupos gerados com sucesso'

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
	-- DECLARA VARIAVEIS 
	DECLARE @I AS INT,
			@DTJOGOTJOGO AS DATE,
			@A AS INT,
			@B AS INT,
			@F AS INT,
			@RA AS INT,
			@RB AS INT,
			@ID AS INT,
			@J AS INT,
			@FLAG AS INT,
			@DTJOGO AS DATE

	-- CRIA TABELAS TEMPORARIAS 

	CREATE TABLE #TODOS_JOGOS(
	ID INT,
	TIMEA INT,
	TIMEB INT)

	CREATE TABLE #REFERENCIAS(
	ID INT,
	R INT)

	CREATE TABLE #TODASDATAS(
	ID INT,
	DATA DATE UNIQUE)

	-- GERA TODAS AS DATAS
	SET @I = 0
	SET @DTJOGOTJOGO = '2019-01-20'

	WHILE(@I < 12)
	BEGIN

		IF (@I <> 0 AND @I % 2 <> 0)
		BEGIN 
			SET @DTJOGOTJOGO = (DATEADD(DAY, 3, @DTJOGOTJOGO))
		END
		IF (@I <> 0 AND @I % 2 = 0)
		BEGIN
			SET @DTJOGOTJOGO = (DATEADD(DAY, 4, @DTJOGOTJOGO))
		END
	
		INSERT INTO #TODASDATAS VALUES
		((@I + 1),(@DTJOGOTJOGO))

		SET @I = @I + 1
	END
	
	-- INSERE VALOR DE REFERENCIA
	INSERT INTO #REFERENCIAS VALUES
	(1,1), (2,5), (3,9), (4,13),
	(5,1), (6,9), (7,5), (8,13),
	(9,1), (10,13), (11,5), (12,9)

		-- GERA TODOS OS JOGOS
	DELETE FROM #TODOS_JOGOS
	
		
	SET @I = 1
	SET @ID = 1
	
	WHILE(@I < 12)
	BEGIN
	
		SET @RA = (SELECT R.R FROM #REFERENCIAS R WHERE R.ID = @I)
		SET @RB = (SELECT R.R FROM #REFERENCIAS R WHERE R.ID = @I + 1)
		SET @F = 1
		SET @A = @RA
		SET @B = @RB
	
		WHILE(@F < 17)
		BEGIN
	
	
			INSERT INTO #TODOS_JOGOS VALUES
			(@ID, @A, @B)
			SET @ID = @ID + 1
	
			IF(@B = (@RB + 3))
			BEGIN
				SET @B = @RB
			END
			ELSE
			BEGIN
				SET @B =  @B + 1
			END
	
	
			IF(@A = (@RA + 3))
			BEGIN
				SET @A =  @RA
				SET @B =  @B + 1
			END
			ELSE
			BEGIN
				SET @A = @A +1	
			END
	
			SET @F = @F + 1
	
		END
		
	
		SET @I = @I + 2
	END

	-- COLOCA JOGOS NA TABELA JOGOS
	SET @FLAG = 0
	SET @J = 1

	SET @DTJOGO = (SELECT TOP 1 t.DATA FROM #TODASDATAS t ORDER BY NEWID())
	DELETE FROM #TODASDATAS WHERE #TODASDATAS.DATA = @DTJOGO
	WHILE(@J < 92)
	BEGIN

		IF(@FLAG = 0)
		BEGIN
			INSERT INTO jogos VALUES
			((SELECT J.TIMEA FROM #TODOS_JOGOS J WHERE J.ID = @J) , (SELECT J.TIMEB FROM #TODOS_JOGOS J WHERE J.ID = @J), NULL, NULL, @DTJOGO),
			((SELECT J.TIMEA FROM #TODOS_JOGOS J WHERE J.ID = (@J + 16)) , (SELECT J.TIMEB FROM #TODOS_JOGOS J WHERE J.ID = (@J + 16)), NULL, NULL, @DTJOGO)
		END
		ELSE
		BEGIN
			INSERT INTO jogos VALUES
			((SELECT J.TIMEB FROM #TODOS_JOGOS J WHERE J.ID = @J) , (SELECT J.TIMEA FROM #TODOS_JOGOS J WHERE J.ID = @J), NULL, NULL, @DTJOGO),
			((SELECT J.TIMEB FROM #TODOS_JOGOS J WHERE J.ID = (@J + 16)) , (SELECT J.TIMEA FROM #TODOS_JOGOS J WHERE J.ID = (@J + 16)), NULL, NULL, @DTJOGO)
		END
		IF(@J % 16 = 0)
		BEGIN
			SET @J = @J + 16
		END


		IF(@J % 4 = 0)
		BEGIN
			SET @DTJOGO = (SELECT TOP 1 t.DATA FROM #TODASDATAS t ORDER BY NEWID())
			DELETE FROM #TODASDATAS WHERE #TODASDATAS.DATA = @DTJOGO
			IF(@FLAG = 0)
			BEGIN
				SET @FLAG = 1
			END
			ELSE
			BEGIN
				SET @FLAG = 0
			END
		END

		SET @J = @J +1
	END


SET @saida = 'Rodadas geradas com sucesso'



-- chamando a procedure para gerar os jogos e as rodadas
DECLARE @out VARCHAR(MAX)
EXEC sp_criando_rodadas @out OUTPUT
PRINT '@out'

-- mostrando os jogos
SELECT * FROM jogos 
ORDER BY data

-- saida da procedure gerar rodadas
SELECT time1.nomeTime AS 'Mandante', time2.nomeTime AS 'Visitante', 
time1.estadio AS 'Estadio', time1.cidade AS 'Cidade', data AS 'Data' 
FROM times AS time1
INNER JOIN jogos
ON time1.codigoTime = jogos.codigoTimeA
INNER JOIN times AS time2
ON time2.codigoTime = jogos.codigoTimeB
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

-- cria função retorna rodadas
CREATE FUNCTION fn_RetornaRodadas()
RETURNS @table TABLE (
Mandante    VARCHAR(100),
Visitante    VARCHAR(100),
Estadio        VARCHAR(100),
Cidade        VARCHAR(100),
Dataj        date
)
AS
BEGIN
    INSERT INTO @table (Mandante, Visitante, Estadio, Cidade, Dataj)
        SELECT time1.nomeTime AS 'Mandante', time2.nomeTime AS 'Visitante', 
		time1.estadio AS 'Estadio', time1.cidade AS 'Cidade', data AS 'Dataj' 
		FROM times AS time1
		INNER JOIN jogos
		ON time1.codigoTime = jogos.codigoTimeA
		INNER JOIN times AS time2
		ON time2.codigoTime = jogos.codigoTimeB
		ORDER BY data
    RETURN
END

-- chamando a function que retorna as rodadas
SELECT * FROM fn_RetornaRodadas()

-- FUNCTION QUE BUSCA TODOS OS JOGOS DE UMA DATA

CREATE FUNCTION fn_BuscaJogos(@dat VARCHAR(10))
RETURNS @table TABLE (
Mandante	VARCHAR(100),
Visitante	VARCHAR(100),
Dataj		date
)
AS
BEGIN
	SET @dat = CONVERT(DATE, @dat, 103)
	INSERT INTO @table (Mandante, Visitante, Dataj)
		SELECT time1.nomeTime AS 'Mandante', time2.nomeTime AS 'Visitante', 
		data AS 'Dataj' 
		FROM times AS time1
		INNER JOIN jogos
		ON time1.codigoTime = jogos.codigoTimeA
		INNER JOIN times AS time2
		ON time2.codigoTime = jogos.codigoTimeB
		WHERE data = @dat
 	
	RETURN
END

-- chamando a function que busca jogos
SELECT Mandante, Visitante, Dataj FROM fn_BuscaJogos('20/01/2019')

SELECT *
  FROM jogos
 WHERE codigoTimeA = 1 AND codigoTimeB = 5
   AND CONVERT(DATE, data, 103) BETWEEN CONVERT(DATE, '15/05/2017', 103) AND CONVERT(DATE, '31/05/2017', 103);
DECLARE @d AS VARCHAR(10)
SET @d = (SELECT CONVERT(VARCHAR(10), data, 103) from jogos j where codigoTimeA = 1 AND codigoTimeB = 5)
PRINT CONVERT(DATE, @d, 103)