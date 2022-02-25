select * from all_tables;

create table board(
	bid int primary key,
	writer varchar(20) not null,
	title varchar(30) not null,
	content varchar(50) not null
);
select * from board;
insert into board values(1,'작성자','제목','내용');
insert into board values(2,'티모','탑갈사람~?','버섯 농장');
insert into board values(3,'티모','탑','🍄🍄🍄');