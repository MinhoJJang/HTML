<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.setAttribute("uid", request.getParameter("uid"));
	// uid 같은 이름은 맞춰놓자. 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%=session.getAttribute("uid") %>님, 환영합니다!<br>
	<h1>상품목록</h1>
	<hr>
	<form action="shop_add.jsp" method="post">
		<select name="product">
			<option>상품	1</option>
			<option>상품	2</option>
			<option>상품	3</option>
			<option>상품	4</option>
			<option>상품	5</option>
		</select>
		<input type="submit" value="장바구니에 추가">
	</form>
	<hr>
	<a href="shop_result.jsp">결제하기</a>
</body>
</html>