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
	if(session.isNew()){
		out.println("<script>alert('새로운 세션!')</script>");
		session.setMaxInactiveInterval(10);
		session.setAttribute("user", "작은티모"); // int a=10;
											// a==user 10==작은티모
	}
%>
<h1>브라우저 단위로 데이터를 저장하는 session 내장객체</h1>
<%=session.getAttribute("user")%>님, 환영합니다!

</body>
</html>