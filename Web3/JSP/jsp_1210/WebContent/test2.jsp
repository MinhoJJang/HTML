<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>데이터 목록</h2>
<hr>
<ol>
<c:forEach var="v" items="${datas}">
	<li>
		mid: ${v.mid} <br>
		mpw: ${v.mpw} <br>
		mname: ${v.mname} <hr>
	</li>
</c:forEach>
</ol>
<br>
<hr>
<br>
<a href="test1.jsp">처음으로 돌아가기</a>

</body>
</html>