# 1214 

문제점: 포트폴리오페이지에 대한 이해부족&의사소통 오류 

# 화면 구성 

로그인 & 로그아웃 기능 
(비 로그인 시)회원가입 기능
(로그인 시)회원탈퇴 기능

자기소개 페이지 

포트폴리오(게시판) 기능 
이 사용자가 관리자면 포트폴리오 올리기 버튼을 보여준다

	- 댓글
	- 좋아요 
	- 검색(내가 좋아요 누른 포트폴리오만 볼 수 있도록 등등?)


문의하기 기능

문의확인 기능 
이 사용자가 관리자면? 문의 확인페이지를 보여준다 

# ERD 

1. 어떤테이블
2. 테이블에 어떤 데이터가 들어갈거고
3. 테이블끼리의 관계는 어떻게 되고

어떤테이블? 

0. 관리자 
    - 관리자번호 (pk)
    - 관리자 이름
    - 관리자 아이디 
    - 관리자 비밀번호
    - 관리자 이메일 

```sql
create table admin(
    adminNum int primary key,
    adminName varchar(20) not null,
    adminId varchar(20) not null,
    adminPw varchar(20) not null,
    adminEmail varchar(30) not null
)
```

1. 회원 
    - 회원번호 (pk)
    - 회원이름 
    - 회원 아이디 
    - 회원 비밀번호 
    - 회원 이메일   
```sql
create table member(
    memberNum int primary key ,
    memberName varchar(20) not null,
    memberID varchar(20) not null,
    memberPw varchar(20) not null,
    memberEmail varchar(30) not null
)
```

2. 포트폴리오 
    - 포트폴리오 번호(pk)
    - 관리자 번호(fk)
    - 포트폴리오 제목  
    - 포트폴리오 내용 
    - 포트폴리오 작성시간 
    // 좋아요 
```sql
create table portfolio(
    portNum int primary key, 
    adminNum  int foreign key,
    portTitle varchar(50) not null,
    portContent varchar(200),
    portTime DateTime,
    constraint admin_fk foreign key(adminNum) references admin(adminNum) on DELETE CASCADE 
);
```
    
3. 댓글 
    - 댓글번호 (pk)
    - 포트폴리오 번호 (fk) 
    - 댓글내용
    - 회원번호 
    - 회원이름 
    - 댓글 작성시간 

```sql
create table reply(
    replyNum int primary key,
    portNum int not null,
    replyContent varchar(50),
    memberNum int not null,
    memberName varchar(20) not null,
    replyTime DateTime
    constraint portfolio_fk foreign key(portNum) references portfolio(portNum) on DELETE CASCADE  
)
```

4. 문의하기 
    - 문의번호 (pk)
    - 문의유형 [버그제보, 그냥 연락수단 등] select? 
    - 포트폴리오 번호 
    - 회원번호  
    - 문의내용 
    - 회원 이메일 
```sql
create table contact (
    contactNum int primary key,
    contactType varchar(30) not null,
    portNum int not null,
    memberNum int not null,
    contactContent varchar(250) not null,
    memberEmail varchar(30) not null
)

# 
    
    