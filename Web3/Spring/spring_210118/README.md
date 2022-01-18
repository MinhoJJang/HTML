# ModelAttribute

MA의 사용법은 두가지가 있다. 하나씩 살펴보자. 

1. EL식에서의 변수명 전달 

```java
@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String showLogin(@ModelAttribute("user")MemberVO vo) {	
		vo.setMid("kim");
		vo.setPassword("1234");
		return "login.jsp";
	}
```
여기서의 MA는, MemberVO 객체를 View파트에서 user이라는 이름으로 부를 수 있도록 하기 위해 써주는 것이다. 

2. Controller에서의 Model사용 가능하게 함 

- 클라이언트가 main.do 요청 
- ModelAttribute 확인
- RequestMapping 확인
- 확인한 RequestMapping 에 model, vo 같은 것들이 있을 경우 확인한 MA에 따라서 

```java
package com.test.app.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.app.board.impl.BoardDAO;

@Controller
public class BoardController {

// @RequestMapping보다 먼저 호출되는 @MA
    @ModelAttribute("conditionMap")
    public Map<String,String> searchConditionMap(){
        Map<String,String> conditionMap = new HashMap<String,String>();
        conditionMap.put("제목", "title");
        conditionMap.put("작성자", "writer");
        return conditionMap;
    }

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
		// 이 model 에는 현재 Map과 ArrayList 두가지 컬렉션이 들어있다. 
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

이때, View부분도 바꿔주어야 한다. 
```

```

# @SessionAttributes

아무것도 입력하지 않은 상태에서 글 내용을 수정할 경우, null update 현상이 발생할 수 있다. 이를 막기 위해 만약 어떠한 값도 들어오지 않았을 경우에 기존의 값을 전달해야 된다 - 는 로직이 필요하다. 이때, 아무것도 입력하지 않았을 경우 기존에 있던 값을 저장해야 되는데 그 역할을 바로 @SessionAttributes 가 해준다. 

이를 BoardController에다가 적용시켜보자. 

```java
package com.test.app.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.test.app.board.impl.BoardDAO;

@Controller
@SessionAttributes("data") 
// /board.do RM을 통해 전달받은 data 을 저장하고 있다가, 필요하면 MA로 불러서 데이터를 보내준다. 
public class BoardController {

	// @RequestMapping보다 먼저 호출되는 @MA
	   @ModelAttribute("conditionMap")
	   public Map<String,String> searchConditionMap(){
	      Map<String,String> conditionMap = new HashMap<String,String>();
	      conditionMap.put("제목", "title");
	      conditionMap.put("작성자", "writer");
	      return conditionMap;
	   }
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
		// 이 model 에는 현재 Map과 ArrayList 두가지 컬렉션이 들어있다. 
		model.addAttribute("datas",dao.selectAll(vo));
		return "main.jsp";
	}
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("data")BoardVO vo,BoardDAO dao) {
		// MA를 통해서 SA가 갖고있는 값을 불러올 수 있고, null update 방지 처리를 해준다. 
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