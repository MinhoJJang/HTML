<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form method="post" action="member_controller.jsp">
	<c:choose>
		<c:when test="${mid == null}">
			<input type="hidden" name="action" value="login">
			ID: <input type="text" name="mid" required>
			PW: <input type="password" name="mpw" required>
			<input type="submit" value="login">
		</c:when>
		
		<c:otherwise>
			<input type="hidden" name="action" value="logout">
			${mid} 님 환영합니다!
			<input type="submit" value="logout">
		</c:otherwise>
	</c:choose>
</form>
