package com.test.app.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.test.app.board.impl.BoardDAO;

public class BoardController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		BoardVO vo=new BoardVO();
		vo.setBid(Integer.parseInt(request.getParameter("bid")));
		BoardDAO dao=new BoardDAO();
		BoardVO data=dao.selectOne(vo);
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("data",data);
		mav.setViewName("board");
		return mav;
	}

}
