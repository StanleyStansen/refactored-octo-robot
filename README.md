# refactored-octo-robot

## Infos zur Datenbank
Falls ihr Zugriff auf MySQL braucht, so könnt ihr wie folgt eine eigene Datenbank einrichten:
1.) Konsole öffnen und dort als root anmelden mit dem Befehl: "mysql -uroot -p" und dem Passwort "wertwert".
2.) Datenbank erstellen: "CREATE DATABAES discountsystem;"
3.) Datenbank auswählen: "USE discountsystem;
4.) Tabllen erstellen:

CREATE TABLE Customers
(
CustomerId int,
Forename varchar(200),
Lastname varchar(200),
Birthday date,
Street varchar(200),
HouseNumber INT,
Postcode varchar(5),
City varchar(200),
PRIMARY KEY (CustomerId)
);


CREATE TABLE Orders
(
OrderId int,
Turnover float,
CustomerId int,
PRIMARY KEY (OrderId), 
FOREIGN KEY (CustomerId) REFERENCES Customers(CustomerId)
);

5.) Überprüfen mit "show tables;"

Ergebnis sollte so aussehen:

+--------------------------+
| Tables_in_discountsystem |
+--------------------------+
| Customers                |
| Orders                   |
+--------------------------+

6.) Überprüfen der Spalten mit "show columns from Customers;" bzw. "show columns from Orders;"

mysql> show columns from Customers;
+-------------+--------------+------+-----+---------+-------+
| Field       | Type         | Null | Key | Default | Extra |
+-------------+--------------+------+-----+---------+-------+
| CustomerId  | int(11)      | NO   | PRI | 0       |       |
| Forename    | varchar(200) | YES  |     | NULL    |       |
| Lastname    | varchar(200) | YES  |     | NULL    |       |
| Birthday    | date         | YES  |     | NULL    |       |
| Street      | varchar(200) | YES  |     | NULL    |       |
| HouseNumber | int(11)      | YES  |     | NULL    |       |
| Postcode    | varchar(5)   | YES  |     | NULL    |       |
| City        | varchar(200) | YES  |     | NULL    |       |
+-------------+--------------+------+-----+---------+-------+
8 rows in set (0.00 sec)

mysql> show columns from Orders;
+------------+---------+------+-----+---------+-------+
| Field      | Type    | Null | Key | Default | Extra |
+------------+---------+------+-----+---------+-------+
| OrderId    | int(11) | NO   | PRI | 0       |       |
| Turnover   | float   | YES  |     | NULL    |       |
| CustomerId | int(11) | YES  | MUL | NULL    |       |
+------------+---------+------+-----+---------+-------+
3 rows in set (0.00 sec)


## Infos zur VM
Passwort: wertwert