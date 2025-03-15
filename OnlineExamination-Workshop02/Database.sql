CREATE DATABASE OnlineExamination;
GO
USE OnlineExamination;

-- Bảng Users
CREATE TABLE tblUsers (
    username VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('Instructor', 'Student'))
);

-- Bảng Exam Categories
CREATE TABLE tblExamCategories (
    category_id INT IDENTITY(1,1) PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL,
    description TEXT
);

-- Bảng Exams
CREATE TABLE tblExams (
    exam_id INT IDENTITY(1,1) PRIMARY KEY,
    exam_title VARCHAR(100) NOT NULL,
    subject VARCHAR(50) NOT NULL,
    category_id INT NOT NULL,
    total_marks INT NOT NULL,
    duration INT NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    FOREIGN KEY (category_id) REFERENCES tblExamCategories(category_id),
    FOREIGN KEY (created_by) REFERENCES tblUsers(username)
);

-- Bảng Questions
CREATE TABLE tblQuestions (
    question_id INT IDENTITY(1,1) PRIMARY KEY,
    exam_id INT NOT NULL,
    question_text TEXT NOT NULL,
    option_a VARCHAR(100) NOT NULL,
    option_b VARCHAR(100) NOT NULL,
    option_c VARCHAR(100) NOT NULL,
    option_d VARCHAR(100) NOT NULL,
    correct_option CHAR(1) NOT NULL CHECK (correct_option IN ('A', 'B', 'C', 'D')),
    created_by VARCHAR(50) NOT NULL,
    FOREIGN KEY (exam_id) REFERENCES tblExams(exam_id),
    FOREIGN KEY (created_by) REFERENCES tblUsers(username)
);

-- Bảng Exam Results
CREATE TABLE tblExamResults (
    result_id INT IDENTITY(1,1) PRIMARY KEY,
    exam_id INT NOT NULL,
    student VARCHAR(50) NOT NULL,
    score INT NOT NULL,
    FOREIGN KEY (exam_id) REFERENCES tblExams(exam_id),
    FOREIGN KEY (student) REFERENCES tblUsers(username)
);




USE OnlineExamination;
GO

SET IDENTITY_INSERT tblExamCategories ON;
INSERT INTO tblExamCategories (category_id, category_name, description) VALUES
(1, 'Programming', 'Exams related to coding and software development'),
(2, 'Database', 'Exams related to SQL and databases');
SET IDENTITY_INSERT tblExamCategories OFF;

-- Thêm Users (Giảng viên và Sinh viên)
INSERT INTO tblUsers (username, name, password, role) VALUES
('admin123', 'Admin', '123456', 'Instructor'),
('teacher01', 'Instructor 01', 'abcdef', 'Instructor'),
('student01', 'Student 01', '123456', 'Student'),
('student02', 'Student 02', 'abcdef', 'Student');

SET IDENTITY_INSERT tblExams ON;
INSERT INTO tblExams (exam_id, exam_title, subject, category_id, total_marks, duration, created_by) VALUES
(1, 'Java Basics', 'Java', 1, 100, 60, 'admin123'),
(2, 'SQL Fundamentals', 'Database', 2, 50, 30, 'teacher01');
SET IDENTITY_INSERT tblExams OFF;

SET IDENTITY_INSERT tblQuestions ON;
INSERT INTO tblQuestions (question_id, exam_id, question_text, option_a, option_b, option_c, option_d, correct_option, created_by) VALUES
(1, 1, 'What is Java?', 'A language', 'A fruit', 'An animal', 'A drink', 'A', 'admin123'),
(2, 1, 'Which company developed Java?', 'Microsoft', 'Apple', 'Sun Microsystems', 'Google', 'C', 'admin123'),
(3, 2, 'What does SQL stand for?', 'Structured Query Language', 'Simple Query Logic', 'Sequential Query Language', 'None of the above', 'A', 'teacher01');
SET IDENTITY_INSERT tblQuestions OFF;

SET IDENTITY_INSERT tblExamResults ON;
INSERT INTO tblExamResults (result_id, exam_id, student, score) VALUES
(1, 1, 'student01', 80),
(2, 1, 'student02', 90),
(3, 2, 'student01', 50),
(4, 2, 'student02', 70);
SET IDENTITY_INSERT tblExamResults OFF;

