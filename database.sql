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