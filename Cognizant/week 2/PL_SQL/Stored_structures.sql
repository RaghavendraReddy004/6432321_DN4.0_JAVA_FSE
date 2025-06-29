create database COGNIZAN;
use COGNIZAN;

CREATE TABLE customers (
    customer_id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    balance DECIMAL(15,2),
    is_vip CHAR(1) DEFAULT 'N'
);


CREATE TABLE accounts (
    account_id INT PRIMARY KEY,
    customer_id INT,
    account_type VARCHAR(20), -- 'SAVINGS', 'CHECKING', 'LOAN'
    balance DECIMAL(15,2),
    interest_rate DECIMAL(5,2),
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);



CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(50),
    salary DECIMAL(15,2),
    hire_date DATE
);

CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    from_account_id INT,
    to_account_id INT,
    amount DECIMAL(15,2),
    transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    transaction_type ENUM('TRANSFER', 'DEPOSIT', 'WITHDRAWAL'),
    description VARCHAR(200)
);


*/
-- Process monthly interest for all savings accounts
DELIMITER $$

CREATE PROCEDURE ProcessMonthlyInterest()
BEGIN
    DECLARE done INT DEFAULT 0;
    DECLARE v_account_id INT;
    DECLARE v_customer_id INT;
    DECLARE v_current_balance DECIMAL(15,2);
    DECLARE v_interest_rate DECIMAL(5,2);
    DECLARE v_interest_amount DECIMAL(15,2);
    DECLARE v_new_balance DECIMAL(15,2);
    DECLARE v_accounts_processed INT DEFAULT 0;
    DECLARE v_total_interest_paid DECIMAL(15,2) DEFAULT 0.0;
    DECLARE v_monthly_interest_rate DECIMAL(5,2) DEFAULT 0.01;

    DECLARE c_savings_accounts CURSOR FOR
        SELECT account_id, customer_id, balance, COALESCE(interest_rate, v_monthly_interest_rate)
        FROM accounts
        WHERE account_type = 'SAVINGS' AND balance > 0;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN c_savings_accounts;

    read_loop: LOOP
        FETCH c_savings_accounts INTO v_account_id, v_customer_id, v_current_balance, v_interest_rate;

        IF done THEN
            LEAVE read_loop;
        END IF;

        SET v_interest_amount = v_current_balance * v_interest_rate;
        SET v_new_balance = v_current_balance + v_interest_amount;

        UPDATE accounts 
        SET balance = v_new_balance
        WHERE account_id = v_account_id;

        INSERT INTO transactions (
            to_account_id,
            amount,
            transaction_type,
            description,
            transaction_date
        ) VALUES (
            v_account_id,
            v_interest_amount,
            'DEPOSIT',
            CONCAT('Monthly interest - ', DATE_FORMAT(NOW(), '%b-%Y')),
            NOW()
        );

        SET v_accounts_processed = v_accounts_processed + 1;
        SET v_total_interest_paid = v_total_interest_paid + v_interest_amount;
    END LOOP;

    CLOSE c_savings_accounts;

    -- Use SELECT instead of DBMS_OUTPUT
    SELECT CONCAT('Accounts processed: ', v_accounts_processed) AS message1,
           CONCAT('Total interest paid: $', FORMAT(v_total_interest_paid, 2)) AS message2;

END$$

DELIMITER ;


USE COGNIZAN;

INSERT INTO customers (customer_id, name, age, balance, is_vip) VALUES
(1, 'Swa', 30, 10000.00, 'N'),
(2, 'BOBBY', 45, 5000.00, 'Y');


INSERT INTO accounts (account_id, customer_id, account_type, balance, interest_rate, created_date) VALUES
(101, 1, 'SAVINGS', 10000.00, 0.02, NOW()),
(102, 2, 'CHECKING', 5000.00, NULL, NOW()),
(103, 1, 'SAVINGS', 2000.00, NULL, NOW());

/* DROP TABLE IF EXISTS transactions;
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    from_account_id INT,
    to_account_id INT,
    amount DECIMAL(15,2),
    transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    transaction_type ENUM('TRANSFER', 'DEPOSIT', 'WITHDRAWAL'),
    description VARCHAR(200)
);*/

CALL ProcessMonthlyInterest();
SELECT account_id, balance FROM accounts WHERE account_type = 'SAVINGS';

SELECT * FROM transactions;