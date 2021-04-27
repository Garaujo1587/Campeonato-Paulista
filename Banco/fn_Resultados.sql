-- FUNCTION TABELA RESULTADOS

CREATE FUNCTION fn_Resultados(@grupo char(1))
RETURNS @table TABLE (
    cod             int IDENTITY,
	id 				int,
	time_nome	    VARCHAR(100),
	num_jogos		int,
	vitoria			int,
	empate			int,
	derrota         int,
	gol_pro         int,
	gol_com			int,
    saldo_gol       int,
    pontos          int
)

AS
BEGIN     

    DECLARE @cont           int

    DECLARE @resultado TABLE (
    cod             int IDENTITY,
	id 				int,
	time_nome	    VARCHAR(100),
	num_jogos		int,
	vitoria			int,
	empate			int,
	derrota         int,
	gol_pro         int,
	gol_com			int,
    saldo_gol       int,
    pontos          int
)

    set @cont = 1;

    IF(@grupo = '')
    BEGIN

        WHILE(@cont < 17)
        BEGIN
            INSERT INTO @resultado (id, time_nome, num_jogos, vitoria, empate, derrota, 
            gol_pro, gol_com, saldo_gol, pontos)
            select (select @cont as 'id'), (select t.nomeTime from times t WHERE t.codigoTime =  @cont) as 'time_nome', num_jogos as 'num_jogos', vitoria as 'vitorias', empate as 'empate', 
            derrota as 'derrota', gol_pro as 'gol_pro', gol_com as 'gol_com', saldo as 'saldo_gol', pontos as 'pontos' from fn_jogos(@cont)


            set @cont = @cont + 1
        END
    END
    ELSE
    BEGIN

        WHILE(@cont < 17)
        BEGIN

            IF((select grupo from times where codigoTime = @cont) = @grupo)
            BEGIN
                INSERT INTO @resultado (id, time_nome, num_jogos, vitoria, empate, derrota, 
                gol_pro, gol_com, saldo_gol, pontos)
                select (select @cont as 'id'), (select t.nomeTime from times t WHERE t.codigoTime =  @cont) as 'time_nome', num_jogos as 'num_jogos', vitoria as 'vitorias', empate as 'empate', 
                derrota as 'derrota', gol_pro as 'gol_pro', gol_com as 'gol_com', saldo as 'saldo_gol', pontos as 'pontos' from fn_jogos(@cont)
            END

            set @cont = @cont + 1
        END

    END

    INSERT INTO @table (id, time_nome, num_jogos, vitoria, empate, derrota, 
        gol_pro, gol_com, saldo_gol, pontos)
        SELECT id as 'id', time_nome as 'time_nome', num_jogos as 'num_jogos', vitoria as 'vitorias', empate as 'empate', 
        derrota as 'derrota', gol_pro as 'gol_pro', gol_com as 'gol_com', saldo_gol as 'saldo_gol', pontos as 'pontos' from @resultado
        ORDER by pontos desc, vitoria desc, gol_pro desc, saldo_gol desc

    RETURN
END
