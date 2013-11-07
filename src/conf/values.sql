INSERT INTO Persons  VALUES (1, 'Mette', 'Imba', 'metteimba@mail.com', 'Metteimbavej 6', 2750, 'Ballerup', 12345678);
INSERT INTO Persons  VALUES (2, 'Daniel', 'Thomsen', 'thomsen@mail.com', 'Thomsenvej 27', 2800, 'Kongens Lyngby', 87654321);

INSERT INTO Users VALUES (1, 'password1', 'Customer');
INSERT INTO Users VALUES (2, 'password2', 'Customer');

INSERT INTO Transactions VALUES (date('1995-12-25'), 1000, 2000, 3000, 500, 500, -500, 'Fra Metteimba');
INSERT INTO Transactions VALUES (date('2000-04-06'), 1001, 3000, 2000, 250, 250, -250, 'Til Metteimba');

INSERT INTO Accounts VALUES ('CheckingAccount', 2000, 1.0, 50054.3, date('1994-07-10'));
INSERT INTO Accounts VALUES ('CheckingAccount', 3000, 1.5, 2700.58, date('1998-07-10'));

SELECT *
FROM Persons;
SELECT *
FROM Users;
SELECT *
FROM Transactions;
SELECT *
FROM Accounts;