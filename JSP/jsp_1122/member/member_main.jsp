<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	request.setCharacterEncoding("UTF-8");
    %>
<jsp:useBean id="mb" class="day06.MemberBean" />
<jsp:setProperty property="*" name="mb"/>
<jsp:useBean id="mdb" class="day06.MemberDataBean" scope="application" />
<%
	mdb.add(mb);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

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