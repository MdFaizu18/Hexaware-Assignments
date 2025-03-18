USE hmbank;

-- 1.To Find the average account balance for all customers. 
SELECT ROUND(AVG(balance),2) AS avg_balance
FROM accounts;

-- 2.To Retrieve the top 10 highest account balances.
SELECT *
FROM accounts
ORDER BY balance DESC
LIMIT 10;

-- 3.To Calculate Total Deposits for All Customers in specific date.
SELECT SUM(amount) AS total_deposits 
FROM transactions 
WHERE transaction_type = 'DEPOSIT' 
AND DATE(transaction_date) = '2025-03-17';

-- 4.To Find the Oldest and Newest Customers.
SELECT 
    (SELECT customer_id FROM customers ORDER BY customer_id ASC LIMIT 1) AS oldest_customer_id, 
    (SELECT first_name FROM customers ORDER BY customer_id ASC LIMIT 1) AS oldest_customer_name, 
    (SELECT customer_id FROM customers ORDER BY customer_id DESC LIMIT 1) AS newest_customer_id, 
    (SELECT first_name FROM customers ORDER BY customer_id DESC LIMIT 1) AS newest_customer_name;

-- 5.To Retrieve transaction details along with the account type.
SELECT t.* , a.account_type
FROM transactions t
JOIN accounts a ON t.account_id=a.account_id;

-- 6.To Get a list of customers along with their account details.
SELECT c.*, a.account_id,a.account_type,a.balance
FROM customers c
JOIN accounts a ON c.customer_id = a.customer_id;

-- 7.To Retrieve transaction details along with customer information for a specific account. 
SELECT c.*,t.*
FROM transactions t
JOIN accounts a ON t.account_id = a.account_id
JOIN customers c ON a.customer_id = c.customer_id
WHERE c.account_id = 1;

-- 8.To Identify customers who have more than one account.
SELECT c.customer_id,c.first_name,c.last_name,COUNT(a.account_id) AS account_count
FROM customers c
JOIN accounts a ON c.customer_id = a.customer_id
GROUP BY c.customer_id
HAVING COUNT(a.account_id) > 1; 

-- 9.to Calculate the difference in transaction amounts between deposits and withdrawals.  
SELECT 
    SUM(CASE WHEN t.transaction_type = 'DEPOSIT' THEN t.amount ELSE 0 END) AS total_deposit,
	SUM(CASE WHEN t.transaction_type = 'WITHDRAWAL' THEN t.amount ELSE 0 END) AS total_withdrawal,
    SUM(CASE WHEN t.transaction_type = 'DEPOSIT' THEN t.amount ELSE 0 END) 
    - 
    SUM(CASE WHEN t.transaction_type = 'WITHDRAWAL' THEN t.amount ELSE 0 END) 
    AS net_transaction_diff
FROM transactions t;

-- 10.to Calculate the average daily balance for each account over a specified period.

-- 11.To Calculate the total balance for each account type. 
SELECT account_type, SUM(balance) AS total_balance
FROM accounts
GROUP BY account_type;

-- 12.To Identify accounts with the highest number of transactions order by descending order.
SELECT account_id, COUNT(transaction_id) AS no_of_transactions
FROM transactions
GROUP BY account_id
ORDER BY COUNT(transaction_id) DESC;

-- 13.To  List customers with high aggregate account balances, along with their account types. 
SELECT 
    c.customer_id, 
    c.first_name, 
    c.last_name, 
    a.account_type,
    a.balance
FROM customers c
JOIN accounts a ON c.customer_id = a.customer_id
WHERE a.balance > 10000; 

-- 14.To Identify and list duplicate transactions based on transaction amount, date, and account. 
SELECT 
    account_id, 
    transaction_date, 
    amount, 
    COUNT(*) AS duplicate_count
FROM transactions
GROUP BY account_id, transaction_date, amount
HAVING COUNT(*) > 1;



