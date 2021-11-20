# 내장객체

JSP 파일은 결국 Servlet 파일로 변환된다. 이때 application, session, out, request 같은 내장객체들이 자동으로 제공된다. 이러한 객체들은 데이터를 주고받을 때 사용된다.

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
			<!-- value값은 전부 다르게 하지만, 과일이라는 공통점이 있는 것들의 이름을 전부 'ff'로 맞춰서 후에 불러오기 편해지도록 하였다. -->
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="보내기"></td>
		</tr>
	</table>
</form>
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






