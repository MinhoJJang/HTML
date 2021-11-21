# SHOP 

장바구니 시스템을 만들어보자. 

|login|product|add|result|
|-|-|-|-|
|맨 처음 실행시키는 로그인 페이지|상품목록을 보여주고 사용자가 상품을 장바구니에 담을 수 있게 해주는 페이지|사용자가 상품을 담으면 장바구니를 만들고 상품을 저장하는 페이지|장바구니에 담긴 상품 목록 조회하는 페이지|

## login

[login.jsp](shop_login.jsp)
```jsp
<body>
	<h1> 로그인페이지	</h1>
	<form action="shop_product.jsp" method="post">
		<!-- form을 제출하면 shop_product로 가도록 한다. -->
		<input type="text" name="uid">
        <!-- user id를 입력받아 제출. 이름은 uid. 
        product 페이지에서 request.getParameter(name); 로 값을 받을 것임 -->
		<input type="submit" value="로그인하기">
	</form>
</body>
```

## product

[product.jsp](shop_product.jsp)
```jsp
<%
	session.setAttribute("uid", request.getParameter("uid"));
	// uid를 가져오는데, uid를 단순히 request로만 가져오게 되면 그 데이터가 유지되지 않기 때문에 uid값을 session에 담아준다. 
%>
...
<body>
	
	<%=session.getAttribute("uid") %>님, 환영합니다!<br>
    <!-- 위에서 session에 담아준 uid값을 가져온다. -->
	<h1>상품목록</h1>
	<hr>
	<form action="shop_add.jsp" method="post">
		<select name="product">
			<option>상품 1</option>
			<option>상품 2</option>
			<option>상품 3</option>
			<option>상품 4</option>
			<option>상품 5</option>
		</select>
		<input type="submit" value="장바구니에 추가">
        <!-- 다음 페이지인 add 에서 선택한 옵션이 무엇인지 알 수 있도록 해주었다. -->
	</form>
	<hr>
	<a href="shop_result.jsp">결제하기</a>
</body>
```

## add

[add.jsp](shop_add.jsp)
```jsp
<%
	request.setCharacterEncoding("UTF-8");
    // 한글 값도 받기 위해 인코딩 변경

	String product= request.getParameter("product");
    // shop_product.jsp 에서 선택한 상품의 parameter값을 product 라는 이름의 변수에 넣어준다. product 처럼, 일반적으로 연관있는 데이터를 가리키는 값들은 이렇게 이름을 맞춰 쓰는 편이다. 

	ArrayList<String> datas = (ArrayList)session.getAttribute("datas");
    // ArrayList를 사용해 장바구니를 만들 것이다. 당연히 import는 앞에서 해준다.

	if(datas==null){
		datas = new ArrayList<String>();
		session.setAttribute("datas", datas);
	}
	// 장바구니를 새로 만들고 세션에 넣는다
	// 그런데 이 과정을 매번하는 것이 아니라 최초의 한번만 하면 된다. 
    // 처음으로 장바구니에 상품을 추가할 때만 1회 생성됨. 

	datas.add(product);
%>
...
<body>
	<script type="text/javascript">
		alert("<%=product%>가 장바구니에 추가되었습니다");
		history.go(-1); 
        <!-- 바로 직전 페이지로 가겠다는 명령  -->
	</script>
	
</body>
```

코드 설명:       

|`String product= request.getParameter("product");`|`ArrayList<String> datas = (ArrayList)session.getAttribute("datas");`|`if(datas==null){datas = new ArrayList<String>();session.setAttribute("datas", datas);}`|`datas.add(product);`|
|-|-|-|-|
|product 페이지에서 선택한 상품의 이름을 String 타입의 product에 받는다|`ArrayList<String> datas` 을 만들고 그곳에 datas라는 이름을 가진 데이터들을 세션단위로 ArrayList 에 넣는다|이때 ArrayList 에 아무것도 없으면, 장바구니가 만들어지지 않았다는 의미이므로 새 장바구니를 만들어 준다|여기까지 도착하면, 무조건 장바구니가 존재한다는 의미이므로 그냥 바로 datas에 물품을 add 해준다|

## result

[result.jsp](shop_result.jsp)
```jsp
<body>

<h1>선택한 상품 목록</h1>
<hr>
<%
	ArrayList<String> datas=(ArrayList)session.getAttribute("datas");
    // add 페이지에서 사용했던 datas 라는 객체가 session 단위로 남아 있을 테니, result 페이지에서도 데이터가 유지된다. (request, response와는 다른 차이점)
    
	if(datas!=null){
        // 만약 장바구니가 비어있다면 500 에러가 발생하게 되는데, 이를 방지하기 위해 혹시나 datas가 null일 경우를 체크해주자. 
        // 장바구니가 비어있다는 의미는, add페이지를 가지 않았다는 소리이므로 datas에 아무것도 담겨있지 않을 것이다. 
		out.println("<ol>");
		for(String v:datas){
            // datas에 있는 물품들을 차례대로 꺼낸다. 
			out.println("<li>"+v+"</li><br>");
		}
		out.println("</ol>");
	}
	else{
		out.println("<h3>선택한 상품이 없습니다!</h3>");
	}
%>

</body>
```