package com.test.app.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LogoutController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		session.invalidate();
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:index.jsp");
		// 1. 데이터 유지 및 전달
		// 2. prefix,suffix
		return mav;
	}

}
