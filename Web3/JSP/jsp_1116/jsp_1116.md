# JSP & SERVLET

> [JSP&SERVLET BLOG](https://m.blog.naver.com/acornedu/221128616501)   

> [JSP&SERVELT TISTORY](https://mangkyu.tistory.com/14)
<HR>  

## Servlet

**html을 Java내부에서 작성가능**
- 클래스파일의 한 종류이다.
- 일반적으로 선언,생성하여 사용하는 클래스를 "POJO"라고 하는데, 다음과 같은 클래스를 의미한다.

```java
class Student{
}
class Car{
}
```
**POJO => Plain Old Java Object**\
그 의미는 자바 언어를 사용해야 한다는 제한 외에 어떠한 제한도 없는 자바 오브젝트이다. 

"POJO가 아닌 클래스" : Servlet/필터/리스너 이들은 생성,관리방법이 특이해서 사용하기에 불편함이 있다. 그래서 그 단점을 보완하고자 만든 것이 JSP이다. 

SERVELT이 왜 불편하냐고 묻는다면 아래 두 코드를 비교해 보자. 

1. JSP
```html
<form action="./CalcServlet.java" method="post">
	   <input type="text" name="num1">
	   <select name="op">
	      <option selected>+</option>
	      <option>-</option>
	   </select>
	   <input type="text" name="num2">
	   <input type="submit" value="계산하기">
	</form>
```

2. SERVLET
```java
out.println("<HTML>");
out.println("<BODY>");
out.println("<h1>"+ num1 + op + num2 + "= </h1>");
out.println("<h1>계산결과는"+ res + "입니다.</h1>");
out.println("</BODY>");
out.println("</HTML>");
```

## JSP

**JSP => Java Server Page**
**HTML내부에서 Java코드를 작성**

- 서블릿 기반의 스크립트 기술. 
- 서블릿으로만 웹을 만들게 되면 너무 비효율적이기 때문에, 서블릿을 작성하지 않고도 간편하게 웹프로그래밍을 구현하게 만든 기술이다. 
- 결국에 Servlet파일로 변환된다. 
- 서블릿 컨테이너==톰캣 서버
- JSP에서 작성한 <%= 표현식코드는 out.println()에 들어감
- JSP->Servlet변환되면, application/session/out 등의 "내장객체"가 기본제공됨


