<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,java.util.Date"%>
<%
	request.setCharacterEncoding("UTF-8");
	// request객체에 CharacterEncoding이라는 멤버변수가 있음
	// UTF-8이라는 값을 설정

	String msg=request.getParameter("msg");

	ArrayList<String> datas=(ArrayList)application.getAttribute("datas");
	if(datas==null){
		datas=new ArrayList<String>();
		application.setAttribute("datas", datas);
	}
	
	Date date=new Date();
	datas.add(session.getAttribute("uid")+"님으로부터  "+msg+"  ["+date+"]");
	
	response.sendRedirect("sns_list.jsp"); // == 리스트보기 버튼을 누른 행동
%>