<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form>
	<input type="text" name="num">
	<input type="submit" value="6개의 일련번호들">
</form>
<ol>
<c:forTokens items="${param.num}" delims="-" var="v">
	<li>${v}</li>
</c:forTokens>
</ol>

</body>
</html>