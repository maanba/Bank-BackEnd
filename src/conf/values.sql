INSERT INTO Persons  VALUES ((NEXT VALUE FOR person_id_sequence), 'Mette', 'Imba', 'metteimba@mail.com', 'Metteimbavej 6', 2750, 'Ballerup', 12345678);
INSERT INTO Persons  VALUES ((NEXT VALUE FOR person_id_sequence), 'Daniel', 'Thomsen', 'thomsen@mail.com', 'Thomsenvej 27', 2800, 'Kongens Lyngby', 87654321);
INSERT INTO Persons  VALUES ((NEXT VALUE FOR person_id_sequence), 'Mark', 'Fappington', 'maanba@mail.com', 'Fappingvej 6', 2600, 'Glostrup', 88888888);
INSERT INTO Persons  VALUES ((NEXT VALUE FOR person_id_sequence), 'Svans', 'Svanesen', 'mads@mail.com', 'Madsensvej 27', 2620, 'Albertslund', 77777777);

INSERT INTO Roles VALUES ('Customer');
INSERT INTO Roles VALUES ('BankTeller');
INSERT INTO Roles VALUES ('Manager');

INSERT INTO Users VALUES ('Imba1000Customer', '0b14d501a594442a01c6859541bcb3e8164d183d32937b851835442f69d5c94e', 'Customer');
INSERT INTO Users VALUES ('Thomsen1001Customer', '6cf615d5bcaac778352a8f1f3360d23f02f34ec182e259897fd6ce485d7870d4', 'Customer');
INSERT INTO Users VALUES ('Fappington1002BankTeller', '5906ac361a137e2d286465cd6588ebb5ac3f5ae955001100bc41577c3d751764', 'BankTeller');
INSERT INTO Users VALUES ('Svanesen1003Customer', 'b97873a40f73abedd8d685a7cd5e5f85e4a9cfb83eac26886640a0813850122b', 'Customer');
INSERT INTO Users VALUES ('Svanesen1003BankTeller', 'b97873a40f73abedd8d685a7cd5e5f85e4a9cfb83eac26886640a0813850122b', 'BankTeller');
INSERT INTO Users VALUES ('Svanesen1003Manager', 'b97873a40f73abedd8d685a7cd5e5f85e4a9cfb83eac26886640a0813850122b', 'Manager');

INSERT INTO Person_Users VALUES ('Imba1000Customer',1000);
INSERT INTO Person_Users VALUES ('Thomsen1001Customer',1001);
INSERT INTO Person_Users VALUES ('Fappington1002BankTeller',1002);
INSERT INTO Person_Users VALUES ('Svanesen1003Customer',1003);
INSERT INTO Person_Users VALUES ('Svanesen1003BankTeller',1003);
INSERT INTO Person_Users VALUES ('Svanesen1003Manager',1003);

INSERT INTO Account_Types VALUES ('CheckingAccount');
INSERT INTO Account_Types VALUES ('MoneyMarketAccount');
INSERT INTO Account_Types VALUES ('TimeDepositAccount');

INSERT INTO Accounts VALUES (1000, 'CheckingAccount', (NEXT VALUE FOR account_number_sequence), 1.0, 50054.3, date('1994-07-10'));
INSERT INTO Accounts VALUES (1001, 'CheckingAccount', (NEXT VALUE FOR account_number_sequence), 1.5, 2700.58, date('1998-07-10'));
INSERT INTO Accounts VALUES (1002, 'CheckingAccount', (NEXT VALUE FOR account_number_sequence), 1.0, 27000.00, date('2000-07-10'));
INSERT INTO Accounts VALUES (1003, 'CheckingAccount', (NEXT VALUE FOR account_number_sequence), 1.5, 9000.00, date('2001-07-10'));

INSERT INTO Transactions VALUES (date('1995-12-25'), (NEXT VALUE FOR transaction_id_sequence), 5000, 5001, 500, 'Til Daniel Thomsen');
INSERT INTO Transactions VALUES (date('2000-04-06'), (NEXT VALUE FOR transaction_id_sequence), 5001, 5000, 250, 'Til Mette imba');
INSERT INTO Transactions VALUES (date('1995-12-25'), (NEXT VALUE FOR transaction_id_sequence), 5002, 5003, 970, 'Til Mark Fappington');
INSERT INTO Transactions VALUES (date('2000-04-06'), (NEXT VALUE FOR transaction_id_sequence), 5003, 5002, 300, 'Til Svans Svanesen');

