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
(1, 'Botafogo-SP', 'Ribeir�o Preto', 'Santa Cruz'),
(2, 'Bragantino', 'Bragantino Paulista', 'Nabi Abi Chedid'),
(3, 'Corinthians', 'S�o Paulo',	'Arena Corinthians'),
(4, 'Ferrovi�ria', 'Araraquara', 'Fonte Luminosa'),
(5, 'Guarani', 'Campinas', 'Brinco de Ouro da Princesa'),
(6, 'Ituano', 'Itu', 'Novelli J�nior'),
(7, 'Mirassol',	'Mirassol', 'Jos� Maria de Campos Maia'),
(8, 'Novorizontino', 'Novo Horizonte', 'Jorge Ismael de Biasi'),
(9, 'Oeste', 'Barueri',	'Arena Barueri'),
(10, 'Palmeiras', 'S�o Paulo', 'Allianz Parque'),
(11, 'Ponte Preta', 'Campinas',	'Mois�s Lucarelli'),
(12, 'Red Bull Brasil',	'Campinas',	'Mois�s Lucarelli'),
(13, 'Santos', 'Santos', 'Vila Belmiro'),
(14, 'S�o Bento', 'Sorocaba', 'Walter Ribeiro'),
(15, 'S�o Caetano',	'S�o Caetano do Sul', 'Anacletto Campanella'),
(16, 'S�o Paulo', 'S�o Paulo', 'Morumbi')

CREATE PROCEDURE sp_criando_grupos (@saida VARCHAR(MAX) OUTPUT) 
AS

SELECT TOP 4 t.codigoTime FROM times t ORDER BY NEWID()
/*
Select cast(RAND(checksum(newid()))*17 as int )

SELECT ABS(CHECKSUM(NewId())) % 17

SELECT CAST(RAND(CHECKSUM(NEWID())) * 16 AS INT) + 1

*/

CREATE TABLE teste (
grupo CHAR(1) NOT NULL,  
codigoTime INT NOT NULL
PRIMARY KEY (codigoTime)
FOREIGN KEY (codigoTime) REFERENCES times (codigoTime)
)
select * from times
	
DECLARE @var INT
SET @var = (SELECT TOP 1 t.codigoTime FROM times t ORDER BY NEWID())

INSERT INTO teste VALUES
('A', @var)

SELECT * FROM teste
ORDER BY grupo

IF ((SELECT COUNT (grupo) FROM teste WHERE grupo = 'A') = 4)
BEGIN
	PRINT 'Grupo A formado'
END
SELECT COUNT (grupo) FROM teste WHERE grupo = 'B'
SELECT COUNT (grupo) FROM teste WHERE grupo = 'C'
SELECT COUNT (grupo) FROM teste WHERE grupo = 'D'