# Spring

## HandlerMapping

지금까지, HandlerMapping을 통해서 들어오는 액션값에 따라 어떤 컨트롤러를 사용할지, 정해주었다. 객체 생성을 xml에서 해줄 수 있기 때문에, HandlerMapping.java 파일의 내용을 DispatcherServlet-serlvet.xml 파일로 옮기자. 

`HandlerMapping.java` 
```java
package com.test.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.mvc.Controller;

import com.test.app.board.BoardController;
import com.test.app.board.MainController;
import com.test.app.member.LoginController;
import com.test.app.member.LogoutController;

public class HandlerMapping {

	private Map<String,Controller> mappings;
	
	public HandlerMapping() {
		mappings=new HashMap<String,Controller>();
		mappings.put("/login.do", new LoginController());
		mappings.put("/logout.do", new LogoutController());
		mappings.put("/main.do", new MainController());
		mappings.put("/board.do", new BoardController());
	}
	
	// 사용자의 요청을 보고 C객체를 검색
	public Controller getController(String action) {
		return mappings.get(action);
	}
}
```

이 파일을 없애고, xml에서 사용자의 요청에 따라 map을 사용해 property를 정해주자. 

`DispatcherServlet-serlvet.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

   <bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
      <property name="mappings">
         <props>
            <prop key="/login.do">login</prop>
            <prop key="/main.do">main</prop>
            <prop key="/board.do">board</prop>
            <prop key="/logout.do">logout</prop>
            <prop key="/insertBoard.do">insert</prop>
            <prop key="/deleteBoard.do">delete</prop>
            <prop key="/updateBoard.do">update</prop>
         </props>
      </property>
   </bean>

   <bean class="com.test.app.member.LoginController" id="login" />
   <bean class="com.test.app.member.LogoutController" id="logout" />
   <bean class="com.test.app.board.MainController" id="main" />
   <bean class="com.test.app.board.BoardController" id="board" />
   <bean class="com.test.app.board.InsertBoardController" id="insert" />
   <bean class="com.test.app.board.UpdateBoardController" id="update" />
   <bean class="com.test.app.board.DeleteBoardController" id="delete" />
</beans>
```

## ViewResolver

우리는  View 페이지를 두갈래로 나눠서 생각할 수 있다. 데이터를 보여주는지, 안보여주는지. 이때, 컨트롤러를 통해야만 하는, 데이터를 보여주는 파일들은 WEB-INF에 넣는다. 그 이유는 WEB-INF 내부 파일들은 브라우저에서 바로 읽을 수 없도록 되어 있기 때문에, 내부 View를 읽으려면 우회해서 들어가야 한다. 즉, ViewResolver을 통해서만 이동할 수 있게, 만의 하나 데이터없이 페이지전환이 이루어지는 것을 막기 위해 만드는 것이다. 

따라서, HandlerMapping 클래스를 xml에서 만들어주었듯이 기존의 ViewResolver 역시 xml에서 생성해준다.

```java
package com.test.app.controller;

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

```xml
<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/WEB-INF/test/" />
    <property name="suffix" value=".jsp" />
</bean>
```

## Controller Setting

이제 Controller파일로 가서, 기존에 main.do 나 login.jsp같은 요청을 보낼 때 어떻게 동작할지 다시 설정해주어야 한다. 기존 LoginController를 예시를 들어보고 전체적인 흐름을 파악해 보자. 

```java
package com.test.app.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.test.app.member.impl.MemberDAO;

public class LoginController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		MemberVO vo=new MemberVO();
		vo.setMid(request.getParameter("mid"));
		vo.setPassword(request.getParameter("password"));
		MemberDAO dao=new MemberDAO();
		MemberVO data=dao.selectOne(vo);
		
		ModelAndView mav = new ModelAndView();	
		if(data!=null) {
			mav.addObject("member", data);
			mav.setViewName("main.do");			
		}
		else {
			mav.setViewName("login.jsp");			
		}
		return mav;
	}
}
```

이때 main.do로 이동하고 싶지만, 그냥 이렇게 써버리면 ViewResolver가 Prefix, Suffix를 붙여버린다. 따라서 아래와같이 즉시 이동하라는 메세지를 보내주어야 한다. 
```java
if(data!=null) {
    mav.addObject("member", data);
    mav.setViewName("redirect:main.do");		
}
else {
    mav.setViewName("redirect:login.jsp");
}
```
이때 왜 `redirect:main.do`인가? 만약 main.jsp로 하게 되면, main.jsp로 즉시 이동하는 것이기 때문에 View에서 View로 이동하게 된다. 그러나 main은 데이터가 필요한 페이지이기 때문에, Controller을 반드시 거쳐야 한다.

즉, ViewResolver을 통해서 데이터를 받아와야 하는 페이지가 아닐 경우에만 `redirect:` 을 사용하면 된다. 위와 같이 만들면, LoginController에서 로그인에 성공할 경우, main.do 요청을 하게 되는데 이때 모든 .do 요청은 아래와 같이 web.xml 의 xml 설정에 따라 DispatcherServlet으로 이동한다. 
```xml
  <servlet>
    <description></description>
    <display-name>DispatcherServlet</display-name>
    <servlet-name>DispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!-- 스프링 프레임워크에서 기본제공하는 클래스로 변경 -->
  </servlet>
  
  <servlet-mapping>
    <servlet-name>DispatcherServlet</servlet-name>
    <url-pattern>*.do</url-pattern>
  </servlet-mapping>
```

그러면 아래와 같이 DispatcherServlet 파일에서 .do요청을 받으면 해당하는 컨트롤러를 불러오게 된다. 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

   <bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
      <property name="mappings">
         <props>
            <prop key="/login.do">login</prop>
            <prop key="/main.do">main</prop>
            <prop key="/board.do">board</prop>
            <prop key="/logout.do">logout</prop>
            <prop key="/insertBoard.do">insert</prop>
            <prop key="/deleteBoard.do">delete</prop>
            <prop key="/updateBoard.do">update</prop>
         </props>
      </property>
   </bean>

   <bean class="com.test.app.member.LoginController" id="login" />
   <bean class="com.test.app.member.LogoutController" id="logout" />
   <bean class="com.test.app.board.MainController" id="main" />
   <bean class="com.test.app.board.BoardController" id="board" />
   <bean class="com.test.app.board.InsertBoardController" id="insert" />
   <bean class="com.test.app.board.UpdateBoardController" id="update" />
   <bean class="com.test.app.board.DeleteBoardController" id="delete" />
   
   <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
      <property name="prefix" value="/WEB-INF/test/" />
      <property name="suffix" value=".jsp" />
   </bean>
   
</beans>
```
그러면 들어온 요청이 main.do 이기 때문에, MainController으로 이동한다. 
```java
package com.test.app.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.test.app.board.impl.BoardDAO;

public class MainController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
	
		BoardVO vo=new BoardVO(); 
		BoardDAO dao=new BoardDAO();
		List<BoardVO> datas=dao.selectAll(vo);
		
		HttpSession session=request.getSession(); // 지금은 세션으로 처리
		session.setAttribute("datas", datas);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		mav.addObject("datas", datas);
		
		return mav; // VR가 viewName처리
	}
}
```
이때 `mav.setViewName("main");` 이 있으므로, ViewResolver으로 이동한다. 

```xml
<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
      <property name="prefix" value="/WEB-INF/test/" />
      <property name="suffix" value=".jsp" />
</bean>
``` 
이에 따라 `/WEB-INF/test/main.jsp` 로 이동하고, 페이지를 불러오게 된다. 