# Search + image 

## 검색기능 추가 

1. VO 수정 

```java
private String searchCondition;
private String searchContent;

+ getters setters 
```

2. DAO에 sql문 추가 및 로직 수정 

- mysql의 경우 parameter 값의 위치인 `?` 을 인식해야 한다. 그래서 `?`에다가 자꾸 이상한 따옴표같은걸 붙이면 pstmt가 sql을 실행시킬 때 문제가 발생한다. 그래서 sql문에서 `?`양옆에 `%`을 붙이는게 아니라, 문자열이 sql에 들어가기 직전에 `Object[] obj= {"%"+vo.getSearchContent()+"%"};` 이런식으로 붙여줘야 한다. ***이걸몰라서 이틀을 헤멨다...!***

```java
private String selectW = "select * from newboard where writer like ? order by bid desc";
private String selectT = "select * from newboard where title like ? order by bid desc";

public List<BoardVO> selectAll(BoardVO vo) {
		 
		 String sql = selectT;
		 
		 System.out.println(vo.getSearchCondition());
		 System.out.println(vo.getSearchContent());
		
		 if(vo.getSearchCondition().equals("title")) {
			 sql = selectT;
		 } else if(vo.getSearchCondition().equals("writer")){
			sql = selectW;
		 }	 
	     Object[] obj= {"%"+vo.getSearchContent()+"%"};
	     return getJdbcTemplate().query(sql,obj,new BoardRowMapper());
	 } 
```


## File 추가 

1. board.jsp 파일 수정
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성 페이지</title>
</head>
<body>
<h1>글 작성 페이지</h1>
<a href="main.do">메인 페이지로 이동하기</a>
<hr>
<form action="insertBoard.do" method="post" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<td>글 제목</td>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer"></td>
		</tr>
		<tr>
			<td>글 내용</td>
			<td><input type="text" name="content"></td>
		</tr>
		<tr>
			<td>파일 업로드</td>
			<td><input type="file" name="file"></td>
		</tr>
		
		<tr>
			<td colspan="2"><input type="submit" value="글 등록하기"></td>
		</tr>
	</table>
</form>
</body>
</html>
```
여기서 `enctype = "multipart/form-data"` 는	모든 문자를 인코딩하지 않음을 명시하는데, 이 방식은 <form> 요소가 파일이나 이미지를 서버로 전송할 때 주로 사용한다. 우리는 이미지를 보내볼 것이기 때문에 따로 인코딩을 해서 보내지 않는다. 

2. BoardVO 수정 
```java
import org.springframework.web.multipart.MultipartFile;
...

private MultipartFile file;
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
```
이때, MultipartFile이라는 특수한 타입의 객체를 사용한다. 이는 지금까지 우리가 사용해왔던 int나 String에서 사용해왔던 연산을 하지 못한다는 의미이다. 그렇기 때문에 여러가지 코드들을 추가해 주어야 한다. 

3. pom.xml에 dependency 추가 
```xml
<!-- 파일업로드 -->
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.3.1</version>
</dependency>
```

4. DS 수정 

```xml
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    <property name="maxUploadSize" value="-1" />
</bean>
```

5. 파일 업로드 

먼저 업로드된 파일을 보관할 폴더를 하나 생성하고, 그 폴더의 절대경로를 저장한다. 그리고 file.Transfer을 통해 파일을 해당 폴더로 전달할 수 있다. 

```java
final private String path = "/mnt/18b3ea8d-ef9b-4057-be1e-87840846fb20/JMH_WEB3/upload/";
	
@RequestMapping(value="/insertBoard.do")
	public String insertBoard(BoardVO vo) throws IllegalStateException, IOException {
		MultipartFile file = vo.getFile();
		if(!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			System.out.println("filename = "+fileName);
			
			file.transferTo(new File(path+fileName));
		}
		bs.insertBoard(vo);
		return "main.do";
	}
``` 

6. DB 에 File 타입 추가 

```sql
create table newboard(
   bid int primary key auto_increment,
   title varchar(20),
   writer varchar(10),
   content varchar(50),
   bdate datetime,
   cnt int default 0,
   filepath varchar(100)
);
``` 

7. DAO의 select* 부분 수정 

DB에 데이터가 추가되었다. 즉, VO가 바뀐 것이고, VO가 바뀌었으므로 VO를 사용하는 DAO를 수정해주어야 한다. 
```java
data.setFilepath(rs.getString("filepath"));
```

8. jsp파일에 img 태그 추가 

```jsp
<img alt="파일업로드 실습" src="${data.filepath}">
```

## + 오류 

이상하게 사진이 제대로 출력되지 않는다. 경로는 제대로 들어온다. 어쩌면 읽기 권한이 없기 때문일 수도 있다... 수정해야겠다 