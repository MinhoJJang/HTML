# 설계순서

**요구사항->기능/디자인->기능추가,완성도 높이기->버그테스트->배포,서비스**

"자동차" 를 만든다고 치면...
요구사항에 맞는 기능들을 선구현,
단계별로 점진적 발전을시켜나감

스케이드보드---UI/UX--->>전동킥보드---디자인--->오토바이---기능추가--->자동차\
(					  			  )--------->스쿠터  
**이런식으로 언제든 롤백할 수 있도록 예전의 데이터를 보관해놓아야함**

v1.0.0\
v1.0.1  -> 1.0.2에서 더이상 진행하기 싫다? 그럼 1.0.1로 돌아온 다음에 1.0.1의 			다음 버전을 만들자.\
v1.0.2     v1.1.1 ...\
	   	  
"버전관리"==형상관리
"패치노트"v1.0.1★★★

# 주석관리

```jsp
<!-- 주석 1 -->
-> 웹에서 콘솔을 열었을 때 이 내용의 주석이 보인다. 

<%-- 주석 2 --%>
-> 주석 1과는 다르게, 웹에서 이 주석의 내용이 보이지 않는다. 
```

# 지시어 (<%@ ... %>)

지시어는 액션과 다르게 **닫히는 태그가 없다**. 
```jsp
<%@ ... %>
```
닫히는 태그란 html의 head, html, body, title 같은 태그들을 의미한다. 
```html
<body> 
	...
</body>
```

## page 

1. import 

+ import: 해당 클래스를 선언해줄 때 사용한다. 
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
```

2. errorPage + isErrorPage

+ errorPage: 만약 에러가 난다면 그곳으로 페이지를 이동시키라는 의미\
[test.jsp](test.jsp)
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
```

+ isErrorPage: 이 jsp 파일이 에러 전용 페이지인지 알려주는 의미로 사용됨. default값은 false\
[error.jsp](error.jsp)
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
```

## include

다른 페이지의 내용을 포함시킬 수 있음. 위 태그는 ***.jsp 파일들을 포함시킨 후*** 에 컴파일하기 때문에, ***정적인 페이지*** 가 유리하다. 

```jsp
<body>
<%@ include file="news.jsp" %>
</body>
```

```jsp
<body>
<table border="1">
	<tr>
		<td>apple</td>
		<td>banana</td>
	</tr>
</table>
</body>
```

## taglib 

커스텀 태그를 사용할 때 사용한다. 지금까지는 html 에서 기본으로 제공하는 태그를 사용했지만, 후에 직접 태그명을 만들어서 사용할 것이다.\
<br>

# 액션 ( < jsp: ... > ... </ jsp: ... >)

액션은 닫히는 태그가 있다. 
```jsp
<jsp: ...>
	tag body
</jsp: ...>
```

## include 

지시어의 include와는 다르게, 액션의 include는 ***.jsp 파일들을 실행시점에서 호출*** 한다. 따라서 ***동적인 페이지*** 에서 유리하다. 즉, 파라미터를 불러오는 작업에서 유리하다. 하는 일 자체는 똑같다. 다른 페이지의 내용을 현재 페이지에 불러오는 것이다. 

아래 코드에서, include 액션 내에서 param이라고 하는 액션을 넣고 value값과 name을 지정해 줄 수 있다. 그리고 이 값들을 이용하기 위해 news.jsp에서 request(내장객체)를 이용해 getParameter(메서드)로 value값을 가져올 수 있다. 

[include](test2.jsp)
```jsp
<jsp:include page="news.jsp">
	<jsp:param value="apple" name="a"/> 
	<jsp:param value="banana" name="b"/>
