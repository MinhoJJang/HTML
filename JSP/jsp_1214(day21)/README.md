# Controller Modification 

지금까지 우리는 controller.jsp 파일을 통해 컨트롤러 역할을 수행하는 로직을 구현하였다. 그러나, 로직이 변경되는 경우가 생겼을 때, jsp파일을 바꾸는 것은 곧 서블릿파일이 바뀌는 것이므로 서버에 부담을 줄 수 있으나, java파일을 통해 관리하면 jsp파일이 변경되지 않기 때문에 서버의 부담을 줄일 수 있다.

이제, servlet 파일을 만들어서 그 파일이 컨트롤러 역할을 하도록 변경할 것이다. 
```java
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
// 사용자의 모든 요청을 FC로 향하게 해야함!!!! 
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDO(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDO(request,response);
	}
	
	private void actionDO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// 1. 요청을 파악
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		String cp = request.getContextPath();
		System.out.println(cp);
		
		String command = uri.substring(cp.length());
		System.out.println(command);	
	}

}
```

이러한 작업을 하면, 사용자의 요청을 일단 전부 다 위의 서블릿 파일로 가도록 하는 모종의 장치가 필요하다. 생각해보자. 우리의 index 파일을 보면 `response.sendRedirect("board_controller.jsp?action=main")` 이런 식으로 파라미터를 url에서 넘겨주었다. 그러나 이는 통일성이 부족하고 가독성의 문제가 생길 여지가 높다. 따라서 우리는 `pageContext.forward("main.do");` 이런 식으로 만들어서 일단 무슨 액션이든 간에 컨트롤러로 가도록 해야 할 것이다. 그러기 위해서는 서버를 실행시키기 전에 사용자의 요청을 servlet 파일로 redirect하도록 해야 하는데, 이 역할을 바로 web.xml이 수행할 것이다. 왜냐? 서블릿 파일은 xml파일이나 Filter파일과는 다르게 서버 실행 시 우선시되지 않기 때문이다. 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app>
	<servlet>
		<servlet-name>fc</servlet-name>
		<servlet-class>controller.FrontController</servlet-class>
	</servlet>
	
	<servlet-mapping>
		<servlet-name>fc</servlet-name>
		<url-pattern>*.do</url-pattern>
	</servlet-mapping>

</web-app>
```

이렇게 만들면 *.do 라고 끝나는 요청에 대해 전부 `controller.FrontController` 로 가도록 하게 하는 역할을 해준다. 

***

컨트롤러의 역할을 java파일로 옮기는 코딩을 수행해보자. 그렇다면 먼저 controller.jsp가 어떤 역할을 했는지 파악하는 것부터 해야 할 것이다. 

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,model.BoardVO" errorPage="error.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="dao" class="model.BoardDAO" />
<jsp:useBean id="vo" class="model.BoardVO" />
<jsp:setProperty property="*" name="vo"/>
<%
	String action=request.getParameter("action");

	if(action.equals("main")){
		ArrayList<BoardVO> datas=dao.selectAll();		
		request.setAttribute("datas", datas);
		pageContext.forward("main.jsp");
	}
	else if(action.equals("board")){		
		BoardVO data=dao.selectOne(vo);
		request.setAttribute("data", data);
		pageContext.forward("board.jsp");
	}
	...
%>
```

위 jsp 코드의 로직적인 순서를 알아보자. 

1. 인코딩 변경 
```java
request.setCharacterEncoding("UTF-8");
```

2. 객체생성 
```jsp
<jsp:useBean id="dao" class="model.BoardDAO" />
<jsp:useBean id="vo" class="model.BoardVO" />
<jsp:setProperty property="*" name="vo"/>
```

3. 사용자 액션 감지
```java
String action=request.getParameter("action");
```

4. 사용자 액션에 따른 로직 구현 
```java
if(action.equals("main")){
		ArrayList<BoardVO> datas=dao.selectAll();		
		request.setAttribute("datas", datas);
		pageContext.forward("main.jsp");
	}
```

여기서 재미있는 점은, jsp 파일이지만, jsp에서만 사용할 수 있는 useBean 액션을 제외하고는 전부 스크립트릿으로 이루어진 **자바코드**라는 것이다. 즉, 이는 곧 우리가 컨트롤러를 자바파일로 관리할 수 있음을 눈치챌 수 있다. 

***

이제 우리가 만든 FrontController.java 파일을 수정해보자. controller.jsp 파일에 있던 기능들을 하나씩 변경해보도록 하겠다. 

1. 인코딩 변경

