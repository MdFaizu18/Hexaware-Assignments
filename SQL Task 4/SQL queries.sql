USE hmbank;

-- 1.To Retrieve the customer(s) with the highest account balance.
SELECT c.customer_id,c.first_name,c.last_name,a.balance,a.account_type
FROM customers c
JOIN accounts a ON c.customer_id = a.customer_id
WHERE  c.customer_id = (
          SELECT a.customer_id 
          FROM accounts a
          ORDER BY a.balance DESC
          LIMIT 1
);

-- 2.To Calculate the average account balance for customers who have more than one account.
SELECT c.customer_id,c.first_name,c.last_name,ROUND(AVG(a.balance),2) AS avg_balance
FROM customers c
JOIN accounts a ON c.customer_id = a.customer_id
WHERE c.customer_id IN (
          SELECT a.customer_id
          FROM accounts a
          GROUP BY a.customer_id
          HAVING COUNT(a.account_id)>1
)
GROUP BY c.customer_id;

-- 3.To Retrieve accounts with transactions whose amounts exceed the average transaction amount.
SELECT c.customer_id, c.first_name, c.last_name, t.account_id, t.transaction_id, t.amount
FROM customers c
JOIN accounts a ON c.customer_id = a.customer_id
JOIN transactions t ON a.account_id = t.account_id
WHERE t.amount > (
    SELECT AVG(amount) FROM transactions
);

-- 4.To Identify customers who have no recorded transactions.
SELECT c.customer_id, CONCAT(c.first_name, ' ', c.last_name) AS full_name, c.email, a.account_id
FROM customers c
JOIN accounts a ON c.customer_id = a.customer_id
WHERE a.account_id NOT IN (
    SELECT DISTINCT account_id FROM transactions
);

-- 5.To Calculate the total balance of accounts with no recorded transactions. 
SELECT SUM(balance) AS total_balance
FROM accounts
WHERE account_id NOT IN (
    SELECT DISTINCT account_id FROM transactions
);

-- 6.To Retrieve transactions for accounts with the lowest balance. 
SELECT t.*,a.account_type
FROM transactions t
JOIN accounts a ON t.account_id = a.account_id
JOIN (
    SELECT account_id
    FROM accounts
    ORDER BY balance ASC
    LIMIT 3
) AS low_bal_acc
ON t.account_id = low_bal_acc.account_id;

-- 7.To Identify customers who have accounts of multiple types. 
SELECT
      c.customer_id,
	  CONCAT(c.first_name,' ',c.last_name) AS full_name,
      c.email,
      a.account_id,
      a.account_type
FROM customers c
JOIN accounts a ON c.customer_id = a.customer_id
WHERE c.customer_id IN (
		SELECT customer_id
        FROM accounts
        GROUP BY customer_id
        HAVING COUNT(DISTINCT account_type)>1
);

-- 8.To Calculate the percentage of each account type out of the total number of accounts. 
SELECT 
     account_type,
     COUNT(*) AS account_count,
     ROUND((COUNT(*) * 100.0 / (SELECT COUNT(*) FROM accounts)), 2) AS percentage
FROM accounts
GROUP BY account_type;

-- 9.To Retrieve all transactions for a customer with a given customer_id. 
SELECT c.first_name, t.*
FROM transactions t
JOIN accounts a ON t.account_id = a.account_id
JOIN customers c ON a.customer_id = c.customer_id
WHERE t.account_id IN (
    SELECT account_id 
    FROM accounts 
    WHERE customer_id = 3
);

-- 10. To Calculate the total balance for each account type, including a subquery within the SELECT clause.
SELECT 
    account_type,
    (SELECT SUM(balance) 
     FROM accounts b 
     WHERE b.account_type = a.account_type) AS total_balance
FROM accounts a
GROUP BY account_type;
