create database COGNIZANT_W;
USE COGNIZANT_W;


DELIMITER $$

CREATE PROCEDURE ProcessSeniorLoanDiscount()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_customer_id INT;
    DECLARE v_customer_name VARCHAR(100);
    DECLARE v_age INT;
    DECLARE v_loan_id INT;
    DECLARE v_current_rate DECIMAL(5,2);
    DECLARE v_new_rate DECIMAL(5,2);
    DECLARE v_customers_processed INT DEFAULT 0;
    DECLARE v_discount_rate DECIMAL(5,2) DEFAULT 1.0;

    DECLARE cur CURSOR FOR
        SELECT c.customer_id, c.name, c.age, l.loan_id, l.interest_rate
        FROM customers c
        INNER JOIN loans l ON c.customer_id = l.customer_id
        WHERE c.age > 60;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO v_customer_id, v_customer_name, v_age, v_loan_id, v_current_rate;
        IF done THEN
            LEAVE read_loop;
        END IF;

        SET v_new_rate = v_current_rate - v_discount_rate;
        IF v_new_rate < 0 THEN
            SET v_new_rate = 0;
        END IF;

        UPDATE loans SET interest_rate = v_new_rate WHERE loan_id = v_loan_id;

        SET v_customers_processed = v_customers_processed + 1;
    END LOOP;

    CLOSE cur;

    SELECT CONCAT('Total processed: ', v_customers_processed) AS result;
END$$

DELIMITER ;




DELIMITER $$

CREATE PROCEDURE PromoteVIPCustomers()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_customer_id INT;
    DECLARE v_customer_name VARCHAR(100);
    DECLARE v_balance DECIMAL(15,2);
    DECLARE v_customers_promoted INT DEFAULT 0;

    DECLARE cur CURSOR FOR
        SELECT customer_id, name, balance
        FROM customers
        WHERE balance > 10000 AND is_vip = 'N';

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO v_customer_id, v_customer_name, v_balance;
        IF done THEN
            LEAVE read_loop;
        END IF;

        UPDATE customers SET is_vip = 'Y' WHERE customer_id = v_customer_id;

        SET v_customers_promoted = v_customers_promoted + 1;
    END LOOP;

    CLOSE cur;

    SELECT CONCAT('Total promoted: ', v_customers_promoted) AS result;
END$$

DELIMITER ;


DELIMITER $$

CREATE PROCEDURE SendLoanReminders()
BEGIN
    DECLARE v_urgency_level VARCHAR(10);
    DECLARE v_reminders_sent INT DEFAULT 0;

    -- Reminder logic with urgency levels
    SELECT 
        c.customer_id,
        c.name AS customer_name,
        c.age,
        l.loan_id,
        l.loan_amount,
        l.due_date,
        DATEDIFF(l.due_date, CURDATE()) AS days_until_due,
        CASE
            WHEN DATEDIFF(l.due_date, CURDATE()) <= 7 THEN 'URGENT'
            WHEN DATEDIFF(l.due_date, CURDATE()) <= 15 THEN 'HIGH'
            ELSE 'MEDIUM'
        END AS urgency_level
    FROM customers c
    INNER JOIN loans l ON c.customer_id = l.customer_id
    WHERE l.due_date BETWEEN CURDATE() AND CURDATE() + INTERVAL 30 DAY
    ORDER BY l.due_date;

    SELECT COUNT(*) INTO v_reminders_sent
    FROM loans
    WHERE due_date BETWEEN CURDATE() AND CURDATE() + INTERVAL 30 DAY;

    SELECT CONCAT('Total reminders sent: ', v_reminders_sent) AS result;
END$$

DELIMITER ;



DELIMITER $$

CREATE PROCEDURE GenerateBankSummary()
BEGIN
    DECLARE v_total_senior_customers INT;
    DECLARE v_total_vip_customers INT;
    DECLARE v_total_due_loans INT;

    SELECT COUNT(DISTINCT c.customer_id)
    INTO v_total_senior_customers
    FROM customers c
    JOIN loans l ON c.customer_id = l.customer_id
    WHERE c.age > 60;

    SELECT COUNT(*) INTO v_total_vip_customers
    FROM customers WHERE is_vip = 'Y';

    SELECT COUNT(*) INTO v_total_due_loans
    FROM loans
    WHERE due_date BETWEEN CURDATE() AND CURDATE() + INTERVAL 30 DAY;

    SELECT 
        v_total_senior_customers AS senior_customers_with_loans,
        v_total_vip_customers AS vip_customers,
        v_total_due_loans AS loans_due_within_30_days;
END$$

DELIMITER ;


-- Create customers table
CREATE TABLE customers (
    customer_id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    balance DECIMAL(15,2),
    is_vip CHAR(1) DEFAULT 'N'
);

-- Create loans table
CREATE TABLE loans (
    loan_id INT PRIMARY KEY,
    customer_id INT,
    loan_amount DECIMAL(15,2),
    interest_rate DECIMAL(5,2),
    due_date DATE,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
INSERT INTO customers (customer_id, name, age, balance, is_vip) VALUES
(1, 'John Smith', 65, 12000.00, 'N'),
(2, 'Alice Brown', 58, 9000.00, 'N'),
(3, 'Mark Davis', 72, 15000.00, 'N');

INSERT INTO loans (loan_id, customer_id, loan_amount, interest_rate, due_date) VALUES
(101, 1, 5000.00, 5.5, CURDATE() + INTERVAL 15 DAY),
(102, 2, 8000.00, 6.0, CURDATE() + INTERVAL 25 DAY),
(103, 3, 10000.00, 7.0, CURDATE() + INTERVAL 10 DAY);

CALL ProcessSeniorLoanDiscount();

 
CALL SendLoanReminders();

CALL GenerateBankSummary();