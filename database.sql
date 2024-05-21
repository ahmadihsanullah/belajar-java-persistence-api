CREATE DATABASE belajar_java_peristence_api;

USE belajar_java_persistence_api;

CREATE Table customers(
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

SELECT * FROM customers;

ALTER TABLE customers
    ADD COLUMN primary_email VARCHAR(150);

ALTER TABLE customers
    ADD COLUMN age TINYINT;

ALTER TABLE customers
    ADD COLUMN married BOOLEAN;

ALTER TABLE customers
    ADD COLUMN type VARCHAR(50);

CREATE TABLE categories(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500)
);
ALTER TABLE categories
    ADD COLUMN created_at TIMESTAMP;

ALTER TABLE categories
    ADD COLUMN updated_at TIMESTAMP;

SELECT * FROM categories;

CREATE TABLE images(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    image BLOB
);

desc images;

SELECT * FROM images;

CREATE TABLE departments(
    company_id VARCHAR(100) NOT NULL,
    department_id VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (company_id, department_id)
)

SELECT * FROM departments;

CREATE Table members(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(150) NOT NULL,
    title VARCHAR(100),
    first_name VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100),
    last_name VARCHAR(100)
);

SELECT * FROM members;

CREATE TABLE hobbies(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    member_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    FOREIGN KEY fk_members_hobbies (member_id)
        REFERENCES members(id)
);


SELECT *
FROM hobbies;

CREATE TABLE skills
(
    id        INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    member_id INT          NOT NULL,
    name      VARCHAR(100) NOT NULL,
    value     INT          NOT NULL,
    FOREIGN KEY fk_members_skills (member_id) REFERENCES members (id),
    CONSTRAINT skills_unique UNIQUE (member_id, name)
) ENGINE InnoDB;

SELECT * FROM skills;

SELECT * FROM categories;

CREATE TABLE employess(
    id VARCHAR(100)NOT NULL,
    type VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    total_employee INT,
    total_manager INT
)

SELECT * FROM employess;


CREATE TABLE payments(
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    amount BIGINT NOT NULL
);

CREATE TABLE payments_credit_card(
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    masked_card VARCHAR(100) NOT NULL,
    bank VARCHAR(100) NOT NULL,
    FOREIGN KEY fk_payments_credit_card (id) REFERENCES payments(id)
);

CREATE TABLE payments_gopay(
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    gopay_id VARCHAR(100) NOT NULL,
    FOREIGN KEY fk_payments_gopay (id) REFERENCES payments(id)
);

CREATE Table transactions(
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    balance BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

SELECT * FROM transactions;


CREATE Table transactions_debit(
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    balance BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    debit_amount BIGINT NOT NULL
);

SELECT * FROM transactions_debit;


CREATE Table transactions_credit(
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    balance BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    credit_amount BIGINT NOT NULL
);

SELECT * FROM transactions_credit

ALTER TABLE brands
    ADD COLUMN created_at TIMESTAMP;

ALTER TABLE brands
    ADD COLUMN updated_at TIMESTAMP; 



SELECT * FROM brands ;

ALTER TABLE brands
ADD COLUMN version BIGINT;

SHOW TABLES;

SELECT * from members;

select * from members where  first_name COLLATE utf8mb4_bin = 'ahmad';
