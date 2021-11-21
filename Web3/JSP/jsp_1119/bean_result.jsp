<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="lb" class="day05.LoginBean" />
<jsp:setProperty property="*" name="lb"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	if(lb.check()){
		out.println("로그인 성공!");
	}
	else{
		out.println("<script>alert('로그인 실패!')</script>");
	}
%>

<hr>

<jsp:getProperty property="uid" name="lb"/> <br>
<%=lb.getUpw()%>

</body>
</html>