package com.test.app.member;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.test.app.member.impl.MemberDAO;

public class MemberClient {
	public static void main(String[] args) {
		AbstractApplicationContext factory=new GenericXmlApplicationContext("applicationContext.xml");
		
		MemberService ms=(MemberService)factory.getBean("memberService");
			
		// 로그인 화면에서 ID,PW를 입력하고 로그인 버튼을 누름
		MemberVO vo=new MemberVO();
		vo.setMid("kim");
		vo.setPassword("1234");
		
		MemberVO data=ms.selectOne(vo);
		if(data==null) {
			System.out.println("로그인 실패!");
		}
		else {
			System.out.println(" ["+data.getRole()+"]"+data.getName()+"님,반갑습니다!:D");
		}
	
		factory.close();
	}
}
