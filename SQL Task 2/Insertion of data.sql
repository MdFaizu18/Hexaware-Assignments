USE hmbank;

SELECT * FROM customers;
INSERT INTO customers (first_name,last_name,DOB,email,phone_number,address) VALUES
('Monish', 'Coumar', '2003-05-15', 'monishcoumar@gmail.com', '9876543210', '123 Main Road, Pondicherry'),
('Rishaba', 'Rajan', '2004-08-22', 'rishabarajan@gmail.com', '9123456789', '456 North St, Chennai'),
('Deepak', 'Yuvaraj', '2005-11-10', 'deepaky@gmail.com', '9876123450', '789 Junction, Salem'),
('Monisha', 'M', '2003-08-30', 'monisham@gmail.com', '8765432190', '321 Old Bustand, Theni'),
('John', 'Wick', '1995-03-25', 'johnwick@gmail.com', '9654321789', '567 TNagar, Chennai'),
('Md', 'Faizu', '2002-09-17', 'mdfaizu@gmail.com', '8543219876', '678 Statue, Kannayakumari'),
('Virat', 'Kholi', '1980-09-05', 'viratkholi@gmail.com', '7432198765', '789 Guindy, Mumbai'),
('Chirs', 'Evans', '1991-12-18', 'chrisevans@gmail.com', '6321987654', '890 Gandhi Road, Pune'),
('Allen', 'Captain', '1987-04-27', 'allenvc@gmail.com', '5219876543', '901 Bangok, Thailand'),
('Raja', 'Teachalla', '1994-10-09', 'rajateachalla@gmail.com', '4198765432', '234 Valehoris, Wakanda');

SELECT * FROM accounts;
INSERT INTO accounts (customer_id, account_type,balance) VALUES
(1, 'SAVINGS', 5000.00),
(2, 'CURRENT', 12000.50),
(3, 'ZERO_BALANCE', 0.00),
(4, 'SAVINGS', 7500.75),
(5, 'CURRENT', 20000.00),
(6, 'SAVINGS', 4300.60),
(7, 'CURRENT', 15000.20),
(8, 'ZERO_BALANCE', 0.00),
(9, 'SAVINGS', 9800.90),
(10, 'CURRENT', 17500.30);

SELECT * FROM transactions;
INSERT INTO transactions (account_id,transaction_type,amount) VALUES
(1, 'DEPOSIT', 1000.00),
(1, 'WITHDRAWAL', 500.00),
(3, 'DEPOSIT', 3000.00),
(4, 'TRANSFER', 2500.75),
(3, 'DEPOSIT', 4000.50),
(6, 'WITHDRAWAL', 200.00),
(3, 'TRANSFER', 1500.20),
(1, 'DEPOSIT', 3500.00),
(9, 'WITHDRAWAL', 600.90),
(1, 'TRANSFER', 2700.30);