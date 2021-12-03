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
<jsp:useBean id="nl" class="day12.Selection" scope="session" />
<form action="showResult.jsp" method="post">
	<select name="cname">
<c:forEach var="v" items="${nl.nameList}"> <!-- nl의 nameList를 items로 가져온다는 의미 -->
	<option>${v }</option>
</c:forEach>
		
	<!-- 스크립트릿 없이 JSPL사용하기 -->
	</select>
	<input type="submit" value="캐릭터 선택완료">
</form>

</body>
</html>