<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="mytag" uri="/WEB-INF/tld/SelectionHandler.tld" %>
   <!-- mytag : 내가사용할 태그 이름 
   		uri : 가져올 xml file name. (설정파일) 
   		설정파일에 tag 라는 속성이 있으므로 사용가능 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<mytag:sel bgcolor="lightblue" border="5">character name list</mytag:sel>

</body>
</html>