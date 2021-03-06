-- TRUNCATE TABLE map
-- TRUNCATE TABLE city
-- TRUNCATE TABLE baggage
-- TRUNCATE TABLE truck
-- TRUNCATE TABLE driver
-- TRUNCATE TABLE orderroute
-- TRUNCATE TABLE 'user'

INSERT INTO baggage VALUES (1, 'tea',        '0', '1200')
INSERT INTO baggage VALUES (2, 'coffee',     '0', '990' )
INSERT INTO baggage VALUES (3, 'cola',       '0', '100' )
INSERT INTO baggage VALUES (4, 'vegetables', '0', '500' )

--
INSERT INTO city VALUES (1, 'Moscow')
INSERT INTO city VALUES (2, 'Saint-Petersburg')
INSERT INTO city VALUES (3, 'Berlin')
INSERT INTO city VALUES (4, 'Paris')

--
INSERT INTO map VALUES (1, 2, 706)
INSERT INTO map VALUES (1, 3, 1835)
INSERT INTO map VALUES (2, 3, 1667)
INSERT INTO map VALUES (4, 1, 2836)
INSERT INTO map VALUES (4, 2, 2752)
INSERT INTO map VALUES (4, 3, 1054)

--
INSERT INTO truck VALUES ('AB12345', 4, 1, 0, 1)
INSERT INTO truck VALUES ('AC12345', 4, 1, 0, 1)
INSERT INTO truck VALUES ('AB54321', 6, 1, 0, 2)
INSERT INTO truck VALUES ('AB65478', 8, 1, 0, 3)
INSERT INTO truck VALUES ('AB65477', 8, 1, 1, 3)

-- --
INSERT INTO driver VALUES (1, 120, 'Petrov',  'Vasily',    1 ,  2, 1)
INSERT INTO driver VALUES (2, 128, 'Zabrodin',    'Dmitriy', 1,   1, 1)
INSERT INTO driver VALUES (3, 121, 'Bunin',    'Ivan',     NULL,   0, 1)
INSERT INTO driver VALUES (4, 122, 'Kinchev',    'Kostya',     2,   1, 3)
INSERT INTO driver VALUES (5, 123, 'Tcoy',    'Victor',     NULL,   0, 1)
INSERT INTO driver VALUES (6, 124, 'G',           'B',     NULL,   0, 1)

-- password= 1216985755
-- secret= -906277200
INSERT INTO user VALUES (1, '1', 1216985755,  0)
INSERT INTO user VALUES (2, '2', 1216985755,  0)
INSERT INTO user VALUES (3, '3', 1216985755,  0)
INSERT INTO user VALUES (4, '4', 1216985755,  0)
INSERT INTO user VALUES (5, '5', 1216985755,  0)
INSERT INTO user VALUES (6, '6', 1216985755,  0)
INSERT INTO user VALUES (1000, 'manager', -906277200,  1)


-- --
-- order city bag; isDone status; truck type visitNumber
-- isDone check is done for concrete point:loan unload
-- status order status
-- type - load/unload
INSERT INTO orderroute VALUES (1,1,1,  0,42,  'AB12345', 0, 1) -- ��������� ��� � ���
INSERT INTO orderroute VALUES (1,2,1,  0,42,  'AB12345', 1, 2) -- ���������� ������

INSERT INTO orderroute VALUES (2,2,2,  1,42,  'AB65478', 0, 1) -- ��������� ��� � ������
INSERT INTO orderroute VALUES (2,3,2,  0,42,  'AB65478', 1, 2) -- ���������� �������


INSERT INTO orderroute VALUES (3,1,3,  1,42,  'AB54321', 0, 1) -- ��������� ����� � ���
INSERT INTO orderroute VALUES (3,2,3,  1,42,  'AB54321', 1, 2) -- ���������� � ���
