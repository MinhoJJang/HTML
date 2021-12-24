DispatcherServlet == FrontController
    @Autowired
 -> 핸들러매핑클래스

	new->.xml OR @

HandlerMapping 클래스
Mapper가 존재함!
String -> controller객체
	Controller 인터페이스
	각각의 Controller 클래스 implements Controller

ViewResolver가 처리완료된 요청들을 맞는 View로 이동시킴!

=> C 인터페이스
C 클래스 implements C
HM
VR

### 클라이언트 요청
-> DS(*.do) -> HM: C검색 -> 요청처리 -> VR -> V 도착!


***

1. 먼저 컨트롤러 Interface를 만들어준다. 
`Controller.java`
```java
package com.test.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	String handleRequest(HttpServletRequest request, HttpServletResponse response);
	// : req,res 사용자의 요청정보를 받아서 -> 화면 네비게이션
}
```

2. 그 컨트롤러를 상속받는 클래스를 만들어준다. 
```java
package com.test.app.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.app.controller.Controller;
import com.test.app.member.impl.MemberDAO;

public class LoginController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		MemberVO vo=new MemberVO();
		vo.setMid(request.getParameter("mid"));
		vo.setPassword(request.getParameter("password"));
		MemberDAO dao=new MemberDAO();
		MemberVO data=dao.selectOne(vo);
		if(data!=null) {
			// 세션에 정보 저장
			return "main.do";
		}
		else {
			return "login";
			// VR: 확장자여부확인 -> .jsp 자동추가
		}
	}
}
```

3. 사용자의 요청을 보고 컨트롤러 객체를 검색하는 클래스, Handler Mapping을 만들어준다. Map을 활용하여 String과 Controller을 묶어주자. 
```java
package com.test.app.controller;

import java.util.HashMap;
import java.util.Map;

import com.test.app.member.LoginController;

public class HandlerMapping {

	private Map<String,Controller> mappings;
	
	public HandlerMapping() {
		mappings=new HashMap<String,Controller>();
		mappings.put("/login.do", new LoginController());
	}
	
	// 사용자의 요청을 보고 C객체를 검색
	public Controller getController(String action) {
		return mappings.get(action);
	}
	
}
```


4. 이제 ViewResolver를 통해 View로 갈 수 있는 경로를 가져올 수 있도록 만들자. 여기서 `getView()`를 해주면, 우리가 설정한 값에 따라 prefix과 suffix가 붙어서 자동으로 적절한 View의 이름을 가져올 수 있다. 
```java
public class ViewResolver {
	
	public String prefix;
	public String suffix;
	
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getView(String viewName) {
		return prefix+viewName+suffix;
	}
}
```

5. 이것들을 전부 종합하여 DispatcherServlet에서 사용한다. 
```java
package com.test.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HandlerMapping handlerMapping;
    private ViewResolver viewResolver;
    
    public void init() {
    	handlerMapping=new HandlerMapping();
    	viewResolver=new ViewResolver();
    	viewResolver.setPrefix("./");
    	viewResolver.setSuffix(".jsp");
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		String action=uri.substring(uri.lastIndexOf("/"));
		System.out.println(action);
		
		Controller ctrl=handlerMapping.getController(action);
		String viewName=ctrl.handleRequest(request, response);
		
		if(!viewName.contains(".do")) {
			viewName=viewResolver.getView(viewName);
		}		
		response.sendRedirect(viewName);
	}
}
```

## 

이제 view작업을 위해 jstl을 추가해주자. pom.xml에서 dependency를 추가해주면 된다. 
```xml
<!-- JDBC 드라이버 설정 -->
      	<dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <version>8.0.22</version>
        </dependency>
        
<!-- JSTL jar 2개 추가 -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
        <dependency>
            <groupId>taglibs</groupId>
            <artifactId>standard</artifactId>
            <version>1.1.2</version>
        </dependency>
```