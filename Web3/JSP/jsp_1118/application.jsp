<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	application.setAttribute("uname", "홍길동");
	application.setAttribute("cnt", 1);
%>
<a href="applicationEnd.jsp">결과는?</a>

</body>
</html>