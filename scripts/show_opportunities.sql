connect 'jdbc:derby://localhost:1527/fpx;create=true';
SHOW TABLES;
DESCRIBE OPPORTUNITYTABLE;

SELECT CAST(ID AS VARCHAR(18)) AS ID,CAST(OWNERID AS VARCHAR(18)),NAME FROM OPPORTUNITYTABLE;



