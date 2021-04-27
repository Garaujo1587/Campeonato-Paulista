CREATE DATABASE paulistao
GO
USE paulistao

CREATE TABLE times (
codigoTime INT NOT NULL,  
nomeTime VARCHAR(100),
cidade VARCHAR(100),
estadio VARCHAR(100),
grupo CHAR(1),
PRIMARY KEY (codigoTime)
)


CREATE TABLE jogos (
	id INT NOT NULL IDENTITY,
	codigoTimeA INT NOT NULL,  
	codigoTimeB INT NOT NULL,
	golsTimeA INT, 
	golsTimeB INT, 
	data DATE NOT NULL
	PRIMARY KEY(id)
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

-- FUNCTION QUE MOSTRA AS RODADAS

CREATE FUNCTION fn_RetornaRodadas()
RETURNS @table TABLE (
cod 		INT	 IDENTITY(1,1) PRIMARY KEY,
id 			int,
Mandante    VARCHAR(100),
Visitante   VARCHAR(100),
Gol_M		int,
Gol_V		int,
Estadio     VARCHAR(100),
Cidade      VARCHAR(100),
Dataj       VARCHAR(10)
)
AS
BEGIN
    INSERT INTO @table (id, Mandante, Visitante,Gol_M , Gol_V, Estadio, Cidade, Dataj)
        SELECT j.id, time1.nomeTime AS 'Mandante', time2.nomeTime AS 'Visitante',
        CASE WHEN (j.golsTimeA  IS NOT NULL)
		THEN
			j.golsTimeA 
		ELSE
			-1
    	END as 'Gol_M', 
        CASE WHEN (j.golsTimeB  IS NOT NULL)
		THEN
			 j.golsTimeB
		ELSE
			-1
	    END as 'Gol_V',
		time1.estadio AS 'Estadio', time1.cidade AS 'Cidade', CONVERT(VARCHAR(10), j.data, 103) AS 'Dataj' 
		FROM times AS time1
		INNER JOIN jogos j
		ON time1.codigoTime = j.codigoTimeA
		INNER JOIN times AS time2
		ON time2.codigoTime = j.codigoTimeB
		ORDER BY j.[data] 
    RETURN
END

-- FUNCTION QUE BUSCA TODOS OS JOGOS DE UMA DATA

CREATE FUNCTION fn_BuscaJogos(@dat VARCHAR(10))
RETURNS @table TABLE (
	id 				int,
	Mandante		VARCHAR(100),
	Visitante		VARCHAR(100),
	Gol_M			int,
	Gol_V			int,
	Estadio         VARCHAR(100),
	Cidade          VARCHAR(100),
	Dataj			VARCHAR(10)
)
AS
BEGIN

	SET @dat = CONVERT(DATE, @dat, 103)
	
	IF NOT EXISTS (SELECT * FROM jogos WHERE data = @dat)
	BEGIN
		INSERT INTO @table (Mandante, Dataj)
			SELECT '-1' AS 'Mandante',
			CONVERT(VARCHAR(10), data, 103) AS 'Dataj' 
			FROM times AS time1
			INNER JOIN jogos
			ON time1.codigoTime = jogos.codigoTimeA
			INNER JOIN times AS time2
			ON time2.codigoTime = jogos.codigoTimeB
			GROUP BY data
	END
	ELSE
	BEGIN
	INSERT INTO @table (id, Mandante, Visitante,Gol_M , Gol_V, Estadio, Cidade, Dataj)
		SELECT jogos.id, time1.nomeTime AS 'Mandante', time2.nomeTime AS 'Visitante',
		CASE WHEN (jogos.golsTimeA  IS NOT NULL)
		THEN
			jogos.golsTimeA 
		ELSE
			-1
    	END as 'Gol_M', 
        CASE WHEN (jogos.golsTimeB  IS NOT NULL)
		THEN
			 jogos.golsTimeB
		ELSE
			-1
	    END as 'Gol_V',
		time1.estadio AS 'Estadio', time1.cidade AS 'Cidade',
		CONVERT(VARCHAR(10), data, 103) AS 'Dataj' 
		FROM times AS time1
		INNER JOIN jogos 
		ON time1.codigoTime = jogos.codigoTimeA
		INNER JOIN times AS time2
		ON time2.codigoTime = jogos.codigoTimeB
		WHERE data = @dat
	END

	RETURN
END

CREATE PROCEDURE sp_insereGol (@id INT, @golA int, @golB int) 
AS
    update jogos
    set golsTimeA = @golA, golsTimeB = @golB
    where id = @id

-- trigger que impede realizar as operacoes insert, delete e update na tabela times
CREATE TRIGGER t_times ON times
FOR INSERT, DELETE, UPDATE
AS
BEGIN
	ROLLBACK TRANSACTION
	RAISERROR('Acao invalida', 16, 1)
END

-- trigger que impede realizar as operacoes insert, delete na tabela grupos
CREATE TRIGGER t_jogos ON jogos
FOR INSERT, DELETE
AS
BEGIN
	ROLLBACK TRANSACTION
	RAISERROR('Acao invalida', 16, 1)
END

CREATE FUNCTION fn_tabela_grupo (@grupo CHAR(1))
RETURNS @table TABLE (
	nome_time 				VARCHAR(100),
	num_jogos_disputados	INT,
	vitorias				INT,
	empates					INT,
	derrotas				INT,
	gols_marcados			INT,
	gols_sofridos			INT,
	saldo_gols				INT,
	pontos					INT
)
AS
BEGIN
	INSERT INTO @table (nome_time, num_jogos_disputados)
	-- contando jogos disputados
	select times.nomeTime as times, count(jogos.id) as jogos_disputados
	from times,jogos where jogos.golsTimeA >= 0 and (times.codigoTime = jogos.codigoTimeA or times.codigoTime = jogos.codigoTimeB)
	group by times.nomeTime
	
	--contando vitorias
	select times.nomeTime as times, count (jogos.id) as vitorias
	from times,jogos 
	where (times.codigoTime = jogos.codigoTimeA AND jogos.golsTimeA > jogos.golsTimeB)
	or (times.codigoTime = jogos.codigoTimeB AND jogos.golsTimeB > jogos.golsTimeA)
	group by times.nomeTime

	--contando derrotas
	select times.nomeTime as times, count (jogos.id) as derrotas
	from times,jogos 
	where (times.codigoTime = jogos.codigoTimeA AND jogos.golsTimeA < jogos.golsTimeB)
	or (times.codigoTime = jogos.codigoTimeB AND jogos.golsTimeB < jogos.golsTimeA)
	group by times.nomeTime

	--contando empates
	select times.nomeTime as times, count (jogos.id) as empates
	from times,jogos
	where (times.codigoTime = jogos.codigoTimeA AND jogos.golsTimeA = jogos.golsTimeB)
	or (times.codigoTime = jogos.codigoTimeB AND jogos.golsTimeB = jogos.golsTimeA)
	group by times.nomeTime

	-- gols marcados como mandante
	select t.nomeTime, SUM(j.golsTimeA) as golsMarcadosMandante
	from times t, jogos j
	where (t.codigoTime = j.codigoTimeA)
	group by t.nomeTime

	-- gols marcados como visitante
	select t.nomeTime, SUM(j.golsTimeB) as golsMarcadosVisitante
	from times t, jogos j
	where (t.codigoTime = j.codigoTimeB)
	group by t.nomeTime

END