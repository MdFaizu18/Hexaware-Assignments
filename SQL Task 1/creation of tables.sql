-- creation of database
CREATE DATABASE HMBank;
USE HMBank;

-- to create the customers table
CREATE TABLE customers(
      customer_id INT PRIMARY KEY AUTO_INCREMENT,
      first_name VARCHAR(50),
      last_name VARCHAR(50),
      DOB DATE NOT NULL,
      email VARCHAR(100) NOT NULL UNIQUE,
      phone_number VARCHAR(15) NOT NULL UNIQUE,
      address VARCHAR(150)
);

-- to create the accounts table
CREATE TABLE accounts(
      account_id INT PRIMARY KEY AUTO_INCREMENT,
      customer_id INT,
      account_type VARCHAR(20) CHECK (account_type IN ('SAVINGS', 'CURRENT', 'ZERO_BALANCE')),
      balance DECIMAL(10,2) NOT NULL DEFAULT 0.00,
      FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
);	

-- to create the transactions table
CREATE TABLE transactions(
      transaction_id INT PRIMARY KEY AUTO_INCREMENT,
	  account_id INT,
	  transaction_type VARCHAR(20) CHECK (transaction_type IN ('DEPOSIT', 'WITHDRAWAL', 'TRANSFER')),
	  amount DECIMAL(10,2) NOT NULL,
      transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (account_id) REFERENCES accounts(account_id) ON DELETE CASCADE
);

-- to show all tables in database
SHOW TABLES;
