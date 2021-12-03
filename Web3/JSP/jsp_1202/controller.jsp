<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.board.*" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="dao" class="model.board.BoardDAO" />
<jsp:useBean id="vo" class="model.board.BoardVO" />
<jsp:setProperty property="*" name="vo"/>
<%
	// 사용자가 어떤 요청을 했는지에 따라, -> url 파라미터 action
	// 비즈니스메서드를 수행시켜야함(DAO를 호출해야함)
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
	else if(action.equals("insert")){
		if(dao.insert(vo)){
			// 메인화면을 보여주는 행동
			// == 메인화면으로 가는 버튼을 누른것
			response.sendRedirect("controller.jsp?action=main");
		}
		else{
			throw new Exception("insert에서 오류발생!!!");
		}
	}
	else if(action.equals("update")){
		
	}
	else if(action.equals("delete")){
		
	}
	else{
		out.println("<script>alert('action파라미터의 값을 확인해주세요!')</script>");
	}
%>