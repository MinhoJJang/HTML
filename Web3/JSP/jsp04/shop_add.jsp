<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
    <%
    	request.setCharacterEncoding("UTF-8");
    	String product= request.getParameter("product");
	
    	ArrayList<String> datas = (ArrayList)session.getAttribute("datas");
    	if(datas==null){
	    	datas = new ArrayList<String>();
	    	// 장바구니를 새로 만들고 세션에 넣는다
	    	// 그런데 이 과정을 매번하는 것이 아니라 최초의 한번만 하면 된다. 그것이 언제? 처음으로, 장바구니에 상품을 추가할 때만 1회 생성됨. 
	    	session.setAttribute("datas", datas);
    	}
    	
    	datas.add(product);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		alert("<%=product%>가 장바구니에 추가되었습니다");
		history.go(-1); // 바로 직전 페이지로 가겠다는 명령 
	</script>
	
</body>
</html>