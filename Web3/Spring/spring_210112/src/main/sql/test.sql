select * from all_tables;

create table board(
	bid int primary key,
	title varchar(20),
	writer varchar(10),
	content varchar(50),
	bdate date default sysdate,
	cnt int default 0
);
/* MySQL: insert ? now() */

select * from board;

create table member(
	mid varchar(10) primary key,
	password varchar(10),
	name varchar(10),
	role varchar(10)
);
insert into member values('kim','1234','티모','USER');
insert into member values('admin','1234','관리자','ADMIN');
select * from member;
