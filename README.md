# refactored-octo-robot

## Infos zur Datenbank
Falls ihr Zugriff auf MySQL braucht, so könnt ihr wie folgt eine eigene Datenbank einrichten:
<br/><br/>
1.) Konsole öffnen und dort als root anmelden mit dem Befehl: "mysql -uroot -p" und dem Passwort "wertwert".<br/>
2.) Datenbank erstellen: "CREATE DATABASE discountsystem;"<br/>
3.) Datenbank auswählen: "USE discountsystem;<br/>
4.) Tabllen erstellen:<br/>
<br/><br/>
CREATE TABLE Customers<br/>
(<br/>
CustomerId int NOT NULL AUTO_INCREMENT,<br/>
Forename varchar(200),<br/>
Lastname varchar(200),<br/>
Birthday date,<br/>
Street varchar(200),<br/>
HouseNumber INT,<br/>
Postcode varchar(5),<br/>
City varchar(200),<br/>
PRIMARY KEY (CustomerId)<br/>
);
<br/><br/>
CREATE TABLE Orders<br/>
(<br/>
OrderId int NOT NULL AUTO_INCREMENT,<br/>
Turnover float,<br/>
CustomerId int,<br/>
PRIMARY KEY (OrderId), <br/>
FOREIGN KEY (CustomerId) REFERENCES Customers(CustomerId)<br/>
);<br/>
<br/><br/>
5.) Überprüfen mit "show tables;"
<br/><br/>
Ergebnis sollte so aussehen:
<br/><br/>
+--------------------------+<br/>
| Tables_in_discountsystem |<br/>
+--------------------------+<br/>
| Customers                |<br/>
| Orders                   |<br/>
+--------------------------+<br/>
<br/><br/>
6.) Überprüfen der Spalten mit "show columns from Customers;" bzw. "show columns from Orders;"
<br/><br/>
mysql> show columns from Customers;<br/>
+-------------+--------------+------+-----+---------+-------+<br/>
| Field       | Type         | Null | Key | Default | Extra |<br/>
+-------------+--------------+------+-----+---------+-------+<br/>
| CustomerId  | int(11)      | NO   | PRI | 0       |       |<br/>
| Forename    | varchar(200) | YES  |     | NULL    |       |<br/>
| Lastname    | varchar(200) | YES  |     | NULL    |       |<br/>
| Birthday    | date         | YES  |     | NULL    |       |<br/>
| Street      | varchar(200) | YES  |     | NULL    |       |<br/>
| HouseNumber | int(11)      | YES  |     | NULL    |       |<br/>
| Postcode    | varchar(5)   | YES  |     | NULL    |       |<br/>
| City        | varchar(200) | YES  |     | NULL    |       |<br/>
+-------------+--------------+------+-----+---------+-------+<br/>
<br/><br/>
mysql> show columns from Orders;<br/>
+------------+---------+------+-----+---------+-------+<br/>
| Field      | Type    | Null | Key | Default | Extra |<br/>
+------------+---------+------+-----+---------+-------+<br/>
| OrderId    | int(11) | NO   | PRI | 0       |       |<br/>
| Turnover   | float   | YES  |     | NULL    |       |<br/>
| CustomerId | int(11) | YES  | MUL | NULL    |       |<br/>
+------------+---------+------+-----+---------+-------+<br/>
<br/><br/>
## Infos zur VM
Passwort: wertwert

## Hibernate funktioniert nicht?
- Configure Build Path<br>
- Add External JARs<br>
- /home/igt/Eclipse Components/hibernate-release-5.1.0.Final/lib/required --> Alle Files einbinden..