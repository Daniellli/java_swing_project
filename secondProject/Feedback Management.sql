use master
create table Users(
	Login_ID nvarchar(50) primary key,
	User_Name nvarchar(100) NOT NULL,
	User_Type nvarchar(10) NOT NULL check(User_Type in('admin','customer')),
	User_Phone varchar(12) NOT NULL,
	Password nvarchar(50) NOT NULL,
	Security_Question nvarchar(200) NOT NULL,
	Security_Answer nvarchar(200) NOT NULL
)
select * from Users
create table Teacher
(
	Teacher_ID int primary key identity(101,1),
	Teacher_Name nvarchar(50) not null,
	Teacher_Gender varchar(7) not null check(Teacher_Gender in('male','female')),
	Teacher_Phone varchar(12) not null
)
select * from Teacher
create table Module
(
	Module_ID int primary key identity(11,1),
	Module_Name nvarchar(50) not null,
	Module_Description nvarchar(200) null
)
select * from Module
create table Batch
(
	Batch_ID int primary key identity(1,1),
	Batch_Start date not null,
	Batch_Close date null,
	Module_ID int not null references Module(Module_ID),
	Teacher_ID int not null references Teacher(Teacher_ID),
	Batch_Description nvarchar(200) null
)
select * from Batch
insert into Batch(Batch_Start,Module_ID,Teacher_ID,Batch_Description) values('2019-06-16',11,101,'Java batch for 2019')
	
SELECT Batch.Batch_ID, Batch.Batch_Start, Batch.Batch_Close, Batch.Module_ID, Batch.Teacher_ID, 
Batch.Batch_Description, Module.Module_Name, Teacher.Teacher_Name, Teacher.Teacher_Gender, 
Teacher.Teacher_Phone, Module.Module_Description FROM Batch INNER JOIN Module ON 
Batch.Module_ID = Module.Module_ID INNER JOIN Teacher ON Batch.Teacher_ID = Teacher.Teacher_ID

select * from Users

