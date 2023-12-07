-- Create the database (if it doesn't exist already)
CREATE DATABASE IF NOT EXISTS universityDatabase;

-- Switch to the newly created database
USE universityDatabase;

-- Create user_account table
CREATE TABLE IF NOT EXISTS user_account (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
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
    ('user1', 'password1', 'John', 'Doe', 'student'),
    ('jane_smith', 'pass456', 'Jane', 'Smith', 'teacher'),
    ('bob_johnson', 'secret789', 'Bob', 'Johnson', 'student');

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
    ('Mathematics', 2, 1, 'MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEFj3DE210RCXwvBL+K+CwxOP58bvqDmvl3a0fxu2vtLMOcM2GTxV1uJZ8QBnRGdJcLOYuIr/qe9yNuGhXlvYslA==');

-- Insert test values into enrollment table
INSERT INTO enrollment (student_id, course_id, grade)
VALUES
    (1, 1, 90),
    (2, 2, 85),
    (3, 1, 95);