먼저 인코딩 변경의 경우 필터를 만들어서 해결하였다. 이때 필터를 적용할 확장자는 `*.do` 도 있지만, view 에서 view 로 바로 가는 경우도 존재하기 때문에 `*.jsp` 도 추가해준다. 초기화 매개변수 등을 사용하여 EncFilter.java 파일을 만들어주었다. 

```xml
 <context-param>
 	<param-name>encoding</param-name>
 	<param-value>UTF-8</param-value>
 </context-param>
```

```java
package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncFilter
 */
@WebFilter({"*.do" , "*.jsp"})
public class EncFilter implements Filter {
private String encoding; 
    /**
     * Default constructor. 
     */
    public EncFilter() {
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding); 
		response.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.encoding=fConfig.getServletContext().getInitParameter("encoding");
	}
}
```

2. 액션값 추출 

jsp에서는 `request.getParameter` 으로 액션값을 가져올 수 있었지만, 이제 우리는 uri에 더이상 `action=main`으로 파라미터값을 전달하지 않는다. 그렇기 때문에 전달받은 uri값 중에서 우리가 원하는 값만 따로 잘라내서 어떤 액션을 원하는지 감지해야 한다. 

```java
String uri = request.getRequestURI();
System.out.println(uri);

String cp = request.getContextPath();
System.out.println(cp);

String command = uri.substring(cp.length());
System.out.println(command);
```
이것을 실행시키면, 콘솔창에 아래와 같은 결과가 나온다. 
```r
/day21_C/main.do
/day21_C
/main.do
```

이렇게 하면, command 값이 곧 우리가 감지해야 할 액션값이라는 것을 알 수 있다. 

3. ActionForward 클래스 생성 

우리는 이제 더 이상 하드코딩을 하지 않기 위해 컨트롤러를 자바 파일, 서블릿으로 바꾸었다. 따라서, 아래와 같이 uri값에 하드코딩을 할 수 없다. 
```java
request.setAttribute("data", data);
pageContext.forward("board.jsp");

...

if(dao.insert(vo)){
	response.sendRedirect("controller.jsp?action=main");
}
```
그렇다면 무엇을 알아야 할까? 일단 하드코딩 되어 있는 저 uri값을 가져올 수 있어야 하며, 데이터를 다음 페이지에 전달하는지에 대한 유무도 알고 있어야 한다. 즉, 어떤 객체를 만들어서 그 객체에 위의 두 가지 정보를 담을 수 있게 만들어야 한다. 그래서 `ActionForward.java` 라는 VO를 만들어 준다. 

```java 
public class ActionForward {
	private boolean redirect; // 데이터 전송여부 
	private String path; // 페이지 정보 
	
	// .sendredirect T -> 전달할 정보가 없음
	// .forward F -> 전달할 정보 있음 
	
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
```

4. 인터페이스 생성 

이제 우리는 수많은 액션을 수행할 코드를 작성해야 한다. 예를 들어 main 이라는 액션이 들어오면 뭔가를 해야 하고, board 라는 액션이 들어오면 저걸 하고 ... 이런식으로 해야 하는데, 앞에서도 언급했듯이, 우리는 서블릿 파일에 더이상 하드코딩을 하지 않는다. 

따라서 이러한 액션을 처리하는 코드는 다른 자바 파일에 위치해야 하고, 그 자바파일을 실행시키면 본래 controller.jsp에 있었던 아래 코드를 실행시킨 것과 같은 결과가 나오게 만들어야 한다. 
```java
if(action.equals("main")){
		ArrayList<BoardVO> datas=dao.selectAll();		
		request.setAttribute("datas", datas);	
		pageContext.forward("main.jsp");		
	}
```

이러한 역할을 하는 자바 파일을 우리는 MainAction.java, BoardAction.java ... 이렇게 부를 것이고, 그 자바 파일들의 공통점은 모두 Action을 한다는 것에 있다. 따라서 인터페이스로 Action들 사이의 공통점을 만들어줌으로써 유지보수와 재사용성을 높이는 코드를 만들 수 있다. 

```java
public interface Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
```

5. 액션 클래스 생성 

Action을 상속받는 여러 클래스들을 만들어주면 된다. 예를 들어 InsertAction.java 파일을 만들 때 아래와 같이 만들어주면 된다. 

```java
public class InsertAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardVO vo=new BoardVO();
		vo.setWriter(request.getParameter("writer"));
		vo.setContent(request.getParameter("content"));
		vo.setTitle(request.getParameter("title"));
		BoardDAO dao=new BoardDAO();
		dao.insert(vo);
		
		ArrayList<BoardVO> datas=dao.selectAll();
		request.setAttribute("datas", datas);
		
		ActionForward forward=new ActionForward();
		forward.setPath("main.jsp"); 
		forward.setRedirect(false);
		return forward;
	}
}
```