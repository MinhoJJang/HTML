# SCOPE

내장객체의 설명을 들어가기 전에, 먼저 scope에 대해 알아보자. 컴퓨터 프로그래밍에서 scope란 변수의 작용 범위를 뜻한다. 이것이 웹 프로그래밍에서 중요한 이유는 무엇일까? 

예를 들어, 사용자가 어떠한 앱에 로그인을 했다고 가정해 보자. 그런데 이 로그인 한 결과가 앱을 종료할 때마다 사라진다면, 혹은 핸드폰이 꺼졌을 때마다 사라진다면, 상황과 경우에 따라 불편한 상황이 발생할 것이다. 따라서 개발자는 사용자가 사용하는 기능의 특징에 입각해 입력받은 데이터의 범위를 설정해야 한다. 만약 온라인 쇼핑몰에서 사용자가 열심히 장바구니에 여러 제품들을 담고 계산하려는 찰나, 브라우저가 닫혔다고 가정해 보자. 만약 브라우저가 닫혔다고 해서 장바구니에 담은 정보들이 전부 사라진다면, 사용자는 쇼핑몰을 이용하는 데 불편함을 느낄 수밖에 없을 것이다. 반면에, 금융과 관련되어 사용자가 입력한 정보가 한 번 입력한 후에 영구히 보존된다면 보안에 취약해질지도 모른다. 이처럼 기능에 따라 적절한 변수범위를 설정하는 것이 중요하다. 

# 내장객체

JSP 파일은 결국 Servlet 파일로 변환된다. 이때 **application, session, out, request** 같은 내장객체들이 자동으로 제공된다. 이러한 객체들은 주로 데이터를 주고받을 때 사용된다.


## request

[start.html](start.html)
```html
<form action="end.jsp" method="post">
	<!-- form의 action을 end.jsp로 해주고 데이터를 서버로 전송하기 위해 post를 사용함 -->
	<table border="1">
		<tr>
			<td>ID</td>
			<td><input type="text" name="uid"></td>
			<!-- 사용자 아이디를 'uid' 라는 이름으로 받았음 -->
			<!-- uid = user id -->
		</tr>
		<tr>
			<td>관심언어</td>
			<td><select name="ln">
				<!-- 사용자가 선택한 것을 'ln' 이라는 이름으로 받았음 -->
				<!-- ln = language -->
				<option selected>C언어</option>
				<!-- selected를 통해 다양한 option에서 하나를 고를 수 있게 함 -->
				<option>파이썬</option>
				<option>Java</option>
				<option>JSP</option>
			</select></td>
		</tr>
		<tr>
			<td>좋아하는 과일</td>
			<td><input type="checkbox" name="ff" value="사과">사과
			<input type="checkbox" name="ff" value="바나나">바나나
			<input type="checkbox" name="ff" value="키위">키위</td>
			<!-- 과일이라는 공통점이 있는 것들의 이름을 전부 'ff'로 맞춰서 후에 데이터를 불러오기 편해지도록 하였다. -->
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="보내기"></td>
			<!-- submit을 통해 form 태그 내부의 모든 정보를 post방식으로 전송한 뒤 end.jsp로 이동하게 하였다. -->
		</tr>
	</table>
</form>
```

그렇다면 submit을 통해 전송된 form내부 정보들을 어떻게 이용할 수 있을까? end.jsp를 살펴보자,

[end.jsp](end.jsp)
```jsp
<table border="1">
		<tr>
			<td>ID</td>
			<td><%=request.getParameter("uid")%></td>
			<!-- 사용자의 아이디 name 인 'uid' 의 parameter 값, 즉 사용자가 입력한 아이디를  request.getParameter(name); 을 통해 받아올 수 있다. 이는 보통 단독값을 입력하는 type에 사용된다. (text, select, radio ...)-->
		</tr>
		<tr>
			<td>관심언어</td>
			<td><%=request.getParameter("ln")%></td>
			<!-- 위와 마찬가지 -->
		</tr>
		<tr>
			<td>좋아하는 과일</td>
			<td><%
				String datas[]=request.getParameterValues("ff");
				/* 과일의 종류는 여러 가지이고, 그 중에서 사용자가 몇 개를 택했을지는 아무도 모르기 때문에 일단 모든 과일의 parameter 값을 가져와야 한다. 그런데 ff라는 name을 가진 input tag 가 여러가지이기 때문에, 그 중 한 가지만 가져올 수는 없다. 따라서 모든 parameter 값을 가져오기 위해 복수의 파라미터 값을 가져오는 request.getParameterValues(name); 을 사용한다. 이는 복수의 선택값을 갖는 checkbox 같은 type에 사용한다. 
				*/
			if(datas!=null){
				// if 절을 넣어준 이유는, 만약 사용자가 어떠한 것도 선택하지 않아서 datas가 null 이면 500 에러가 발생하기 때문에 그 에러를 방지하기 위해서이다. 
				for(String v:datas){
					// datas에 들어가 있는 데이터들을 데이터가 없을 때까지 순서대로 선택해서 출력한다. 
					out.println(v+"<br>");
					// out.println 대신 <%= %> 안에 넣어도 되지만, 그렇게 하면 코드의 가독성도 떨어지고 실용성도 없기 때문에 이렇게 사용한다. 
				}
			}
			else{
				out.println("좋아하는 과일이 없습니다!");
			}
			%></td>
		</tr>
	</table>
	<hr>
	<a href="start.html">처음으로 돌아가기</a>
```

