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
	int cnt=(Integer)application.getAttribute("cnt");
	cnt++;
	application.setAttribute("cnt", cnt); // 데이터의 변경사항이 유지됨
%>

<%=application.getAttribute("uname")%>님, 투데이 방문자수는 <%=cnt%>명입니다!

</body>
</html>