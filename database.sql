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

SELECT * FROM categories;

CREATE TABLE images(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    image BLOB
);

desc images;

SELECT * FROM images;