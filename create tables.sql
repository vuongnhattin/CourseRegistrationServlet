CREATE DATABASE QLHP_JAVA
GO
USE QLHP_JAVA
GO


CREATE TABLE ACCOUNT (
	USERNAME VARCHAR(50),
	PASSWORD VARCHAR(50),
	TYPE VARCHAR(20)

	PRIMARY KEY (USERNAME)
)
GO

INSERT INTO ACCOUNT VALUES 
('duy', 'duy', 'admin'),
('tin', 'tin', 'admin'),
('hien', 'hien', 'student'),
('khang', 'khang', 'student')

CREATE TABLE ADMIN (
	ID INT IDENTITY(1,1),
	USERNAME VARCHAR(50),
	NAME NVARCHAR(50),
	GENDER NVARCHAR(10) CHECK (GENDER IN (N'Nam', N'Nữ', N'Khác')),
	BIRTHDAY DATE

	PRIMARY KEY (ID)
)
GO

ALTER TABLE ADMIN
ADD CONSTRAINT FK_ADMIN_ACCOUNT
FOREIGN KEY (USERNAME)
REFERENCES ACCOUNT(USERNAME)

INSERT INTO ADMIN VALUES
('duy', N'Trần Quốc Duy', 'Nam', '2024-01-01'),
('tin', N'Vương Nhật Tín', 'Nam', '2024-01-01')

CREATE TABLE STUDENT (
	ID INT IDENTITY(1,1),
	USERNAME VARCHAR(50),
	NAME NVARCHAR(50),
	GENDER NVARCHAR(10) CHECK (GENDER IN (N'Nam', N'Nữ', N'Khác')),
	BIRTHDAY DATE,
	DEPARTMENT NVARCHAR(50) CHECK (DEPARTMENT IN (
		N'Công nghệ thông tin',
		N'Sinh học',
		N'Vật lý',
		N'Hoá học',
		N'Toán tin'
	))

	PRIMARY KEY (ID)
)
GO

ALTER TABLE STUDENT
ADD CONSTRAINT FK_STUDENT_ACCOUNT
FOREIGN KEY (USERNAME)
REFERENCES ACCOUNT(USERNAME)

INSERT INTO STUDENT VALUES
('hien', N'Vương Nhật Hiển', 'Nam', '2024-01-01', N'Công nghệ thông tin'),
('khang', N'Đỗ Duy Khang', 'Khác', '2024-01-01', N'Toán tin')

CREATE TABLE SUBJECT (
	ID CHAR(8),
	NAME NVARCHAR(50),
	CREDITS INT CHECK (CREDITS >= 1 AND CREDITS <= 4),

	PRIMARY KEY (ID)
)

INSERT INTO SUBJECT VALUES
('CSC10008', N'Mạng máy tính', 4),
('CSC10009', N'Hệ thống máy tính', 2),
('MTH00003', N'Vi tích phân 1B', 4),
('BAA00007', N'Phương pháp luận sáng tạo', 2),
('CSC10006', N'Cơ sở dữ liệu', 4),
('BAA00003', N'Tư tưởng Hồ Chí Minh', 2),
('BAA00103', N'Chủ nghĩa xã hội khoa học', 2),
('CSC10004', N'Cấu trúc dữ liệu và giải thuật', 4)

CREATE TABLE SEMESTER (
	ID INT IDENTITY(1,1),
	NAME CHAR(3) CHECK (NAME IN ('HK1', 'HK2', 'HK3')),
	YEAR INT,
	START_DATE DATE,
	END_DATE DATE

	PRIMARY KEY (ID),
	UNIQUE (NAME, YEAR)
)

CREATE TABLE CURRENT_SEMESTER (
	ID INT CHECK (ID = 1),
	SEMESTER_ID INT

	PRIMARY KEY (ID)
)

ALTER TABLE CURRENT_SEMESTER
ADD CONSTRAINT FK_CURRENT_SEMESTER_SEMESTER
FOREIGN KEY (SEMESTER_ID)
REFERENCES SEMESTER(ID)

INSERT INTO SEMESTER VALUES
('HK1', 2024, '2024-01-01', '2024-02-02'),
('HK2', 2024, '2024-03-03', '2024-04-04')

INSERT INTO CURRENT_SEMESTER VALUES
(1, 1)

CREATE TABLE STUDY (
	STUDENT_ID INT,
	CLASS1_ID INT

	PRIMARY KEY (STUDENT_ID, CLASS1_ID)
)

ALTER TABLE STUDY
ADD CONSTRAINT FK_STUDY_STUDENT
FOREIGN KEY (STUDENT_ID)
REFERENCES STUDENT(ID)


CREATE TABLE CLASS1 (
	ID INT IDENTITY(1,1),
	NAME NVARCHAR(50)

	PRIMARY KEY (ID)
)

ALTER TABLE STUDY
ADD CONSTRAINT FK_STUDY_CLASS1
FOREIGN KEY (CLASS1_ID)
REFERENCES CLASS1(ID)

INSERT INTO CLASS1 VALUES
('CNTN'),
('CTT2')

INSERT INTO STUDY VALUES
(1, 1),
(2, 1)
go

create function countGender(@CLASS1Id int, @gender nvarchar(50))
returns int
as begin
	declare @res int
	select @res = count(*)
	from STUDENT s, STUDY st
	where s.id = st.STUDENT_ID
	and st.CLASS1_ID = @CLASS1Id
	and s.GENDER = @gender

	if @res is null
		set @res = 0
	return @res
end
go

