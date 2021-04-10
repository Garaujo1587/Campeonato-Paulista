-- TESTANDO A PROCEDURE DE GERAR GRUPOS

DECLARE @out VARCHAR(MAX)
EXEC sp_criando_grupos @out OUTPUT
PRINT @out

SELECT * FROM times






-- TESTANDO A PROCEDURE QUE GERA OS JOGOS/RODADAS

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







-- CHAMANDO A FUNCTION QUE RETORNA AS RODADAS

SELECT * FROM fn_RetornaRodadas()






-- CHAMANDO A FUNCTION QUE BUSCA OS JOGOS

SELECT Mandante, Visitante, Cidade, Estadio, Dataj FROM fn_BuscaJogos('29/01/2019')




-- OUTROS SELECTS USADOS

-- select que mostra caso um time jogue mais de uma vez naquela data
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






-- CONVERSAO USADA NA FUNCTION

-- convertendo varchar pra date e date pra varchar
DECLARE @d AS VARCHAR(10)
SET @d = (SELECT CONVERT(VARCHAR(10), data, 103) from jogos j where codigoTimeA = 1 AND codigoTimeB = 5)
PRINT CONVERT(DATE, @d, 103)