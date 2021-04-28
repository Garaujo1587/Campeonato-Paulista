-- PROCEDURE INSERE GOLS

CREATE PROCEDURE sp_insereGol (@id INT, @golA int, @golB int) 
AS
    update jogos
    set golsTimeA = @golA, golsTimeB = @golB
    where id = @id


-- FUNCTION QUE CALCULA OS RESULTADOS DE CADA TIME

CREATE FUNCTION fn_jogos(@id_time INT)
    RETURNS @table TABLE (
            num_jogos       int,
        	gol_pro         int,
        	gol_com			int,
            vitoria          int,
            derrota         int,
            empate          int,
            saldo           int,
            pontos          int
            )  
    AS
    BEGIN
        declare 
        @jogostem TABLE(
            jogos int IDENTITY,
            golp int,
            golc int,
            v int,
            d int,
            e int
    
        )

    	declare @cont as int,
                @golA as int,
                @golB as int,
                @vitoria as int,
                @derrota as int,
                @empate as int,
                @saldo   as int,
                @pontos as int

         set @cont = 0

         set @pontos = 0

         WHILE(@cont < 96)
         BEGIN

            set @golA = (SELECT jogos.golsTimeA from jogos WHERE jogos.id = @cont )
            set @golB = (SELECT jogos.golsTimeB from jogos WHERE jogos.id = @cont )
            set @vitoria = 0
            set @derrota = 0
            Set @empate = 0
            


            if((SELECT jogos.codigoTimeA from jogos WHERE jogos.id = @cont ) = @id_time
                and @golA is not null
                or (SELECT jogos.codigoTimeB from jogos WHERE jogos.id = @cont ) = @id_time
                and @golB is not null
                 )
                BEGIN
                    if((select jogos.codigoTimeA from jogos where jogos.id = @cont) = @id_time)
                    BEGIN

                        IF(@golA > @golB)
                        BEGIN
                            set @vitoria = 1
                            set @pontos = @pontos + 3
                        END
                        ELSE
                        IF(@golA < @golB)
                        BEGIN
                            set @derrota = 1
                        END
                        ELSE
                        BEGIN
                            set @empate = 1
                            set @pontos = @pontos + 1
                        END
                        

                        INSERT INTO @jogostem (golp, golc, v, d, e)
                        select  @golA as 'golp', @golB as 'golc', @vitoria as 'v', @derrota as 'd', @empate as 'e'
                    END
                    ELSE
                    BEGIN
                        IF((select jogos.codigoTimeB from jogos where jogos.id = @cont) = @id_time)
                        BEGIN


                            IF(@golB > @golA)
                            BEGIN
                                set @vitoria = 1
                                set @pontos = @pontos + 3
                            END
                            ELSE
                            IF(@golB < @golA)
                            BEGIN
                                set @derrota = 1
                            END
                            ELSE
                            BEGIN
                                set @empate = 1
                                set @pontos = @pontos + 1
                            END

                            INSERT INTO @jogostem (golp, golc, v, d, e)
                            select  @golB as 'golp', @golA as 'golc', @vitoria as 'v', @derrota as 'd', @empate as 'e'
                            
                        END
                    END
                END
               set @cont = @cont + 1
        END

        set @saldo = ((select sum(golp) from @jogostem) - (select sum(golc) from @jogostem))

        INSERT INTO @table (num_jogos, gol_pro, gol_com, vitoria, derrota, empate, saldo, pontos)
        select (select COUNT(jogos) from @jogostem) as 'num_jogos', (select sum(golp) from @jogostem) as 'gol_pro',
        (select sum(golc) from @jogostem) as 'gol_com', (select sum(v) from @jogostem) as 'vitoria', (select sum(d) from @jogostem) as 'derrota',
        (select sum(e) from @jogostem) as 'empate', @saldo as 'saldo', @pontos as 'pontos'

    	RETURN
    END


-- FUNCTION QUE GERA TABELA RESULTADOS

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

select * from fn_Resultados('')