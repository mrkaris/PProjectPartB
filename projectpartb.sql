drop database if exists personal_project;
create schema personal_project;
use personal_project;

-- TABLES
create table users(
userid int primary key auto_increment,
username varchar(25) not null,
userpassword varchar(25) not null,
bandate datetime
)auto_increment=1;

insert into users(username, userpassword)
values('admin','admin');

create table coursestream(
streamid int primary key auto_increment,
streamname varchar(15) not null,
constraint checkstream check (streamname='full-time' or streamname='part-time')
)auto_increment = 1;

create table trainer(
tid int primary key auto_increment,
tfname varchar(15) not null,
tlname varchar(15) not null,
tsubject varchar(25) not null
)auto_increment =1 ;

create table student(
sid int primary key auto_increment,
sfname varchar(20) not null,
slname varchar(20) not null,
tuitionfees int not null,
sdob date
)auto_increment = 1;

create table course(
cid int primary key auto_increment,
ctitle varchar(15) not null,
ctype varchar(15) not null,
cstartdate date,
cenddate date,
cstreamid int,
constraint fk_stream foreign key(cstreamid) references coursestream (streamid)
ON DELETE SET NULL
)auto_increment=1;

create table assignment(
asid int primary key auto_increment,
astitle varchar(15) not null,
asdescr varchar(45) not null,
subdate date not null,
cid int not null,
constraint fk_course foreign key(cid) references course (cid) ON DELETE CASCADE
)auto_increment=1;
 
create table courseStudent(
csid int primary key auto_increment,
stuid int not null,
cid int not null,
constraint fk_stuid foreign key (stuid) references student(sid) ON DELETE CASCADE,
constraint fk_courseid foreign key (cid) references course(cid) ON DELETE CASCADE
)auto_increment =1 ;

create table courseTrainer(
ctid int primary key auto_increment,
tid int not null,
cid int not null,
constraint fk_tid foreign key (tid) references trainer(tid),
constraint fk_coursetid foreign key (cid) references course(cid) ON DELETE CASCADE
)auto_increment =1 ;

create table studentAssignment(
said int primary key auto_increment,
sid int not null,
asid int not null,
oralmark varchar(15),
totalmark varchar(15),
constraint fk_sid foreign key (sid) references student(sid) ON DELETE CASCADE,
constraint fk_assignid foreign key (asid) references assignment(asid) ON DELETE CASCADE
)auto_increment =1 ;


-- INSERTS

insert into student(sfname,slname,tuitionfees,sdob)
values 
/*1*/('Nick',	'Nickolson',100,19981202),
/*2*/('Ioannis','Ioannou',100,19991024),
/*3*/('Basilis','Basiliou',100,20000808),
/*4*/('Giorgos','Georgiou',100,19990314),
/*5*/('Lefteris','Liakos',100,20010202),
/*6*/('Dimitris','Dimitriou',100,19970624);

insert into coursestream(streamname)
values
		('full-time'),
        ('part-time');
                
insert into trainer(tfname, tlname, tsubject)
values
/*1*/('Panos', 'Panou', 'OOP'),
/*2*/('Boby', 'Bob' , 'Databases'),
/*3*/('Mary', 'Popins', 'Home ec'),
/*4*/('Severous', 'Snape', 'Potions');
        
insert into course(ctitle, ctype, cstartdate,cenddate, cstreamid)
values
/*1*/('Java','Beginner', 20190601, 20191124,2),
/*2*/('PHP','Advanced', 20190602,20191125,1),
/*3*/('SQL','Beginner', 20190603,20191128,2);
        
insert into assignment(astitle,asdescr,subdate, cid)
values
/*1*/('Hotel','Make a hotel manager',20191115,1),
/*2*/('Calculator','Make a calculator',20191115,1),
/*3*/('School','Make a school manager',20191114,2),
/*4*/('Hospital','Make a db for a hospital',20191116,3);

insert into courseStudent(stuid, cid)
values
		(1,1),
        (1,3),
        (2,2),
        (3,2),
        (3,3),
        (4,1),
        (5,3),
        (6,2),
        (6,3);

insert into courseTrainer(tid, cid)
values
		(1,1),
        (2,2),
        (2,3),
        (3,1),
        (3,3),
        (4,2);
        
insert into studentAssignment(sid,asid,oralmark,totalmark)
values
		(1,1,'80','75'),
        (1,2,'75','80'),
        (2,3,'65','70'),
		(3,3,'77','88'),
        (3,4,'55','76'),
        (4,1,'73','77'),
        (4,2,'80','82'),
        (5,4,'92','84'),
        (6,3,'79','72'),
        (6,4,'68','85');


-- ------------------QUERIES-----------------------------




-- PROJECT QUERIES
-- A list of all the students
select*from student;

-- A list of all the trainers
select*from trainer;

-- A list of all the assignments
select asid, astitle, asdescr, subdate 
from assignment;

-- A list of all the courses
create view courses as
select cid, ctitle, ctype , cstartdate, cenddate, streamname 
from course as c left join coursestream as cstream 
on c.cstreamid= cstream.streamid;
select * from courses;

-- All the students per course 
create view stdntsPerCourse as
select c.cid, c.ctitle,s.sid, s.sfname, s.slname from student as s, course as c, courseStudent as cs
where cs.stuid=s.sid and cs.cid=c.cid order by c.cid;
select * from stdntsPerCourse;

-- All the trainers per course
create view trnrsPerCourse as
select c.ctitle, t.tfname, t.tlname from trainer as t, course as c, courseTrainer as ct
where ct.ctid=t.tid and ct.cid=c.cid;
select * from trnrsPerCourse;

-- All the assignments per course
create view asmntsPerCourse as
select ctitle, astitle
from assignment as a left join course as c 
on a.cid=c.cid;
select * from asmntsPerCourse;

-- All the assignments per course per student
-- either one
create view asmntsPerCrsePerStdnt as
select s.sid as studentid, s.sfname as firstname, s.slname as lastname, c.ctitle as courset, a.astitle as assignment, sa.oralmark, sa.totalmark
from student as s , assignment as a, studentAssignment as sa, course as c
where s.sid=sa.sid and a.asid=sa.asid and a.cid=c.cid order by s.sid;
select * from asmntsPerCrsePerStdnt;

/*					
select s.sfname, s.slname, c.ctitle, a.astitle, sa.oralmark, sa.totalmark  from student as s 
inner join studentAssignment as sa on s.sid=sa.sid 
inner join assignment as a on sa.asid=a.asid 
left join course as c on a.cid=c.cid order by s.sid; 
*/

-- A list of students that belong to more than one courses
-- select  s.sfname , slname, count(cs.csid) as countcs from student as s , courseStudent cs where cs.stuid=s.sid and cs.stuid=s.sid group by s.sid having countcs>1;
create view moreThanTwoCourses as
select s.sid, s.sfname, s.slname, count(cs.csid) as countc from student as s left join courseStudent as cs on cs.stuid=s.sid
group by s.sid having countc>=2;
select*from MoreThanTwoCourses;
