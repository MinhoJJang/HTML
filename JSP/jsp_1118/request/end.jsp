<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
		<tr>
			<td>ID</td>
			<td><%=request.getParameter("uid")%></td>
		</tr>
		<tr>
			<td>관심언어</td>
			<td><%=request.getParameter("ln")%></td>
		</tr>
		<tr>
			<td>좋아하는 과일</td>
			<td><%
				String datas[]=request.getParameterValues("ff");
			if(datas!=null){
				for(String v:datas){
					out.println(v+"<br>");
				}
			}
			else{
				out.println("좋아하는 과일이 없습니다!");
			}
			%></td>
		</tr>
	</table>
	<hr>
	<a href="start.html">처음으로 돌아가기</a>
</body>
</html>