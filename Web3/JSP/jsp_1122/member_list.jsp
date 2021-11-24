<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="day06.MemberBean"%>
<jsp:useBean id="mdb" class="day06.MemberDataBean" scope="application" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="member_search.jsp" method="post">
	검색할 이름입력: <input type="text" name="sname"> <input type="submit" value="검색하기">
</form>
<hr>
<h1>회원목록</h1>
<table border="1">
	<tr>
		<td>이름</td><td>아이디</td><td>비밀번호</td>
	</tr>
	<%
		for(MemberBean v:mdb.getDatas()){
	%>
			<tr>
				<td><%=v.getUname()%></td><td><%=v.getUid()%></td><td><%=v.getUpw()%></td>
			</tr>
	<%
		}
	%>
</table>

<hr>

<a href="member_add.html">회원가입하러가기</a>

</body>
</html>