</jsp:include>
```

[news](news.jsp)
```jsp
뉴스광고
<%=request.getParameter("a")%>
<%=request.getParameter("b")%>
```

## useBean

useBean 액션은 혼자 사용되지는 않고, setProperties + getProperties 가 같이 사용된다. 이것의 자세한 의미는 11/22(월) 에 알아보자...
// TODO 

Bean 은 흔히 javaBeans라고 부르는 그것이 맞다. 이는 자바 **객체**를 의미하는데 서버 프로그래밍에서 **객체**가 갖는 의미는 **기능**이다. 

즉, "Bean을 추가했다" 라는 말의 의미는 곧 "기능을 추가했다" 라는 의미와 일맥상통한다. 그리고 만약 "그 기능이 없다" 라고 한다면 Servlet 파일에서 무언가를 추가하는 것이 아니라, 새로운 Class을 만들고 그 Class가 기능을 수행할 수 있도록 만들어야 한다. 

#### 객체 == 기능 ?? 
어떻게 서버 프로그래밍에서는 객체가 기능을 수행할까?\
[CalcServlet.class](https://github.com/MinhoJJang/Web_study/blob/main/Web3/JSP/jsp_1117/CalcServlet.class) 에서, 미리 계산 기능을 구현한 [Calc.java](https://github.com/MinhoJJang/Web_study/blob/main/Web3/JSP/jsp_1117/Calc.java) 파일을 만든 뒤 아래와 같은 코드를 삽입했었다. 
```java
Calc calc=new Calc(num1,num2,op);
		res=calc.getRes();
```
객체를 생성하면서 그 클래스의 생성자가 실행이 되고, 우리는 그 생성자를 통해서 기능을 수행하기 때문에 __객체 == 기능__ 인 것이다.  

만약에, 계속 Servelet 파일 내부에 클래스를 만들고 기능을 구현한다면, 당장 기능은 제대로 실행되겠지만, 추후에 변경사항이 생겼을 경우 코드 재사용과 유지보수에 어려움이 있을 수밖에 없다!

아무튼 이런식으로 클래스를 만들고 객체를 생성할 때, 그 객체를 항상 import해주어야 하는데 이것을 한번에 import해주는 방법이 바로 useBean을 사용하는 것이다. 이것의 자세한 이용법은 추후에 기술하겠다. 
// TODO 

## forward 

forward의 키워드는 **제어권** 이다. 이를 include와 비교해서 살펴보자. 

[action: forward](test3.jsp)
```jsp
확인
<jsp:forward page="news.jsp">
	<jsp:param value="apple" name="a"/>
	<jsp:param value="banana" name="b"/>
</jsp:forward>
```

[action: include](test2.jsp)
```jsp
확인
<jsp:include page="news.jsp">
	<jsp:param value="apple" name="a"/>
	<jsp:param value="banana" name="b"/>
</jsp:include>
```

이들을 직접 실행시켜보면, include에서는 "확인" 이라는 문구가 나오지만 forward에서는 "확인" 이라는 문구가 나타나지 않는다. 그 이유는 **forward가 제어권을 다음 페이지, 즉 forward 대상 페이지로 제어권을 넘겨주기 때문에, 이전 페이지의 내용을 볼 수 없다**. 

여기서 한가지 의문점이 들 수 있다. 왜 "확인"이라는 문구가 "forward" 키워드 보다 앞에 있음에도 불구하고 화면에 출력되지 않을까? 

근본적으로, **브라우저와 콘솔은 다르다**. **콘솔에서는 지금까지 했던 모든 행동들이 stack 에 남아서 사용자에게 보여준다**. **그러나 브라우저는 주도권을 가진 하나의 페이지만 출력해 준다**. 따라서 브라우저에서는 전에 무슨 행동을 했던, 다른 브라우저로 넘어온 이상 전 브라우저의 내용이 보여지지 않는다. _예를 들어 네이버에서 구글로 이동했다면, 구글에서 네이버의 내용이 보이지 않는다._ 마찬가지로 이미 제어권을 넘겨준 이상, 이전 페이지의 내용을 볼 수 없기 때문에 [action: forward](test3.jsp)에서 "확인" 이라는 문구가 출력되지 않는 것이다. 

# JSP 내 자바식

- <%! %> 선언식 -> 변수 혹은 메서드를 선언할 때 사용한다.
```jsp
<%!
     public int sum(int i, int j){
           return i + j;
     }
%>
```

- <%= %> 표현식 -> out.println(); 에 들어간 것과 똑같은 의미이다.
```jsp
<%= sum(1, 3) %>

<%=result%> 
out.println(result);

둘 다 똑같은 결과이다. 
```

- <%  %> 스크립트릿 -> 자바 코드를 실행할 때 사용되는 코드의 블록을 말한다. 
```jsp
<%
     int i = 10;
     int j = 20;
     int sum = i + j;
%>
```





