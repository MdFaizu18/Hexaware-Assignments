-- To create the database for PayXpert
CREATE DATABASE PayXpert;
USE PayXpert;

-- To create Employee Table
CREATE TABLE employee (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    date_of_birth DATE NOT NULL,
    gender ENUM('MALE', 'FEMALE', 'OTHERS') NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    phone_number VARCHAR(15) UNIQUE NOT NULL,
    address VARCHAR(150),
    job_position VARCHAR(100) NOT NULL,
    joining_date DATE NOT NULL,
    termination_date DATE
);
-- To create PayRoll Table
CREATE TABLE payroll (
    payroll_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    pay_period_start_date DATE NOT NULL,
    pay_period_end_date DATE NOT NULL,
    base_salary DECIMAL(20,2) NOT NULL DEFAULT 0.00,
    overtime_pay DECIMAL(20,2) NOT NULL DEFAULT 0.00,
    deductions DECIMAL(20,2) NOT NULL DEFAULT 0.00,
    net_salary DECIMAL(20,2) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
);
-- To create Taz Table
CREATE TABLE tax (
    tax_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    tax_year INT NOT NULL,
    taxable_income DECIMAL(20,0) NOT NULL DEFAULT 0.00,
    tax_amount DECIMAL(20,2) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
);
-- To create Financial_record Table
CREATE TABLE financial_record (
    record_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    record_date DATE NOT NULL,
    description VARCHAR(255) NOT NULL,
    amount DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    record_type ENUM('INCOME', 'EXPENSE', 'TAX_PAYMENT') NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
);

-- Ensuring Tables present
SHOW TABLES;
DESC FINANCIAL_RECORD;


-- inserting sample data into table 
INSERT INTO employee (first_name, last_name, date_of_birth, gender, email, phone_number, address, job_position, joining_date, termination_date)
VALUES
('Md', 'Faizu', '1990-05-12', 'MALE', 'mdfaizu@gmail.com', '9876543210', '123 Main St, Salem', 'Software Engineer', '2019-06-01', NULL),
('Monish', 'Coumar', '1992-08-21', 'MALE', 'monishcoumar@gmail.com', '8765432109', '456 Elm St, Pondycherry', 'HR Manager', '2018-03-15', NULL),
('Parth', 'sacpal', '1985-12-30', 'MALE', 'parthsacpal@gmail.com', '7654321098', '789 Oak St, Pune', 'Finance Analyst', '2020-07-10', NULL),
('Monisha', 'Das', '1995-04-05', 'FEMALE', 'monishadas@gmail.com', '6543210987', '101 Pine St, Theni', 'Marketing Executive', '2021-01-25', NULL);

INSERT INTO payroll (employee_id, pay_period_start_date, pay_period_end_date, base_salary, overtime_pay, deductions, net_salary)
VALUES
(1, '2024-03-01', '2024-03-31', 5000.00, 200.00, 300.00, 4900.00),
(2, '2024-03-01', '2024-03-31', 6000.00, 150.00, 400.00, 5750.00),
(3, '2024-03-01', '2024-03-31', 5500.00, 100.00, 350.00, 5250.00),
(4, '2024-03-01', '2024-03-31', 4500.00, 250.00, 250.00, 4500.00);

INSERT INTO tax (employee_id, tax_year, taxable_income, tax_amount)
VALUES
(1, 2024, 60000, 12000.00),
(2, 2024, 72000, 15000.00),
(3, 2024, 66000, 13200.00),
(4, 2024, 54000, 10800.00);

INSERT INTO financial_record (employee_id, record_date, description, amount, record_type)
VALUES
(1, '2024-03-15', 'Performance Bonus', 500.00, 'INCOME'),
(2, '2024-03-18', 'Travel Reimbursement', 300.00, 'INCOME'),
(3, '2024-03-20', 'Health Insurance Deduction', -200.00, 'EXPENSE'),
(4, '2024-03-22', 'Annual Tax Payment', -5000.00, 'TAX_PAYMENT');


