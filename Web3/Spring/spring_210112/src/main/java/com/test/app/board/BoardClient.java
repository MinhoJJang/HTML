package com.test.app.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardClient {

	public static void main(String[] args) {
		AbstractApplicationContext factory=new GenericXmlApplicationContext("applicationContext.xml");
		
		BoardService bs=(BoardService)factory.getBean("boardService");
		// 게시글 작성하기 버튼을 누름
		// 게시글 작성 페이지
		// 게시글을 작성함
		// 글 등록 버튼을 누름
		BoardVO vo=new BoardVO();
		vo.setContent("작성한 내용");
		vo.setTitle("글 제목");
		vo.setWriter("티모");
		bs.insertBoard(vo); // c가 insert 비즈니스메서드를 수행
		// -> 실질적으로는 dao가 해당 로직을 수행함
		
		// 전체목록보기 페이지
		List<BoardVO> datas=bs.selectAll(vo);
		for(BoardVO v:datas) {
			System.out.println(v);
		}
				
		factory.close();
	}

}
