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