create function countAll(@CLASS1Id int)
returns int
as begin
	declare @res int
	select @res = count(*)
	from STUDY st
	where st.CLASS1_ID = @CLASS1Id

	if @res is null
		set @res = 0
	return @res
end
go

alter table CLASS1
add COUNT_STUDENTS as (dbo.countAll(id))

alter table CLASS1
add COUNT_BOYS as (dbo.countGender(id, 'Nam'))

alter table CLASS1	
add COUNT_GIRLS as (dbo.countGender(id, N'Nữ'))

alter table CLASS1
add COUNT_OTHERS as (dbo.countGender(id, N'Khác'))

CREATE TABLE REGISTER_TIME (
	ID INT CHECK (ID = 1),
	START_TIME DATETIME,
	END_TIME DATETIME

	PRIMARY KEY (ID)
)

INSERT INTO REGISTER_TIME VALUES
(1, '2024-01-01 00:00:00', '2024-01-01 23:59:59')

CREATE TABLE COURSE (
	ID INT IDENTITY(1, 1),
	SEMESTER_ID INT,
	SUBJECT_ID CHAR(8),
	TEACHER_ID INT,
	ROOM NVARCHAR(10),
	DAY NVARCHAR(20) CHECK (DAY IN (N'Thứ hai', N'Thứ ba', N'Thứ tư', N'Thứ năm', N'Thứ sáu', N'Thứ bảy', N'Chủ nhật')),
	TIME NVARCHAR(50) CHECK (TIME IN ('7:30 - 9:30', '9:30 - 11:30', '13:30 - 15:30', '15:30 - 17:30')),
	MAX_SLOTS INT

	PRIMARY KEY (ID)
)

CREATE TABLE TEACHER (
	ID INT IDENTITY(1,1),
	NAME NVARCHAR(50)

	PRIMARY KEY (ID)
)

INSERT INTO TEACHER VALUES
(N'Ngyễn Thanh Phương'),
(N'Trần Minh Triết'),
(N'Dương Nguyên Vũ'),
(N'Nguyễn Trần Minh Thư'),
(N'Trần Trà Hương'),
(N'Trần Hà Sơn')

ALTER TABLE COURSE
ADD CONSTRAINT FK_COURSE_SEMESTER
FOREIGN KEY (SEMESTER_ID)
REFERENCES SEMESTER (ID)

ALTER TABLE COURSE
ADD CONSTRAINT FK_COURSE_SUBJECT
FOREIGN KEY (SUBJECT_ID)
REFERENCES SUBJECT (ID)

ALTER TABLE COURSE
ADD CONSTRAINT FK_COURSE_TEACHER
FOREIGN KEY (TEACHER_ID)
REFERENCES TEACHER (ID)

INSERT INTO COURSE VALUES
(1, 'BAA00007', 1, 'E.203', N'Thứ hai', '15:30 - 17:30', 100),
(1, 'CSC10009', 3, 'E.103', N'Thứ ba', '7:30 - 9:30', 40),
(1, 'BAA00007', 4, 'E.204', N'Thứ hai', '13:30 - 15:30', 420),
(1, 'BAA00103', 2, 'F.103', N'Thứ bảy', '13:30 - 15:30', 69),
(1, 'BAA00003', 5, 'E.205', N'Thứ sáu', '15:30 - 17:30', 120),
(1, 'CSC10004', 6, 'F.303', N'Thứ hai', '7:30 - 9:30', 120),
(1, 'CSC10004', 3, 'F.207', N'Thứ ba', '9:30 - 11:30', 120),
(2, 'CSC10009', 5, 'F.201', N'Thứ năm', '15:30 - 17:30', 120),
(2, 'CSC10009', 6, 'E.102', N'Thứ hai', '7:30 - 9:30', 120),
(2, 'MTH00003', 1, 'E.201', N'Thứ tư', '13:30 - 15:30', 120),
(2, 'BAA00103', 1, 'F.102', N'Thứ sáu', '7:30 - 9:30', 120),
(2, 'CSC10009', 2, 'F.207', N'Thứ năm', '13:30 - 15:30', 100),
(2, 'CSC10008', 3, 'E.102', N'Thứ hai', '9:30 - 11:30', 60),
(2, 'CSC10008', 2, 'E.304', N'Thứ bảy', '7:30 - 9:30', 120)

--('CSC10008', N'Mạng máy tính', 4),
--('CSC10009', N'Hệ thống máy tính', 2),
--('MTH00003', N'Vi tích phân 1B', 4),
--('BAA00007', N'Phương pháp luận sáng tạo', 2),
--('CSC10006', N'Cơ sở dữ liệu', 4),
--('BAA00003', N'Tư tưởng Hồ Chí Minh', 2),
--('BAA00103', N'Chủ nghĩa xã hội khoa học', 2),
--('CSC10004', N'Cấu trúc dữ liệu và giải thuật', 4)

CREATE TABLE REGISTER (
	STUDENT_ID INT,
	COURSE_ID INT,
	TIME DATETIME

	PRIMARY KEY (STUDENT_ID, COURSE_ID)
)

ALTER TABLE REGISTER
ADD CONSTRAINT FK_REGISTER_STUDENT
FOREIGN KEY (STUDENT_ID)
REFERENCES STUDENT (ID)

ALTER TABLE REGISTER
ADD CONSTRAINT FK_REGISTER_COURSE
FOREIGN KEY (COURSE_ID)
REFERENCES COURSE (ID)

USE master
DROP DATABASE QLHP_JAVA