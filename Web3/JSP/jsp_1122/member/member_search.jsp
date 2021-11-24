<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="day06.MemberBean" errorPage="error.jsp"%>    
<jsp:useBean id="mdb" class="day06.MemberDataBean" scope="application" />
<%
	request.setCharacterEncoding("UTF-8");
	MemberBean mb=mdb.search(request.getParameter("sname"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>검색된 회원정보</h1>
<table border="1">
		<tr>
			<td>이름</td>
			<td><%=mb.getUname()%></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><%=mb.getUid()%></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><%=mb.getUpw()%></td>
		</tr>
</table>
<hr>
<a href="member_list.jsp">회원목록보기</a>

</body>
</html>