DROP TABLE Accounts;
DROP TABLE Users;
DROP TABLE Transactions;
DROP TABLE Persons;

CREATE TABLE Persons (
id int PRIMARY KEY,
firstName varchar(30) NOT NULL,
lastName varchar(30) NOT NULL,
email varchar(50) NOT NULL,
street varchar(50) NOT NULL,
zip int NOT NULL,
city varchar(30) NOT NULL,
phonenumber int NOT NULL
);

CREATE TABLE Users (
id int PRIMARY KEY REFERENCES Persons,
password varchar(30) NOT NULL,
title varchar(30) NOT NULL
);

CREATE TABLE Transactions (
transaction_date date,
transaction_number int PRIMARY KEY,
from_Account int NOT NULL,
to_Account int NOT NULL,
amount int NOT NULL,
to_Amount decimal(10,2) NOT NULL,
from_Amount decimal(10,2) NOT NULL,
comment varchar(30)
);

CREATE TABLE Accounts (
accountType varchar(30) NOT NULL,
-- transaction_number int NOT NULL REFERENCES Transactions,
accountNumber int PRIMARY KEY,
interest double NOT NULL,
balance decimal(10,2) NOT NULL,
created date NOT NULL
);

-- CREATE TABLE Person_Accounts (
-- id VARCHAR(50) REFERENCES Persons (id),
-- accountNumber VARCHAR(50) REFERENCES Accounts (accountNumber),
-- PRIMARY KEY (id, accountNumber)
-- );

