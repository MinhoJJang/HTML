<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!-- 이 페이지는 사용자에게 보여질 일이 없다. 따라서 html코드가 필요없다.  -->

<%
	pageContext.forward("controller.jsp?action=main");
	// 컨트롤러에게 ~~한 기능을 요청하는 것. 
	// 네이버 웹툰에게 자까일기 10화를 보여줘~ 하는 느낌 
	// action이라는 이름에 main 이라는 파라미터가 들어가있음 
%>