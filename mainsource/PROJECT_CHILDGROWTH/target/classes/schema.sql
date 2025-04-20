CREATE DATABASE IF NOT EXISTS child_growth_db;
USE child_growth_db;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS child_profile (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    gender VARCHAR(10),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS growth_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    height DOUBLE,
    weight DOUBLE,
    date DATE,
    child_id BIGINT,
    FOREIGN KEY (child_id) REFERENCES child_profile(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS advice_request (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message TEXT,
    response TEXT,
    created_at DATE,
    resolved BOOLEAN,
    child_id BIGINT,
    FOREIGN KEY (child_id) REFERENCES child_profile(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS membership_plan (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    description TEXT,
    max_children INT,
    allow_doctor_consult BOOLEAN
);
select * from users;