-- Create the database (if it doesn't exist already)
CREATE DATABASE IF NOT EXISTS universityDatabase;

-- Switch to the newly created database
USE universityDatabase;

-- Create user_account table
CREATE TABLE IF NOT EXISTS user_account (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password CHAR(72) NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    role VARCHAR(255) DEFAULT 'student' NOT NULL
);

-- Create course table
CREATE TABLE IF NOT EXISTS course (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cfu INTEGER NOT NULL
);

-- Create student table
CREATE TABLE IF NOT EXISTS student (
    id SERIAL PRIMARY KEY,
    gpa FLOAT,
    user_account_id BIGINT UNSIGNED UNIQUE,
    FOREIGN KEY (user_account_id) REFERENCES user_account(id) ON DELETE CASCADE
);

-- Create teacher table
CREATE TABLE IF NOT EXISTS teacher (
    id SERIAL PRIMARY KEY,
    taught_course VARCHAR(255) NOT NULL,
    user_account_id BIGINT UNSIGNED UNIQUE,
    course_id BIGINT UNSIGNED UNIQUE,
    ecc_public_key TEXT UNIQUE,
    FOREIGN KEY (user_account_id) REFERENCES user_account(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE
);

-- Create enrollment table
CREATE TABLE IF NOT EXISTS enrollment (
    student_id BIGINT UNSIGNED NOT NULL,
    course_id BIGINT UNSIGNED NOT NULL,
    grade INTEGER,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE
);

-- Insert test values into user_account table
INSERT INTO user_account (username, password, name, surname, role)
VALUES
    ('user1', '$2a$12$z5bYbHJVEajPZyNnEA5snuS0gCZUNslpnI2MgcUk4qoItjgskzZSO', 'John', 'Doe', 'student'),
    ('jane_smith', '$2a$12$1Wlee7nzlbikykl2scLGkOOiXEk/FXl6F4upbMbQKK0XAmSd67vGi', 'Jane', 'Smith', 'teacher'),
    ('bob_johnson', '$2a$12$Fpb/8imWNSKjB.BXmg2QBOi84bGhvaldXZEGEFN6lGWv6ruJNHrQW', 'Bob', 'Johnson', 'student'),
    ('admin', '$2a$12$JBLpalawdFYBddWM8kV9reqkDfN4k44WQNpraB.gHvfgsnuGq4xpu', 'Bobob', 'Johnsonson', 'admin');

-- Insert test values into student table
INSERT INTO student (gpa, user_account_id)
VALUES
    (3.8, 1),
    (3.5, 2),
    (4.0, 3);

-- Insert test values into course table
INSERT INTO course (name, cfu)
VALUES
    ('Math 101', 3),
    ('History 202', 4);

-- Insert test values into teacher table
INSERT INTO teacher (taught_course, user_account_id, course_id, ecc_public_key)
VALUES
    ('Mathematics', 2, 1, '-----BEGIN PUBLIC KEY-----
MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEJmvAi0EWPgiayF+Kx/Ad5w5ewPGG
XOFgln5MJV3DNXoEHnrgVc7wTbkeuDPU8syXOWC+blotIPUSdiLUOYvWug==
-----END PUBLIC KEY-----');

-- Insert test values into enrollment table
INSERT INTO enrollment (student_id, course_id, grade)
VALUES
    (1, 1, 90),
    (2, 2, 85),
    (3, 1, 95);

