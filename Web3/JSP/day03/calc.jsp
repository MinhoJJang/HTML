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
	int res = 0;
	
	if(request.getMethod().equals("POST")){
	// application, out, session, request, response == 내장객체 
	int num1 = Integer.parseInt(request.getParameter("num1"));
	// 형변환을 해야하는데 대상이 null이다 
	// num1이라는 이름의 파라미터가 없음 
	
	// int num = 10+2*3; // 16 
	// 우선순위에 대해 알아보자
	// "num1" 이라는 값이 들어온 이후에야 계산을 할 수 있다는 의미임. 
	
	int num2 = Integer.parseInt(request.getParameter("num2"));
	String op = request.getParameter("op");
	
	if(op.equals("+")) {
		res = num1+num2;
	}
	else {
		res = num1 - num2;
	}
	}

%>

	<form  method="post">
	   <input type="text" name="num1">
	   <select name="op">
	      <option selected>+</option>
	      <option>-</option>
	   </select>
	   <input type="text" name="num2">
	   <input type="submit" value="계산하기">
</form>

<hr>

<h2>계산결과는 <%=res%> 입니다.</h2>
</body>
</html>