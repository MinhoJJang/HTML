<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cb" class="day06.CalcBean" />
<jsp:setProperty property="*" name="cb"/>
<%
   cb.calc();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form method="post">
   <input type="text" name="num1">
   <select name="op">
      <option>+</option>
      <option>-</option>
   </select>
   <input type="text" name="num2">
   <input type="submit" value="계산하기">
</form>

<hr>

계산결과: <%=cb.getRes()%>

</body>
</html>ml>