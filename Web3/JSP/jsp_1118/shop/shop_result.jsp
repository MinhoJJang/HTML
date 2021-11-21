<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>선택한 상품 목록</h1>
<hr>
<%
// 저희는 장바구니가 없다면, 500에러가 발생!
// 장바구니에 담긴 물품이 없다면(==즉, 장바구니가 없다면)
// "선택한 상품이 없습니다!"라는 문구가 화면에 출력될수있도록 코딩해주세요!~~
	ArrayList<String> datas=(ArrayList)session.getAttribute("datas");
	if(datas!=null){
		out.println("<ol>");
		for(String v:datas){
			out.println("<li>"+v+"</li><br>");
		}
		out.println("</ol>");
	}
	else{
		out.println("<h3>선택한 상품이 없습니다!</h3>");
	}
%>

</body>
</html>