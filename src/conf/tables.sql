DROP TABLE Accounts;
DROP TABLE Person_Users;
DROP TABLE Users;
DROP TABLE Transactions;
DROP TABLE Persons;

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

CREATE TABLE Users (
username int PRIMARY KEY NOT NULL,
password varchar(30) NOT NULL,
title varchar(30) NOT NULL
);

CREATE TABLE Person_Users (
username int not null references Users(username),
person_id int not null,
PRIMARY KEY (username, Person_id),
CONSTRAINT FK_Person_id FOREIGN KEY (person_id) references Persons(person_id)
);

CREATE TABLE Transactions (
transaction_date date,
transaction_number int PRIMARY KEY,
from_account_number int NOT NULL references Account(account_number),
to_account_number int NOT NULL references Account(account_number),
amount int NOT NULL,
to_amount decimal(10,2) NOT NULL,
from_amount decimal(10,2) NOT NULL,
comment varchar(30)
);

CREATE TABLE Accounts (
person_id int NOT NULL REFERENCES Persons(person_id,
account_type varchar(30) NOT NULL,
account_number int PRIMARY KEY NOT NULL,
interest double NOT NULL,
balance decimal(10,2) NOT NULL,
created date NOT NULL
);

