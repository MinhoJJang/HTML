<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>

<table border="1">
	
	<tr>
		<th>글 번호</th>
		<th>글 제목</th>
		<th>작성자</th>
	</tr>
	
	<%
		// for(데이터 ==VO v: 컬렉션[ArrayList]){
	%>
	<tr>
		<th>글 번호</th>
		<th>글 제목</th>
		<th>작성자</th>
	</tr>
	<%
		}
	%>
</table>

<hr>

<a href="form.jsp">글 작성하기</a>

</body>
</html>