<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
</head>
<body>

<h1>상세 페이지</h1>
<a href="main.do">메인 페이지로 이동하기</a>
<hr>
<form action="updateBoard.do" method="post">
<input type="hidden" name="bid" value="${data.bid}">
	<table border="1">
		<tr>
			<td>글 제목</td>
			<td><input type="text" name="title" value="${data.title}"></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${data.writer}</td>
		</tr>
		<tr>
			<td>글 내용</td>
			<td><input type="text" name="content" value="${data.content}"></td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${data.bdate}</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${data.cnt}</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="글 변경하기"></td>
		</tr>
	</table>
</form>
<hr>
<a href="deleteBoard.do?bid=${data.bid}">글 삭제하기</a>

</body>
</html>