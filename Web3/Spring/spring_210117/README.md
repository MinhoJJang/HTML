# 설정들의 위치 

1. `@` 
    - java파일에 위치
    - 잘 바뀌지 않는 설정에 대해 사용 

2. xml 
    - 잘 변경되는 사항에 대해 사용


## 기존 코드의 문제점  
```java
package com.test.app.board;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.app.board.impl.BoardDAO;

@Controller
public class InsertBoardController {

	@RequestMapping(value="/insertBoard.do")
	public void insertBoard(HttpServletRequest arg0) {
		BoardVO vo=new BoardVO();
		vo.setContent(arg0.getParameter("content"));
		vo.setTitle(arg0.getParameter("title"));
		vo.setWriter(arg0.getParameter("writer"));
		BoardDAO dao=new BoardDAO();
		dao.insertBoard(vo);		
	}
}
```

기존의 문제점 
 - new로 객체생성을 해주어야함. 
 - Controller 을 import 받는다는것 자체가 Servlet의 강제가 들어가게 되어서 경량이 아니게 되어버림. 

## Command 객체를 사용한 변경 코드 
따라서 이러한 문제점을 한번에 잡아주는 **Command 객체** 를 사용할 것이다. 

VO객체를 new해주고 set도 해준다. 사용자의 입력값을 추출하고, 객체를 생성하고 setter 해주는 역할을 바로 Command 객체가 해준다. 

이렇게 쓰면, 기존에 있던 코드들 : 
```java
BoardVO vo=new BoardVO(); vo.setContent(arg0.getParameter("content"));
vo.setTitle(arg0.getParameter("title"));
vo.setWriter(arg0.getParameter("writer")); 
BoardDAO dao=new BoardDAO();
```
이런것들이 전혀 필요없어진다. 

변경후의 코드를 살펴보자. 

```java
package com.test.app.board;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.app.board.impl.BoardDAO;

@Controller 
public class InsertBoardController {

	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(BoardVO vo, BoardDAO dao) {
		System.out.println("Log: insertBoard Controller");		
		dao.insertBoard(vo);
        return "main.do";
	}
}
``` 

|keyword|변경 전|변경 후|
|-|-|-|
|Controller |우리가 원래 사용했던 Controller는 servlet의 영향을 받았기 때문에 무겁다. `import org.springframework.web.servlet.mvc.Controller;`|따라서, 이를 경량으로 만들기 위해 @Controller을 사용하고, `import org.springframework.stereotype.Controller;` 를 import 받는다.|
|@RequsetMapping |@Override된 handleRequest를 사용하였다.| Controller을 바꾸었기 때문에 @RequestMapping을 사용해 요청을 인식해준다. |
|new|  VO에 값을 세팅해주고 dao에서 vo를 인자로 받아 작업을 수행하였다.| 그러나 이를 위해 new 연산자를 사용하였기 때문에, 이는 좋지 못하다. 따라서 메서드의 파라미터값으로 vo와 dao를 넘겨준다. |
|ModelAndView|ModelAndView 객체를 사용하여 작업을 수행한 후에 어떤 페이지로 이동할 것인지 명시해주었다. |그러나, 만약 **데이터를 보낼 필요가 없는 요청**일 경우에는 ModelAndView가 필요없다. 따라서 String타입으로 return해주었다. |

## 코드 단순화 작업 

```java
package com.test.app.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.test.app.board.impl.BoardDAO;

@Controller
public class DeleteBoardController {

	@RequestMapping(value="/main.do")
	public String handleRequest(BoardVO vo, BoardDAO dao) throws Exception {	
		dao.deleteBoard(vo);
		return "main.do";
	}
}
```

사용자의 요청이 들어오면 @RequestMapping을 통해 해당하는 메서드를 찾고, 그 메서드를 실행한다. 그러나, 메서드가 그냥 실행될까? 당연히 그 메서드가 존재하는 클래스의 객체가 먼저 생성되어야 하고, 이 일을 @Controller 을 통해 수행하였다. 

그러면 결국 핵심은 @RequestMapping 이다! 즉, 하나의 Controller 클래스에  @RequestMapping 을 모두 집어넣으면, 하나의 컨트롤러 클래스만 만들어도 여러가지 메서드를 호출할 수 있다. 

따라서, 하나의 클래스에 모든 @RequestMapping 을 집어넣어서 파일을 단순화하자. 

```java
package com.test.app.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.app.board.impl.BoardDAO;

@Controller
public class BoardController {

	@RequestMapping("/board.do")
	public String board(BoardVO vo,BoardDAO dao,Model model) {
	
		model.addAttribute("data", dao.selectOne(vo));
		return "board.jsp";
	}
	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(BoardVO vo,BoardDAO dao) {
		
		dao.insertBoard(vo);
		return "main.do";
	}
	@RequestMapping("/main.do")
	public String main(BoardVO vo,BoardDAO dao,Model model) {

		model.addAttribute("datas",dao.selectAll(vo));
		return "main.jsp";
	}
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO vo,BoardDAO dao) {
	
		dao.updateBoard(vo);
		return "main.do";
	}
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo,BoardDAO dao) {
	
		dao.deleteBoard(vo);
		return "main.do";
	}
}
```

## 로그인 Get? Post?

Get 방식과 Post 방식 두가지로 로그인을 동시에 처리할 수 있는 방법이 있다. 

Get 방식이라 함은, 로그인 버튼을 눌러서 로그인 페이지로 들어가면서 사용자 정보를 **가져오는** 것이고, Post 방식은 사용자가 아이디 비밀번호를 입력하고 로그인 버튼을 눌러서 컨트롤러로 데이터를 **보내는** 것이다. 사실 Post방식만 있으면 되지만, Get방식을 추가하여 사용자 데이터를 미리 전달해줄 수 있다. 

```java
package com.test.app.member;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.app.member.impl.MemberDAO;

@Controller
public class LoginController {

	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String showLogin(@ModelAttribute("user")MemberVO vo) {
		// 핵심!!!!! @MA를 통해 View 로 user 이라는 이름의 데이터로 vo 데이터를 보낼 수 있음!!!! 
		System.out.println("Get방식으로 로그인");
		vo.setMid("mhj");
		vo.setPassword("0000");
		return "login.jsp";
	}
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public String login(MemberVO vo,MemberDAO dao,HttpSession session) {
		System.out.println("Post방식으로 로그인");
		MemberVO data=dao.selectOne(vo);
		if(data!=null) {
			session.setAttribute("userName", data.getName());
			return "main.do";
		}
		return "index.jsp";
	}
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {

		session.invalidate();
		return "index.jsp";
	}
}
```

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>

<form action="login.do" method="post">
	<table border="1">
<tr>
			<td>ID</td>
			<td><input type="text" name="mid" value="${user.mid }"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password"  value="${user.password }"></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="submit" value="Login"></td>
		</tr>
		
	</table>	
</form>
</body>
</html>
```