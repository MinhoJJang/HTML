## Controller 로직 순서 

1. Controller 인터페이스 생성
- Spring에서 기본제공하는 Controller으로 변경 
`import org.springframework.web.servlet.mvc.Controller;`

2. Controller 생성 
```java
package com.test.app.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.test.app.board.impl.BoardDAO;


public class MainController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
	
		BoardVO vo=new BoardVO(); 
		BoardDAO dao=new BoardDAO();
		List<BoardVO> datas=dao.selectAll(vo);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		mav.addObject("datas", datas);
		
		return mav; // VR가 viewName처리
	}

}
```

3. Annotation `@Controller` 추가하여 아래와 같이 변경하면, import되는 Controller이 변하는 것을 볼 수 있다. 

|Before|After|
|--|--|
|`import org.springframework.web.servlet.mvc.Controller;`|`import org.springframework.stereotype.Controller;`|

```java
package com.test.app.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.test.app.board.impl.BoardDAO;

@Controller
public class MainController {

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
	
		BoardVO vo=new BoardVO(); 
		BoardDAO dao=new BoardDAO();
		List<BoardVO> datas=dao.selectAll(vo);
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		mav.addObject("datas", datas);
		
		return mav; // VR가 viewName처리
	}
}
```
하지만 `import org.springframework.web.servlet.ModelAndView;` 로 인해서 여전히 servlet의 영향을 받고 있다. 

4. 내부 메서드에 `@RequestMapping(value="")` 추가하여 아래와 같이 변경한다. 이렇게 하면 servlet의 영향을 받지 않는 상태가 된다. 

|Before|After|
|--|--|
|`import org.springframework.web.servlet.ModelAndView;`|`import org.springframework.web.bind.annotation.RequestMapping;`|

```java
package com.test.app.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.app.board.impl.BoardDAO;

@Controller
public class MainController {
	
	@RequestMapping(value="/main.do")
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
	
		BoardVO vo=new BoardVO(); 
		BoardDAO dao=new BoardDAO();
		List<BoardVO> datas=dao.selectAll(vo);
		
		/*
        ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		mav.addObject("datas", datas);
		return mav; // VR가 viewName처리	
        */	
		return "";		
	}
}
```