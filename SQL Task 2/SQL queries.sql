USE hmbank;

-- 1.To retrieve the name, account type and email of all customers.
SELECT
    CONCAT(c.first_name, ' ',c.last_name) AS full_name,
    a.account_type,
    c.email
FROM customers c 
JOIN accounts a ON a.customer_id=c.customer_id;


-- 2.To list all transaction corresponding customer.
SELECT
	CONCAT(c.first_name, ' ',c.last_name) AS full_name,
    c.email, c.phone_number,
    t.transaction_id, t.amount, t.transaction_type,t.transaction_date
FROM customers c
JOIN accounts a ON a.customer_id=c.customer_id
JOIN transactions t ON t.account_id = a.account_id; 


-- 3.To increase the balance of a specific account by a certain amount.
UPDATE accounts
SET balance = balance+1500
WHERE account_id = 5;


-- 4.To Combine first and last names of customers as a full_name. 
SELECT 
     CONCAT(c.first_name, ' ' , c.last_name) AS full_name
FROM customers c;


-- 5.To remove accounts with a balance of zero where the account type is savings
SET SQL_SAFE_UPDATES = 0;
DELETE FROM accounts
WHERE balance=0.00 AND account_type= 'SAVINGS';


-- 6.To Find customers living in a specific city.
SELECT * 
FROM customers 
WHERE address LIKE '%Chennai%';


-- 7.To Get the account balance for a specific account. 
SELECT account_id,balance
FROM accounts
WHERE account_id = 5;
     
     
-- 8.To List all current accounts with a balance greater than $1,000.
SELECT * 
FROM accounts
WHERE account_type='CURRENT' AND balance > 1000;


-- 9.To Retrieve all transactions for a specific account. 
SELECT * 
FROM transactions
WHERE account_id = 1;


-- 10.To Calculate the interest accrued on savings accounts based on a given interest rate.
SELECT 
    account_id, 
    balance, 
    ROUND(balance * 0.2, 2) AS interest_amount
FROM accounts
WHERE account_type = 'SAVINGS';


-- 11.To Identify accounts where the balance is less than a specified overdraft limit.
SELECT * FROM accounts 
WHERE balance < -500;


-- 12.To Find customers not living in a specific city. 
SELECT * FROM customers
WHERE address NOT LIKE '%Chennai';

   
     