CREATE DATABASE belajar_java_peristence_api;

USE belajar_java_persistence_api;

CREATE Table customers(
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

SELECT * FROM customers;