## response 

먼저, a.jsp에서 action을 위해 to1.jsp, to2.jsp 파일을 만든다. 그리고 그 파일 안에서 어차피 페이지 주도권을 z.jsp에 줄 것이기 때문에 기타 html 태그가 전혀 의미가 없다. 따라서 html 관련 태그는 전부 삭제한다. 

[a.jsp](a.jsp)
```html
<form action="to1.jsp" method="post">
	<input type="text" name="data">
	<input type="submit" value="보내기1">
</form>
<HR>
<form action="to2.jsp" method="post">
	<input type="text" name="data">
	<input type="submit" value="보내기2">
</form>
```

[to1.jsp](to1.jsp)
```jsp
<%
	response.sendRedirect("z.jsp");
	// 페이지 제어권을 z.jsp로 넘겨줌
%>
```
[to2.jsp](to1.jsp)
```jsp
<jsp:forward page="z.jsp">
	<jsp:param value="abc" name="data2"/>
</jsp:forward>
```

to1.jsp와 to2.jsp는 기능페이지이다. 예를 들어 무언가를 예약하고 싶어서 예약 버튼을 누르면 잠시 로딩을 하다가 예약이 완료되었다는 결과 페이지로 넘어간다. 여기서 잠시 로딩을 하는 페이지가 바로 이러한 기능 페이지이다. 즉, ***사용자의 눈에는 보일 필요가 없으며 필요한 기능만 처리하는 페이지***인 것이다. 

[z.jsp](z.jsp)
```jsp
<body>
data는 <%=request.getParameter("data")%> 입니다.
<%=request.getParameter("data2")%>
</body>
```

위 코드에서 a.jsp를 실행시키고 보내기 1을 누르면 id값에 어떤 값을 넣어도 `data는 null 입니다` 라는 문구가 뜬다. url도 `~/z.jsp` 로 되어 있다. 왜 그럴까? 이 상황에 대해서는 `request` 와 `response` 에 대한 이해가 필요하다. 

***

[z.jsp](z.jsp) 에서 `request.getParameter("data")` 의 `request` 라는 내장객체는 **이전페이지에서 다음 페이지로 갈 때까지만 유효**하다. 더 정확히 말하자면, `request` 객체는 ***요청 페이지로부터 전송된 데이터를 응답 페이지에서 사용할 수 있게 하는 것*** 이다. 그렇기 때문에 `a.jsp` 에서 분명히 data 라는 이름의 input tag가 있었음에도 불구하고, 그 정보는 응답 페이지 `to1.jsp` 를 지나고 난 뒤에는 request로 불러올 수 없기 때문에 `data는 null 입니다` 라는 문구가 뜬다. null인 이유는 `z.jsp`에서 data라는 이름의 parameter 값이 *없기* 때문이다. 

|request range|a.jsp|to1.jsp|z.jsp|
|--|--|--|--|
|a.jsp의 request||O(`a.jsp` 의 응답 페이지이기 때문)|X(`a.jsp`의 응답 페이지가 아님)|

***

[to1.jsp](to1.jsp) 에서 `response.sendRedirect("z.jsp");` 라는 코드 때문에 요청 헤더, 즉 데이터가 교체되었다. 이는 url에서 `~/z.jsp` 라고 되어 있는 것에서 유추할 수 있는데, `response.sendRedirect(url);` 의 처리 과정은 다음과 같다.

|1|2|3|4|
|-|-|-|-|
|웹 브라우저가 a.jsp페이지 요청|a.jsp페이지 요청 수행|to1.jsp에서 `response.sendRedirect("z.jsp");` 이 문장을 만나면, 이 문장을 만나기 전까지 실행된 모든 코드 중 출력될 값을 버리고 z.jsp로 이동|z.jsp 페이지를 수행한 결과를 웹 브라우저로 응답하고 **z.jsp 페이지 내용만 처리 결과로 화면에 출력한다**.|

결론: **a.jsp 와 z.jsp 는 데이터를 공유하지 않는다**

그렇다면 데이터를 공유하려면 어떻게 해야 할까? 전에 배웠던 forward를 사용하면 된다. [to2.jsp](to2.jsp)처럼 forward를 사용하게 되면 데이터를 공유하면서 페이지 이동이 가능하고, URL이 다음 페이지로 변경되지 않음을 볼 수 있다. 
***
결론적으로, **request와 response는 scope가 굉장히 작다**는 의미로 받아들일 수도 있다. 그렇기 때문에 request 같은 객체를 로그인 혹은 장바구니 같은 기능에 사용해서는 안 될 것이다. 

## session 














```
[내장객체]
 JSP ---> Servlet파일로 변환
	application,session,out,request,...
 내장객체들은 주로 데이터를 주고받을때 사용됨

1. request
이전페이지->다음페이지

2. response
response.sendRedirect("z.jsp");
요청헤더(==데이터)를 교체해버림

★정보 유지 범위 scope
 - 정확하게 범위세팅하는것은 중요!
 - 장바구니: localxxx
	   로그인을 해서 이용하는 경우, 사용자에게 설정
	   실수로 창을 닫아버림....헉.......
	   유지가 안되는경우도있음...... == 브라우저 범위

3. session
브라우저 단위로 데이터 유지
ex) 로그인,장바구니,커피 어플 등에 사용됨


4. application
서버 scope



☆ 장바구니
☆ SNS






