DROP TABLE Transactions;
DROP TABLE Accounts;
DROP TABLE Person_Users;
DROP TABLE Users;
DROP TABLE Persons;
DROP TABLE Roles;
DROP SEQUENCE person_id_sequence RESTRICT;
DROP SEQUENCE transaction_id_sequence RESTRICT;
DROP SEQUENCE account_number_sequence RESTRICT;

CREATE SEQUENCE person_id_sequence START WITH 1000 INCREMENT BY 1;
CREATE SEQUENCE transaction_id_sequence START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE account_number_sequence START WITH 5000 INCREMENT BY 1;

CREATE TABLE Persons (
person_id int PRIMARY KEY,
first_name varchar(30) NOT NULL,
last_name varchar(30) NOT NULL,
email varchar(50) NOT NULL,
street varchar(50) NOT NULL,
zip int NOT NULL,
city varchar(30) NOT NULL,
phonenumber int NOT NULL
);

CREATE TABLE Roles (
title varchar(30) PRIMARY KEY
);

CREATE TABLE Users (
username varchar(30) PRIMARY KEY NOT NULL,
password varchar(30) NOT NULL,
title varchar(30) NOT NULL REFERENCES Roles (title)
);

CREATE TABLE Person_Users (
username varchar(30) NOT NULL REFERENCES Users(username),
person_id int NOT NULL REFERENCES Persons(person_id),
PRIMARY KEY (username, person_id)
);

CREATE TABLE Accounts (
person_id int NOT NULL REFERENCES Persons(person_id),
account_type varchar(30) NOT NULL,
account_number int PRIMARY KEY NOT NULL,
interest double NOT NULL,
balance decimal(10,2) NOT NULL,
created date NOT NULL
);

CREATE TABLE Transactions (
transaction_date date,
transaction_number int PRIMARY KEY,
from_account_number int references Accounts(account_number),
to_account_number int references Accounts(account_number),
amount double NOT NULL,
comment varchar(30)
);