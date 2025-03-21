CREATE DATABASE PayXpert;
USE PayXpert;

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
CREATE TABLE tax (
    tax_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    tax_year INT NOT NULL,
    taxable_income DECIMAL(20,0) NOT NULL DEFAULT 0.00,
    tax_amount DECIMAL(20,2) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
);
CREATE TABLE financial_record (
    record_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    record_date DATE NOT NULL,
    description VARCHAR(255) NOT NULL,
    amount DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    record_type ENUM('INCOME', 'EXPENSE', 'TAX_PAYMENT') NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
);

SHOW TABLES;
DROP TABLE financial_record;
DROP TABLE tax;
DROP TABLE payroll;
DROP TABLE employee;
DESC FINANCIAL_RECORD;

